package br.senai.jandira.sp.lionschool.model

data class Course(
    val nome: String,
    val sigla: String,
    val icone: String,
    val conclusao: String,
    val disciplinas: DisciplinaList
)
