package com.alwihabsyi.newsapp.domain.usecases.news

import com.alwihabsyi.newsapp.domain.model.Article
import com.alwihabsyi.newsapp.domain.repository.NewsRepository

class UpsertArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article: Article) {
        newsRepository.upsertArticle(article)
    }
}