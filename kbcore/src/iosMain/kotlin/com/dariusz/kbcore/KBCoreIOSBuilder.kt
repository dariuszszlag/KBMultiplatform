package com.dariusz.kbcore

import kotlinx.coroutines.CoroutineDispatcher

class KBCoreIOSBuilder: KBCoreBuilder {

    override fun coroutineDispatcher(coroutineDispatcher: CoroutineDispatcher): KBCoreBuilder {
        TODO("Not yet implemented")
    }

    override fun build(): KBCore {
        TODO("Not yet implemented")
    }

}

actual fun kbCoreBuilder(): KBCoreBuilder = KBCoreIOSBuilder()