package com.infosys.data.model.user

import com.infosys.utils.enums.LoginType

data class User(
    var name: String? = null,
    var email: String? = null,
    var password: String? = null,
    var authenticate: Boolean = false,
    var type: LoginType = LoginType.Guest,
    var id: String = System.currentTimeMillis().toString(),
)