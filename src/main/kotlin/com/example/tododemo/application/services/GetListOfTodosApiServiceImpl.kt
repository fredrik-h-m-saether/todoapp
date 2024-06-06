package com.example.tododemo.application.services

import com.example.tododemo.application.GetListOfTodosApiService
import com.example.tododemo.domain.model.Todo
import com.example.tododemo.domain.repository.GetListOfTodosApiRepository

/**
 * The Service class orchestrates the use case.
 *
 * @author Øystein Opedal
 * @since 0.0.1
 *
 * @see <a href="https://www.baeldung.com/spring-component-repository-service">Spring Component, Repository, Service</a>
 */
class GetListOfTodosApiServiceImpl(val getListOfTodosApiRepository: GetListOfTodosApiRepository) : GetListOfTodosApiService {

    override fun getListOfTodosV1(): List<Todo> {
        return getListOfTodosApiRepository.findAllTodosV1()
    }
}