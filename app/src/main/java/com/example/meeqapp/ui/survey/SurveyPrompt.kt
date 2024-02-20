package com.example.meeqapp.ui.survey

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
fun SurveyPrompt(
    onPressYes: () -> Unit,
    onPressNo: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 48.dp)
    ) {
        SubHeader(text = "We'd love your feedback! 🙏")
        HintHeader(text = "Answer 4 questions to make Quirk better.")

        GhostButtonWithGuts(
            style = Modifier
                .padding(top = 12.dp)
                .background(Color.White)
                .fillMaxWidth(),
            onClick = onPressYes
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Give feedback",
                    modifier = Modifier
                        .padding(0.dp)
                        .padding(end = 8.dp),
                    color = Color(0xFF007AFF),
                    fontSize = 14.sp
                )
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = Color(0xFF007AFF),
                    modifier = Modifier
                        .size(16.dp)
                )
            }
        }

        GhostButtonWithGuts(
            style = Modifier
                .padding(top = 6.dp)
                .background(Color.White)
                .fillMaxWidth(),
            onClick = onPressNo
        ) {
            Text(
                text = "No Thanks",
                modifier = Modifier.padding(0.dp),
                color = Theme.lightText,
                fontSize = 14.sp
            )
        }
    }
}
