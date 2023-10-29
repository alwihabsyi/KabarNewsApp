package com.alwihabsyi.newsapp.domain.usecases.news

import androidx.paging.PagingData
import com.alwihabsyi.newsapp.domain.model.Article
import com.alwihabsyi.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.searchNews(searchQuery, sources)
    }
}