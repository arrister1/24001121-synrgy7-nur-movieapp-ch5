package com.example.movieapp.helper

import android.content.Context
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.prefs.Preferences

private val Context.dataStore by preferencesDataStore("user_prefs")
class DataStore (val context: Context) {


    companion object {
        private val EXTRA_USERNAME = stringPreferencesKey("username")
        private val EXTRA_EMAIL = stringPreferencesKey("email")
        private val EXTRA_PASS = stringPreferencesKey("password")
        private val EXTRA_IS_LOGIN = booleanPreferencesKey("isLogin")
        private val EXTRA_DOB = stringPreferencesKey("dob")
        private val EXTRA_FULL_NAME = stringPreferencesKey("fullName")
        private val EXTRA_ADDRESS = stringPreferencesKey("address")
    }

    val username: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[EXTRA_USERNAME]
    }

    val email: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[EXTRA_EMAIL]
    }

    val pass: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[EXTRA_PASS]
    }

    val isLogin: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[EXTRA_IS_LOGIN] ?: false
    }

    val fullName: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[EXTRA_FULL_NAME]
    }

    val address: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[EXTRA_ADDRESS]
    }

    val dob: Flow<String?> =  context.dataStore.data.map { preferences ->
        preferences[EXTRA_DOB]
    }
    suspend fun saveUserData(username: String, email: String, pass: String){
        context.dataStore.edit { preferences ->
            preferences[EXTRA_USERNAME] = username
            preferences[EXTRA_EMAIL] = email
            preferences[EXTRA_PASS] = pass

        }
    }

    suspend fun setLogin(isLogin: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[EXTRA_IS_LOGIN] =  isLogin
        }
    }

    suspend fun clearUserData(){
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    suspend fun logout() {
        context.dataStore.edit{ preferences ->
            preferences[EXTRA_IS_LOGIN] = false
        }
    }

    suspend fun updateUserData(username: String, fullName: String, address: String, dob: String) {
        context.dataStore.edit{preferences ->
            preferences[EXTRA_USERNAME] = username
            preferences[EXTRA_FULL_NAME] = fullName
            preferences[EXTRA_ADDRESS] = address
            preferences[EXTRA_DOB] = dob
        }
    }


}