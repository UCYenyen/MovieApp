package com.example.movieappnew.ui.view


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun RegisterView() {
    var name by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    var showDialogue by rememberSaveable { mutableStateOf(false) }

    var formValid = isValidName(name) && isValidEmail(email) && isValidPassword(password)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Account Register",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {

            Text(text = "Name", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),

                shape = RoundedCornerShape(12.dp),
                value = name,
                onValueChange = { name = it },
                placeholder = { Text("Input name") },
                isError = !isValidName(name) && name.isNotEmpty(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF5F5F5),
                    unfocusedContainerColor = Color(0xFFF5F5F5),
                    unfocusedIndicatorColor = Color.Gray.copy(alpha = 0.3f),
                    focusedIndicatorColor = Color.Gray.copy(alpha = 1f),
                )
            )

            Text(text = "Email", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),

                shape = RoundedCornerShape(12.dp),
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Input email") },
                isError = !isValidEmail(email) && email.isNotEmpty(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF5F5F5),
                    unfocusedContainerColor = Color(0xFFF5F5F5),
                    unfocusedIndicatorColor = Color.Gray.copy(alpha = 0.3f),
                    focusedIndicatorColor = Color.Gray.copy(alpha = 1f),
                )
            )

            Text(text = "Password", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),

                shape = RoundedCornerShape(12.dp),
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Input password") },
                isError = !isValidPassword(password) && password.isNotEmpty(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF5F5F5),
                    unfocusedContainerColor = Color(0xFFF5F5F5),
                    unfocusedIndicatorColor = Color.Gray.copy(alpha = 0.3f),
                    focusedIndicatorColor = Color.Gray.copy(alpha = 1f),
                ),
                visualTransformation =
                    if (passwordVisible) VisualTransformation.None
                    else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible)
                                Icons.Default.Visibility
                            else
                                Icons.Default.VisibilityOff,
                            contentDescription = "Password Visibility"
                        )
                    }
                }
            )

            Button(
                onClick = { showDialogue = true },
                modifier = Modifier.fillMaxWidth(),
                enabled = formValid,
            ) { Text("Submit") }

            if(showDialogue){
                AlertDialog(
                    onDismissRequest = { showDialogue = false },
                    title = { Text(text = if (isFormValid(name, email, password)) "Success" else "Error") },
                    text = {
                        Text(
                            text = if (isFormValid(name, email, password)) "Register Success"
                            else "Please correct the errors in the form"
                        )
                    },
                    confirmButton = {
                        Button(
                            onClick = { showDialogue = false }
                        ) {
                            Text("OK")
                        }
                    }
                )
            }
        }
    }
}

private fun isFormValid(name: String, email: String, password: String): Boolean {
    return isValidName(name) && isValidEmail(email) && isValidPassword(password)
}

fun isValidName(name: String): Boolean {
    return name.isNotBlank() && name.length > 3
}

fun isValidEmail(email: String): Boolean {
    return email.trim().matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
}

fun isValidPassword(password: String): Boolean {
    return password.length >= 6
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun RegisterViewPreview() {
    RegisterView()
}