package com.example.juaraandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.juaraandroid.ui.theme.JuaraAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JuaraAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout() {
    var stage by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier
            .padding(16.dp, top = 32.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ArtDisplay(
            stage = stage,
        )
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { if(stage!=1){stage--}else{stage = 4} }) {
                Text("Previous")
            }
            Button(
                onClick = { if(stage!=4){stage++}else{stage = 1} },
                ) {
                Text("Next")
            }
        }
    }
}

@Composable
fun ArtDisplay(
//    @DrawableRes artImage: Int,
//    @StringRes artTitle: Int,
//    @StringRes artist: Int,
    stage: Int,
//    onStageChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val artImage = when(stage) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val artTitle = when(stage) {
        1 -> R.string.lemon1
        2 -> R.string.lemon2
        3 -> R.string.lemon3
        else -> R.string.lemon4
    }
    val artist = when(stage) {
        1 -> R.string.lemon1
        2 -> R.string.lemon2
        3 -> R.string.lemon3
        else -> R.string.lemon4
    }
    Box(
        modifier = Modifier
            .shadow(elevation = 10.dp, shape = RectangleShape)
    ) {
        Image(
                painter = painterResource(id = artImage),
        contentDescription = null,
        modifier = modifier
            .padding(20.dp)

        )
    }
    Spacer(modifier = Modifier.height(16.dp))
    Column {
        Text(
            text = stringResource(id = artTitle),
            fontSize = 24.sp,
            fontWeight = FontWeight.Thin
        )
        Text(
            text = stringResource(id = artist),
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    JuaraAndroidTheme {
        ArtSpaceLayout()
    }
}

