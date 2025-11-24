package com.example.polipostresmultiplatform.info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.polipostresmultiplatform.theme.PinkAccent
import com.example.polipostresmultiplatform.theme.SurfaceColor
import com.example.polipostresmultiplatform.theme.WhiteText
import com.example.polipostresmultiplatform.theme.GreyText

@Composable
fun CreditsScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            TextButton(onClick = onBack) {
                Text("< Back", color = PinkAccent)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Créditos", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.DarkGray),
            contentAlignment = Alignment.Center
        ) {
            Text("Foto del Equipo", color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text("Universidad del Cauca", color = GreyText)
        Text("Departamento de Telemática", color = GreyText)
        Text("Asignatura: Electiva Desarrollo de Apps Móviles", color = GreyText, modifier = Modifier.padding(horizontal = 24.dp))

        Spacer(modifier = Modifier.height(24.dp))
        Text("Estudiantes", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start))
        Spacer(modifier = Modifier.height(16.dp))

        val students = listOf("Gustavo Sandoval", "Farid Carvajal", "Fabio Valencia")
        students.forEach { name ->
            Card(
                colors = CardDefaults.cardColors(containerColor = SurfaceColor),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            ) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Person, contentDescription = null, tint = WhiteText)
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(name, color = WhiteText, fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}