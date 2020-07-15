package no.nav.personbruker.api.template.greeting

import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.request.receiveText
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post

fun Routing.greetingApi(greetingRepository: GreetingRepository) {

    post("/greetings/create/hello") {
        greetingRepository.createGreeting("Hello")
        call.respondText(text = "Created greeting with text [Hello]", contentType = ContentType.Text.Plain)
    }

    post("/greetings/create/howdy") {
        greetingRepository.createGreeting("Howdy")
        call.respondText(text = "Created greeting with text [Howdy]", contentType = ContentType.Text.Plain)
    }

    post("/greetings/create/custom") {
        call.receiveText().let { customText ->
            greetingRepository.createGreeting(customText)
            call.respondText(text = "Created greeting with text [$customText]", contentType = ContentType.Text.Plain)
        }
    }

    get("/greetings") {
        greetingRepository.getAllGreetingsSorted().joinToString(",\n") {
            "${it.greetingTime}: ${it.text}"
        }.let { greetings ->
            call.respondText(text = greetings, contentType = ContentType.Text.Plain)
        }
    }
}