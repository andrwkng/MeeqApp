package com.spryteam.meeqapp.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spryteam.meeqapp.ui.components.Badge
import com.spryteam.meeqapp.ui.components.HintHeader
import com.spryteam.meeqapp.ui.components.Paragraph
import com.spryteam.meeqapp.ui.components.SubHeader
import com.spryteam.meeqapp.ui.theme.Theme

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
            .border(1.dp, Theme.lightGray, RoundedCornerShape(8.dp))
            .then(modifier)
    ) {
        children()
    }
}


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
    /*Badge(
        text = text,
        icon = icon,
        //modifier = Modifier.padding(bottom = 18.dp)
    )*/
    Row(
        modifier = Modifier
            .background(
                color = Theme.colorGray,
                shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
            )
            //.padding(vertical = 8.dp, horizontal = 12.dp)
            .fillMaxWidth()
            .padding(12.dp)
        //contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.W700,
            color = Theme.lightText,
            letterSpacing = 1.sp,
            modifier = Modifier.weight(1f)
        )
        Icon(
            icon,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(12.dp)
        )
    }

    /*Row(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .background(Theme.lightOffWhite)
            .padding(12.dp)
            .fillMaxWidth(),
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            color = Theme.lightText,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Theme.lightText,
            modifier = Modifier.size(16.dp),
        )
    }*/
}

@Composable
fun CardTitleAndSubtitleContent(title: String, subtitle: String) {
    Column(
        modifier = Modifier
            //.fillMaxSize()
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
    //val defaultDensity = Density(density = 1f)
    /*val fontFamilyResolver = createFontFamilyResolver(
        InstrumentationRegistry.getInstrumentation().context
    )*/
    /*val constraints = Constraints()

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
        //fontFamilyResolver = fontFamilyResolver
    )*/
//    color: theme.darkText,
//    fontWeight: "400",
//    fontSize: 16,
//    marginBottom: 8,
//    paddingLeft: 12,
//    paddingRight: 12,
//    paddingTop: 12,
//    paddingBottom: 6,
    Paragraph(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .padding(top = 12.dp, bottom = 6.dp)
            .padding(start = 12.dp, end = 12.dp),
        fontWeight = FontWeight.W400,
        textDirection = TextDirection.Content,
        color = Theme.darkText,
    ) { text }
}


@Composable
fun CardMutedContent(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            //.fillMaxSize()
            .padding(4.dp)
            .background(
                color = Theme.lightOffWhite,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(12.dp, 6.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCardCrown() {
    CardCrown(text = "THOUGHT", Icons.Default.Email)
}

@Preview(showBackground = true)
@Composable
fun CardTextContentPreview() {
    CardTextContent(text = "Alternative thought")
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
            .offset((-5).dp, 7.dp)
            .size(18.dp)
            .background(Theme.colorPink, CircleShape)
            //.align(Alignment.TopEnd)
    )
}

@Preview(showBackground = true)
@Composable
fun CardBadgePreview() {
    CardBadge(text = "Felt better later on", Icons.Default.ThumbUp)
}

@Preview(showBackground = true)
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
