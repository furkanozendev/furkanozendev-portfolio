package com.furkanozendev.feature.portfolio.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.furkanozendev.feature.portfolio.presentation.home.state.ShadeState
import com.furkanozendev.feature.portfolio.presentation.home.state.mouseWheelShadeTrigger
import com.furkanozendev.feature.portfolio.presentation.home.state.shadeTrigger
import com.furkanozendev.feature.portfolio.presentation.home.state.smartMouseWheelTrigger

@Composable
fun HomeBody(
    modifier: Modifier = Modifier,
    shadeState: ShadeState
) {
    BoxWithConstraints(modifier = modifier) {
        val isWideLayout = maxWidth > 800.dp

        if (isWideLayout) {
            Column(
                modifier = Modifier
                    .shadeTrigger(shadeState)
                    .mouseWheelShadeTrigger(shadeState)
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(52.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Column(
                        modifier = Modifier
                            .weight(0.4f)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        ProfileWidget(modifier = Modifier.fillMaxWidth())
                        AppCell(modifier = Modifier.fillMaxWidth())
                        TechStackWidget(modifier = Modifier.fillMaxWidth())
                    }

                    Column(
                        modifier = Modifier
                            .weight(0.6f)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        ExperienceWidget(modifier = Modifier.weight(0.65f).fillMaxWidth())
                        ProjectsWidget(modifier = Modifier.weight(0.35f).fillMaxWidth())
                    }
                }
            }
        } else {
            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .smartMouseWheelTrigger(shadeState, scrollState)
                    .verticalScroll(scrollState)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ProfileWidget(modifier = Modifier.fillMaxWidth())
                AppCell(modifier = Modifier.fillMaxWidth())
                ExperienceWidget(modifier = Modifier.fillMaxWidth())
                TechStackWidget(modifier = Modifier.fillMaxWidth())
                ProjectsWidget(modifier = Modifier.height(200.dp).fillMaxWidth())
            }
        }
    }
}
