package br.senai.jandira.sp.lionschool

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.jandira.sp.lionschool.model.Aluno
import br.senai.jandira.sp.lionschool.services.RetrofitFactory
import br.senai.jandira.sp.lionschool.ui.theme.LionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotaAluno : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                // A surface container using the 'background' color from the theme

                val matricula = intent.getStringExtra("Matricula")
                NotaAlunoFun(matricula.toString())

            }
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotaAlunoFun(matricula: String) {
    val context = LocalContext.current

    var aluno by remember {
        mutableStateOf(br.senai.jandira.sp.lionschool.model.Aluno("", "", "", "", emptyList()))
    }

    val call = RetrofitFactory().getStudentService().getAlunoByMatricula(matricula)

    call.enqueue(object : Callback<br.senai.jandira.sp.lionschool.model.Aluno> {
        override fun onResponse(
            call: Call<Aluno>,
            response: Response<br.senai.jandira.sp.lionschool.model.Aluno>
        ) {
            if (response.isSuccessful){
                val studentResponse = response.body()
                if (studentResponse != null){
                    aluno = studentResponse
                }
            }else{
                Log.e("teste", "Erro na resposta da API: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<Aluno>, t: Throwable) {
            Log.i("teste", "onFailure: ${t.message} ")
        }
    })

    Log.i("tag", "StudentGradeScreen: ${aluno}")

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(51, 71, 176)
    ) {
        Column(Modifier.fillMaxSize()) {
            Text(text = aluno.nome)
            Button(onClick = {
                var openStudents = Intent(context, Alunos::class.java)
                context.startActivity(openStudents)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = ""
                )
                        }
                    }
                }
            }





