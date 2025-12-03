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

    //val current_user = userViewModel.users["JT4"]
    //val users_fish_list = userViewModel.fish["JT4"] ?: emptyList()
    var fish_message by remember { mutableStateOf("") }

    val input_fish_image = remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? -> input_fish_image.value = uri }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(transparentLightBlue),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Column(
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth()
        )
        {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth(),
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
        // Column for text fields
        Column {
            Row(modifier = Modifier.padding())
            {
                Column() {
                    TextField(
                        value = input_fish_name,
                        onValueChange = { input_fish_name = it },
                        label = { Text("Enter Fish Name: ") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp)
                            .padding(12.dp)
                    )
                    if (name_validation.isNotEmpty()) {
                        Text(
                            text = name_validation,
                            color = Color.Red,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(start = 12.dp)
                        )
                    }
                    TextField(
                        value = input_fish_length,
                        onValueChange = { input_fish_length = it },
                        label = { Text("Enter Fish Length (Inches): ") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp)
                            .padding(12.dp)
                    )
                    if (length_validation.isNotEmpty()) {
                        Text(
                            text = length_validation,
                            color = Color.Red,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(start = 12.dp)
                        )
                    }
                    TextField(
                        value = input_fish_weight,
                        onValueChange = { input_fish_weight = it },
                        label = { Text("Enter Fish Weight (Pounds): ") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp)
                            .padding(12.dp)
                    )
                    if (weight_validation.isNotEmpty()) {
                        Text(
                            text = weight_validation,
                            color = Color.Red,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(start = 12.dp)
                        )
                    }
                }
            }
                // Row used for buttons
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Button(modifier = Modifier
                        .width(140.dp)
                        .height(75.dp),
                        onClick = { launcher.launch("image/*")},
                        colors = ButtonDefaults.buttonColors(containerColor = LightBlue)) {
                        Text("Import Image", fontSize = 20.dp.value.sp,
                            textAlign = TextAlign.Center)

                    }

                    input_fish_image.value?.let { uri ->
                        Image(
                            painter = rememberAsyncImagePainter(uri),
                            contentDescription = "Selected Image",
                            modifier = Modifier
                                .padding(12.dp)
                                .height(200.dp)
                                .fillMaxWidth()
                        )
                    }

                    Button(
                        modifier = Modifier
                            .width(140.dp)
                            .height(75.dp).padding(top = 16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = LightBlue),
                        onClick = {
                            name_validation = ""
                            length_validation = ""
                            weight_validation = ""
                            var error_occured = false

                            // Error checking and input validation
                            if (input_fish_name.isBlank()) {
                                name_validation = "Fish name cannot be empty"
                                error_occured = true
                            }

                            val length_to_int = input_fish_length.toIntOrNull()
                            if (input_fish_length.isBlank()) {
                                length_validation = "Fish length is required"
                                error_occured = true
                            }
                            else if (length_to_int == null || length_to_int <= 0) {
                                length_validation = "Fish length must be a positive number"
                                error_occured = true
                            }

                            val weight_to_int = input_fish_weight.toIntOrNull()
                            if (input_fish_weight.isBlank()) {
                                weight_validation = "Fish weight is required"
                                error_occured = true
                            } else if (weight_to_int == null || weight_to_int <= 0) {
                                weight_validation = "Fish weight must be a positive number"
                                error_occured = true
                            }

                            // If an image isn't chosen, use the default image
                            if (!error_occured) {
                                val chosen_fish_image = input_fish_image.value?.toString()
                                    ?: "android.resource://edu.gvsu.cis.jafarovn.fishingandroidapp/${R.drawable.bass_image}"

                                userViewModel.AddFish(
                                    "JT4",
                                    input_fish_name,
                                    chosen_fish_image,
                                    input_fish_length.toInt(),
                                    input_fish_weight.toInt()
                                )

                                fish_message = "$input_fish_name added for JT4."
                            }
                        },
                    ) {
                        Text("Upload Your Fish", fontSize = 20.dp.value.sp,
                            textAlign = TextAlign.Center)
                    }

                    // Confirmation message
                    if (fish_message.isNotEmpty()) {
                        Text(
                            text = fish_message,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                    Spacer(Modifier.weight(1f))

                    // Bottom navigation buttons
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth()
                    ) {
                        Button( modifier = Modifier
                            .width(140.dp)
                            .height(75.dp).padding(8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = LightBlue), onClick = onNavigateToMain) {
                            Text("Back to Map", fontSize = 20.dp.value.sp,
                                textAlign = TextAlign.Center)
                        }
                        Button(modifier = Modifier
                            .width(140.dp)
                            .height(75.dp).padding(8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = LightBlue), onClick = onNavigateToProfile) {
                            Text("Back to Profile", fontSize = 20.dp.value.sp,
                                textAlign = TextAlign.Center)
                        }
                    }
                }
            }
        }
    }
    }
