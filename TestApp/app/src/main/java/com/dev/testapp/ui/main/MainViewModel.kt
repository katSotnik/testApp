package com.dev.testapp.ui.main

import androidx.lifecycle.viewModelScope
import com.dev.testapp.data.enums.EActionType
import com.dev.testapp.domain.usecases.MainUseCase
import com.dev.testapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainUseCase: MainUseCase
) : BaseViewModel() {

    init {
        viewModelScope.launch {
            mainUseCase.getSettings()
        }
    }

    fun checkIsEnabled(type: EActionType): Boolean {
        return mainUseCase.checkIsEnabled(type)
    }

}