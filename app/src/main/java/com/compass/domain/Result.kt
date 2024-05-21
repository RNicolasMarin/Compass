package com.compass.domain

sealed interface Result<out D> {
    data class Success<out D>(val data: D): Result<D>
    data class Error(val error: String): Result<Nothing>
}