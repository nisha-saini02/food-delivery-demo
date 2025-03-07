package com.infosys.data.localDatabase

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.infosys.MyApplication
import com.infosys.data.model.user.User
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MyDataStore @Inject constructor(
    @ApplicationContext val context: Context
) {
    private val Context.dataStore by preferencesDataStore(name = "user_datastore")
    private val userAuth = stringPreferencesKey("user_auth")

    val readUserInfo: Flow<User?> = context.dataStore.data.map { preferences ->
        if (!preferences[userAuth].isNullOrBlank()) {
            MyApplication.gson.fromJson(preferences[userAuth], User::class.java)
        } else {
            null
        }
    }

    suspend fun writeUserInfo(userInfo: User) {
        context.dataStore.edit { preferences ->
            preferences[userAuth] = MyApplication.gson.toJson(userInfo)
        }
    }

    suspend fun clearUserInfo() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}