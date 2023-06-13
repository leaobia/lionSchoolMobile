package br.senai.jandira.sp.lionschool.model

data class Aluno (
    val foto: String,
    val nome: String,
    val matricula: String,
    val status: String,
    val curso: novoCurso? = null
)