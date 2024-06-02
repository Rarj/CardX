package com.rioarj.labs

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

/**
 * [FrontCard] function is [Composable] that will generate a certain view of the Front part of the Card.
 *
 * @param titleCard is [String] that will show as Issuing Bank (i.e: Visa, Mastercard)
 * @param iconCard is [DrawableRes] that will show as logo of Issuing Bank (i.e: Visa, Mastercard)
 * @param numberCard is [String] that represent Account Number
 * @param nameCard is [String] that represent Cardholder Name
 * @param validDate is [String] that will show an Expiration Date of the Card (i.e: 01/24).
 *                  First two digits (01) is equals with January, that is represent a Month and starts with 0 if month is single digit.
 *                  Last two digits (24) is equals with 2024, that is represent a Last-Two-Digit of Expiration Year.
 * @param frontAnimation is [Float] that represent a duration that show flip animation.
 */
@Composable
internal fun FrontCard(
    titleCard: String,
    @DrawableRes iconCard: Int,
    numberCard: String,
    nameCard: String,
    validDate: String,
    frontAnimation: Float,
) {
    val animationModifier = Modifier
        .graphicsLayer {
            alpha = frontAnimation
        }

    ConstraintLayout(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(15.dp))
            .width(360.dp)
            .height(184.dp)
            .background(color = Color.White)
            .padding(16.dp),
    ) {
        val (title, iconType, iconChip, cardNumber, cardName, validThru) = createRefs()

        TitleText(
            title = titleCard,
            modifier = animationModifier
                .wrapContentSize()
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )

        BankIcon(
            icon = iconCard,
            modifier = animationModifier
                .size(48.dp)
                .constrainAs(iconType) {
                    top.linkTo(title.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(title.bottom)
                }
        )

        BankChip(
            modifier = animationModifier
                .padding(top = 24.dp, start = 16.dp)
                .constrainAs(iconChip) {
                    top.linkTo(title.bottom)
                    start.linkTo(parent.start)
                }
        )

        CardNumber(
            number = numberCard,
            modifier = animationModifier
                .padding(top = 24.dp, start = 16.dp)
                .wrapContentSize()
                .constrainAs(cardNumber) {
                    top.linkTo(iconChip.bottom)
                    start.linkTo(parent.start)
                }
        )

        CardName(
            name = nameCard,
            modifier = animationModifier
                .padding(top = 4.dp, start = 16.dp)
                .wrapContentSize()
                .constrainAs(cardName) {
                    top.linkTo(cardNumber.bottom)
                    start.linkTo(parent.start)
                }
        )

        ValidThru(
            date = validDate,
            modifier = animationModifier
                .padding(top = 4.dp, start = 16.dp)
                .wrapContentSize()
                .constrainAs(validThru) {
                    top.linkTo(cardName.top)
                    end.linkTo(parent.end)
                }
        )

    }
}

/**
 * [TitleText] function is responsible to show Issuing Bank
 *
 * @param title will pass [String] to show Issuing Bank at the Left-Top-Corner of the Front Card
 * @param modifier An ordered, immutable collection of modifier elements that decorate or
 *                 add behavior to Compose UI elements.
 *                 For example, backgrounds, padding and click event listeners decorate or add behavior to rows, text or buttons.
 * @see Modifier
 */
@Composable
private fun TitleText(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = title,
        modifier = modifier,
        fontSize = 18.sp,
        fontFamily = FontFamily(Font(R.font.sono_medium)),
    )
}

/**
 * [BankIcon] function is responsible to show logo of Issuing Bank
 *
 * @param icon will pass [DrawableRes] to show logo of Issuing Bank at the Right-Top-Corner of the Front Card
 * @param modifier An ordered, immutable collection of modifier elements that decorate or
 *                 add behavior to Compose UI elements.
 *                 For example, backgrounds, padding and click event listeners decorate or add behavior to rows, text or buttons.
 * @see Modifier
 */
@Composable
private fun BankIcon(
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier,
) {
    Image(
        modifier = modifier,
        painter = painterResource(id = icon),
        contentDescription = "Bank Icon"
    )
}

/**
 * [BankChip] function is responsible to show default embedded chip icon
 *
 * @param modifier An ordered, immutable collection of modifier elements that decorate or
 *                 add behavior to Compose UI elements.
 *                 For example, backgrounds, padding and click event listeners decorate or add behavior to rows, text or buttons.
 * @see Modifier
 */
@Composable
private fun BankChip(
    modifier: Modifier = Modifier,
) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_chip),
        contentDescription = "Chip"
    )
}

/**
 * [CardNumber] function is responsible to show Account Number
 *
 * @param number will pass [String] to show Account Number.
 * @param modifier An ordered, immutable collection of modifier elements that decorate or
 *                 add behavior to Compose UI elements.
 *                 For example, backgrounds, padding and click event listeners decorate or add behavior to rows, text or buttons.
 * @see Modifier
 */
@Composable
private fun CardNumber(
    number: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = number,
        modifier = modifier,
        fontSize = 20.sp,
        fontFamily = FontFamily(Font(R.font.sono_semibold)),
    )
}

/**
 * [CardName] function is responsible to show Cardholder Name at the Left-Bottom-Corner of the Front Card
 *
 * @param name will pass [String] to show Cardholder Name.
 * @param modifier An ordered, immutable collection of modifier elements that decorate or
 *                 add behavior to Compose UI elements.
 *                 For example, backgrounds, padding and click event listeners decorate or add behavior to rows, text or buttons.
 * @see Modifier
 */
@Composable
private fun CardName(
    name: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = name,
        modifier = modifier,
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(R.font.sono_regular)),
    )
}

/**
 * [CardName] function is responsible to show
 *
 * @param date will pass [String] to show an Expiration Date of the Card (i.e: 01/24).
 *             First two digits (01) is equals with January, that is represent a Month and start with 0 if month is single digit.
 *             Last two digits (24) is equals with 2024, that is represent a Last-Two-Digit of Expiration Year.
 * @param modifier An ordered, immutable collection of modifier elements that decorate or
 *                 add behavior to Compose UI elements.
 *                 For example, backgrounds, padding and click event listeners decorate or add behavior to rows, text or buttons.
 * @see Modifier
 */
@Composable
private fun ValidThru(
    date: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = date,
        modifier = modifier,
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(R.font.sono_semibold)),
    )
}


@Preview
@Composable
private fun XPreview() {
    FrontCard(
        titleCard = "Paypal",
        iconCard = R.drawable.ic_visa,
        numberCard = "•••• •••• •••• 1234",
        nameCard = "Personal Name",
        validDate = "01/24",
        frontAnimation = 1f
    )
}
