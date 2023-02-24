package com.dariusz.kbcore

import com.dariusz.kbcore.KBCore
import com.dariusz.kbcore.KBCoreBuilder
import com.dariusz.kbcore.KBCoreImpl
import kotlinx.coroutines.CoroutineScope

class KBCoreAndroidBuilder : KBCoreBuilder {

    private lateinit var coroutineScopeImpl: CoroutineScope

    override fun setCoroutineScope(coroutineScope: CoroutineScope): KBCoreAndroidBuilder {
        coroutineScopeImpl = coroutineScope
        return this
    }

    override fun build(): KBCore {
        return if (this::coroutineScopeImpl.isInitialized)
            KBCoreImpl(coroutineScopeImpl)
        else
            throw IllegalStateException("No coroutineScope provided")
    }

}

actual fun getKBCoreBuilder(): KBCoreBuilder = KBCoreAndroidBuilder()