package com.ahatuhov.artspace

import android.os.Bundle
import android.service.autofill.OnClickAction
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahatuhov.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    var currentStep by remember {
        mutableStateOf(1)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (currentStep) {
            1 -> {
                ContentBlock(
                    drawableResourceId = R.drawable.cat0,
                    contentDescriptionId = R.string.cat0,
                    titleId = R.string.art_title,
                    subtitleId = R.string.art_artist
                )
            }

            2 -> {
                ContentBlock(
                    drawableResourceId = R.drawable.cat1,
                    contentDescriptionId = R.string.cat1,
                    titleId = R.string.art_title1,
                    subtitleId = R.string.art_artist1
                )
            }

            3 -> {
                ContentBlock(
                    drawableResourceId = R.drawable.cat2,
                    contentDescriptionId = R.string.cat2,
                    titleId = R.string.art_title2,
                    subtitleId = R.string.art_artist2
                )
            }
        }

        ButtonBlock(
            onClickPrevious = { currentStep = clickActionPprevious(currentStep) },
            onClickNext = { currentStep = clickActionNext(currentStep) }
        )
    }
}

@Composable
fun ContentBlock(
    drawableResourceId: Int,
    contentDescriptionId: Int,
    titleId: Int,
    subtitleId: Int
) {
    val imgModifier = Modifier
        .size(250.dp)
        .padding(bottom = 10.dp)

    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = imgModifier,
                painter = painterResource(drawableResourceId),
                contentDescription = stringResource(contentDescriptionId),
                contentScale = ContentScale.Crop
            )
        }
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(titleId),
                color = Color.Black,
                style = TextStyle(fontSize = 20.sp)
            )
        }
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(subtitleId),
                color = Color.Black,
                style = TextStyle(fontSize = 16.sp)
            )
        }
    }
}

@Composable
fun ButtonBlock(
    onClickPrevious: () -> Unit,
    onClickNext: () -> Unit,
) {
    val buttonModifier = Modifier
        .width(130.dp)
        .height(50.dp)
        .padding(5.dp)

    Row(
        modifier = Modifier
            .padding(top = 30.dp)
    ){
        Box(
            modifier = buttonModifier,
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = onClickPrevious,
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
            ) {
                Text(
                    modifier = Modifier.width(120.dp),
                    text = stringResource(R.string.previous),
                    color = Color.Black,
                    style = TextStyle(fontSize = 16.sp)

                )
            }
        }
        Box(
            modifier = buttonModifier,
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = onClickNext,
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)

            ) {
                Text(
                    modifier = Modifier.width(120.dp),
                    text = stringResource(R.string.next),
                    color = Color.Black,
                    style = TextStyle(fontSize = 16.sp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@VisibleForTesting
internal fun clickActionNext(externalStep: Int): Int {
    var step = externalStep

    if (step < 3) {
        step++
    } else {
        step = 1
    }

    return step
}

@VisibleForTesting
internal fun clickActionPprevious(externalStep: Int): Int {
    var step = externalStep

    if (step == 1) {
        step = 3
    } else {
        step--
    }

    return step
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        Greeting()
    }
}