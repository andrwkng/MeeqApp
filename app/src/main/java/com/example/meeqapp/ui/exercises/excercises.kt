package com.example.meeqapp.ui.exercises

import com.example.meeqapp.ui.thoughts.Exercise
import java.time.LocalDate

suspend fun getSortedExerciseGroups(
    viewModel: ExerciseViewModel
): List<ExerciseGroup> {
    val thoughts = viewModel.getOrderedThoughts()
    val checkups = viewModel.getOrderedCheckups()
    val predictions = viewModel.getOrderedPredictions()

    // Combine existing exercises and sort them by days
    val exercises: List<Exercise> = (thoughts + checkups + predictions)
    val sortedExercises = exercises.sortedByDescending { it.createdAt }

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

