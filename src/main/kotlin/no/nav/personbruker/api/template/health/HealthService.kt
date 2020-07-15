package no.nav.personbruker.api.template.health

import no.nav.personbruker.api.template.config.ApplicationContext

class HealthService(private val applicationContext: ApplicationContext) {

    suspend fun getHealthChecks(): List<HealthStatus> {
        return listOf(
                applicationContext.database.status()
        )
    }
}
