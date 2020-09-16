package energy.uniper.FP.model

import java.time.LocalDateTime

class Parkplatz(
        val abgabe: LocalDateTime,
        var id: Int,
        val car: Car?,
) {
    var abholung: LocalDateTime? = null

}
