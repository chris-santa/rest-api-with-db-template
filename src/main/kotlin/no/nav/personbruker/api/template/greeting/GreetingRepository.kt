package no.nav.personbruker.api.template.greeting

import no.nav.personbruker.api.template.database.Database

class GreetingRepository(private val database: Database) {
    suspend fun createGreeting(text: String) {
        database.queryWithExceptionTranslation {
            createGreeting(text)
        }
    }

    suspend fun getAllGreetingsSorted(): List<Greeting> {
        return database.queryWithExceptionTranslation {
            getAllGreetings()
        }.sortedBy { it.greetingTime }
    }
}