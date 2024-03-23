package com.labs.x

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

/**
 * [BackCard] function is [Composable] that will generate a certain view of the Back part of the Card.
 *
 * @param cvvNumber is [String] that will show a 3 or 4 digit number
 * @param backAnimation is [Float] that represent a duration that show flip animation.
 * @param rotationAnimation is [Float] that represent a duration that show flip animation.
 */
@Composable
internal fun BackCard(
    cvvNumber: String,
    backAnimation: Float,
    rotationAnimation: Float,
) {
    ConstraintLayout(
        modifier = Modifier
            .width(360.dp)
            .height(184.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .background(color = Color.White),
    ) {
        val (divider, cvv) = createRefs()

        Divider(
            modifier = Modifier
                .constrainAs(divider) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(top = 16.dp)
                .graphicsLayer {
                    alpha = backAnimation
                },
            color = Color.Black,
            thickness = 50.dp
        )

        CVV(
            cvvNumber = cvvNumber,
            modifier = Modifier
                .constrainAs(cvv) {
                    top.linkTo(divider.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            backAnimation = backAnimation,
            rotationAnimation = rotationAnimation
        )
    }
}

/**
 * [CVV] function is [Composable] that will generate a CVV.
 *
 * @param cvvNumber is [String] that will show a 3 or 4 digit number
 * @param modifier An ordered, immutable collection of modifier elements that decorate or
 *                 add behavior to Compose UI elements.
 *                 For example, backgrounds, padding and click event listeners decorate or add behavior to rows, text or buttons.
 * @param backAnimation is [Float] that represent a duration that show flip animation.
 * @param rotationAnimation is [Float] that represent a duration that show flip animation.
 * @see Modifier
 */
@Composable
private fun CVV(
    cvvNumber: String,
    modifier: Modifier = Modifier,
    backAnimation: Float,
    rotationAnimation: Float,
) {
    Text(
        text = cvvNumber,
        color = Color.Black,
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(Color.LightGray)
            .padding(10.dp)
            .graphicsLayer {
                alpha = backAnimation
                rotationY = rotationAnimation
            },
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(R.font.sono_semibold)),
        textAlign = TextAlign.End,
    )
}

@Preview
@Composable
private fun CVVPreview() {
    BackCard(cvvNumber = "123", backAnimation = 1f, rotationAnimation = 0f)
}