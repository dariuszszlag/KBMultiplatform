package com.dariusz.kbmultiapp

import kotlinx.coroutines.CoroutineScope

class KBCoreIosBuilder : KBCoreBuilder {

    override fun setCoroutineScope(coroutineScope: CoroutineScope): KBCoreBuilder {
        TODO("Not yet implemented")
    }

    override fun build(): KBCore {
        TODO("Not yet implemented")
    }
}

actual fun getKBCoreBuilder(): KBCoreBuilder = KBCoreIosBuilder()