package com.dariusz.kbcore

interface KBCoreBuilder {

    fun build(): KBCore

}

expect fun getKBCoreBuilder(): KBCoreBuilder