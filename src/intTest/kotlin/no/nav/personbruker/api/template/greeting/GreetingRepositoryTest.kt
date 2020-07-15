package no.nav.personbruker.api.template.greeting

import kotlinx.coroutines.runBlocking
import no.nav.personbruker.api.template.database.H2Database
import org.amshove.kluent.`should contain same`
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class GreetingRepositoryTest {

    val database = H2Database()

    val greetingRepository = GreetingRepository(database)

    @AfterEach
    fun tearDown() {
        runBlocking {
            database.dbQuery {
                deleteAllGreeting()
            }
        }
    }

    @Test
    fun `Should perist and fetch greetings correctly`() {
        runBlocking {
            val greetingTexts = listOf("Hello", "Hail", "Oi")

            greetingTexts.forEach { greeting ->
                greetingRepository.createGreeting(greeting)
            }

            val greetingDtos = greetingRepository.getAllGreetingsSorted()

            assertTrue(greetingDtos.isSortedWithRespectTo(Greeting::greetingTime))

            greetingDtos.map { it.text } `should contain same` greetingTexts
        }
    }

    private fun <T, R: Comparable<R>> List<T>.isSortedWithRespectTo(sortOn: T.() -> R): Boolean {
        return asSequence().zipWithNext { prev, next ->
            prev.sortOn() < next.sortOn()
        }.all { it }
    }
}