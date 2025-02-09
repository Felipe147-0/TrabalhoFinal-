package br.edu.ifsp.dmo.trabalhofinal.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import br.edu.ifsp.dmo.trabalhofinal.util.dataStore

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
}