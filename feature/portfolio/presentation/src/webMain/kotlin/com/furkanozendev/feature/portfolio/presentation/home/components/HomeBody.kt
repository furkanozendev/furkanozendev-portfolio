package com.furkanozendev.feature.portfolio.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeBody(
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(modifier = modifier) {
        val isDesktop = maxWidth > 800.dp
        val scrollState = rememberScrollState()

        if (isDesktop) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(52.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(0.4f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ProfileWidget(modifier = Modifier.weight(1f).fillMaxWidth())
                    TechStackWidget(modifier = Modifier.weight(1f).fillMaxWidth())
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
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ProfileWidget(modifier = Modifier.height(300.dp).fillMaxWidth())
                TechStackWidget(modifier = Modifier.height(250.dp).fillMaxWidth())
                ExperienceWidget(modifier = Modifier.height(400.dp).fillMaxWidth())
                ProjectsWidget(modifier = Modifier.height(200.dp).fillMaxWidth())
            }
        }
    }
}
