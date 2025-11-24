package com.example.polipostresmultiplatform.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
fun RegisterScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Crear cuenta", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = WhiteText)
        Spacer(modifier = Modifier.height(24.dp))

        PoliTextField(value = "", onValueChange = {}, label = "Nombre")
        Spacer(modifier = Modifier.height(12.dp))
        PoliTextField(value = "", onValueChange = {}, label = "Correo")
        Spacer(modifier = Modifier.height(12.dp))
        PoliTextField(value = "", onValueChange = {}, label = "Contraseña", isPassword = true)
        Spacer(modifier = Modifier.height(12.dp))
        PoliTextField(value = "", onValueChange = {}, label = "Confirmar contraseña", isPassword = true)
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth().height(50.dp),
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