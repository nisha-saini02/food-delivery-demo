package com.infosys.domain.usecase

import com.infosys.domain.repository.FetchOrderLocalRepository
import javax.inject.Inject

class FetchOrderLocalUserCase @Inject constructor(var repo: FetchOrderLocalRepository) {
    suspend fun getOrder(id: String) = repo.getOrder(id)
}