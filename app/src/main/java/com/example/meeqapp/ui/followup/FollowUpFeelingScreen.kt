package com.example.meeqapp.ui.followup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.meeqapp.ui.components.GhostButton
import com.example.meeqapp.ui.components.HintHeader
import com.example.meeqapp.ui.theme.Theme

@Composable
fun FollowUpFeelingScreen() {
    val theme = Theme

    Column(
        modifier = Modifier
            .fillMaxSize()
            //.padding(top = statusBarHeight + 24.dp, backgroundColor = theme.lightOffwhite)
    ) {
        /*StatusBar(
            barStyle = StatusBarStyle.DarkContent,
            hidden = false
        )*/

        /*SubHeader(
            modifier = Modifier
                .margin(bottom = 12.dp),
            text = "Next, let's check-in."
        )*/

        HintHeader(text = "How are you doing now?")

        GhostButton(
            title = "Better than before 👍",
            //width = Modifier.fillMaxWidth(),
            borderColor = theme.lightGray,
            textColor = theme.darkText,
            style = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                .background(color = Color.White),
            onClick = { /* Handle button click */ }
        )

        GhostButton(
            title = "About the same 🤷‍",
            //width = Modifier.fillMaxWidth(),
            borderColor = theme.lightGray,
            textColor = theme.darkText,
            style = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                .background(color = Color.White),
            onClick = { /* Handle button click */ }
        )

        GhostButton(
            title = "Worse than before 👎",
            //width = Modifier.fillMaxWidth(),
            borderColor = theme.lightGray,
            textColor = theme.darkText,
            style = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                .background(color = Color.White),
            onClick = { /* Handle button click */ }
        )
    }

}