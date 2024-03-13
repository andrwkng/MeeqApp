package com.sprytm.meeqapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprytm.meeqapp.ui.distortions.CognitiveDistortion
import com.sprytm.meeqapp.ui.distortions.distortions
import com.sprytm.meeqapp.ui.theme.Theme

@Composable
fun RoundedSelector(
    items: List<CognitiveDistortion>?,
    onPress: (slug: String) -> Unit,
    style: Modifier = Modifier,
) {
    Surface(
        modifier = style.padding(4.dp),
        color = Theme.lightOffwhite
    ) {
        Column {
            /*items?.forEach { item ->
                Text(stringResource(item.label))

            }*/
            items?.forEach { item ->
            SelectorTextItem(
                emoji = item.emoji ?: "🍎", // Default emoji to "🍎" if not provided
                text = stringResource(item.label),
                description = stringResource(item.description),
                selected = item.selected,
                onPress = { onPress(item.slug) }
            )
        }
        }
    }
}

@Composable
fun SelectorTextItem(
    onPress: () -> Unit,
    selected: Boolean = false,
    emoji: String,
    text: String,
    description: String
) {
    val borderColor = if (selected) Theme.darkBlue else Theme.lightGray
    val backgroundColor = if (selected) Theme.colorBlue else Color.White
    val textColor = if (selected) Color.White else Theme.darkText

    Column(
        modifier = Modifier
            .padding(bottom = 4.dp)
            //.border(1.dp, borderColor)
            .border(1.dp, borderColor, shape = RoundedCornerShape(8.dp))
            .padding(vertical = 1.dp)
            .background(backgroundColor)
            .clickable(onClick = onPress)
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 12.dp)
        ) {
            Text(
                text = emoji,
                modifier = Modifier.padding(horizontal = 12.dp),
                fontSize = 24.sp
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = text,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = textColor,
                    modifier = Modifier.weight(1f)
                )

                if (selected) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(if (selected) Theme.darkBlue else Theme.lightOffwhite)
                .padding(6.dp)
                .clip(RoundedCornerShape(8.dp))
        ) {
            Text(
                text = description,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = textColor,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
            )
        }
    }
}

@Preview
@Composable
fun RoundedSelectorPreview() {
    RoundedSelector(items = distortions, onPress = {})
}

@Preview
@Composable
fun SelectorTextItemPreview() {
    SelectorTextItem(
        emoji = "🌓",
        text = "All or nothing thinking",
        description = "That was a thorough waste of time",
        onPress = {},

    )
}