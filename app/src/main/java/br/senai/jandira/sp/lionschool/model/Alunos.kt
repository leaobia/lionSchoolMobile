package br.senai.jandira.sp.lionschool.model

data class Alunos (
    val id: Long,
    val nome: String,
    val status: String,
    val foto: String,
    val matricula: String,
    val curso: Course
)