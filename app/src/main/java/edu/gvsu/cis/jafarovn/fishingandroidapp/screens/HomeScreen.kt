package edu.gvsu.cis.jafarovn.fishingandroidapp.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import edu.gvsu.cis.jafarovn.fishingandroidapp.MapViewModel
import edu.gvsu.cis.jafarovn.fishingandroidapp.R
import edu.gvsu.cis.jafarovn.fishingandroidapp.UserViewModel

// Fishing spot with description
data class FishingSpot(
    val name: String,
    val lat: Double,
    val lng: Double,
    val description: String
)

// Fake Michigan + USA spots
private val fishingSpots = listOf(
    // Michigan
    FishingSpot(
        "Grand Haven Pier (MI)",
        43.0628, -86.2520,
        "Popular Lake Michigan pier fishing for salmon, trout, and steelhead."
    ),
    FishingSpot(
        "Muskegon Lake (MI)",
        43.2280, -86.2989,
        "Great for walleye, perch, and bass close to downtown Muskegon."
    ),
    FishingSpot(
        "Holland State Park (MI)",
        42.7762, -86.2050,
        "Pier and shore fishing with access to Lake Michigan and Lake Macatawa."
    ),
    FishingSpot(
        "Saginaw Bay (MI)",
        43.7600, -83.9330,
        "Known for walleye fishing on Lake Huron’s inner bay."
    ),
    FishingSpot(
        "Lake St. Clair (MI)",
        42.4360, -82.7960,
        "Famous muskie and smallmouth bass fishery near Detroit."
    ),
    FishingSpot(
        "Detroit River (MI)",
        42.2850, -83.1200,
        "Spring walleye run with strong current and boat access."
    ),
    // Other US spots (fake list for demo)
    FishingSpot(
        "Lake Erie - Western Basin (OH)",
        41.7000, -83.0000,
        "Walleye and perch hotspot, lots of charter boats."
    ),
    FishingSpot(
        "Lake Guntersville (AL)",
        34.3990, -86.3000,
        "Well-known tournament bass lake on the Tennessee River."
    ),
    FishingSpot(
        "Chesapeake Bay (MD)",
        38.9700, -76.4900,
        "Striped bass (rockfish) and blue crab in a large estuary."
    ),
    FishingSpot(
        "Florida Keys - Marathon (FL)",
        24.7136, -81.0895,
        "Offshore and bridge fishing for snapper, tarpon, and more."
    ),
    FishingSpot(
        "Lake Okeechobee (FL)",
        26.9600, -80.8000,
        "Shallow grass-filled lake famous for largemouth bass."
    ),
    FishingSpot(
        "Columbia River (WA/OR)",
        45.6500, -121.9500,
        "Salmon, steelhead, and sturgeon in a big river system."
    ),
    FishingSpot(
        "Lake Powell (UT/AZ)",
        37.0400, -111.2300,
        "Striped bass and canyon scenery on the Colorado River."
    ),
    FishingSpot(
        "Snake River (ID)",
        43.6000, -112.0000,
        "Trout and smallmouth bass in mixed current and reservoirs."
    )
)

@Composable
fun HomeScreen(
    mapViewModel: MapViewModel,
    userViewModel: UserViewModel,
    onNavigateToCaughtFish: () -> Unit,
    onNavigateToLeaderBoard: () -> Unit,
    onNavigateToProfile: () -> Unit,
    modifier: Modifier = Modifier,
    transparentLightBlue: Color = Color(red = 0f, green = 0.5f, blue = 1f, alpha = 0.25f),
    lightBlue: Color = Color(red = 0f, green = 0.5f, blue = 1f, alpha = 0.75f)
) {
    val context = LocalContext.current
    val selectedSpotState = remember { mutableStateOf<FishingSpot?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(transparentLightBlue),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header: logo + title
        Column(
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.fishook_logo),
                    contentDescription = "FisHook App Logo"
                )
            }
            Box(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Welcome to FisHook",
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp
                    )
                )
            }
        }

        // Subtitle
        Text(
            text = "Locate Fishing Spots with the Map!",
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontFamily = FontFamily.Default,
                fontSize = 20.sp
            ),
            modifier = Modifier
                .padding(bottom = 8.dp)
                .padding(top = 8.dp)
        )

        // --- Google Map with markers ---
        // Center more or less on USA but still shows Michigan well
        val usaCenter = LatLng(41.5, -95.0)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(usaCenter, 4.5f)
        }

        Box(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(320.dp)
                .border(BorderStroke(8.dp, lightBlue)),
            contentAlignment = Alignment.Center
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                fishingSpots.forEach { spot ->
                    val position = LatLng(spot.lat, spot.lng)
                    Marker(
                        state = MarkerState(position = position),
                        title = spot.name,
                        snippet = spot.description,
                        onClick = {
                            selectedSpotState.value = spot
                            // return false to also show default info window
                            false
                        }
                    )
                }
            }
        }

        // Info card for selected spot
        selectedSpotState.value?.let { spot ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(12.dp)
                ) {
                    Text(
                        text = spot.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = spot.description,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Open in Google Maps",
                        color = lightBlue,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .clickable {
                                val uri = Uri.parse(
                                    "geo:${spot.lat},${spot.lng}?q=${spot.lat},${spot.lng}(${spot.name})"
                                )
                                val intent = Intent(Intent.ACTION_VIEW, uri).apply {
                                    setPackage("com.google.android.apps.maps")
                                }
                                context.startActivity(intent)
                            }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Buttons row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(175.dp)
        ) {
            Button(
                modifier = Modifier
                    .width(140.dp)
                    .height(75.dp),
                onClick = onNavigateToCaughtFish,
                colors = ButtonDefaults.buttonColors(containerColor = lightBlue)
            ) {
                Text("Add Fish", fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.width(24.dp))

            Image(
                painter = painterResource(id = R.drawable.app_pfp),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(80.dp)
                    .clickable { onNavigateToProfile() }
            )

            Spacer(modifier = Modifier.width(24.dp))

            Button(
                modifier = Modifier
                    .width(140.dp)
                    .height(75.dp),
                onClick = onNavigateToLeaderBoard,
                colors = ButtonDefaults.buttonColors(containerColor = lightBlue)
            ) {
                Text("Leader Board", fontSize = 20.sp)
            }
        }

        // Footer names
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.height(100.dp)
        ) {
            Text(
                "Tara Barnett, Zeke Turnbough, and Nazim Jafarov",
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}
