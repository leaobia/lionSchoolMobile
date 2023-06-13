package br.senai.jandira.sp.lionschool.services

import br.senai.jandira.sp.lionschool.model.CoursesList
import retrofit2.Call
import retrofit2.http.GET
//import retrofit2.http.Path
//import retrofit2.http.Query

interface CourseService {
    @GET("cursos")
    fun getCourses(): Call<CoursesList>
}