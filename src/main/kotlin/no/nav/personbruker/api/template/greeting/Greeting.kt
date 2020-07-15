package no.nav.personbruker.api.template.greeting

import java.time.LocalDateTime

data class Greeting (
    val text: String,
    val greetingTime: LocalDateTime
)