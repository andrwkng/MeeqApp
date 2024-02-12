package com.example.meeqapp.ui.pulse

/*@Composable
fun AnimatedCounter(value: Int, keyFrames: Int, time: Int) {
    var animatedValue by remember { mutableStateOf(value) }

    DisposableEffect(value) {
        if (animatedValue != value) {
            animateValue(animatedValue, value, keyFrames, time)
        }

        onDispose { *//* cleanup logic here *//* }
    }

    Label(
        text = animatedValue.toString(),
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colorScheme.onBackground
    )
}*/
/*

private fun CoroutineScope.animateValue(
    oldValue: Int,
    newValue: Int,
    keyFrames: Int,
    time: Int
) {
    val interpolator: (Float) -> Float = { t -> (oldValue + t * (newValue - oldValue)) }

    for (i in 1..keyFrames) {
        scheduleKeyFrame(interpolator, i.toFloat() / keyFrames, time)
    }
}
*/

/*private fun CoroutineScope.scheduleKeyFrame(
    interpolator: (Float) -> Float,
    decimalIndex: Float,
    time: Int
) {
    launch {
        delay((decimalIndex * time).toLong())
        animatedValue = floor(interpolator(decimalIndex)).toInt()
    }
}*/
