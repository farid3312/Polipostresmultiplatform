package com.example.polipostresmultiplatform.data

import com.example.polipostresmultiplatform.model.Dessert
import com.example.polipostresmultiplatform.model.User

interface DessertRepository {
    fun getAllDesserts(): List<Dessert>
    fun insertDessert(dessert: Dessert)
    fun updateDessert(dessert: Dessert)
    fun deleteDessertById(id: String)
}

interface UserRepository {
    fun findByEmailAndPassword(email: String, password: String): User?
    fun existsByEmail(email: String): Boolean
    fun insertUser(user: User)
    fun findByEmail(email: String): User?
}

interface SessionRepository {
    fun getLoggedInUserEmail(): String?
    fun setLoggedInUserEmail(email: String)
    fun clearSession()
}
