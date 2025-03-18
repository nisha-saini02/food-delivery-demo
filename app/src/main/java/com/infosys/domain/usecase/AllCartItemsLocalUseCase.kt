package com.infosys.domain.usecase

import com.infosys.domain.repository.CartsDetailLocalRepository

class AllCartItemsLocalUseCase (var localRepository: CartsDetailLocalRepository) {
    suspend fun fetchAllItems()
            = localRepository.fetchAllItems()
}