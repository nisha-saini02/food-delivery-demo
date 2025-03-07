package com.infosys.data.model.usecase

import com.infosys.domain.usecase.AllCartItemsLocalUseCase
import com.infosys.domain.usecase.DeleteCartItemLocalUseCase
import com.infosys.domain.usecase.FetchCartItemLocalUseCase
import com.infosys.domain.usecase.InsertCartItemLocalUseCase
import com.infosys.domain.usecase.OrderListLocalUseCase
import com.infosys.domain.usecase.UpdateCartItemLocalUseCase
import javax.inject.Inject

class LocalUseCase @Inject constructor(
    var allCartItemsLocalUseCase: AllCartItemsLocalUseCase,
    var fetchCartItemLocalUseCase: FetchCartItemLocalUseCase,
    var insertCartItemLocalUseCase: InsertCartItemLocalUseCase,
    var updateCartItemLocalUseCase: UpdateCartItemLocalUseCase,
    var deleteCartItemLocalUseCase: DeleteCartItemLocalUseCase,
    var orderListLocalUseCase: OrderListLocalUseCase,
)