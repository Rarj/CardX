package com.rioarj.labs

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class XComponentTest : PaparazziSnapshot() {

    @Test
    fun front_card_component() {
        paparazzi.snapshot {
            FrontCard(
                titleCard = "Visa",
                iconCard = R.drawable.ic_visa,
                numberCard = "1234 1234 1234 1234",
                nameCard = "Big Smoke",
                validDate = "01/28",
                frontAnimation = 1f
            )
        }
    }

}