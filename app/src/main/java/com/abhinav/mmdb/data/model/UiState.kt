package com.abhinav.mmdb.data.model

sealed class UiState {
    data class Snackbar(val msg: String) : UiState()
    data class Toast(val msg: String) : UiState()
}