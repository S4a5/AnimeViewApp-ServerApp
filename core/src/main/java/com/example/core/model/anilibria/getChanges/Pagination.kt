package com.example.core.model.anilibria.getChanges

data class Pagination(
    val current_page: Int,
    val items_per_page: Int,
    val pages: Int,
    val total_items: Int
)