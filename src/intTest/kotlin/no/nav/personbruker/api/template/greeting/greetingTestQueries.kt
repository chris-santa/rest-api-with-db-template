package no.nav.personbruker.api.template.greeting

import java.sql.Connection

fun Connection.deleteAllGreeting() =
    prepareStatement("""DELETE FROM GREETING""")
        .use {it.execute()}