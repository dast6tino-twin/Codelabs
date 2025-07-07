package ru.dast_6_tino.adaptiveapps.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.dast_6_tino.adaptiveapps.data.Email
import ru.dast_6_tino.adaptiveapps.data.EmailsRepository
import ru.dast_6_tino.adaptiveapps.data.EmailsRepositoryImpl

class ReplyHomeViewModel(
    private val emailsRepository: EmailsRepository = EmailsRepositoryImpl(),
) : ViewModel() {

    // UI state exposed to the UI
    private val _uiState = MutableStateFlow(ReplyHomeUIState(loading = true))
    val uiState: StateFlow<ReplyHomeUIState> = _uiState

    init {
        observeEmails()
    }

    private fun observeEmails() {
        viewModelScope.launch {
            emailsRepository.getAllEmails()
                .catch { ex ->
                    _uiState.value = ReplyHomeUIState(error = ex.message)
                }
                .collect { emails ->
                    val currentSelected = _uiState.value.selectedEmail
                    _uiState.value = ReplyHomeUIState(
                        emails = emails,
                        selectedEmail = currentSelected ?: emails.firstOrNull(),
                    )
                }
        }
    }

    fun setSelectedEmail(email: Email) {
        _uiState.update {
            it.copy(selectedEmail = email)
        }
    }

}
