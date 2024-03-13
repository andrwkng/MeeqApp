package com.sprytm.meeqapp.ui.thoughts

import com.sprytm.meeqapp.ui.distortions.CognitiveDistortion
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate

/* open class ThoughtI(
     open val automaticThought: String,
     open val alternativeThought: String,
     open val cognitiveDistortions: List<CognitiveDistortion>,
     open val challenge: String,
     open val immediateCheckup: ImmediateCheckup,
     val followUpDate: String? = null,
     val followUpCompleted: Boolean? = null,
     val followUpCheckup: String? = null,
     val followUpNote: String? = null
)*/

/*
data class Thought(
     val automaticThought: String,
     val alternativeThought: String,
     val cognitiveDistortions: List<CognitiveDistortion>,
     val challenge: String,
     val immediateCheckup: ImmediateCheckup,
     val followUpDate: String? = null,
     val followUpCompleted: Boolean? = null,
     val followUpCheckup: String? = null,
     val followUpNote: String? = null
)
*/

enum class ImmediateCheckup {
    BETTER,
    WORSE,
    SAME
}

/*
data class SavedThought(
    val thought: Thought,
    val createdAt: Date,
    val updatedAt: Date,
    val uuid: String,
    override val automaticThought: String,
    override val alternativeThought: String,
    override val cognitiveDistortions: List<CognitiveDistortion>,
    override val challenge: String,
    override val immediateCheckup: ImmediateCheckup
): ThoughtI(automaticThought, alternativeThought, cognitiveDistortions, challenge, immediateCheckup)
*/

interface Exercise{
    val createdAt: LocalDate
    var updatedAt: LocalDate
    val uuid: String
}

/*open class Thought(
    open var automaticThought: String,
    open val alternativeThought: String,
    open val cognitiveDistortions: List<CognitiveDistortion>,
    open val challenge: String,
    open val immediateCheckup: ImmediateCheckup? = null,
    open val followUpDate: String? = null,
    open val followUpCompleted: Boolean? = null,
    open val followUpCheckup: String? = null,
    open val followUpNote: String? = null
)*/

interface Thought {
    val automaticThought: String
    val alternativeThought: String
    val cognitiveDistortions: List<CognitiveDistortion>
    val challenge: String
    val immediateCheckup: ImmediateCheckup?
    val followUpDate: String?
    val followUpCompleted: Boolean?
    val followUpCheckup: String?
    val followUpNote: String?
}

//@Serializable
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
    override val followUpNote: String? = null,
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
    override val followUpNote: String? = null,
    @Contextual
    override val createdAt: LocalDate,
    @Contextual
    override var updatedAt: LocalDate,
    override val uuid: String
) : Exercise, SavedThought

/*
data class SavedThought(
    override var automaticThought: String,
    override val alternativeThought: String,
    override val cognitiveDistortions: List<CognitiveDistortion>,
    override val challenge: String,
    override val immediateCheckup: ImmediateCheckup? = null,
    override val followUpDate: String? = null,
    override var followUpCompleted: Boolean? = null,
    override val followUpCheckup: String? = null,
    override val followUpNote: String? = null,
    override val createdAt: Date,
    override var updatedAt: Date,
    override val uuid: String
) : Exercise, Thought(automaticThought, alternativeThought, cognitiveDistortions, challenge, immediateCheckup, followUpDate, followUpCompleted, followUpCheckup, followUpNote)
*/

data class ThoughtGroup(val date: String, val thoughts: List<SavedThought>)
//data class CognitiveDistortion(val distortionType: String, val description: String)
