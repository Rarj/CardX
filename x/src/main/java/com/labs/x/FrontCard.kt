package com.labs.x

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
