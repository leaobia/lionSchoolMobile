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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.jandira.sp.lionschool.model.Aluno
import br.senai.jandira.sp.lionschool.model.novoCurso
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
        mutableStateOf(br.senai.jandira.sp.lionschool.model.Aluno(foto = "", nome ="", matricula ="", curso = novoCurso("", "", "", emptyList()), status = ""))
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
                    Log.i("tag3", "StudentGradeScreen: ${aluno}")
                }
            }else{
                Log.e("testeErro", "Erro na resposta da API: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<Aluno>, t: Throwable) {
            Log.i("teste", "onFailure: ${t.message} ")
        }
    })

    Log.i("tag", "StudentGradeScreen: ${aluno}")
    Log.i("tag2", "StudentGradeScreen: ${matricula}")

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(51, 71, 176)
    ) {
        Column(Modifier.fillMaxSize()) {
            Button(modifier = Modifier.padding(10.dp),
                onClick = {
                var openStudents = Intent(context, Courses::class.java)
                context.startActivity(openStudents)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = ""
                )
                        }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(50.dp, 15.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                AsyncImage(model = aluno.foto, contentDescription = null, modifier = Modifier.size(90.dp))
                Text(text = aluno.nome.toUpperCase(), color = Color.White,
                    textAlign = TextAlign.Center, modifier = Modifier.width(180.dp), fontWeight = FontWeight.Light)
            }
            Text(
                text = "Statistics",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(20.dp, 10.dp, 0.dp, 0.dp)
            )


            LazyColumn(modifier = Modifier.padding(20.dp, 10.dp, 0.dp, 0.dp)){
                items(aluno.curso.disciplinas){
                        //Text(text = it.nome)
                    var barra = 2.4 * it.media.toDouble()
                    var corBarra = Color.LightGray

                    if (it.media.toDouble() > 60){
                        corBarra = Color(2,19,92)
                    }else if (it.media.toDouble() < 60 && it.media.toDouble() > 50){
                        corBarra = Color(254,194,64)
                    }else{
                        corBarra = Color(128,0,0)
                    }
                    Card(modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp, 20.dp, 20.dp, 0.dp)
                        .height(80.dp), backgroundColor = Color.White, shape = RoundedCornerShape(10.dp)) {
                         Column(modifier = Modifier.fillMaxSize() .padding(20.dp, 10.dp)) {
                             Row(modifier = Modifier
                                 .fillMaxWidth() .padding(0.dp, 0.dp, 0.dp, 10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween ) {
                                 Text(text = it.nome)
                                 Text(text = it.media)
                             }
                             Box(modifier = Modifier.size(330.dp,20.dp) .background(Color.LightGray)){
                                 Box(
                                     modifier = Modifier
                                         .fillMaxHeight()
                                         .clip(RoundedCornerShape(0.dp, 10.dp, 10.dp,0.dp))
                                         .background(
                                             corBarra
                                         )
                                         .width(barra.dp)
                                         .padding(0.dp, 0.dp, 5.dp, 0.dp)
                                 )
                             }
                         }
                    }
                }
            }


        }
    }
}





