package com.dariusz.kbembed.data

import com.dariusz.kbcore.KBCoreBuilder
import kotlinx.coroutines.Dispatchers

object KBCoreDataSource {

    val get = KBCoreBuilder.coroutineDispatcher(Dispatchers.IO).build()

}
