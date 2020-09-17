package energy.uniper.FP.model

import java.time.LocalDateTime

data class Parkplatz(val id: Int) {

    var car:Car? = null
    var abgabe: LocalDateTime? = null
    var abholung: LocalDateTime? = null
}