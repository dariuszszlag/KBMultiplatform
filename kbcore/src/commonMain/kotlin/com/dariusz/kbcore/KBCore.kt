package com.dariusz.kbcore

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow

interface KBCore {

    val userBalanceFlow: Flow<Int>

    val summaryFlow: Flow<Summary>

    val postsFlow: Flow<List<Post>>

    fun getPosts(userId: Int): Job

    fun getSummary(userId: Int): Job

    fun getUserBalance(userId: Int): Job

}
