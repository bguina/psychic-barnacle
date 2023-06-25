package com.bguina.eurosport.test.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.bguina.eurosport.test.presentation.ui.MainScreen
import com.bguina.eurosport.test.presentation.ui.theme.EurosportTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EurosportTheme {
                MainScreen()
            }
        }
    }
}
