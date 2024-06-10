package com.example.tododemo.infrastructure.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DriverManagerDataSource
import javax.sql.DataSource


@Configuration
class DatabaseConfig {
    private val logger: Logger = LoggerFactory.getLogger(DatabaseConfig::class.java)

    @Bean
    fun dataSource(
        @Value("\${spring.datasource.url}") url: String,
        @Value("\${spring.datasource.username}") username: String,
        @Value("\${spring.datasource.password}") password: String,
        @Value("\${spring.datasource.driver-class-name}") driverClassName: String
    ): DataSource {
        logger.info("Configuring JDBC datasource - URL={}, USER={}, driverClassName={}", url, username, driverClassName)

        val dataSource = DriverManagerDataSource()
        dataSource.url = url
        dataSource.username = username
        dataSource.password = password
        dataSource.setDriverClassName(driverClassName)

        return dataSource
    }
}