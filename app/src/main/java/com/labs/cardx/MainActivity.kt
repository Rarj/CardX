package com.labs.cardx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.labs.cardx.ui.theme.CardXTheme
import com.labs.x.R
import com.labs.x.X

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CardXTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = MaterialTheme.colorScheme.secondaryContainer)
                            .padding(16.dp)
                    ) {
                        DebitCard()
                    }
                }
            }
        }
    }
}

@Composable
private fun DebitCard() {
    X(
        titleCard = "Paypal",
        iconCard = R.drawable.ic_visa,
        numberCard = "1234 1234 1234 1234",
        nameCard = "Big Smoke",
        validDate = "01/28",
        cvvNumber = "555"
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CardXTheme {
        DebitCard()
    }
}