package pl.mrmatiasz.fitapp.presentation.components

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalorieBar(
    modifier: Modifier = Modifier,
    maxCalories: Int,
    currentCalories: Int,
) {
    var circleCenter by remember { mutableStateOf(Offset.Zero) }

    val remainingCalories by remember { mutableIntStateOf(maxCalories - currentCalories) }

    Box(modifier = modifier) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            val width = size.width
            val height = size.height
            circleCenter = Offset(width / 2f, height / 2f)

            val strokeWidth = size.minDimension * 0.1f
            val textSizeCalories = size.minDimension * 0.15f
            val textSizeRemaining = size.minDimension * 0.12f
            val textSpacing = textSizeCalories * 0.8f

            drawCircle(
                color = Color.LightGray,
                radius = (size.minDimension - strokeWidth) / 2f,
                center = center,
                style = Stroke(
                    width = strokeWidth
                )
            )

            drawContext.canvas.nativeCanvas.apply {
                drawIntoCanvas {
                    drawText(
                        "$remainingCalories cals",
                        circleCenter.x,
                        circleCenter.y,
                        Paint().apply {
                            color = Color.White.toArgb()
                            textAlign = Paint.Align.CENTER
                            textSize = textSizeCalories
                            isFakeBoldText = true
                        }
                    )

                    drawText(
                        "Remaining",
                        circleCenter.x,
                        circleCenter.y + textSpacing,
                        Paint().apply {
                            color = Color.LightGray.toArgb()
                            textAlign = Paint.Align.CENTER
                            textSize = textSizeRemaining
                            isFakeBoldText = true
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CalorieBarPrev() {
    CalorieBar(
        modifier = Modifier.size(150.dp),
        maxCalories = 3000,
        currentCalories = 900
    )
}