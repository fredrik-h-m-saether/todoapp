package com.example.tododemo.api.errorhandling

data class ErrorResponse(
    val timestamp: Long = System.currentTimeMillis(),
    val status: Int,
    val message: String?,
    val path: String
)