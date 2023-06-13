package br.senai.jandira.sp.lionschool.model

data class novoCurso(
    val nome: String,
    val sigla: String,
    val conclusao: String,
    val disciplinas: List<Disciplinas>
)
