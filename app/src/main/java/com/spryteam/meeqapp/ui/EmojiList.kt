package com.spryteam.meeqapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.spryteam.meeqapp.ui.components.Paragraph
import com.spryteam.meeqapp.ui.distortions.CognitiveDistortion
import com.spryteam.meeqapp.ui.distortions.distortions
import com.spryteam.meeqapp.ui.distortions.emojiForSlug



@Composable
fun EmojiList(distortions:  List<CognitiveDistortion>) {
    val emojiList = distortions
        .asSequence()
        .filter { it.selected }
        .map { emojiForSlug(it.slug) }
        //.take(8)
        .filter { it.isNotBlank() }
        .joinToString(" ")

    Paragraph {emojiList}
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

@Preview
@Composable
fun PreviewEmojiList() {
    val list = distortions
    list[2].selected = true
    list[3].selected = true
    EmojiList(list)
}