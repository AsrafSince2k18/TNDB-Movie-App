package com.example.movieshow.root.data.repository.onBoardImpl

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.movieshow.root.domain.onBoardRepo.OnBoardRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OnBoardRepoImpl(
    private val context: Context
) : OnBoardRepo {
    override suspend fun saveOnBoard() {
        context.dataStore.edit {
            it[Preference.PreferenceKey] = true
        }
    }

    override fun readOnBoard(): Flow<Boolean> {
        return context.dataStore.data.map {
            it[Preference.PreferenceKey] ?: false
        }
    }
}


private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name="read_onBoard")

private object Preference{
    val PreferenceKey = booleanPreferencesKey(name="save_onBoard")
}
