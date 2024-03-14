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

@Preview
@Composable
fun PreviewEmojiList() {
    val list = distortions
    list[2].selected = true
    list[3].selected = true
    EmojiList(list)
}