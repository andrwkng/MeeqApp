package com.spryteam.meeqapp.ui.thoughts

import com.spryteam.meeqapp.ui.distortions.CognitiveDistortion
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate

enum class ImmediateCheckup {
    BETTER,
    WORSE,
    SAME
}

interface Exercise{
    val createdAt: LocalDate
    var updatedAt: LocalDate
    val uuid: String
}

interface Thought {
    val automaticThought: String
    val alternativeThought: String
    val cognitiveDistortions: List<CognitiveDistortion>
    val challenge: String
    val immediateCheckup: ImmediateCheckup?
    val followUpDate: String?
    val followUpCompleted: Boolean?
    val followUpCheckup: String?
    val followUpNote: String
}

sealed interface SavedThought: Thought, Exercise

data class ThoughtImpl(
    override var automaticThought: String,
    override val alternativeThought: String,
    override var cognitiveDistortions: List<CognitiveDistortion> = emptyList(),
    override val challenge: String,
    override val immediateCheckup: ImmediateCheckup? = null,
    override val followUpDate: String? = null,
    override var followUpCompleted: Boolean? = null,
    override val followUpCheckup: String? = null,
    override val followUpNote: String,
) : Thought

@Serializable
data class SavedThoughtImpl(
    override var automaticThought: String,
    override val alternativeThought: String,
    override var cognitiveDistortions: List<CognitiveDistortion> = emptyList(),
    override val challenge: String,
    override val immediateCheckup: ImmediateCheckup? = null,
    override val followUpDate: String? = null,
    override var followUpCompleted: Boolean? = null,
    override val followUpCheckup: String? = null,
    override val followUpNote: String,
    @Contextual
    override val createdAt: LocalDate,
    @Contextual
    override var updatedAt: LocalDate,
    override val uuid: String
) : Exercise, SavedThought

data class ThoughtGroup(val date: String, val thoughts: List<SavedThought>)