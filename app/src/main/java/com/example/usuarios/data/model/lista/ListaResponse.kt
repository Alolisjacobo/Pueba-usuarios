package com.example.usuarios.data.model.lista

data class ListaResponse(
    val data: List<Data>,
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int
)