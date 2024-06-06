package com.example.tododemo.application.services

import com.example.tododemo.application.GetListOfTodosApiService

/**
 * The Service class orchestrates the use case.
 *
 * @author Øystein Opedal
 * @since 0.0.1
 *
 * @see <a href="https://www.baeldung.com/spring-component-repository-service">Spring Component, Repository, Service</a>
 */
class GetListOfTodosApiServiceImpl : GetListOfTodosApiService {

    /**
     * Retrieves a list of TODOs from the Infrastructure-layer.
     *
     * @see <a href="https://journit.atlassian.net/wiki/spaces/~63d512d64a3c3294ac05bb66/pages/7667713/TodoApp+-+Get+list+of+TODOs">
     *      User story - Get list of TODOs</a>
     */
    override fun getListOfTodosV1(): List<String> {
        TODO("Not yet implemented")
    }
}