package com.dariusz.kbmultiapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform