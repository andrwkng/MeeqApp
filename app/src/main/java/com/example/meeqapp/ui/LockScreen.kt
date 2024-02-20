package com.example.meeqapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meeqapp.R
import com.example.meeqapp.ui.components.GhostButton
import com.example.meeqapp.ui.theme.Theme
import kotlinx.coroutines.delay

class YourComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var isReady by remember { mutableStateOf(false) }
            var isSettingCode by remember { mutableStateOf(true) }
            var code by remember { mutableStateOf("") }

            DisposableEffect(Unit) {
                // Lifecycle events or equivalent for Jetpack Compose
                onDispose {
                    // Clean up if needed
                }
            }

            // Similar to componentDidMount
            LaunchedEffect(true) {
                // Add listeners or equivalent for Jetpack Compose
                // ...

                // Simulate a delay for a smooth fade-in
                delay(100)
                isReady = true
            }

            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Header
                    Text(
                        text = if (isSettingCode) "Please set a passcode" else "Please enter your passcode.",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Notifier Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        (1..4).forEach { index ->
                            Notifier(isActive = code.length >= index)
                        }
                    }

                    // Keypad Buttons
                    (0..2).forEach { row ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            (1..3).forEach { column ->
                                val digit = row * 3 + column
                                KeypadButton(title = digit.toString(), onPress = { onEnterCode(digit.toString()) })
                            }
                        }
                    }

                    // Last Row with special buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        KeypadSideButton(
                            icon = Icons.Default.Info,
                            accessibilityLabel = "help",
                            onPress = {
                                // Handle the special button action
                            },
                            //style = Modifier.opacity(0f)
                        )
                        KeypadButton(title = "0", onPress = { onEnterCode("0") })
                        KeypadSideButton(
                            icon = Icons.Default.Delete,
                            accessibilityLabel = "back",
                            onPress = { onBackspace() }
                        )
                    }
                }
            }
        }
    }

    private fun onEnterCode(key: String) {
        // Handle entering code
    }

    private fun onBackspace() {
        // Handle backspace
    }
}

val BUTTON_SIZE = 96.dp

@Composable
fun KeypadButton(title: String, onPress: () -> Unit, style: Modifier = Modifier) {
    GhostButton(
        title = title,
        borderColor = Theme.gray,
        textColor = Theme.darkText,
        width = BUTTON_SIZE,
        height = BUTTON_SIZE,
        fontSize = 18.sp,
        style = Modifier
            .background(Color.White)
            .then(style),
        onClick = onPress
    )
}

@Composable
fun KeypadSideButton(
    icon: ImageVector,
    accessibilityLabel: String,
    onPress: () -> Unit,
    style: Modifier = Modifier
) {

    IconButton(
        onClick = onPress,
        modifier = Modifier
            .background(Color.White)
            .width(BUTTON_SIZE)
            .then(style)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.your_icon_resource), // Replace with your actual icon resource
            contentDescription = accessibilityLabel
        )
    }
}

@Composable
fun Notifier(isActive: Boolean) {
    val backgroundColor = if (isActive) Color.Magenta else Color.Gray
    val borderColor = if (isActive) Color.DarkGray else Color.Black

    Box(
        modifier = Modifier
            .size(32.dp)
            .background(color = backgroundColor)
            .border(width = 2.dp, color = borderColor, shape = CircleShape)
    )
}
