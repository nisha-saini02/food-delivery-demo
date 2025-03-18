package com.infosys.domain.usecase

import com.infosys.domain.repository.CartsDetailLocalRepository

class DeleteAllCartsLocalUseCase(var repo: CartsDetailLocalRepository) {
    suspend fun deleteAllCarts() = repo.deleteAllCarts()
}