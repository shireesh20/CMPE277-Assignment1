package com.example.mylifecycleapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mylifecycleapp.ui.theme.MyLifeCycleAppTheme

class ActivityC : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyLifeCycleAppTheme {
                ActivityScreen("Activity C") {
                    setResult(RESULT_OK, Intent().putExtra(MainActivity.RESULT_FROM_ACTIVITY_KEY, MainActivity.FROM_ACTIVITY_C))
                    finish()
                }
            }
        }
    }
}

// The ActivityScreen composable is the same as used in ActivityB.
// You can keep this composable in a common file to be shared between ActivityB and ActivityC if you prefer.

@Preview(showBackground = true)
@Composable
fun ActivityCPreview() {
    MyLifeCycleAppTheme {
        ActivityScreen("Activity C") {}
    }
}
