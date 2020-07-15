package no.nav.personbruker.api.template.database.util

import io.ktor.util.toZonedDateTime
import java.sql.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

fun <T> ResultSet.mapSingleResult(result: ResultSet.() -> T): T =
        if (next()) {
            result()
        } else {
            throw SQLException("Found no rows")
        }

fun <T> ResultSet.mapList(mapper: ResultSet.() -> T): List<T> =
        mutableListOf<T>().apply {
            while (next()) {
                add(mapper())
            }
        }

fun Connection.executeBatchUpdateQuery(sql: String, paramInit: PreparedStatement.() -> Unit) {
    autoCommit = false
    prepareStatement(sql).use { statement ->
        statement.paramInit()
        statement.executeBatch()
    }
    commit()
}

fun Connection.executeBatchPersistQuery(sql: String, paramInit: PreparedStatement.() -> Unit): IntArray {
    autoCommit = false
    val result = prepareStatement("""$sql ON CONFLICT DO NOTHING""").use { statement ->
        statement.paramInit()
        statement.executeBatch()
    }
    commit()
    return result
}

fun ResultSet.getEpochTimeInSeconds(label: String): Long {
    return getTimestamp(label).toInstant().epochSecond
}

fun nowAtUtc(): LocalDateTime = LocalDateTime.now(ZoneId.of("UTC"))

fun ResultSet.getUtcDateTime(columnLabel: String): LocalDateTime = getTimestamp(columnLabel).toLocalDateTime()