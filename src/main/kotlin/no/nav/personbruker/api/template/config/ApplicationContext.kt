package no.nav.personbruker.api.template.config

import no.nav.personbruker.api.template.database.Database
import no.nav.personbruker.api.template.database.PostgresDatabase
import no.nav.personbruker.api.template.greeting.GreetingRepository
import no.nav.personbruker.api.template.health.HealthService
import org.slf4j.LoggerFactory

class ApplicationContext {

    private val log = LoggerFactory.getLogger(ApplicationContext::class.java)

    val environment = Environment()
    val database: Database = PostgresDatabase(environment)

    val healthService = HealthService(this)

    val greetingRepository = GreetingRepository(database)

}
