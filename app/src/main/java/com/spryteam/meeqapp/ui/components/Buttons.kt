package com.spryteam.meeqapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.OutlinedButton
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
import com.spryteam.meeqapp.ui.theme.Theme

@Composable
fun ActionButton(
    title: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    fillColor: Color = Theme.colorBlue,
    textColor: Color = Color.White,
    width: Dp = 120.dp,
    height: Dp = 48.dp,
    marginTop: Dp = 0.dp,
    marginBottom: Dp = 0.dp,
    disabled: Boolean = false,
    flex: Float = 1f,
) {
    Box(
        modifier = modifier
            .padding(top = marginTop, bottom = marginBottom)
            .border(1.dp, Theme.darkBlue, shape = RoundedCornerShape(10.dp))
            .width(width)
            .height(height)
            .background(fillColor, shape = RoundedCornerShape(10.dp))
            .padding(12.dp)
            .clickable(onClick = onClick, enabled = !disabled)
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
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit = {},
    iconSize: Dp = 24.dp,
    //hasBadge: Boolean = false
) {
    androidx.compose.material3.IconButton(
        onClick = onClick,
        modifier = modifier
            .height(48.dp)
            .width(48.dp)
            .size(iconSize)
            .background(
                Theme.lightGray,
                shape = RoundedCornerShape(10.dp)
            )
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
    modifier: Modifier = Modifier,
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
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(top = marginTop, bottom = marginBottom, end = marginRight)
            .border(1.dp, borderColor, shape = RoundedCornerShape(10.dp))
            .width(width)
            .height(height)
            .padding(12.dp)
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
    modifier: Modifier = Modifier,
    width: Dp = LocalDensity.current.run { 200.dp },
    height: Dp = 48.dp,
    onClick: () -> Unit,
    borderColor: Color = Theme.lightGray,
    disabled: Boolean = false,
    flex: Float? = null,
    content: @Composable RowScope.() -> Unit
) {
    OutlinedButton(
        onClick = { onClick() },
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        colors = ButtonColors(
            containerColor = Theme.lightOffWhite,
            contentColor = Theme.lightText,
            disabledContainerColor = Theme.lightOffWhite,
            disabledContentColor = Theme.veryLightText
        ),
        border = BorderStroke(
            width = 1.dp,
            color = borderColor,
        ),
        content = content
    )
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

@Composable
fun ExerciseButton(
    title: String,
    hint: String,
    icon: ImageVector = Icons.AutoMirrored.Filled.ArrowForward,
    hasYourAttention: Boolean = false,
    onClick: () -> Unit
) {
    ListItem(
        headlineContent = { Text(title) },
        modifier = Modifier
            .clickable { onClick() }
            .padding(bottom = 12.dp)
            .background(color = Theme.lightGray)
            .border(
                width = 1.dp,
                color = if (hasYourAttention) Theme.lightPink else Theme.lightGray,
                shape = RoundedCornerShape(5.dp)
            ),
        supportingContent = {
            Text(hint)
        },
        trailingContent = {
            Icon(
                icon,
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
        },
        colors = ListItemColors(
            containerColor = Theme.lightOffWhite,
            headlineColor = Theme.darkText,
            trailingIconColor = if (hasYourAttention) Theme.colorPink else Theme.colorBlue,
            overlineColor = Theme.lightGray,
            supportingTextColor = Theme.lightText,
            leadingIconColor = Theme.lightGray,
            disabledHeadlineColor = Theme.lightGray,
            disabledLeadingIconColor = Theme.lightGray,
            disabledTrailingIconColor = Theme.lightGray,
        )
    )
}

@Preview(showBackground = true)
@Composable
fun ExerciseButtonAttentionPreview() {
    ExerciseButton(
        title = "Exercise Button",
        hint = "Manage anxiety around upcoming events or tasks.",
        icon = Icons.Default.Star,
        onClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun ExerciseButtonPreview() {
    ExerciseButton(
        title = "Exercise Button",
        hint = "Manage anxiety around upcoming events or tasks.",
        icon = Icons.Default.Star,
        hasYourAttention = true,
        onClick = {}
    )
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
    IconButton(Icons.Default.Close, label = "IconButton")
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

@Preview(showBackground = true)
@Composable
fun GhostButtonWithGutsPreview() {
    GhostButtonWithGuts(
        onClick = {},
    ) { Text("GhostButtonWithGuts") }
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