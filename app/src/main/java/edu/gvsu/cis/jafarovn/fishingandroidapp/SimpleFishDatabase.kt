package edu.gvsu.cis.jafarovn.fishingandroidapp

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Entity(tableName = "fish")
data class FishEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: String,
    val name: String,
    val length: Int,
    val weight: Int,
    val image: String?
)

@Dao
interface FishDao {
    @Insert
    suspend fun insert(fish: FishEntity)

    @Query("SELECT * FROM fish WHERE userId = :userId")
    suspend fun getForUser(userId: String): List<FishEntity>

    @Query("DELETE FROM fish WHERE userId = :userId")
    suspend fun deleteForUser(userId: String)
}

@Database(entities = [FishEntity::class], version = 1)
abstract class SimpleFishDatabase : RoomDatabase() {
    abstract fun fishDao(): FishDao

    companion object {
        @Volatile
        private var INSTANCE: SimpleFishDatabase? = null

        fun get(context: Context): SimpleFishDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    SimpleFishDatabase::class.java,
                    "simple_fish_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}

class FishRepository(context: Context) {
    private val dao = SimpleFishDatabase.get(context).fishDao()

    suspend fun saveFish(entity: FishEntity) = withContext(Dispatchers.IO) {
        dao.insert(entity)
    }

    suspend fun loadFish(userId: String): List<FishEntity> = withContext(Dispatchers.IO) {
        dao.getForUser(userId)
    }

    suspend fun clearFishForUser(userId: String) = withContext(Dispatchers.IO) {
        dao.deleteForUser(userId)
    }
}
