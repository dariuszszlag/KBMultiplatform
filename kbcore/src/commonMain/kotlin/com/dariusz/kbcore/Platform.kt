package com.dariusz.kbcore

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform