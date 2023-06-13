//package br.senai.jandira.sp.lionschool
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import br.senai.jandira.sp.lionschool.ui.theme.LionSchoolTheme
//
//class CousesActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            LionSchoolTheme {
//                // A surface container using the 'background' color from the theme
//
//                Courses()
//
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun Courses() {
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = Color(51, 71, 176)
//    ) {
//        Column(Modifier.fillMaxSize() .padding(0.dp,50.dp,0.dp,0.dp),horizontalAlignment = Alignment.CenterHorizontally) {
//                Text(text = "Escolha um curso para gerenciar", color = Color.White, fontSize = 30.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(0.dp,0.dp,0.dp,30.dp))
//                OutlinedTextField(
//                    value = "",  onValueChange = {
//                    },
//                    modifier = Modifier.size(340.dp,60.dp) ,
//                    label = { Text(text = "Find your course", color = Color(183,182,180), fontSize = 18.sp, modifier = Modifier.padding(15.dp,0.dp,0.dp,0.dp)) },
//                    shape = RoundedCornerShape(25.dp),
//                    colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = Color.White),
//                    trailingIcon = {
//                        Icon(
//                            painter = painterResource(id = R.drawable.baseline_search_24 ),
//                            contentDescription = "",
//                            tint = Color(183,182,180),
//                            modifier = Modifier
//                                .size(40.dp)
//                                .padding(0.dp, 0.dp, 10.dp, 0.dp)
//                        )
//                    }
//                )
//
//            Column(Modifier.fillMaxWidth() .padding(20.dp,30.dp,0.dp,0.dp), horizontalAlignment = Alignment.Start) {
//                Text(text = "Cursos",color = Color.White, fontSize = 28.sp)
//                LazyColumn(){
//                    items(10){
//                        Text(text = "Teste")
//                    }
//                }
//            }
//
//        }
//    }
//}
//
