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

class ActivityB : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyLifeCycleAppTheme {
                ActivityScreen("Activity B") {
                    setResult(RESULT_OK, Intent().putExtra(MainActivity.RESULT_FROM_ACTIVITY_KEY, MainActivity.FROM_ACTIVITY_B))
                    finish()
                }
            }
        }
    }
}

@Composable
fun ActivityScreen(title: String, onFinish: () -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = title)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onFinish) {
                Text("Finish")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActivityBPreview() {
    MyLifeCycleAppTheme {
        ActivityScreen("Activity B") {}
    }
}
