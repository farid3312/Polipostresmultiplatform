package com.example.polipostresmultiplatform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.polipostresmultiplatform.data.InMemoryDessertRepository
import com.example.polipostresmultiplatform.data.InMemoryUserRepository
import com.example.polipostresmultiplatform.data.InMemorySessionRepository

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Creamos las implementaciones en memoria
        val dessertRepository = InMemoryDessertRepository()
        val userRepository = InMemoryUserRepository()
        val sessionRepository = InMemorySessionRepository()
        //  Farid es un sapo desgraciado

        setContent {
            App(
                dessertRepository = dessertRepository,
                userRepository = userRepository,
                sessionRepository = sessionRepository,
                onSelectImage = {
                    // Aquí va tu lógica de selección de imagen si la tienes
                }
            )
        }
    }
}
