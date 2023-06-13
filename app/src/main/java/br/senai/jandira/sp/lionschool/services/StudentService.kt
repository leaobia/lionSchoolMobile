package br.senai.jandira.sp.lionschool.services

import br.senai.jandira.sp.lionschool.model.Aluno
import br.senai.jandira.sp.lionschool.model.AlunosList
import br.senai.jandira.sp.lionschool.model.Course
import br.senai.jandira.sp.lionschool.model.CoursesList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StudentService {

// https://lionschool-api.cyclic.app/v1/lion-school/alunos?curso=RDS

   @GET("alunos/")
    fun getAlunosByCourseName(@Query("curso")siglaCurso: String): Call<AlunosList>

    @GET("alunos/{matricula}")
    fun getAlunoByMatricula(@Path("matricula")matricula: String): Call<Aluno>
}