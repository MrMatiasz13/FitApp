package pl.mrmatiasz.fitapp.presentation.screens.auth.registration_screen

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import pl.mrmatiasz.fitapp.presentation.components.EmailTextField
import pl.mrmatiasz.fitapp.presentation.components.FormTextField
import pl.mrmatiasz.fitapp.presentation.components.Logo
import pl.mrmatiasz.fitapp.presentation.components.PasswordTextField
import pl.mrmatiasz.fitapp.presentation.components.Separator
import pl.mrmatiasz.fitapp.presentation.navigation.LoginScreenRoute
import pl.mrmatiasz.fitapp.presentation.screens.auth.FormEvent

@Composable
fun RegistrationScreen(
    navController: NavController,
    viewModel: RegistrationViewModel = hiltViewModel()
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
                val registrationState = viewModel.registrationState.collectAsState()

                var passwordVisibility by remember { mutableStateOf(false) }
                var confirmPasswordVisibility by remember { mutableStateOf(false) }

                val context = LocalContext.current

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    FormTextField(
                        value = formState.username,
                        onValueChange = { viewModel.onEvent(FormEvent.UsernameChanged(it)) },
                        placeholder = "Username",
                        isError = formState.usernameError != null,
                        errorMessage = formState.usernameError.toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )

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

                    PasswordTextField(
                        value = formState.confirmPassword,
                        onValueChange = { viewModel.onEvent(FormEvent.ConfirmPasswordChanged(it)) },
                        placeholder = "Confirm password",
                        isVisible = confirmPasswordVisibility,
                        onIconClick = { confirmPasswordVisibility = !confirmPasswordVisibility },
                        isError = formState.confirmPasswordError != null,
                        errorMessage = formState.confirmPasswordError.toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        shape = MaterialTheme.shapes.large,
                        onClick = { viewModel.onEvent(FormEvent.Submit) },
                        modifier = Modifier.size(128.dp, 42.dp)
                    ) {
                        Text(
                            text = "Sign up",
                            fontSize = 18.sp
                        )
                    }

                    LaunchedEffect(key1 = registrationState.value.isSuccessful) {
                        if(registrationState.value.isSuccessful?.isNotBlank() == true) {
                            val success = registrationState.value.isSuccessful
                            Toast.makeText(context, "$success", Toast.LENGTH_SHORT).show()
                        }
                    }

                    LaunchedEffect(key1 = registrationState.value.isError) {
                        if(registrationState.value.isError?.isNotBlank() == true) {
                            val error = registrationState.value.isError
                            Toast.makeText(context, "$error", Toast.LENGTH_SHORT).show()
                        }
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