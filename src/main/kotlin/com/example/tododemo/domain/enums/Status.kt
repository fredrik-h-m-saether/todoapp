package com.example.tododemo.domain.enums

/**
 * Indicates the status of the task. The status can be one of the following:
 * <ul>
 *   <li>CREATED</li>
 *   <li>IN_PROGRESS</li>
 *   <li>DONE</li>
 * </ul>
 *
 * @author Øystein Opedal
 * @since 0.0.1
 */
enum class Status {
    CREATED,
    DELETED,
    DONE,
    IN_PROGRESS,
    READY,
    UPDATED
}