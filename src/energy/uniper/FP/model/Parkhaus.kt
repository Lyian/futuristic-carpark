package energy.uniper.FP.model
import java.time.LocalDateTime
import kotlin.math.round
import kotlin.math.sqrt
import kotlin.random.Random

class Parkhaus() {
    var parkplaetze = mutableListOf<Parkplatz>()
    var cars = mutableListOf<Car>()
    var carEbenePlatz = mutableListOf<CarEbenePlatz>()
    var parkebenen = mutableListOf<Parkebene>()

    init {
        for (i in 0..10){
            parkebenen.add(Parkebene(i))
        }
    }

    fun parklatzZuordnen(car: Car): CarStatus {
        val wunschebene = ebeneWaehlen(car)
        for (zielebene in wunschebene..10) {
            var aktuelleEbeneParkplatz = parkebenen[zielebene].parkplatzFrei()
            if (aktuelleEbeneParkplatz != null) {
                parkebenen[zielebene].einparken(car,aktuelleEbeneParkplatz )
                parkebenen[zielebene].carAlphbetischSortieren()
                kontrolleSetzen(car)
                car.status = CarStatus.Parkplatz
                return CarStatus.Parkplatz
            }
        }
        car.status = CarStatus.Abgewiesen
        return CarStatus.Abgewiesen

    }



    fun kontrolleSetzen(car: Car) {
        var potKontrolle = carEbenePlatz.filter { it.kennzeichen == car.kennzeichen && it.parkebeneUrsprünglichId > 4 }

        if (potKontrolle.count() > 0) {
            cars?.find { it.kennzeichen == car.kennzeichen }?.kontrolle = 0.01 * potKontrolle[0].parkebeneUrsprünglichId > Random.nextDouble(0.0, 1.0)
        }

    }


    fun ebeneWaehlen(car: Car): Int {
        if (car.parteipersönlichkeit) {return 0}
        var zielebene = 10 - Math.floor(car.wert / 100000 * 10)
        if (zielebene > 10) {zielebene = 10.0}
        if (zielebene < 0) {zielebene = 0.0}
        return zielebene.toInt()
    }

    fun parkplatzAbrechnen(car: Car): CarStatus {
        val wunschebene = ebeneWaehlen(car)
        for (zielebene in wunschebene..10) {
            if (parkebenen[zielebene].autoHier(car) != null) {
                parkebenen[zielebene].ausparken(car)
            }
        }
        car.status = CarStatus.Abgeholt
        return CarStatus.Abgeholt
    }
}