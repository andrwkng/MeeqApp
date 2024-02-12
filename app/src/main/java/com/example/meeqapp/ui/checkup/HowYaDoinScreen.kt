package com.example.meeqapp.ui.checkup

/*

@Composable
fun HowYaDoinScreen(
    onFeelingSelected: (String) -> Unit,
    onChangeNote: (String) -> Unit,
    onNext: () -> Unit
) {
    var currentMood by remember { mutableStateOf("unselected") }
    var note by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn {
            item {
                MediumHeader(text = "How has it been going?")
                HintHeader(text = "Be honest, Quirk adapts based on how you're doing.")
            }

            item {
                RoundedSelectorButton(
                    title = "It's going well 👍",
                    onClick = { onFeelingSelected("good") },
                    selected = currentMood == "good"
                )
                RoundedSelectorButton(
                    title = "It's going okay 🤷‍",
                    onClick = { onFeelingSelected("neutral") },
                    selected = currentMood == "neutral"
                )
                RoundedSelectorButton(
                    title = "It's going poorly 👎",
                    onClick = { onFeelingSelected("bad") },
                    selected = currentMood == "bad"
                )
            }

            item {
                SubHeader(text = "Add a note (optional)")
                OutlinedTextField(
                    value = note,
                    onValueChange = {
                        note = it
                        onChangeNote(it)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    placeholder = { Text("The past few days have been...") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                )
            }

            item {
                ActionButton(
                    title = "Finish",
                    onClick = {
                        onNext()
                        keyboardController?.hide()
                    },
                    enabled = currentMood != "unselected"
                )
            }
        }
    }
}
*/
