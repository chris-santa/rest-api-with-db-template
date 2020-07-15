package no.nav.personbruker.api.template.greeting

import no.nav.personbruker.api.template.database.util.getUtcDateTime
import no.nav.personbruker.api.template.database.util.mapList
import no.nav.personbruker.api.template.database.util.nowAtUtc
import java.sql.Connection
import java.sql.ResultSet
import java.sql.Types

private val createQuery = "INSERT INTO GREETING (text, greetingTime) VALUES (?, ?)"

fun Connection.createGreeting(text: String) =
    prepareStatement(createQuery).use {
        it.setString(1, text)
        it.setObject(2, nowAtUtc(), Types.TIMESTAMP)
        it.executeUpdate()
    }

fun Connection.getAllGreetings() =
    prepareStatement("SELECT * FROM GREETING").use {
        it.executeQuery().mapList {
            toGreeting()
        }
    }

private fun ResultSet.toGreeting() = Greeting(
    text = getString("text"),
    greetingTime = getUtcDateTime("greetingTime")
)