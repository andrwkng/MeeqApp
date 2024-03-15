package com.spryteam.meeqapp.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.spryteam.meeqapp.ui.distortions.CognitiveDistortion
import com.spryteam.meeqapp.ui.distortions.distortions
import com.spryteam.meeqapp.ui.distortions.emojiForSlug
import com.spryteam.meeqapp.ui.distortions.update


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
    EmojiList(list
        .update(2, selected = !list[2].selected)
        .update(3, selected = !list[3].selected)
        .update(4, selected = !list[4].selected))
}