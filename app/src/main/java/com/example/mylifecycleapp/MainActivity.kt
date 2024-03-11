package com.example.mylifecycleapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.mylifecycleapp.ui.theme.MyLifeCycleAppTheme

class MainActivity : ComponentActivity() {
    private var restartCounter by mutableStateOf(0)
    private var showDialog by mutableStateOf(false)
    private var lastActivity: String? = null

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                // Get the identifier of the last activity from the result
                lastActivity = result.data?.getStringExtra(RESULT_FROM_ACTIVITY_KEY)
            }
        }

        setContent {
            MyLifeCycleAppTheme {
                MainScreen(restartCounter, showDialog) { showDialog = it }
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        // Check the lastActivity and increment restartCounter accordingly
        when (lastActivity) {
            FROM_ACTIVITY_B -> restartCounter += 5
            FROM_ACTIVITY_C -> restartCounter += 10
        }
        // Reset lastActivity to avoid repeated increments
        lastActivity = null
    }

    companion object {
        const val RESULT_FROM_ACTIVITY_KEY = "result_from_activity_key"
        const val FROM_ACTIVITY_B = "from_activity_b"
        const val FROM_ACTIVITY_C = "from_activity_c"
    }
}

@Composable
fun MainScreen(restartCounter: Int, showDialog: Boolean, onShowDialogChange: (Boolean) -> Unit) {
    val context = LocalContext.current
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Activity App",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "THREAD COUNTER: $restartCounter",
                style = MaterialTheme.typography.bodyLarge
            )
            Button(
                onClick = {
                    val intent = Intent(context, ActivityB::class.java)
                    resultLauncher.launch(intent)
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("ACTIVITY B")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val intent = Intent(context, ActivityC::class.java)
                    resultLauncher.launch(intent)
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("ACTIVITY C")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { onShowDialogChange(true) },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("DIALOG")
            }
            if (showDialog) {
                SampleDialog(onDismissRequest = { onShowDialogChange(false) })
            }
        }
    }
}

@Composable
fun SampleDialog(onDismissRequest: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            Button(onClick = onDismissRequest) {
                Text("Ok")
            }
        },
        text = {
            Text("Text Dialog")
        }
    )
}
