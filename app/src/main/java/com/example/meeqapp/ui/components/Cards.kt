package com.example.meeqapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.meeqapp.ui.theme.Theme

@Composable
fun FloatingCard(
    children: @Composable () -> Unit,
) {
    Surface(
        modifier = Modifier
            .background(Color.White)
            .border(1.dp, Theme.lightGray, RoundedCornerShape(8.dp))
            .padding(24.dp)
            .clip(RoundedCornerShape(8.dp))
            .shadow(2.dp)
    ) {
        children()
    }
}