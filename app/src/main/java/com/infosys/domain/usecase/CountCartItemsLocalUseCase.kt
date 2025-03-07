package com.infosys.domain.usecase

import com.infosys.domain.repository.CountCartItemsLocalRepository
import javax.inject.Inject

class CountCartItemsLocalUseCase @Inject constructor(var repo: CountCartItemsLocalRepository) {
    suspend fun getCartListCount() = repo.getCartListCount()
}