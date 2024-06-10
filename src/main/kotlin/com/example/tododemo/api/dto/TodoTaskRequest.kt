package com.example.tododemo.api.dto

import com.example.tododemo.domain.enums.Status

data class TodoTaskRequest(val title: String?, val description: String?, val status: Status = Status.CREATED)