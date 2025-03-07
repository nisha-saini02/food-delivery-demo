package com.infosys.domain.usecase

import com.infosys.domain.repository.DeleteAllCartsLocalRepository
import javax.inject.Inject

class DeleteAllCartsLocalUseCase @Inject constructor(var repo: DeleteAllCartsLocalRepository) {
    suspend fun deleteAllCarts() = repo.deleteAllCarts()
}