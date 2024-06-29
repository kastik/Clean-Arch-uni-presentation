package com.learningwithmanos.uniexercise.ui.SettingsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learningwithmanos.uniexercise.AppPreferences
import com.learningwithmanos.uniexercise.heroes.usecase.SettingsScreenUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsScreenVIewModel @Inject constructor(
    private val settingApi: SettingsScreenUC,
) : ViewModel() {

    private val _privateKeyStateFlow = MutableStateFlow(AppPreferences.privatekey.toString())
    private val _apiKeyStateFlow = MutableStateFlow(AppPreferences.apikey.toString())

    val apiKeyStateFlow: StateFlow<String> = _apiKeyStateFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = _apiKeyStateFlow.value
    )

    val privateKeyStateFlow: StateFlow<String> = _privateKeyStateFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = _privateKeyStateFlow.value
    )

    val isButtonEnabledStateFlow: StateFlow<Boolean> = combine(_privateKeyStateFlow, _apiKeyStateFlow) {
            privateKey, apiKey ->
        (privateKey.isNotBlank()) && (apiKey.isNotBlank())
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = false
    )

    fun updateApi(apikey: String, privatekey: String) {
        viewModelScope.launch {
            settingApi.updateApi(apikey, privatekey)
        }
    }

    fun updateApiKey(apiKey: String) {
        _apiKeyStateFlow.update {
            apiKey
        }
    }

    fun updatePrivateKey(privatekey: String) {
        _privateKeyStateFlow.update {
            privatekey
        }
    }


}