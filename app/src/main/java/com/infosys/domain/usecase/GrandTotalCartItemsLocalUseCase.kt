package com.infosys.domain.usecase

import com.infosys.domain.repository.GrandTotalCartItemsLocalRepository
import javax.inject.Inject

class GrandTotalCartItemsLocalUseCase @Inject constructor(var repo: GrandTotalCartItemsLocalRepository) {
    suspend fun getCartGrandSum() = repo.getCartListGrandTotalCount()
}