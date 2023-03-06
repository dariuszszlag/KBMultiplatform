package com.dariusz.kbembed.ui.screens.drafts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dariusz.kbcore.KBCore
import com.dariusz.kbcore.feature.draft.Draft
import com.dariusz.kbembed.utils.Result
import com.dariusz.kbembed.utils.ViewState
import com.dariusz.kbembed.utils.ViewStateUtils.asResult
import com.dariusz.kbembed.utils.ViewStateUtils.onError
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DraftsScreenViewModel(
    dataSource: KBCore
) : ViewModel() {

    val draftsScreenState = dataSource.userDraftsFlow
        .map { drafts -> drafts.ifEmpty { listOf() } }
        .asResult()
        .onError("Error when downloading drafts")
        .map { DraftsScreenState(it) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            DraftsScreenState()
        )

}

data class DraftsScreenState(
    override val data: Result<List<Draft>> = Result.Loading
) : ViewState<List<Draft>>