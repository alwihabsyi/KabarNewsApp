package com.alwihabsyi.newsapp.data.remote.dto

import com.alwihabsyi.newsapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)