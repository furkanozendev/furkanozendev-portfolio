package com.furkanozendev.feature.portfolio.presentation.home.state

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class ShadeState {
    var progress by mutableStateOf(0f)
        private set

    private val maxDragDistance = 600f
    private val openThreshold = 80f
    private val resistance = 0.4f

    private var lockoutExpiration = 0L

    private var rawPullDistance by mutableStateOf(0f)

    fun reportContentScrolling() {
        val now = Clock.System.now().toEpochMilliseconds()
        lockoutExpiration = now + 300L
    }

    fun updatePull(delta: Float, isFling: Boolean) {
        if (isFling && progress == 0f) return

        if (progress == 0f) {
            val now = Clock.System.now().toEpochMilliseconds()
            if (now < lockoutExpiration) {
                return
            }
        }

        val dampenedDelta = delta * resistance
        rawPullDistance = (rawPullDistance + dampenedDelta).coerceAtLeast(0f)
        val effectiveDistance = (rawPullDistance - openThreshold).coerceAtLeast(0f)
        progress = (effectiveDistance / maxDragDistance).coerceIn(0f, 1f)
    }

    val nestedScrollConnection = object : NestedScrollConnection {
        override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
            if (rawPullDistance > 0f) {
                updatePull(available.y, source == NestedScrollSource.SideEffect)
                return if (rawPullDistance > 0f) Offset(0f, available.y) else Offset.Zero
            }
            return Offset.Zero
        }

        override fun onPostScroll(
            consumed: Offset,
            available: Offset,
            source: NestedScrollSource
        ): Offset {
            if (consumed.y != 0f) {
                reportContentScrolling()
            }

            if (available.y > 0) {
                updatePull(available.y, source == NestedScrollSource.SideEffect)
                return available
            }
            return Offset.Zero
        }
    }
}

@Composable
fun rememberShadeState(): ShadeState {
    return remember { ShadeState() }
}

fun Modifier.shadeTrigger(shadeState: ShadeState): Modifier {
    return this.pointerInput(Unit) {
        detectVerticalDragGestures { change, dragAmount ->
            if (dragAmount > 0 || shadeState.progress > 0f) {
                shadeState.nestedScrollConnection.onPostScroll(
                    consumed = Offset.Zero,
                    available = Offset(0f, dragAmount),
                    source = NestedScrollSource.UserInput
                )
                change.consume()
            }
        }
    }
}

fun Modifier.mouseWheelShadeTrigger(shadeState: ShadeState): Modifier {
    return this.pointerInput(Unit) {
        awaitPointerEventScope {
            while (true) {
                val event = awaitPointerEvent()

                if (event.type == PointerEventType.Scroll) {
                    val change = event.changes.firstOrNull()
                    val scrollDelta = change?.scrollDelta ?: Offset.Zero

                    val pixels = scrollDelta.y * -50f

                    if (pixels > 0) {
                        shadeState.updatePull(pixels, isFling = false)
                    } else if (pixels < 0 && shadeState.progress > 0f) {
                        shadeState.updatePull(pixels, isFling = false)
                    }
                }
            }
        }
    }
}

fun Modifier.smartMouseWheelTrigger(
    shadeState: ShadeState,
    scrollState: ScrollState
): Modifier {
    return this.pointerInput(Unit) {
        awaitPointerEventScope {
            while (true) {
                val event = awaitPointerEvent()

                if (event.type == PointerEventType.Scroll) {
                    val change = event.changes.firstOrNull() ?: continue
                    val delta = change.scrollDelta

                    val pixels = delta.y * -50f

                   if (shadeState.progress > 0f || (pixels > 0 && scrollState.value == 0)) {
                        shadeState.updatePull(pixels, isFling = false)
                    }
                }
            }
        }
    }
}