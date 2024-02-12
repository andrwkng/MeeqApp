package com.example.meeqapp.ui.exercises

import android.content.Context
import com.example.meeqapp.ui.checkup.getOrderedCheckups
import com.example.meeqapp.ui.predictions.getOrderedPredictions
import com.example.meeqapp.ui.thoughts.Exercise
import com.example.meeqapp.ui.thoughts.getOrderedThoughts
import java.time.LocalDate

suspend fun getSortedExerciseGroups(context: Context): List<ExerciseGroup> {
    val thoughts = getOrderedThoughts(context)
    val checkups = getOrderedCheckups()
    val predictions = getOrderedPredictions()

    // Combine existing exercises and sort them by days
    val exercises: List<Exercise> = (thoughts + checkups + predictions)
    val sortedExercises = exercises.sortedByDescending { it.createdAt }

    // Bucket the exercises into groups
    var groups = mutableListOf<ExerciseGroup>()
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

