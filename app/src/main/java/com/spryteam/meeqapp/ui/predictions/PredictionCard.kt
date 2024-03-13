package com.spryteam.meeqapp.ui.predictions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.spryteam.meeqapp.ui.CardAttentionDot
import com.spryteam.meeqapp.ui.CardBadge
import com.spryteam.meeqapp.ui.CardCrown
import com.spryteam.meeqapp.ui.CardMutedContent
import com.spryteam.meeqapp.ui.CardTextContent
import com.spryteam.meeqapp.ui.theme.Theme
import com.spryteam.meeqapp.ui.thoughts.Exercise
import kotlinx.serialization.Contextual
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun PredictionCard(
    prediction: Prediction,
    key: String,
    onPress: (checkup: Prediction) -> Unit
) {
    val predictionState = getPredictionState(prediction)
    val predictionResult = getPredictionResult(prediction)
    val theme = Theme

    Column(
        modifier = Modifier.clickable { onPress(prediction) }
    ) {
        if (predictionState == PredictionState.READY) {
            CardAttentionDot()
        }

        CardCrown(text = "PREDICTION", Icons.Default.Star)

        prediction.eventLabel?.let { CardTextContent(it) }

        when (predictionState) {
            PredictionState.READY -> {
                CardBadge(
                    icon = Icons.Default.PlayArrow,
                    text = "Tap to start follow up",
                    backgroundColor = theme.lightPink
                )
            }

            PredictionState.WAITING -> {
                CardMutedContent {
                    Text(
                        text = "*Result revealed on ${prediction.followUpAt.toString()}*",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = theme.veryLightText
                    )
                }
            }

            PredictionState.COMPLETE -> {
                if (predictionResult == PredictionResult.CORRECT) {
                    CardMutedContent {
                        Text(
                            text = "Went as expected",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = theme.veryLightText
                        )
                    }
                }

                if (predictionResult == PredictionResult.BETTER) {
                    CardBadge(
                        text = "Better than expected",
                        icon = Icons.Default.ThumbUp,
                    )
                }

            }
        }
    }
}

enum class PredictionResult {
    BETTER, CORRECT, WORSE
}

enum class PredictionState {
    READY,
    WAITING,
    COMPLETE
}

val score = mapOf(
    Experience.GOOD to 1,
    Experience.NEUTRAL to 0,
    Experience.BAD to -1
)

fun getPredictionResult(prediction: Prediction): PredictionResult {
    return when {
        prediction.actualExperience == prediction.predictedExperience -> PredictionResult.CORRECT
        score[prediction.actualExperience]!! > score[prediction.predictedExperience]!! -> PredictionResult.BETTER
        else -> PredictionResult.WORSE
    }
}

fun getPredictionState(prediction: Prediction): PredictionState {
    val hasCompletedActual = prediction.actualExperience != null
    if (hasCompletedActual) {
        return PredictionState.COMPLETE
    }

    val isAfterFollowUp = LocalDateTime.now().isAfter(LocalDateTime.parse(prediction.followUpAt.toString()))
    if (!isAfterFollowUp) {
        return PredictionState.WAITING
    }

    return PredictionState.READY
}

//@Serializable
sealed interface Prediction: Exercise {
    override val uuid: String

   //@Contextual
    override val createdAt: LocalDate

    //@Contextual
   override var updatedAt: LocalDate
    var eventLabel: String?
    var predictedExperience: Experience?
    val actualExperience: Experience?
    var predictedExperienceNote: String?
    val actualExperienceNote: String?

    //@Contextual
    val followUpAt: LocalDate
}

@Serializable
data class PredictionImpl(
    override val uuid: String,
    @Contextual
    override val createdAt: LocalDate,
    @Contextual
    override var updatedAt: LocalDate,
    override var eventLabel: String? = null,
    override var predictedExperience: Experience? = null,
    override val actualExperience: Experience? = null,
    override var predictedExperienceNote: String? = null,
    override val actualExperienceNote: String? = null,
    @Contextual
    override val followUpAt: LocalDate = LocalDate.MIN
): Prediction

@Serializer(forClass = LocalDate::class)
object LocalDateSerializer : KSerializer<LocalDate> {
    private val formatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("LocalDate", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: LocalDate) {
        encoder.encodeString(value.format(formatter))
    }

    override fun deserialize(decoder: Decoder): LocalDate {
        return LocalDate.parse(decoder.decodeString(), formatter)
    }
}

val json = Json {
    serializersModule = SerializersModule {
        contextual(LocalDate::class, LocalDateSerializer)
    }
}

enum class Experience {
BAD, NEUTRAL, GOOD
}
