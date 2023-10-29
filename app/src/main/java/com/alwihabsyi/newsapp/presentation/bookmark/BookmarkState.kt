package com.alwihabsyi.newsapp.presentation.bookmark

import com.alwihabsyi.newsapp.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)
