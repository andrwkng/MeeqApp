package com.example.meeqapp.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
textInputStyle {
    height: Dimensions.get("screen").height * 0.15,
    backgroundColor: "white",
    padding: 12,
    paddingTop: 14,
    borderRadius: 8,
    fontSize: 16,
    borderColor: theme.lightGray,
    borderWidth: 1,
    // borderBottomWidth: 2,
    color: theme.darkText,
    textAlignVertical: "top",
}
*/

class TextInputStyle(
    color: Color = Theme.darkText,
    fontSize: TextUnit = 16.sp,
    background: Color = Color.White,
    textAlign: TextAlign? = TextAlign.Start,
    heightFraction: Float = 0.15F,
    padding: Dp = 12.dp,
    paddingTop: Dp = 14.dp,
    borderRadius: RoundedCornerShape = RoundedCornerShape(8.dp),
    borderColor: Color = Theme.colorLightGray,
    borderWidth: Dp = 1.dp,
) {
    var style = TextStyle(
        color = color,
        fontSize = fontSize,
        background = background,
        textAlign = textAlign,
    )

    var modifier = Modifier
        .fillMaxHeight(heightFraction)
        .padding(padding, top = paddingTop)
        .background(background)
        .border(borderWidth, borderColor, borderRadius)
}


