package br.senai.jandira.sp.lionschool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.jandira.sp.lionschool.ui.theme.LionSchoolTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                // A surface container using the 'background' color from the theme
              
                    Greeting()
                
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Greeting() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(51, 71, 176)
    ) {
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Image( modifier = Modifier
                    .size(200.dp)
                    .padding(0.dp, 0.dp, 0.dp, 50.dp), painter = painterResource(id = R.drawable.logoimage), contentDescription = "")
            Column(
                Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(8.dp, 0.dp, 8.dp, 0.dp),horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround) {
                Text(text = "Lion School", color = Color.White, fontSize = 50.sp, fontWeight = FontWeight(300))
                Text(text = "A melhor escola de cursos profissionalizantes da região", color = Color.White,
                    textAlign = TextAlign.Center, fontSize = 16.sp, fontWeight = FontWeight(200))
            }
            Row(modifier = Modifier.fillMaxWidth() .padding(0.dp,50.dp,0.dp,0.dp), horizontalArrangement = Arrangement.Center) {
                Button(onClick = { /*TODO*/ } ) {
                    Text(text = "teste")
                }
            }


        }
    }
}


@Composable
fun DefaultPreview() {
    LionSchoolTheme {

    }
}