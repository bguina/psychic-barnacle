package com.bguina.eurosport.test.presentation

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.bguina.eurosport.test.presentation.ext.hideSystemUi
import com.bguina.eurosport.test.presentation.ext.showSystemUi
import com.bguina.eurosport.test.presentation.ui.MainScreen
import com.bguina.eurosport.test.presentation.ui.theme.EurosportTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        setContent {
            EurosportTheme {
                MainScreen(
                    onLandscapeOrientationRequested = { landscapeRequested ->
                        if (landscapeRequested) {
                            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                            hideSystemUi()
                        } else {
                            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                            showSystemUi()
                        }
                    },
                )
            }
        }
    }
}
