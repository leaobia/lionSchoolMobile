package br.senai.jandira.sp.lionschool.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class RetrofitFactory {
    private val BASE_URL = "https://lionschool-api.cyclic.app/v1/lion-school/"
    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl("https://lionschool-api.cyclic.app/v1/lion-school/")
        .addConverterFactory(
            GsonConverterFactory
                .create()
        )
        .build()

    fun getCourseService(): CourseService {
        return retrofitFactory.create(CourseService::class.java)
    }

    fun getStudentService(): StudentService {
        return retrofitFactory.create(StudentService::class.java)
    }
}