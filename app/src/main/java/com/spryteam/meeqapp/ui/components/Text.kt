package com.sprytm.meeqapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprytm.meeqapp.ui.theme.Theme

@Composable
fun Paragraph(
    modifier: Modifier = Modifier,
    text: () -> String,
) {
    Text(
        text = text(),
        fontSize = 16.sp,
        fontWeight = FontWeight.W400,
        color = Theme.lightText,
        modifier = modifier
    )
}

/*@Composable
fun MoodText(mood: Mood?) {
    when (mood) {
        Mood.GOOD -> {
            Paragraph(
                Modifier.Companion
                    .padding(bottom = 12.dp)
            ) { "Going well 👍" }
        }

        Mood.NEUTRAL -> {
            Paragraph(
                Modifier.Companion
                    .padding(bottom = 12.dp)
            ) { "Going okay 🤷‍" }
        }

        Mood.BAD -> {
            Paragraph(
                Modifier.Companion
                    .padding(bottom = 12.dp)
            ) { "Going poorly 👎" }
        }

        else -> {
            null
        }
    }
}*/

@Composable
fun LI(
    text: String,
) {
    Text(
        text = text,
        fontSize = 16.sp,
        color = Theme.lightText,
        modifier = Modifier.padding(bottom = 4.dp)
    )
}

@Preview
@Composable
fun ParagraphPreview() {
    Paragraph {
        "This is a paragraph."
    }
}