package br.senai.jandira.sp.lionschool

import android.content.Intent
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.platform.LocalContext
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import br.senai.jandira.sp.lionschool.Courses
import br.senai.jandira.sp.lionschool.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.jandira.sp.lionschool.model.AlunosList
import br.senai.jandira.sp.lionschool.model.Course
import br.senai.jandira.sp.lionschool.model.CoursesList
import br.senai.jandira.sp.lionschool.services.RetrofitFactory
import br.senai.jandira.sp.lionschool.ui.theme.LionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Alunos : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                // A surface container using the 'background' color from the theme

                val siglaCurso = intent.getStringExtra("Curso")
                val nomeCurso = intent.getStringExtra("CursoNome")
                Aluno(siglaCurso.toString(), nomeCurso.toString())
            }
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Aluno(curso: String, nomeCurso: String) {


    val context = LocalContext.current

    var listStudent by remember {
        mutableStateOf(listOf<br.senai.jandira.sp.lionschool.model.Alunos>())
    }

    val call = RetrofitFactory().getStudentService().getAlunosByCourseName(curso)

    call.enqueue(object : Callback<AlunosList> {
        override fun onResponse(
            call: Call<AlunosList>,
            response: Response<AlunosList>
        ) {
            listStudent = response.body()!!.alunos
        }

        override fun onFailure(call: Call<AlunosList>, t: Throwable) {

        }
    })

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(51, 71, 176)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {

            Button(onClick = {
                var openCourses = Intent(context, Courses::class.java)
                context.startActivity(openCourses)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = ""
                )
            }
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = nomeCurso.substring(6 ) ,color = Color.White, fontSize = 24.sp, textAlign = TextAlign.Center)

                LazyColumn(){
                    items(listStudent){
                        var corFundo = Color(51,71,186);
                        if (it.status == "Cursando"){
                            corFundo= Color(2,19,92)
                        }else{
                            corFundo= Color(254,194,64)
                        }
                        Button(onClick = {
                            var openNotaAluno = Intent(context, NotaAluno::class.java)
                            openNotaAluno.putExtra("Matricula", "${it.matricula}")
                            context.startActivity(openNotaAluno)
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp, 30.dp, 20.dp, 0.dp) , colors = ButtonDefaults.buttonColors( corFundo)) {
                            Card(modifier = Modifier
                                .fillMaxSize(), backgroundColor = corFundo , border = BorderStroke(1.dp,Color(254, 164, 64)),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Row(modifier = Modifier
                                    .fillMaxSize()
                                    .padding(20.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                                    AsyncImage(model = it.foto, contentDescription = null, modifier = Modifier.size(90.dp))
                                    Text(text = it.nome.toUpperCase(),color = Color.White, fontSize = 14.sp, modifier = Modifier
                                     , textAlign = TextAlign.Center)
                                }
                            }


                        }
                    }
            }

            }

        }
    }


}

