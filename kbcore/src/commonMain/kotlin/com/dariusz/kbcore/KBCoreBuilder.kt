package com.dariusz.kbcore

import kotlinx.coroutines.CoroutineScope

interface KBCoreBuilder {

    fun setCoroutineScope(coroutineScope: CoroutineScope): KBCoreBuilder

    fun build(): KBCore

}

expect fun getKBCoreBuilder(): KBCoreBuilder