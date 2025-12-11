package com.furkanozendev.feature.boot

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.furkanozendev.boot.generated.resources.Res
import com.furkanozendev.boot.generated.resources.android
import com.furkanozendev.core.designsystem.colors.BootColors
import com.furkanozendev.feature.boot.components.AndroidBootProgress
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BootScreen() {
    val viewModel: BootViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.startBoot()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BootColors.Background),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier.size(200.dp),
                painter = painterResource(Res.drawable.android),
                contentDescription = "Android Image"
            )

            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = "Rebooting...",
                color = BootColors.Text
            )

            AndroidBootProgress(
                modifier = Modifier.width(300.dp),
                progress = uiState.bootProgress,
            )
        }
    }
}
