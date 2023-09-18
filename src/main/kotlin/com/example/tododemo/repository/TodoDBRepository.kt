package com.example.tododemo.repository


import com.example.tododemo.models.Todo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TodoDBRepository : JpaRepository<Todo, UUID> {


}
