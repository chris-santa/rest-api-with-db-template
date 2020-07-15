package no.nav.personbruker.dittnav.eventaggregator.common.exceptions

class RetriableDatabaseException(message: String, cause: Throwable?) : Exception(message, cause) {
    constructor(message: String) : this(message, null)
}
