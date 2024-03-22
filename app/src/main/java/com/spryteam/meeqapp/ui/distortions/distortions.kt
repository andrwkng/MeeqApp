package com.spryteam.meeqapp.ui.distortions

import com.spryteam.meeqapp.R
import kotlinx.serialization.Serializable

@Serializable
data class CognitiveDistortion(
    val emoji: String? = null,
    val label: Int,
    val slug: String,
    val selected: Boolean = false,
    val description: Int
)

val emojiForSlug: (String) -> String = { slug: String ->
    val distortion = distortions.find { it.slug == slug }
    distortion?.emoji ?: "🤷‍"
}

val distortions = listOf(
    CognitiveDistortion(
        emoji = "🌓",
        label = R.string.all_or_nothing_thinking,
        slug = "all-or-nothing",
        description = R.string.all_or_nothing_thinking_one_liner
    ),
    CognitiveDistortion(
        emoji = "👯‍",
        label = R.string.over_generalization,
        slug = "overgeneralization",
        description = R.string.overgeneralization_one_liner
    ),
    CognitiveDistortion(
        emoji = "🧠",
        label = R.string.mind_reading,
        slug = "mind-reading",
        description = R.string.mind_reading_one_liner
    ),
    CognitiveDistortion(
        emoji = "🔮",
        label = R.string.fortune_telling,
        slug = "fortune-telling",
        description = R.string.fortune_telling_one_liner
    ),
    CognitiveDistortion(
        emoji = "👎",
        label = R.string.magnification_of_the_negative,
        slug = "magnification-of-the-negative",
        description = R.string.magnification_of_the_negative_one_liner
    ),
    CognitiveDistortion(
        emoji = "👍",
        label = R.string.minimization_of_the_positive,
        slug = "minimization-of-the-positive",
        description = R.string.minimization_of_the_positive_one_liner
    ),
    CognitiveDistortion(
        emoji = "🤯",
        label = R.string.catastrophizing,
        slug = "catastrophizing",
        description = R.string.catastrophizing_one_liner
    ),
    CognitiveDistortion(
        emoji = "🎭",
        label = R.string.emotional_reasoning,
        slug = "emotional-reasoning",
        description = R.string.emotional_reasoning_one_liner
    ),
    CognitiveDistortion(
        emoji = "✨",
        label = R.string.should_statements,
        slug = "should-statements",
        description = R.string.should_statements_one_liner
    ),
    CognitiveDistortion(
        emoji = "🏷",
        label = R.string.labeling,
        slug = "labeling",
        description = R.string.labeling_one_liner
    ),
    CognitiveDistortion(
        emoji = "👁",
        label = R.string.self_blaming,
        slug = "self-blaming",
        description = R.string.self_blaming_one_liner
    ),
    CognitiveDistortion(
        emoji = "🧛‍",
        label = R.string.other_blaming,
        slug = "other-blaming",
        description = R.string.other_blaming_one_liner
    )
)

fun newDistortions(): List<CognitiveDistortion> {
    return distortions.toList()
}

fun List<CognitiveDistortion>.update(
    index: Int,
    emoji: String? = null,
    label: Int? = null,
    slug: String? = null,
    description: Int? = null,
    selected: Boolean? = null
): List<CognitiveDistortion> {
    if (index < 0 || index >= size) {
        throw IndexOutOfBoundsException("Index $index is out of bounds.")
    }

    return mapIndexed { i, distortion ->
        if (i == index) {
            distortion.copy(
                emoji = emoji ?: distortion.emoji,
                label = label ?: distortion.label,
                slug = slug ?: distortion.slug,
                description = description ?: distortion.description,
                selected = selected ?: distortion.selected,
            )
        } else {
            distortion
        }
    }
}




/*
fun <T> List<CognitiveDistortion>.update(index: Int, distortion: CognitiveDistortion) : List<T> {
    if (index != -1) {

        val selectedItem = list[index]
        list.removeAt(index)
        list.add(
            index, CognitiveDistortion(
                emoji = selectedItem.emoji,
                label = selectedItem.label,
                slug = selectedItem.slug,
                description = selectedItem.description,
                selected = !selectedItem.selected
            )
        )
        _distortions.value = list
    }
}*/
