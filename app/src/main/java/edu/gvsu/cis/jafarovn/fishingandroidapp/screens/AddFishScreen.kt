package edu.gvsu.cis.jafarovn.fishingandroidapp.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import coil.compose.rememberAsyncImagePainter
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.gvsu.cis.jafarovn.fishingandroidapp.MapViewModel
import edu.gvsu.cis.jafarovn.fishingandroidapp.R
import edu.gvsu.cis.jafarovn.fishingandroidapp.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFishScreen(
    mapViewModel: MapViewModel,
    userViewModel: UserViewModel,
    onNavigateToMain: () -> Unit,
    onNavigateToProfile: () -> Unit,
    modifier: Modifier = Modifier,
    transparentLightBlue: Color = Color(red = 0f, green = 0.5f, blue = 1f, alpha = 0.25f),
    LightBlue: Color = Color(red = 0f, green = 0.5f, blue = 1f, alpha = 0.75f)
) {
    var input_fish_name by remember { mutableStateOf("") }
    var input_fish_length by remember { mutableStateOf("") }
    var input_fish_weight by remember { mutableStateOf("") }

    var name_validation by remember { mutableStateOf("") }
    var length_validation by remember { mutableStateOf("") }
    var weight_validation by remember { mutableStateOf("") }

    var fish_message by remember { mutableStateOf("") }

    val input_fish_image = remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        input_fish_image.value = uri
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(transparentLightBlue),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

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
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.fishook_logo),
                    contentDescription = "FisHook App Logo",
                )
            }
            Box(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Add Your Fish Below",
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp
                    )
                )
            }
        }

        // INPUT FIELDS

        Column {
            Row(modifier = Modifier.padding()) {
                Column {

                    TextField(
                        value = input_fish_name,
                        onValueChange = { input_fish_name = it },
                        label = { Text("Fish Name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    )
                    if (name_validation.isNotEmpty()) {
                        Text(
                            text = name_validation,
                            color = Color.Red,
                            fontSize = 12.sp
                        )
                    }

                    TextField(
                        value = input_fish_length,
                        onValueChange = { input_fish_length = it },
                        label = { Text("Length (inches)") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    )
                    if (length_validation.isNotEmpty()) {
                        Text(text = length_validation, color = Color.Red, fontSize = 12.sp)
                    }

                    TextField(
                        value = input_fish_weight,
                        onValueChange = { input_fish_weight = it },
                        label = { Text("Weight (lbs)") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    )
                    if (weight_validation.isNotEmpty()) {
                        Text(text = weight_validation, color = Color.Red, fontSize = 12.sp)
                    }
                }
            }

            // BUTTONS --------------------------------------------------------

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Button(
                        modifier = Modifier
                            .width(140.dp)
                            .height(75.dp),
                        onClick = { launcher.launch("image/*") },
                        colors = ButtonDefaults.buttonColors(containerColor = LightBlue)
                    ) {
                        Text("Import Image", fontSize = 20.sp)
                    }

                    input_fish_image.value?.let { uri ->
                        Image(
                            painter = rememberAsyncImagePainter(uri),
                            contentDescription = "Selected Image",
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxWidth()
                                .height(200.dp)
                        )
                    }

                    Button(
                        modifier = Modifier
                            .width(140.dp)
                            .height(75.dp)
                            .padding(top = 16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = LightBlue),
                        onClick = {

                            // VALIDATION
                            name_validation = ""
                            length_validation = ""
                            weight_validation = ""

                            var error = false

                            if (input_fish_name.isBlank()) {
                                name_validation = "Name cannot be empty"
                                error = true
                            }

                            val len = input_fish_length.toIntOrNull()
                            if (len == null || len <= 0) {
                                length_validation = "Length must be positive"
                                error = true
                            }

                            val wt = input_fish_weight.toIntOrNull()
                            if (wt == null || wt <= 0) {
                                weight_validation = "Weight must be positive"
                                error = true
                            }

                            if (!error) {
                                val chosenImage = input_fish_image.value?.toString()

                                // NEW API — Logic inside ViewModel
                                userViewModel.addFish(
                                    name = input_fish_name,
                                    image = chosenImage,
                                    length = len!!,
                                    weight = wt!!
                                )

                                fish_message = "Fish '${input_fish_name}' added!"
                            }
                        },
                    ) {
                        Text("Upload Fish", fontSize = 20.sp)
                    }

                    if (fish_message.isNotEmpty()) {
                        Text(fish_message, modifier = Modifier.padding(top = 8.dp))
                    }

                    Spacer(Modifier.weight(1f))

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth()
                    ) {
                        Button(
                            modifier = Modifier
                                .width(140.dp)
                                .height(75.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = LightBlue),
                            onClick = onNavigateToMain
                        ) {
                            Text("Back to Map", fontSize = 20.sp)
                        }

                        Button(
                            modifier = Modifier
                                .width(140.dp)
                                .height(75.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = LightBlue),
                            onClick = onNavigateToProfile
                        ) {
                            Text("Back to Profile", fontSize = 20.sp)
                        }
                    }
                }
            }
        }
    }
}
