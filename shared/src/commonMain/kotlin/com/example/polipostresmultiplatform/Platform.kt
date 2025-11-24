package com.example.polipostresmultiplatform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform