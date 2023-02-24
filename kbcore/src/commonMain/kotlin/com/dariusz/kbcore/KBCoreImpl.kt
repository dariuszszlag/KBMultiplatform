package com.dariusz.kbcore

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class KBCoreImpl(
    private val coroutineScope: CoroutineScope
) : KBCore {

    private val _userBalanceFlow = MutableStateFlow(0)
    private val _summaryFlow = MutableStateFlow(Summary(0, ""))
    private val _postsFlow = MutableStateFlow(listOf<Post>())

    override val userBalanceFlow: Flow<Int> = _userBalanceFlow.asStateFlow()
    override val summaryFlow: Flow<Summary> = _summaryFlow.asStateFlow()
    override val postsFlow: Flow<List<Post>> = _postsFlow.asStateFlow()

    override fun getPosts(userId: Int) = coroutineScope.launch {
        _postsFlow.value = listOf(listOfPosts.find { it.userId == userId } ?: Post(3, "Empty"))
    }

    override fun getSummary(userId: Int) = coroutineScope.launch {
        _summaryFlow.value = listOfSummary.find { it.userId == userId } ?: Summary(3, "No summary")
    }

    override fun getUserBalance(userId: Int) = coroutineScope.launch {
        _userBalanceFlow.value = listOfBalance.find { it.userId == userId }?.balance ?: 0
    }

    private val listOfPosts = listOf(Post(1, "First post"), Post(2, "Second post"))

    private val listOfSummary = listOf(Summary(1, "First summary"), Summary(2, "Second summary"))

    private val listOfBalance = listOf(Balance(1, 1234), Balance(2, 5678))

}

data class Post(val userId: Int, val post: String)

data class Summary(val userId: Int, val summary: String)

data class Balance(var userId: Int, val balance: Int)