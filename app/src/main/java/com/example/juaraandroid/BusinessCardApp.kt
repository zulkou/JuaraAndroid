package com.example.juaraandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.juaraandroid.ui.theme.JuaraAndroidTheme

class BusinessCardApp : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JuaraAndroidTheme {
                Card()
            }
        }
    }
}

@Composable
fun BusinessCard(fullName: String, title: String, phone: String, handle: String, email: String, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Column (
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val logo = painterResource(R.drawable.android_logo)

            Image(
                painter = logo,
                contentDescription = null,
                modifier = Modifier
                    .width(150.dp)
            )
            Text(
                text = fullName,
                fontSize = 32.sp,
            )
            Text(
                text = title,
                fontSize = 16.sp,
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                val phoneIcon = painterResource(R.drawable.ic_phone)

                Icon(
                    painter = phoneIcon,
                    contentDescription = null
                )
                Text(
                    text = phone,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row(
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                val handleIcon = painterResource(R.drawable.ic_handle)

                Icon(
                    painter = handleIcon,
                    contentDescription = null
                )
                Text(
                    text = handle,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row(
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                val emailIcon = painterResource(R.drawable.ic_email)

                Icon(
                    painter = emailIcon,
                    contentDescription = null
                )
                Text(
                    text = email,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Card() {
    JuaraAndroidTheme {
        BusinessCard(
            fullName = "Fahmi Zulkarnain",
            title = "Android Developer",
            phone = "1234567890",
            handle = "@zulkou",
            email = "zulkou@email.com"
        )
    }
}