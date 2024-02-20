package com.example.meeqapp.ui.articles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp


@Composable
fun ArticleTopBar(onExit: () -> Unit, pose: String, progress: Float, shouldShowExitButton: Boolean = true) {
    TopBarContainer {
        if (shouldShowExitButton) {
            IconButton(
                onClick = { onExit() },
                modifier = Modifier
                    .size(32.dp)
                    //.background(MaterialTheme.colorScheme.background, MaterialTheme.cornerShape.small)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = Color.Blue
                )
            }
        }
        /*FadesIn(
            pose,
            modifier = Modifier
                .weight(4f)
                .fillMaxHeight()
        ) {
            ProgressBar(progress = progress)
        }*/
    }
}

@Composable
fun TitleTopBar(onExit: () -> Unit, shouldShowExitButton: Boolean) {
    TopBarContainer {
        if (shouldShowExitButton) {
            IconButton(
                onClick = { onExit() },
                modifier = Modifier
                    .size(32.dp)
                    .background(MaterialTheme.colorScheme.background, MaterialTheme.shapes.small)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = Color.Blue
                )
            }
        }
    }
}

val TAB_BAR_HEIGHT = 76.dp

@Composable
fun TopBarContainer(
    modifier: Modifier = Modifier,
    content: @Composable (Modifier) -> Unit
) {
    Surface(
        modifier = modifier
            .height(TAB_BAR_HEIGHT)
            .background(MaterialTheme.colorScheme.background),
        //elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = LocalDensity.current.run { 24.dp.toPx().toDp() })
                .fillMaxWidth()
                .heightIn(min = TAB_BAR_HEIGHT),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            content(Modifier)
        }
    }
}