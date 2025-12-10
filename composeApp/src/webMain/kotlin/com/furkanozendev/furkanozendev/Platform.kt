package com.furkanozendev.furkanozendev

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform