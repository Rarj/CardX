package com.rioarj.labs

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview

/**
 * [X] is a [Composable] that will show a Front and Back of the Card.
 * by default its using a Flip Animation with 500ms of the duration of the animation.
 *
 * @param titleCard is [String] that will show as Issuing Bank (i.e: Visa, Mastercard)
 * @param iconCard is [DrawableRes] that will show as logo of Issuing Bank (i.e: Visa, Mastercard)
 * @param numberCard is [String] that represent Account Number
 * @param nameCard is [String] that represent Cardholder Name
 * @param validDate is [String] that will show an Expiration Date of the Card (i.e: 01/24).
 *                  First two digits (01) is equals with January, that is represent a Month and starts with 0 if month is single digit.
 *                  Last two digits (24) is equals with 2024, that is represent a Last-Two-Digit of Expiration Year.
 * @param cvvNumber is [String] that will show a 3 or 4 digit number
 * @param modifier An ordered, immutable collection of modifier elements that decorate or
 *                 add behavior to Compose UI elements.
 *                 For example, backgrounds, padding and click event listeners decorate or add behavior to rows, text or buttons.
 * @see Modifier
 */
@Composable
fun X(
    titleCard: String,
    @DrawableRes iconCard: Int,
    numberCard: String,
    nameCard: String,
    validDate: String,
    cvvNumber: String,
    modifier: Modifier = Modifier,
) {
    var rotated by remember { mutableStateOf(false) }

    val rotationAnimation by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f,
        animationSpec = tween(500),
        label = "",
    )

    val frontAnimation by animateFloatAsState(
        targetValue = if (!rotated) 1f else 0f,
        animationSpec = tween(500),
        label = "",
    )

    val backAnimation by animateFloatAsState(
        targetValue = if (rotated) 1f else 0f,
        animationSpec = tween(500),
        label = "",
    )

    Column(
        modifier = modifier
            .wrapContentSize()
            .background(color = Color.Transparent)
            .graphicsLayer {
                rotationY = rotationAnimation
                cameraDistance = 8 * density
            }
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ) {
                rotated = !rotated
            }
    ) {
        if (rotated.not()) {
            FrontCard(
                titleCard = titleCard,
                iconCard = iconCard,
                numberCard = numberCard,
                nameCard = nameCard,
                validDate = validDate,
                frontAnimation = frontAnimation,
            )
        } else {
            BackCard(
                cvvNumber = cvvNumber,
                backAnimation = backAnimation,
                rotationAnimation = rotationAnimation,
            )
        }
    }
}

@Preview(device = "id:pixel_7_pro", showSystemUi = true, showBackground = true)
@Composable
private fun FrontCardPreview() {
    X(
        titleCard = "Paypal",
        iconCard = R.drawable.ic_visa,
        numberCard = "•••• •••• •••• 1234",
        nameCard = "Personal Name",
        validDate = "01/24",
        cvvNumber = "123",
    )
}
