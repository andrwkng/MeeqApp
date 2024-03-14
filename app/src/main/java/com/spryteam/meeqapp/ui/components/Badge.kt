package com.spryteam.meeqapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spryteam.meeqapp.ui.theme.Theme

@Composable
fun Badge(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Theme.lightBlue,
    icon: ImageVector,
) {
    Row(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .background(backgroundColor)
            .padding(12.dp)
            .fillMaxWidth(),
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            color = Theme.lightText,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Theme.lightText,
            modifier = Modifier.size(16.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BadgePreview() {
    Badge(text = "Badge", icon = Icons.Default.AccountCircle)
}