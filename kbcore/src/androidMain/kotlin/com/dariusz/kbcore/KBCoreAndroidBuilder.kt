package com.dariusz.kbcore

import android.util.Log
import com.dariusz.kbcore.MockResponsesHelper.interceptWithMockedResponses
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json

class KBCoreAndroidBuilder : KBCoreBuilder {

    private lateinit var coroutineScopeImpl: CoroutineScope

    private lateinit var httpClientImpl: HttpClient

    override fun build(): KBCore {
        coroutineScopeImpl =
            CoroutineScope(IO + SupervisorJob() + CoroutineExceptionHandler { _, throwable ->
                Log.e(
                    "KBCore error: ",
                    "$throwable"
                )
            })
        httpClientImpl = HttpClient(MockEngine) {
            interceptWithMockedResponses()
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
        }
        return KBCoreImpl(httpClientImpl, coroutineScopeImpl)
    }

}

actual fun getKBCoreBuilder(): KBCoreBuilder = KBCoreAndroidBuilder()