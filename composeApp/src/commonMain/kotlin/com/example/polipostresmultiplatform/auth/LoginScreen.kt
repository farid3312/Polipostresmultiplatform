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
fun LoginScreen(
    onLogin: (String, String) -> Boolean,
    onNavigateToRegister: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("PoliPostres", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = WhiteText)
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            "Bienvenido a PoliPostres",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = WhiteText
        )
        Spacer(modifier = Modifier.height(24.dp))

        PoliTextField(
            value = email,
            onValueChange = { email = it },
            label = "Correo"
        )
        Spacer(modifier = Modifier.height(16.dp))

        PoliTextField(
            value = password,
            onValueChange = { password = it },
            label = "Contraseña",
            isPassword = true,
            isVisible = passwordVisible,
            onVisibilityToggle = { passwordVisible = !passwordVisible }
        )
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                error = null
                val ok = onLogin(email.trim(), password)
                if (!ok) {
                    error = "Usuario no registrado o credenciales incorrectas"
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PinkAccent),
            shape = RoundedCornerShape(25.dp)
        ) {
            Text("→ Ingresar", color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        if (error != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = error!!,
                color = Color.Red,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = onNavigateToRegister) {
            Text("¿No tienes cuenta? Regístrate", color = GreyText)
        }
    }
}
