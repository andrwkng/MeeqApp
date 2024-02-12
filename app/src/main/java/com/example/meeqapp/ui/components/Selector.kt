package com.example.meeqapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.meeqapp.ui.distortions.CognitiveDistortion
import com.example.meeqapp.ui.theme.Theme

@Composable
fun RoundedSelector(
    items: List<CognitiveDistortion>?,
    onPress: (slug: String) -> Unit,
    style: Modifier = Modifier,
) {
    Surface(
        modifier = style.padding(4.dp), // Adjust padding as needed
        color = Theme.lightOffwhite // Assuming lightOffwhite is defined in your theme
    ) {
        items?.forEach { item ->
            val cogDistortion =
                item as CognitiveDistortion // Assuming CognitiveDistortion is a type defined elsewhere
            SelectorTextItem(
                emoji = cogDistortion.emoji ?: "🍎", // Default emoji to "🍎" if not provided
                text = cogDistortion.label.toString(),
                description = cogDistortion.description.toString(),
                selected = item.selected,
                onPress = { onPress(item.slug) }
            )
        }
    }
}

@Composable
fun SelectorTextItem(
    text: String,
    emoji: String,
    description: String,
    selected: Boolean = false,
    onPress: () -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(top = 1.dp, bottom = 4.dp)
            .clickable(onClick = onPress)
            .fillMaxWidth(),
        color = if (selected) Theme.colorBlue else Color.White,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, if (selected) Theme.colorDarkBlue else Theme.colorLightGray),
    ) {
        Column(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 4.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(bottom = 12.dp)
            ) {
                Text(
                    text = emoji,
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp)
                )

                Row(
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 12.dp)
                ) {
                    Text(
                        text = emoji,
                        modifier = Modifier.padding(end = 12.dp)
                    )

                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                    ) {
                        Text(
                            text = text,
                            color = if (selected) Color.White else Theme.darkText,
                            fontWeight = FontWeight.W600,
                            modifier = Modifier
                                .size(16.dp)
                                .padding(end = 8.dp)
                        )
                        if (selected) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier
                                    .size(16.dp)
                            )
                        }
                    }
                }

                Surface(
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                        .clickable(onClick = onPress)
                        .fillMaxWidth(),
                    color = if (selected) Theme.colorDarkBlue else Theme.lightOffwhite,
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(
                        1.dp,
                        if (selected) Theme.colorDarkBlue else Theme.colorLightGray
                    )
                ) {
                    Paragraph(
                        text = { description },
                        modifier = Modifier.size(16.dp),
                    )
                }

            }
        }
    }
}