package br.senai.jandira.sp.lionschool

import android.content.Intent
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.jandira.sp.lionschool.ui.theme.LionSchoolTheme
import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.*
import br.senai.jandira.sp.lionschool.model.CoursesList
import br.senai.jandira.sp.lionschool.services.RetrofitFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Courses : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                // A surface container using the 'background' color from the theme

                CoursesFun()

            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CoursesFun() {

    val context = LocalContext.current
    var listCourse by remember {
        mutableStateOf(listOf<br.senai.jandira.sp.lionschool.model.Course>())
    }
    var nameCourseState by remember {
        mutableStateOf("")
    }

    val call = RetrofitFactory().getCourseService().getCourses()

    call.enqueue(object : Callback<CoursesList>{
        override fun onResponse(
            call: Call<CoursesList>,
            response: Response<CoursesList>
        ) {
            listCourse = response.body()!!.cursos
        }

        override fun onFailure(call: Call<CoursesList>, t: Throwable) {

        }
    })



    var listaCards = listCourse
    fun filterByName (name: String) {

        var listaNova = listCourse.filter {
            val regex = Regex(name, RegexOption.IGNORE_CASE)
            it.nome.contains(regex)
        }
        if(!listaNova.isEmpty()){
            listaCards = listaNova
        }else if(name == ""){
            listaCards = listCourse
        }
    }

        Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(51, 71, 176)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(0.dp, 50.dp, 0.dp, 0.dp),horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Escolha um curso para gerenciar", color = Color.White, fontSize = 30.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(0.dp,0.dp,0.dp,30.dp))
            OutlinedTextField(
                value = nameCourseState,
                onValueChange = {
                    nameCourseState = it

                    filterByName(it)
                },
                modifier = Modifier.size(340.dp,60.dp) ,
                label = { Text(text = "Find your course", color = Color(183,182,180), fontSize = 18.sp, modifier = Modifier.padding(15.dp,0.dp,0.dp,0.dp)) },
                shape = RoundedCornerShape(25.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = Color.White),
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_search_24 ),
                        contentDescription = "",
                        tint = Color(183,182,180),
                        modifier = Modifier
                            .size(40.dp)
                            .padding(0.dp, 0.dp, 10.dp, 0.dp)
                    )
                }
            )

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 30.dp, 0.dp, 0.dp), horizontalAlignment = Alignment.Start) {
                Text(text = "Cursos",color = Color.White, fontSize = 28.sp)
                LazyColumn(){
                    items(listaCards){
                        Button(onClick = {
                            val openStudents = Intent(context, Alunos::class.java)
                            openStudents.putExtra("Curso", "${it.sigla}")
                            openStudents.putExtra("CursoNome", "${it.nome}")
                            context.startActivity(openStudents)
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp) , colors = ButtonDefaults.buttonColors( Color(51, 71, 176))) {
                            Card(modifier = Modifier
                                .fillMaxSize(), backgroundColor = Color(51,71,186) ,
                                border = BorderStroke(1.dp,Color(254, 164, 64)), shape = RoundedCornerShape(10.dp)) {
                                Column(modifier = Modifier
                                    .fillMaxSize()
                                    .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                                    AsyncImage(model = it.icone, contentDescription = null, colorFilter = ColorFilter.tint(
                                        Color.White), modifier = Modifier.size(90.dp))
                                    Text(text = it.nome.substring(6),color = Color.White, fontSize = 14.sp, textAlign = TextAlign.Center, modifier = Modifier
                                        .width(230.dp)
                                        .padding(0.dp, 15.dp, 0.dp, 0.dp))
                                }
                                Text(text = it.sigla, color = Color.White)
                        }


                       }
                    }
                }
            }

        }
    }
}

