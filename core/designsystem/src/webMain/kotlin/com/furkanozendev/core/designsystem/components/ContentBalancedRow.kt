package com.furkanozendev.core.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ContentBalancedRow(
    modifier: Modifier = Modifier,
    gap: Dp = 0.dp,
    content1: @Composable () -> Unit,
    content2: @Composable () -> Unit
) {
    SubcomposeLayout(modifier = modifier) { constraints ->
        val maxWidth = constraints.maxWidth
        if (maxWidth == Constraints.Infinity || maxWidth <= 0) {
            val p1 = subcompose("fallback1", content1).first().measure(Constraints())
            val p2 = subcompose("fallback2", content2).first().measure(Constraints())
            val h = maxOf(p1.height, p2.height).coerceAtLeast(1)
            return@SubcomposeLayout layout(p1.width + p2.width, h) {
                p1.place(0, 0)
                p2.place(p1.width, 0)
            }
        }

        val gapPx = gap.roundToPx().coerceAtLeast(0)
        val availableWidth = (maxWidth - gapPx).coerceAtLeast(0)

        val probeConstraints = Constraints(maxWidth = availableWidth / 2)
        val probe1 = subcompose("probe1", content1).first().measure(probeConstraints)
        val probe2 = subcompose("probe2", content2).first().measure(probeConstraints)

        val totalH = probe1.height + probe2.height
        val weight1 = if (totalH == 0) 0.5f else probe1.height.toFloat() / totalH
        val w1Ratio = weight1.coerceIn(0.35f, 0.65f)

        val w1 = (availableWidth * w1Ratio).toInt().coerceAtLeast(1)
        val w2 = (availableWidth - w1).coerceAtLeast(1)

        val natural1 = subcompose("natural1", content1).first()
            .measure(Constraints.fixedWidth(w1))
        val natural2 = subcompose("natural2", content2).first()
            .measure(Constraints.fixedWidth(w2))

        val maxH = maxOf(natural1.height, natural2.height).coerceAtLeast(1)

        val finalConstraints1 = Constraints.fixed(w1, maxH)
        val finalConstraints2 = Constraints.fixed(w2, maxH)

        val final1 = subcompose("final1") {
            Box(Modifier.fillMaxSize()) { content1() }
        }.first().measure(finalConstraints1)

        val final2 = subcompose("final2") {
            Box(Modifier.fillMaxSize()) { content2() }
        }.first().measure(finalConstraints2)

        layout(maxWidth, maxH) {
            final1.place(0, 0)
            final2.place(w1 + gapPx, 0)
        }
    }
}