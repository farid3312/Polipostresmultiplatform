package com.example.polipostresmultiplatform.dashboard
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.polipostresmultiplatform.model.Dessert
import com.example.polipostresmultiplatform.theme.PinkAccent
import com.example.polipostresmultiplatform.theme.SurfaceColor
import com.example.polipostresmultiplatform.theme.WhiteText
import com.example.polipostresmultiplatform.theme.GreyText
import com.example.polipostresmultiplatform.ui.components.PoliTextField

@Composable
@Composable
fun InventoryScreen(
    desserts: List<Dessert>,
    onAdd: (Dessert) -> Unit,
    onDelete: (String) -> Unit
) {
    var newName by remember { mutableStateOf("") }
    var newStock by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = SurfaceColor),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Añadir postre", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(16.dp))
                    PoliTextField(
                        value = newName,
                        onValueChange = { newName = it },
                        label = "Nombre del postre"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    PoliTextField(
                        value = newStock,
                        onValueChange = { newStock = it },
                        label = "Stock inicial",
                        keyboardType = KeyboardType.Number
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Image, contentDescription = null, tint = GreyText)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Seleccionar imagen", color = WhiteText)
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (newName.isNotEmpty() && newStock.isNotEmpty()) {
                                onAdd(
                                    Dessert(
                                        id = "${System.currentTimeMillis()}",
                                        name = newName,
                                        stock = newStock.toIntOrNull() ?: 0
                                    )
                                )
                                newName = ""
                                newStock = ""
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PinkAccent.copy(alpha = 0.7f),
                            contentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("+ Guardar")
                    }
                }
            }

            Text(
                "Postres guardados",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        items(desserts) { dessert ->
            Card(
                colors = CardDefaults.cardColors(containerColor = SurfaceColor),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.Gray)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Image,
                            contentDescription = null,
                            modifier = Modifier.align(Alignment.Center),
                            tint = Color.DarkGray
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(dessert.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text("Stock: ${dessert.stock}", color = GreyText, fontSize = 14.sp)
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.AddCircle, contentDescription = "Add", tint = GreyText)
                    }
                    IconButton(onClick = { onDelete(dessert.id) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color(0xFFE57373))
                    }
                }
            }
        }
    }
}

