package com.example.polipostresmultiplatform.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.polipostresmultiplatform.theme.PinkAccent
import com.example.polipostresmultiplatform.theme.WhiteText
import com.example.polipostresmultiplatform.theme.GreyText
import com.example.polipostresmultiplatform.ui.components.PoliTextField

@Composable
fun RegisterScreen(
    onRegister: (String, String, String) -> Unit,
    onBack: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Crear cuenta",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = WhiteText
        )
        Spacer(modifier = Modifier.height(24.dp))

        PoliTextField(
            value = name,
            onValueChange = { name = it },
            label = "Nombre"
        )
        Spacer(modifier = Modifier.height(12.dp))

        PoliTextField(
            value = email,
            onValueChange = { email = it },
            label = "Correo"
        )
        Spacer(modifier = Modifier.height(12.dp))

        PoliTextField(
            value = password,
            onValueChange = { password = it },
            label = "Contraseña",
            isPassword = true,
            isVisible = passwordVisible,
            onVisibilityToggle = { passwordVisible = !passwordVisible }
        )
        Spacer(modifier = Modifier.height(12.dp))

        PoliTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = "Confirmar contraseña",
            isPassword = true,
            isVisible = confirmPasswordVisible,
            onVisibilityToggle = { confirmPasswordVisible = !confirmPasswordVisible }
        )
        Spacer(modifier = Modifier.height(24.dp))

        if (error != null) {
            Text(
                text = error!!,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }

        Button(
            onClick = {
                val nameTrim = name.trim()
                val emailTrim = email.trim()
                error = when {
                    nameTrim.isEmpty() || emailTrim.isEmpty() ||
                            password.isEmpty() || confirmPassword.isEmpty() ->
                        "Todos los campos son obligatorios"

                    password != confirmPassword ->
                        "Las contraseñas no coinciden"

                    else -> {
                        // REGISTRA EL USUARIO
                        onRegister(nameTrim, emailTrim, password)
                        // Vuelve a la pantalla de login
                        onBack()
                        null
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PinkAccent),
            shape = RoundedCornerShape(25.dp)
        ) {
            Text("Registrarme", color = Color.Black)
        }

        TextButton(onClick = onBack) {
            Text("¿Ya tienes cuenta? Inicia sesión", color = GreyText)
        }
    }
}
