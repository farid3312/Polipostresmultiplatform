package com.example.polipostresmultiplatform.data

import com.example.polipostresmultiplatform.model.Dessert
import com.example.polipostresmultiplatform.model.User

class InMemoryDessertRepository : DessertRepository {

    private val desserts = mutableListOf<Dessert>()

    override fun getAllDesserts(): List<Dessert> {
        // Devolvemos una copia para que no se pueda modificar desde fuera
        return desserts.toList()
    }

    override fun insertDessert(dessert: Dessert) {
        desserts.add(dessert)
    }

    override fun updateDessert(dessert: Dessert) {
        val index = desserts.indexOfFirst { it.id == dessert.id }
        if (index != -1) {
            desserts[index] = dessert
        }
    }

    override fun deleteDessertById(id: String) {
        desserts.removeAll { it.id == id }
    }
}

class InMemoryUserRepository : UserRepository {

    private val users = mutableListOf<User>()

    override fun findByEmailAndPassword(email: String, password: String): User? {
        val emailNorm = email.trim().lowercase()
        return users.firstOrNull {
            it.email.trim().lowercase() == emailNorm &&
                    it.password == password
        }
    }

    override fun existsByEmail(email: String): Boolean {
        val emailNorm = email.trim().lowercase()
        return users.any {
            it.email.trim().lowercase() == emailNorm
        }
    }

    override fun insertUser(user: User) {
        // No insertamos si ya existe ese correo
        if (!existsByEmail(user.email)) {
            users.add(user)
        }
    }

    override fun findByEmail(email: String): User? {
        val emailNorm = email.trim().lowercase()
        return users.firstOrNull {
            it.email.trim().lowercase() == emailNorm
        }
    }
}

class InMemorySessionRepository : SessionRepository {

    // Muy simple: solo guardamos el email del usuario logueado
    private var currentEmail: String? = null

    override fun getLoggedInUserEmail(): String? = currentEmail

    override fun setLoggedInUserEmail(email: String) {
        currentEmail = email
    }

    override fun clearSession() {
        currentEmail = null
    }
}
