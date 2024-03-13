package com.spryteam.meeqapp.data

import com.spryteam.meeqapp.ui.exercises.ExerciseGroup
import com.spryteam.meeqapp.ui.thoughts.Exercise
import java.time.LocalDate
import javax.inject.Inject

class ExerciseStore @Inject constructor(
    private val thoughtStore: ThoughtStore,
    private val predictionStore: PredictionStore,
    private val checkupStore: CheckupStore
) {
    suspend fun getExerciseList(): List<Exercise> {
        val thoughts = thoughtStore.getOrderedThoughts()
        val checkups = checkupStore.getOrderedCheckups()
        val predictions = predictionStore.getOrderedPredictions()
        // Combine existing exercises
        return (thoughts + checkups + predictions)
    }

    fun getSortedExerciseList(exercises: List<Exercise>): List<Exercise> {
        // Sort them by days
        return exercises.sortedByDescending { it.createdAt }
    }

    fun getSortedExerciseGroups(sortedExercises: List<Exercise>): List<ExerciseGroup> {
        // Bucket the exercises into groups
        val groups = mutableListOf<ExerciseGroup>()
        var day: LocalDate? = null
        for (ex in sortedExercises) {
            if (day == null) {
                day = ex.createdAt
            }

            // First exercise
            if (groups.isEmpty()) {
                groups.add(ExerciseGroup(day.toString(), mutableListOf(ex)))
                continue
            }

            // Exercise on the same day
            if (day.hashCode() == ex.createdAt.hashCode()) {
                groups.last().exercises.add(ex)
            } else {
                // New day
                day = ex.createdAt
                groups.add(ExerciseGroup(day.toString(), mutableListOf(ex)))
            }
        }

        return groups.asReversed() // Reverse shows today first
    }

}