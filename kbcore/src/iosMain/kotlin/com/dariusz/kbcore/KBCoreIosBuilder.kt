package com.dariusz.kbcore

class KBCoreIosBuilder : KBCoreBuilder {

    override fun build(): KBCore {
        TODO("Not yet implemented")
    }
}

actual fun getKBCoreBuilder(): KBCoreBuilder = KBCoreIosBuilder()
