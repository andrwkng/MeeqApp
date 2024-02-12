package com.example.meeqapp.ui.cbt

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meeqapp.R
import com.example.meeqapp.data.HistoryButtonLabelSetting
import com.example.meeqapp.ui.TouchableOpacity
import com.example.meeqapp.ui.components.Paragraph
import com.example.meeqapp.ui.distortions.emojiForSlug
import com.example.meeqapp.ui.theme.Theme
import com.example.meeqapp.ui.thoughts.SavedThought

@Composable
fun ThoughtItem(
    thought: SavedThought,
    historyButtonLabel: HistoryButtonLabelSetting,
    onPress: (SavedThought) -> Unit,
    onDelete: (SavedThought) -> Unit
) {
    Row(
        modifier = Modifier.padding(bottom = 18.dp)
    ) {
        TouchableOpacity(
            onClick = { onPress(thought) },
            style = Modifier
                .background(Color.White)
                .border(2.dp, Theme.colorLightGray, shape = RoundedCornerShape(8.dp))
                .padding(1.dp)
                .fillMaxWidth(0.9f)
        ) {
            Text(
                text = if (historyButtonLabel == HistoryButtonLabelSetting.ALTERNATIVE_THOUGHT) thought.alternativeThought else thought.automaticThought,
                modifier = Modifier
                    .padding(12.dp, bottom = 6.dp),
                color = Theme.darkText,
                fontSize = 16.sp,
                fontWeight = FontWeight.W400
            )
            Column(
                modifier = Modifier
                    .background(Theme.colorLightOffwhite)
                    .padding(12.dp, top = 6.dp)
                    .border(1.dp, Theme.colorLightOffwhite, shape = RoundedCornerShape(8.dp)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Paragraph(
                    Modifier
                        .padding(bottom = 12.dp)
                ){
                    thought.cognitiveDistortions
                        .filter { it.selected }
                        .mapNotNull { emojiForSlug(it.slug) }
                        .take(8)
                        .joinToString(" ")
                }
            }
        }

        IconButton(
            onClick = { onDelete(thought) },
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(id = R.string.delete_thought_button),
            )
        }
    }
}
/*

@Composable
fun EmptyThoughtIllustration() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.looker),
            contentDescription = null,
            modifier = Modifier
                .width(200.dp)
                .height(150.dp)
                .padding(bottom = 32.dp)
        )
        Text(
            text = stringResource(id = R.string.no_thoughts_yet),
            modifier = Modifier.padding(bottom = 18.dp),
            textAlign = TextAlign.Center
        )
    }
}
*/

/*

@Composable
fun ThoughtItemList(
    groups: List<ThoughtGroup>,
    navigateToViewer: (SavedThought) -> Unit,
    onItemDelete: (SavedThought) -> Unit,
    historyButtonLabel: HistoryButtonLabelSetting
) {
    if (groups.isEmpty()) {
        EmptyThoughtIllustration()
    } else {
        groups.forEach { group ->
            val isToday = group.date.toLocalDate() == LocalDate.now()
            Column(
                modifier = Modifier
                    .padding(bottom = 18.dp)
            ) {
                Text(
                    text = if (isToday) stringResource(id = R.string.today) else group.date,
                    modifier = Modifier.padding(bottom = 18.dp),
                    fontWeight = FontWeight.Bold
                )
                group.thoughts.forEach { thought ->
                    ThoughtItem(
                        thought = thought,
                        onPress = { navigateToViewer(it) },
                        onDelete = { onItemDelete(it) },
                        historyButtonLabel = historyButtonLabel
                    )
                }
            }
        }
    }
}
*/
/*

@Composable
fun CBTListScreen(navController: NavHostController) {
    val groups by remember { mutableStateOf(emptyList<ThoughtGroup>()) }
    val historyButtonLabel by remember { mutableStateOf(HistoryButtonLabelSetting.ALTERNATIVE_THOUGHT) }
    val isReady by remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        navController.addOnDestinationChangedListener { _, _, _ ->
            loadSettings()
        }
        onDispose {
            navController.removeOnDestinationChangedListener { _, _, _ ->
                loadSettings()
            }
        }
    }

    DisposableEffect(Unit) {
        loadExercises()
        loadSettings()
        onDispose { }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = ".quirk", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.navigate(SETTING_SCREEN) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = stringResource(id = R.string.settings),
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { navController.navigate(CBT_FORM_SCREEN) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(id = R.string.new_thought),
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(top = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FadesIn(
                    visible = isReady,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ThoughtItemList(
                        groups = groups,
                        navigateToViewer = { thought -> navController.navigate(CBT_VIEW_SCREEN) },
                        onItemDelete = { thought -> onItemDelete(thought) },
                        historyButtonLabel = historyButtonLabel
                    )
                }
            }
        }
    )
}
*/
