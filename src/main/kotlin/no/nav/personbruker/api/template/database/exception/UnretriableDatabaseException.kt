package no.nav.personbruker.dittnav.eventaggregator.common.exceptions

class UnretriableDatabaseException(message: String, cause: Throwable?) : Exception(message, cause) {
    constructor(message: String) : this(message, null)
} 
