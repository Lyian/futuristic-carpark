package energy.uniper.FP.model

import java.time.LocalDateTime

class Parkplatz(
        var id: Int
) {
    var abgabe: LocalDateTime? = null
    var abholung: LocalDateTime? = null
    var car: Car? = null

}
