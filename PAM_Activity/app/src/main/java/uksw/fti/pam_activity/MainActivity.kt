package edu.uksw.fti.pam.pam_activityintent

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uksw.fti.pam_activity.ui.theme.PAM_ActivityTheme
import uksw.fti.pam_activity.HomeActivity
import uksw.fti.pam_activity.ui.theme.PAM_ActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PAM_ActivityTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginForm()
                }
            }
        }
    }
}

internal fun doAuth(
    username: String,
    password: String,
): Boolean {
    return (username.equals("admin") && password.equals("admin"))
}

@Composable
fun LoginForm() {
    val lContext = LocalContext.current

    var usernameInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        TextField(
            value = usernameInput,
            onValueChange = { usernameInput = it },
            label = { Text(text = "Username")},
            modifier = Modifier.fillMaxWidth(),
        )
        TextField(
            value = passwordInput,
            onValueChange = { passwordInput = it },
            label = { Text(text = "Password")},
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
        )
        Button(
            onClick = {
                val isAuthenticated = doAuth(usernameInput, passwordInput)
                if (isAuthenticated)
                    Intent(lContext, HomeActivity::class.java)
                        .apply { putExtra("username", usernameInput)
                        }
            }
        ) {
            Text(text = "Login")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun LoginFormPreview() {
    PAM_ActivityTheme() {
        LoginForm()
    }
}