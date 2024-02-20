package com.example.meeqapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.meeqapp.R

@Composable
fun PurpleBubble() {
    ThoughtDook(
        style = Modifier
            .padding(start = 4.dp, end = 8.dp)
            .size(24.dp),
        painter = painterResource(id = R.drawable.dook_pink),
    )
}

@Composable
fun YellowBubble() {
    ThoughtDook(
        style = Modifier
            .padding(start = 4.dp, end = 8.dp)
            .size(24.dp),
        painter = painterResource(id = R.drawable.dook_pink),
    )
}

@Composable
fun PinkBubble() {
    ThoughtDook(
        style = Modifier
            .padding(start = 4.dp, end = 8.dp)
            .size(24.dp),
        painter = painterResource(id = R.drawable.dook_pink)
    )
}

@Composable
fun BubbleThought(
    children: @Composable (Modifier) -> Unit,
    color: BubbleColor = BubbleColor.Yellow,
    style: Modifier = Modifier
) {
    val bubble = when (color) {
        BubbleColor.Purple -> PurpleBubble()
        BubbleColor.Yellow -> YellowBubble()
        BubbleColor.Pink -> PinkBubble()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, end = 48.dp)
            .then(style)
    ) {
        bubble
        Box(
            modifier = Modifier
                .background(Color.White)
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
        ) {
            children(Modifier)
        }
    }
}

enum class BubbleColor {
    Yellow,
    Purple,
    Pink
}

@Composable
fun ThoughtDook(style: Modifier, painter: Painter) {
    Image(
        painter = painter,
        contentDescription = null, // Provide a meaningful content description if needed
        modifier = Modifier
            .size(48.dp)
            .then(style)
    )
}
