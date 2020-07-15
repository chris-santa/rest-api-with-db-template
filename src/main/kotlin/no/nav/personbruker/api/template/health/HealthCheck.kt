package no.nav.personbruker.api.template.health

interface HealthCheck {

    suspend fun status(): HealthStatus

}
