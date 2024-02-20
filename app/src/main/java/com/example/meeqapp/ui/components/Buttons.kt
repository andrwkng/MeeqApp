package com.example.meeqapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meeqapp.ui.theme.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionButton(
    title: String,
    onClick: () -> Unit,
    fillColor: Color = Theme.colorBlue,
    textColor: Color = Color.White,
    width: Dp = 120.dp,
    height: Dp = 48.dp,
    marginTop: Dp = 0.dp,
    marginBottom: Dp = 0.dp,
    disabled: Boolean = true,
    flex: Float = 1f,
    style: Modifier = Modifier,
) {
    Box(
        //onClick = onClick,
        modifier = Modifier
            .padding(top = marginTop, bottom = marginBottom)
            .border(1.dp, Theme.darkBlue, shape = RoundedCornerShape(10.dp))
            .width(width)
            .height(height)
            .background(fillColor, shape = RoundedCornerShape(10.dp))
            .padding(12.dp)
            //.weight(flex)
            .then(style)
            .clickable(onClick = onClick, enabled = !disabled)
        //contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            color = textColor,
            fontWeight = FontWeight.W700,
            fontSize = 16.sp,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun IconButton(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit = {},
    style: Modifier = Modifier,
    iconSize: Dp = 24.dp,
    //hasBadge: Boolean = false
) {
    androidx.compose.material3.IconButton(
        onClick = { onClick },
        modifier = Modifier
            .size(iconSize)
            .background(MaterialTheme.colorScheme.background, MaterialTheme.shapes.small)
    ) {
        Icon(
            icon,
            contentDescription = label,
            tint = Color.Blue
        )
    }
}

@Composable
fun GhostButton(
    title: String,
    onClick: () -> Unit,
    borderColor: Color = Color.Gray, // Default color can be modified as needed
    textColor: Color = Color.Black, // Default color can be modified as needed
    width: Dp = LocalDensity.current.run { 200.dp }, // Default width can be modified as needed
    height: Dp = 48.dp, // Default height can be modified as needed
    marginTop: Dp = 0.dp,
    marginBottom: Dp = 0.dp,
    marginRight: Dp = 0.dp,
    disabled: Boolean = false,
    //flex: Float = 0f,
    fontSize: TextUnit = 16.sp, // Default font size can be modified as needed
    style: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .padding(top = marginTop, bottom = marginBottom, end = marginRight)
            .border(1.dp, borderColor, shape = RoundedCornerShape(10.dp))
            //.border(2.dp, borderColor, shape = RoundedCornerShape(10.dp))
            //.maxHeight(48.dp)
            .width(width)
            .height(height)
            .padding(12.dp)
            //.weight(flex)
            .then(style)
            .clickable(onClick = onClick, enabled = !disabled)
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            color = textColor,
            fontWeight = FontWeight.Bold,
            fontSize = fontSize,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun GhostButtonWithGuts(
    onClick: () -> Unit,
    borderColor: Color = Color.Gray,
    disabled: Boolean = false,
    flex: Float? = null,
    style: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Button(
        onClick = { onClick() },
        enabled = disabled,
        modifier = Modifier
            .then(style)
            .padding(12.dp)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(10.dp),
                //style = BorderStroke(2.dp, borderColor)
            )
            //.flex(flex ?: 1f)
    ) {
        content()
    }
}

@Composable
fun RoundedSelectorButton(
    title: String,
    selected: Boolean = false,
    onClick: () -> Unit
) {
    val theme = Theme

    Box(
        modifier = Modifier
            .clickable(onClick = onClick)
            .background(if (selected) theme.colorBlue else Color.White)
            .border(
                width = 1.dp,
                color = if (selected) Theme.darkBlue else Theme.lightGray,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(12.dp)
            //.borderBottom(2.dp, if (selected) Theme.darkBlue else Theme.lightGray)
            .padding(end = 12.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = if (selected) Color.White else Theme.darkText,
                modifier = Modifier.padding(start = 12.dp)
            )

            if (selected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun ActionButtonPreview() {
    ActionButton(
        title = "ActionButton",
        onClick = {},
    )
}

@Preview
@Composable
fun IconButtonPreview() {
    IconButton(Icons.Default.Close, "IconButton")
}

@Preview
@Composable
fun GhostButtonPreview() {
    GhostButton(
        title = "GhostButton",
        marginRight = 20.dp,
        onClick = {},
    )
}

@Preview
@Composable
fun GhostButtonWithGutsPreview() {
    GhostButtonWithGuts(
        onClick = {},
    ) {
        Text("GhostButtonWithGuts")
    }
}

@Preview
@Composable
fun RoundedSelectorButtonPreview() {
    RoundedSelectorButton(
        title = "RoundedSelectorButton",
        onClick = { },
        selected = true
    )
}