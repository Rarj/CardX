package com.labs.x

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

@Composable
fun X(
    titleCard: String,
    @DrawableRes iconCard: Int,
    numberCard: String,
    nameCard: String,
    validDate: String,
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
                cvvNumber = "123",
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
    )
}
