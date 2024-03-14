package com.spryteam.meeqapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spryteam.meeqapp.ui.theme.Theme

@Composable
fun Paragraph(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight? = FontWeight.W400,
    color: Color = Theme.lightText,
    textDirection: TextDirection = TextDirection.Unspecified,
    text: () -> String,
) {
    Text(
        text = text(),
        modifier = modifier,
        fontSize = fontSize,
        fontWeight = fontWeight,
        color = color,
        style = TextStyle(
            textDirection = textDirection,
        ),
    )
}

@Composable
fun SingleLineText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun SingleLineTextPreview() {
    SingleLineText(text = "This is a long text and may be cut into a single line")
}
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

@Preview(showBackground = true)
@Composable
fun ParagraphPreview() {
    Paragraph {
        "This is a paragraph."
    }
}