package pl.mrmatiasz.fitapp.presentation.screens.registration_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.mrmatiasz.fitapp.presentation.EmailTextField
import pl.mrmatiasz.fitapp.presentation.PasswordTextField

@Composable
fun RegistrationScreen() {
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Account circle",
                modifier = Modifier.size(150.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                var passwordVisibility by remember { mutableStateOf(false) }
                var repeatPassword by remember { mutableStateOf("") }
                var repeatPasswordVisibility by remember { mutableStateOf(false) }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    EmailTextField(
                        value = email,
                        onValueChange = {email = it},
                        placeholder = "Email",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )

                    PasswordTextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = "Password",
                        isVisible = passwordVisibility,
                        onIconClick = { passwordVisibility = !passwordVisibility },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )

                    PasswordTextField(
                        value = repeatPassword,
                        onValueChange = { repeatPassword = it },
                        placeholder = "Repeat password",
                        isVisible = repeatPasswordVisibility,
                        onIconClick = { repeatPasswordVisibility = !repeatPasswordVisibility },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )

                    Button(
                        onClick = { /*TODO*/ }
                    ) {
                        Text(text = "Sign up")
                    }

                }
            }

            Spacer(modifier = Modifier.height(64.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegistrationScreenPreview() {
    RegistrationScreen()
}