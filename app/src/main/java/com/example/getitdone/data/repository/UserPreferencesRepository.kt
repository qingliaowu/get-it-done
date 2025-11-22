package com.example.getitdone.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Singleton
class UserPreferencesRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val THEME_KEY = stringPreferencesKey("theme_key")
    private val DASHBOARD_CONFIG_KEY = stringPreferencesKey("dashboard_config_key")

    val theme: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[THEME_KEY] ?: "SYSTEM" // SYSTEM, LIGHT, DARK
        }

    val dashboardConfig: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[DASHBOARD_CONFIG_KEY] ?: "DEFAULT" // JSON string or simple identifier
        }

    suspend fun setTheme(theme: String) {
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = theme
        }
    }

    suspend fun setDashboardConfig(config: String) {
        context.dataStore.edit { preferences ->
            preferences[DASHBOARD_CONFIG_KEY] = config
        }
    }
}
