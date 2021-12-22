package com.ramazan.composeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.ramazan.composeapp.ui.theme.ComposeAppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<MainViewModel>()

    @ExperimentalCoilApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onChangeData()
        setContent {
            ComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    TextScreen(
                        url = viewModel.catUrl,
                        text = viewModel.text,
                        isLoading = viewModel.isLoading
                    ) {
                        viewModel.onChangeData()
                    }
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun TextScreen(url: String, text: String, isLoading: Boolean, onClick: () -> Unit) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = rememberImagePainter(
                        data = url,
                        builder = {
                            transformations(CircleCropTransformation())
                        }
                    ),
                    contentDescription = "Cat image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                )

                if (isLoading) {
                    CircularProgressIndicator()
                }
            }
            Text(text = text, Modifier.padding(8.dp))
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = onClick,
                enabled = !isLoading
            ) {
                Text(text = "Click me")
            }
        }
    }
}

@ExperimentalCoilApi
@Preview(showBackground = true)
@Composable
fun TextScreenPreview() {
    ComposeAppTheme {
        TextScreen("https://cdn2.thecatapi.com/images/ati.jpg", "test", false) {

        }
    }
}