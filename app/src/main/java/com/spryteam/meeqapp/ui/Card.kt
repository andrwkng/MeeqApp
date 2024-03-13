package com.sprytm.meeqapp.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.Paragraph
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.createFontFamilyResolver
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.test.platform.app.InstrumentationRegistry
import com.sprytm.meeqapp.ui.components.Badge
import com.sprytm.meeqapp.ui.components.HintHeader
import com.sprytm.meeqapp.ui.components.SubHeader
import com.sprytm.meeqapp.ui.theme.Theme

@Composable
fun TouchableCardContainer(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    children: @Composable () -> Unit,
) {

    TouchableOpacity(
        onClick = onClick,
        modifier = Modifier
            .padding(bottom = 18.dp)
            .background(Color.White)
            .border(1.dp, Theme.colorLightGray, RoundedCornerShape(8.dp))
            .then(modifier)
    ) {
        children()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TouchableOpacity(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    children: @Composable () -> Unit,
) {
    Card(
        onClick = onClick,
        modifier = modifier
    ) {
        children()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PressIconButton(
    onClick: () -> Unit,
    text: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource =
        remember { MutableInteractionSource() },
) {
    val isPressed by interactionSource.collectIsPressedAsState()
    Card(
        onClick = onClick,
        modifier = modifier,
        interactionSource = interactionSource
    ) {
        AnimatedVisibility(visible = isPressed) {
            if (isPressed) {
            }
        }
        text()
    }
}

@Composable
fun CardCrown(
    text: String,
    icon: ImageVector,
    color: Color = Theme.veryLightText,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 12.dp)
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
            )
            .padding(8.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = text,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            letterSpacing = 1.sp
        )
        Icon(
            icon,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(12.dp)
        )
    }
}

@Composable
fun CardTitleAndSubtitleContent(title: String, subtitle: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        SubHeader(
            text = title,
        )
        HintHeader(
            text = subtitle,
            modifier = Modifier.padding(bottom = 0.dp)
        )
    }
}


@Composable
fun CardTextContent(text: String) {
    val defaultDensity = Density(density = 1f)
    val fontFamilyResolver = createFontFamilyResolver(
        InstrumentationRegistry.getInstrumentation().context
    )
    val constraints = Constraints()

    Paragraph(
        text = text,
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            textDirection = TextDirection.Content,
            color = MaterialTheme.colorScheme.onBackground,
        ),
        maxLines = Int.MAX_VALUE,
        ellipsis = false,
        constraints = Constraints(maxWidth = constraints.maxWidth),
        density = defaultDensity,
        fontFamilyResolver = fontFamilyResolver
    )
}


@Composable
fun CardMutedContent(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(12.dp, 6.dp),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
fun CardBadge(
    text: String,
    icon: ImageVector,
    backgroundColor: Color = Color.Unspecified,
) {
    Badge(
        icon = icon,
        text = text,
        backgroundColor = backgroundColor,
        modifier = Modifier.padding(4.dp)
    )
}

@Composable
fun CardAttentionDot() {
    Box(
        modifier = Modifier
            .padding(top = 12.dp, end = 0.dp)
            .offset((-6).dp, 0.dp)
            .size(18.dp)
            .background(Theme.colorPink, CircleShape)
            //.align(Alignment.TopEnd)
    )
}

@Preview
@Composable
fun CardAttentionDotPreview() {
    CardAttentionDot()
}

@Preview(showBackground = true)
@Composable
fun CardTitleAndSubtitleContentPreview() {
    CardTitleAndSubtitleContent("Title", "SubTitle")
}

/*@Composable
@Preview(showBackground = true)
fun TouchableCardContainerPreview() {
    TouchableCardContainer(
        //onPress = {},
    ){

    }
}*/
