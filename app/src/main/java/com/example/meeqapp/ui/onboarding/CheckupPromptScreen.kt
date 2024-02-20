package com.example.meeqapp.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.meeqapp.ui.theme.Theme

/*@Composable
fun CheckupPromptScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 24.dp + statusBarHeight())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            MediumHeader(text = "Quirk is built around weekly milestones.")
            HintHeader(
                text = "These help you see your progress over time and set goals for the next week.",
                modifier = Modifier
                    .padding(bottom = 24.dp)
            )

            SubHeader(text = "Your goals for this first week are to:")

            ListItem(index = "1", text = """Read through "How to use Quirk" in the Learn section of the app.""")
            ListItem(index = "2", text = """Record and challenge automatic negative thoughts when they happen.""")
            ListItem(index = "3", text = "Reach your next milestone.")

            ActionButton(
                onClick = {
                    navController.navigate(CHECKUP_SCREEN)
                },
                title = "Start First Milestone",
                style = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            )
        }
    }
}*/

/*fun statusBarHeight(): Dp {
    // Calculate status bar height if needed
    return 0.dp
}*/

@Composable
fun ListItem(index: Int, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 12.dp, bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .border(1.dp, color = Theme.lightGray)
                .padding(end = 12.dp)
        )

        Text(
            text = text,
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
    }
}
