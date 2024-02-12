package com.example.meeqapp.ui.pulse

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.meeqapp.ui.MARKDOWN_ARTICLE_SCREEN
import com.example.meeqapp.ui.components.GhostButton
import com.example.meeqapp.ui.theme.Theme

data class Boost(
    val score: Int,
    val label: String
)

@Composable
fun Pulse(navController: NavHostController) {
    var data by remember { mutableStateOf(List(30) { 0 }) }
    var boosts by remember { mutableStateOf(emptyList<Boost>()) }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(true) {
        refreshScore()
        refreshBoosts()
    }

    Column(
        modifier = Modifier
            .padding(top = 12.dp, bottom = 24.dp)
            .fillMaxWidth()
            .background(Color.Gray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                CapsLabel(
                    text = "AWARENESS",
                    modifier = Modifier
                        .padding(bottom = 6.dp)
                        .padding(start = 12.dp)
                        //.color(MaterialTheme.colorScheme.onSurface)
                )

                /*Row {
                    AnimatedCounter(
                        value = data.last(),
                        time = 300,
                        keyFrames = 8,
                    )
                }*/

                Column(
                    modifier = Modifier
                        .height(48.dp)
                        .padding(start = 12.dp)
                ) {
                    boosts.forEach { b ->
                        BoostLabel(boost = b)
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(end = 12.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                GhostButton(
                    title = "what is this?",
                    onClick = {
                        navController.navigate(MARKDOWN_ARTICLE_SCREEN)
                    },
                    textColor = Color.Blue,
                    style = Modifier
                        .padding(0.dp)
                        .border(0.dp, Color.Transparent)
                        //.borderBottom(0.dp, Color.Transparent)
                )
            }
        }

        //Chart(data = data)
    }
}

fun refreshScore() {
    // Implementation of _refreshScore function
    // You might want to use ViewModels, repositories, etc.
}

fun refreshBoosts() {
    // Implementation of _refreshBoosts function
    // You might want to use ViewModels, repositories, etc.
}

fun addBoost(boost: Boost) {
    // Implementation of _addBoost function
    // You might want to use ViewModels, repositories, etc.
}

@Composable
fun CapsLabel(text: String, modifier: Modifier = Modifier) {
    ProvideTextStyle(
        value = LocalTextStyle.current.copy(
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = Theme.lightText,
            letterSpacing = 1.sp
        )
    ) {
        Text(
            text = text,
            modifier = modifier
        )
    }
}

/*@Composable
fun Chart(data: List<Int>) {
    // Really shouldn't happen
    if (data.size < 3) {
        return
    }

    AreaChart(
        modifier = Modifier.height(100.dp),
        data = data,
        contentInset = InnerPadding(top = 30.dp, bottom = 30.dp),
        curve = shape = MaterialTheme.shapes.large,
        backgroundColor = Color(119, 139, 235, 0.1f),
        animate = true,
        // if the person hasn't been using it for more than 3 days, give a set yMax so
        // the graph moves and they get some sort of progression
        yMax = if (data.filter { it != 0 }.size <= 3) 50 else Float.POSITIVE_INFINITY
    ) { chartData ->
        SegmentType.Line(
            modifier = Modifier,
            shape = MaterialTheme.shapes.small,
            path = chartData.createLinePath()
        )
    }
}*/

@Composable
fun BoostLabel(
    boost: Boost,
    isVisible: Boolean = false
) {

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Box(
                modifier = Modifier
                    .background(Theme.colorBlue)
                    .clip(RoundedCornerShape(8.dp))
                    .padding(2.dp),
            ) {
                Text(
                    text = "+${boost.score} ${boost.label}",
                    color = Theme.colorOffwhite,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }

}


