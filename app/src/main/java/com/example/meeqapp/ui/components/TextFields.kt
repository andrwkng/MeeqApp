package com.example.meeqapp.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.meeqapp.ui.theme.TextInputStyle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(value: String) {
    var input by remember { mutableStateOf(value) }

    TextField(
        value = input,
        onValueChange = { input = it },
        label = { Text("Enter text") },
        maxLines = 2,
        textStyle = TextInputStyle().style,
        modifier = TextInputStyle().modifier
    )
}

@Preview
@Composable
fun PreviewCustomTextField() {
    CustomTextField("TextField")
}

