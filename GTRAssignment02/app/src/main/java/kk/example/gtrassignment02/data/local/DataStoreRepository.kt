package kk.example.gtrassignment02.data.local

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

val Context.onBoardingDataStore: DataStore<Preferences> by preferencesDataStore(name = "on_boarding_pref")
val Context.Theme: DataStore<Preferences> by preferencesDataStore(name = "theme")

class DataStoreRepository @Inject constructor(context: Context) {

    private object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(name = "on_boarding_completed")
        val theme = booleanPreferencesKey(name = "theme")
    }

    private val _onBoardingDataStore = context.onBoardingDataStore
    private val _theme = context.Theme

    suspend fun saveOnBoardingState(completed: Boolean) {
        _onBoardingDataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = completed
        }
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return _onBoardingDataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferencesKey.onBoardingKey] ?: false
                onBoardingState
            }
    }

    //Theme
    suspend fun saveThemeState(completed: Boolean) {
        _theme.edit { preferences ->
            preferences[PreferencesKey.theme] = completed
        }
    }

    fun readThemeState(): Flow<Boolean> {
        return _theme.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val theme = preferences[PreferencesKey.theme] ?: false
                theme
            }
    }
}