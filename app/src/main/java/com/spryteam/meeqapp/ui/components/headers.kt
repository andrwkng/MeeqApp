package com.spryteam.meeqapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spryteam.meeqapp.ui.theme.Theme

@Composable
fun MediumHeader(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 20.sp,
    textAlign: TextAlign = TextAlign.Left,
    style: TextStyle = TextStyle()
) {
    Text(
        text,
        modifier = Modifier
            .padding(bottom = 12.dp)
            .then(modifier),
        color = Theme.darkText,
        fontSize,
        fontWeight = FontWeight.Bold,
        textAlign = textAlign,
        style = style
    )
}

@Composable
fun HintHeader(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 14.sp,
    textAlign: TextAlign = TextAlign.Left,
    style: TextStyle = TextStyle()
) {
    Text(
        text,
        modifier = Modifier
            .padding(bottom = 12.dp)
            .then(modifier),
        color = Theme.veryLightText,
        fontSize,
        fontWeight = FontWeight.SemiBold,
        textAlign = textAlign,
        style = style
    )
}


@Composable
fun SubHeader(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 16.sp,
    style: TextStyle = TextStyle()
) {
    Text(
        text,
        modifier = Modifier
            .padding(bottom = 12.dp)
            .then(modifier),
        color = Theme.lightText,
        fontSize,
        fontWeight = FontWeight.Bold,
        style = style
    )
}

@Preview
@Composable
fun MediumHeaderPreview() {
    MediumHeader(text = "MediumHeader")
    MediumHeader(
        text = "Do any of these sound familiar?",
        modifier = Modifier
            .padding(bottom = 24.dp)
    )
}

@Preview
@Composable
fun HintHeaderPreview() {
    HintHeader(text = "HintHeader")
}

@Preview
@Composable
fun SubHeaderPreview() {
    SubHeader(text = "SubHeader")
}