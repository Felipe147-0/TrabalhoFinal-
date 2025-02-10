package br.edu.ifsp.dmo.trabalhofinal.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import br.edu.ifsp.dmo.trabalhofinal.util.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreRepository(context: Context) {
    private val dataStore: DataStore<Preferences> = context.dataStore

    object PreferencesFile{
        const val FILE_NAME = "user_preferences"
    }

    private object PreferenceKeys{
        val SAVE_LOGIN = booleanPreferencesKey("save_login")
        val STAY_LOGGED_IN = booleanPreferencesKey("stay_logged_in")
        val EMAIL = stringPreferencesKey("email")
        val PASSWORD = stringPreferencesKey("password")
    }

    suspend fun savePreferences(email: String, senha: String, saveLogin: Boolean, stayLoggedIn: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.EMAIL] = email
            preferences[PreferenceKeys.PASSWORD] = senha
            preferences[PreferenceKeys.SAVE_LOGIN] = saveLogin
            preferences[PreferenceKeys.STAY_LOGGED_IN] = stayLoggedIn
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.STAY_LOGGED_IN] = false
        }
    }

    val loginPreferences: Flow<Pair<Boolean,Boolean>> = dataStore.data.map { preferences ->
        val saveLogin = preferences[PreferenceKeys.SAVE_LOGIN] ?: false
        val stayLoggedIn = preferences[PreferenceKeys.STAY_LOGGED_IN] ?: false
        Pair(saveLogin,stayLoggedIn)
    }

    val dataPreferences: Flow<Pair<String,String>> = dataStore.data.map { preferences ->
        val email = preferences[PreferenceKeys.EMAIL] ?: ""
        val password = preferences[PreferenceKeys.PASSWORD] ?: ""
        Pair(email,password)
    }
}