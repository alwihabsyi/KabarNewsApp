package com.alwihabsyi.newsapp.presentation.detail

import com.alwihabsyi.newsapp.domain.model.Article

sealed class DetailEvent {
    data class UpsertDeleteArticle(val article: Article): DetailEvent()
    data object RemoveSideEffect: DetailEvent()
}