package com.example.meeqapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.meeqapp.ui.components.Paragraph
import com.example.meeqapp.ui.distortions.emojiForSlug
import com.example.meeqapp.ui.thoughts.Thought



@Composable
fun EmojiList(thought: Thought) {
    val emojiList = thought.cognitiveDistortions
        .asSequence()
        .filter { it.selected }
        .map { emojiForSlug(it.slug) }
        .take(8)
        .filter { it.isNotBlank() }
        .joinToString(" ")

    Paragraph(
        Modifier.Companion
            .padding(bottom = 12.dp)
    ){emojiList}
}

/*
@Composable
fun EmptyThoughtIllustration() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.looker),
            contentDescription = null,
            modifier = Modifier
                .width(200.dp)
                .height(150.dp)
                .padding(bottom = 32.dp),
            contentScale = ContentScale.Crop
        )

        Label(
            text = "No thoughts yet!",
            modifier = Modifier.padding(bottom = 18.dp),
            textAlign = TextAlign.Center
        )
    }
}
*/
