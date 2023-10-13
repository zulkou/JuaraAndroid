package com.example.juaraandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.juaraandroid.ui.theme.JuaraAndroidTheme

class LemonadeApp : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JuaraAndroidTheme {
                Lemonade()
            }
        }
    }
}

@Composable
fun LemonSqueeze(modifier: Modifier = Modifier) {
    var stage by remember { mutableStateOf(1) }
    val imageResource = when(stage) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val textResource = when(stage) {
        1 -> R.string.lemon1
        2 -> R.string.lemon2
        3 -> R.string.lemon3
        else -> R.string.lemon4
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { if(stage!=4){stage++}else{stage = 1} }) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = stage.toString()
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(id = textResource))
    }
}

@Preview
@Composable
fun Lemonade() {
    LemonSqueeze(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}
