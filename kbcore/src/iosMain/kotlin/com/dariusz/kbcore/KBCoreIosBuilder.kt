package com.dariusz.kbcore

import com.dariusz.kbcore.KBCore
import com.dariusz.kbcore.KBCoreBuilder
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