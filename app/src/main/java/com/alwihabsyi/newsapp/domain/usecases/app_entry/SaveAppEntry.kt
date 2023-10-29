package com.alwihabsyi.newsapp.domain.usecases.app_entry

import com.alwihabsyi.newsapp.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}