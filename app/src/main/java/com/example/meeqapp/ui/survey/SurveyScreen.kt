package com.example.meeqapp.ui.survey
/*

@Composable
fun SurveyScreen() {
    val index by remember { mutableStateOf(0) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, end = 24.dp, top = 24.dp)
                .background(MaterialTheme.colorScheme.primaryBackground)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 24.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                ProvideWindowInsets {
                    // Additional code for handling keyboard insets if needed
                }
            }
            SinglePageForm(
                steps = listOf(
                    DisappointedStep(),
                    KindOfPeople(),
                    MainBenefit(),
                    ImproveQuirkScreen()
                ),
                index = index
            )
        }

}

@Composable
fun DisappointedStep(onRecordDisappointment: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        MediumHeader(
            modifier = Modifier
                .padding(bottom = 24.dp)
        ) {
            Text("Question 1 of 4")
        }

        SubHeader {
            Text("How would you feel if you could no longer use Meeq?")
        }

        HintHeader {
            Text("This is just for feedback purposes; Quirk isn't going anywhere.")
        }

        RoundedSelectorButton(
            title = "Very Disappointed",
            onClick = { onRecordDisappointment("very") },
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
        RoundedSelectorButton(
            title = "Somewhat Disappointed",
            onClick = { onRecordDisappointment("somewhat") },
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
        RoundedSelectorButton(
            title = "Not Disappointed",
            onClick = { onRecordDisappointment("not") },
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
    }
}


@Composable
fun KindOfPeople(onChangeTypeOfPerson: (String) -> Unit, onNext: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        MediumHeader(
            modifier = Modifier
                .padding(bottom = 24.dp)
        ) {
            Text("Question 2 of 4")
        }

        SubHeader {
            Text("What type of people do you think would most benefit from Meeq?")
        }

        var typeOfPersonValue by remember { mutableStateOf("") }

        BasicTextField(
            value = typeOfPersonValue,
            onValueChange = { value ->
                typeOfPersonValue = value
                onChangeTypeOfPerson(value)
            },
            textStyle = TextStyle.Default.copy(
                fontSize = 16.sp,
                lineHeight = 24.sp
            ),
            maxLines = 6,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(vertical = 8.dp)
        )

        Button(
            onClick = onNext,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            Text("Next")
        }
    }
}


@Composable
fun MainBenefit(
    onChangeBenefit: (String) -> Unit,
    onNext: () -> Unit,
    benefitOfQuirkValue: String
) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MediumHeader(
            modifier = Modifier
                .padding(bottom = 24.dp)
        ) {
            Text("Question 3 of 4")
        }

        SubHeader {
            Text("What's the main benefit you receive from Quirk?")
        }

        BasicTextField(
            value = benefitOfQuirkValue,
            onValueChange = { onChangeBenefit(it) },
            maxLines = 6,
            textStyle = TextStyle.Default.copy(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Button(
            onClick = onNext,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            Text("Next")
        }
    }
}


@Composable
fun ImproveQuirkScreen(onNext: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        MediumHeader(
            modifier = Modifier
                .margin(bottom = 24.dp)
        ) {
            Text("Question 4 of 4")
        }

        SubHeader {
            Text("How can we improve Quirk for you?")
        }

        var improveQuirkValue by remember { mutableStateOf("") }

        TextField(
            value = improveQuirkValue,
            onValueChange = {
                improveQuirkValue = it
            },
            modifier = Modifier
                .heightIn(min = 120.dp)
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 12.dp),
            textStyle = TextStyle.Default.copy(fontSize = 16.sp)
        )

        Button(
            onClick = onNext,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            Text("Finish")
        }
    }
}

*/
