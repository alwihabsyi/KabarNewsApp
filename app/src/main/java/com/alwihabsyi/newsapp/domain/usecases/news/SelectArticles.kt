package com.alwihabsyi.newsapp.domain.usecases.news

import com.alwihabsyi.newsapp.domain.model.Article
import com.alwihabsyi.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectArticles()
    }
}