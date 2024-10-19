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
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pl.mrmatiasz.fitapp.presentation.EmailTextField
import pl.mrmatiasz.fitapp.presentation.FormTextField
import pl.mrmatiasz.fitapp.presentation.Logo
import pl.mrmatiasz.fitapp.presentation.PasswordTextField
import pl.mrmatiasz.fitapp.presentation.Separator
import pl.mrmatiasz.fitapp.presentation.navigation.LoginScreenRoute

@Composable
fun RegistrationScreen(
    navController: NavController
) {
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
            Logo()

            Spacer(modifier = Modifier.height(16.dp))

            Box(modifier = Modifier.fillMaxWidth()) {
                var username by remember { mutableStateOf("") }
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
                    FormTextField(
                        value = username,
                        onValueChange = { username = it },
                        placeholder = "Username",
                        isError = false,
                        errorMessage = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )

                    EmailTextField(
                        value = email,
                        onValueChange = {email = it},
                        placeholder = "Email",
                        isError = false,
                        errorMessage = "",
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
                        isError = false,
                        errorMessage = "",
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
                        isError = false,
                        errorMessage = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        shape = MaterialTheme.shapes.large,
                        onClick = { /*TODO*/ },
                        modifier = Modifier.size(128.dp, 42.dp)
                    ) {
                        Text(
                            text = "Sign up",
                            fontSize = 18.sp
                        )
                    }
                }
            }
            Separator()
            Button(
                shape = MaterialTheme.shapes.large,
                onClick = { navController.navigate(LoginScreenRoute) },
            ) {
                Text(
                    text = "I already have an account",
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegistrationScreenPreview() {
    TODO()
}