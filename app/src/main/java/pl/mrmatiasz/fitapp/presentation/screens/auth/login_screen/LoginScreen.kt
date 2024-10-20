package pl.mrmatiasz.fitapp.presentation.screens.auth.login_screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import pl.mrmatiasz.fitapp.presentation.EmailTextField
import pl.mrmatiasz.fitapp.presentation.Logo
import pl.mrmatiasz.fitapp.presentation.PasswordTextField
import pl.mrmatiasz.fitapp.presentation.Separator
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import pl.mrmatiasz.fitapp.presentation.navigation.RegistrationScreenRoute
import pl.mrmatiasz.fitapp.presentation.screens.auth.FormEvent

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
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
                val formState = viewModel.formState
                val loginState = viewModel.loginState.collectAsState()

                var passwordVisibility by remember { mutableStateOf(false) }
                var checked by remember { mutableStateOf(false) }

                val context = LocalContext.current

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    EmailTextField(
                        value = formState.email,
                        onValueChange = { viewModel.onEvent(FormEvent.EmailChanged(it)) },
                        placeholder = "Email",
                        isError = formState.emailError != null,
                        errorMessage = formState.emailError.toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )

                    PasswordTextField(
                        value = formState.password,
                        onValueChange = { viewModel.onEvent(FormEvent.PasswordChanged(it)) },
                        placeholder = "Password",
                        isVisible = passwordVisibility,
                        onIconClick = { passwordVisibility = !passwordVisibility },
                        isError = formState.passwordError != null,
                        errorMessage = formState.passwordError.toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    ) {
                        Checkbox(
                            checked = checked,
                            onCheckedChange = { checked = !checked }
                        )
                        Text(
                            text = "Keep me logged in.",
                            modifier = Modifier.weight(1f)
                        )

                        Text(
                            text = "Forgot password?",
                            modifier = Modifier.clickable {
                                TODO("Password recovery system or screen")
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        shape = MaterialTheme.shapes.large,
                        onClick = { viewModel.onEvent(FormEvent.Submit) },
                        modifier = Modifier.size(128.dp, 42.dp)
                    ) {
                        Text(
                            text = "Login",
                            fontSize = 18.sp
                        )
                    }

                    LaunchedEffect(key1 = loginState.value.isSuccessful) {
                        if(loginState.value.isSuccessful?.isNotBlank() == true) {
                            val success = loginState.value.isSuccessful
                            Toast.makeText(context, "$success", Toast.LENGTH_SHORT).show()
                        }
                    }

                    LaunchedEffect(key1 = loginState.value.isError) {
                        if(loginState.value.isError?.isNotBlank() == true) {
                            val error = loginState.value.isError
                            Toast.makeText(context, "$error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            Separator()
            Button(
                shape = MaterialTheme.shapes.large,
                onClick = { navController.navigate(RegistrationScreenRoute) },
            ) {
                Text(
                    "Don't have an account? Sign up",
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen(navController = NavController(LocalContext.current))
}