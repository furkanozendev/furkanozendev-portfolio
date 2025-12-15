package com.furkanozendev.feature.boot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlin.random.Random
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class BootViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(BootUiState())
    val uiState: StateFlow<BootUiState> = _uiState.asStateFlow()

    fun startBoot() {
        viewModelScope.launch {
            var currentProgress = 0f

            while (currentProgress < 100f) {
                delay(Random.nextLong(50, 150))

                currentProgress += Random.nextInt(1, 6)

                if (currentProgress > 100f) currentProgress = 100f

                _uiState.update { it.copy(bootProgress = currentProgress) }
            }

            delay(200)
            navigatePortfolio()
        }
    }

    private fun navigatePortfolio() {
        // DONT EDIT HERE
    }
}