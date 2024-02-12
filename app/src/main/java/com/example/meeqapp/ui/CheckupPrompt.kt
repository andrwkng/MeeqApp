package com.example.meeqapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meeqapp.ui.components.GhostButtonWithGuts
import com.example.meeqapp.ui.components.HintHeader
import com.example.meeqapp.ui.components.SubHeader
import com.example.meeqapp.ui.theme.Theme

@Composable
fun CheckupPrompt(onPress: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 48.dp)
    ) {
        SubHeader(text = "You've hit a milestone 👋")
        Spacer(modifier = Modifier.height(8.dp))
        HintHeader(text = "See your progress over time and prepare for the next week.")
        Spacer(modifier = Modifier.height(12.dp))
        GhostButtonWithGuts(
            style = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(vertical = 12.dp),
            onClick = onPress
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                //horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Start Milestone",
                    modifier = Modifier.weight(1f),
                    color = Theme.colorBlue,
                    fontSize = 14.sp
                )
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = Theme.colorBlue,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
