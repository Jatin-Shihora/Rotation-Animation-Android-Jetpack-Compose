// Code for developing rotation animation in android jetpack compose

// Please replace the name of package with your project name
package com.example.rotationanimation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class Animation1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This sets the @Composable function as the root view of the activity.
        // This is meant to replace the .xml file that we would typically
        // set using the setContent(R.id.xml_file) method.
        setContent {
            RotatingSquareComponent()
        }
    }
}

@Composable
fun RotatingSquareComponent() {
    // Column with clickable modifier wraps the child composable
    // and enables it to react to a
    // click through the onClick callback similar to
    // the onClick listener that we are accustomed
    // to on Android.
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            // rememberInfiniteTransition is utilized for creating
            // infinite transition in child animations
            // Animations usually gets invoked automatically
            // once they enter in a composable.
            val infiniteTransition = rememberInfiniteTransition()

            // Create a value that is altered by the transition based on the
            // configuration. We use the animated float value the returns and
            // updates a float from the initial value to
            // target value and repeats it
            // (as its called on the infititeTransition).
            val rotation by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween<Float>(
                        durationMillis = 3000,
                        easing = FastOutLinearInEasing,
                    ),
                )
            )

            // In this example, we assign a size
            // of 250dp to the Canvas using
            // Modifier.preferredSize(250.dp).
            Canvas(modifier = Modifier.size(250.dp)) {
                // As the Transition is changing the interpolating the value
                // of the animated float "rotation", you get access to all
                // the values including the intermediate values as
                // its  being updated. The value of "rotation" goes from
                // 0 to 360 and transitions infinitely due to the
                // infiniteRepetable animationSpec used above.
                rotate(rotation) {
                    drawRect(color = Color(50, 205, 50))
                }
            }
        })
}

/**
 * Combined significance of Preview & Composable annotations
 */
@Preview
@Composable
fun RotatingSquareComponentPreview() {
    RotatingSquareComponent()
}