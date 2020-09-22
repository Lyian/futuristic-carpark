package energy.uniper.FP.model

import java.time.LocalDateTime
import kotlin.math.round
import kotlin.math.sqrt
import kotlin.random.Random

class Parkebene(val id: Int, var maxPlaetze: Int = 20)
{
    val parkplaetze: MutableList<Parkplatz> = mutableListOf<Parkplatz>()

    init {
        for (i in 1..maxPlaetze){
                parkplaetze.add(Parkplatz(i))
        }
    }
    fun parkplatzFrei(): Int? {
        for (x in 0..parkplaetze.count()-1){
            if (parkplaetze[x].car == null){
                 return x
            }
        }
        return null
    }

    fun einparken(car: Car, platz: Int) {
        parkplaetze[platz].abgabe = LocalDateTime.now()
        parkplaetze[platz].car = car
    }

    fun carAlphbetischSortieren() {
        parkplaetze.sortedBy{it.car?.kennzeichen}
        var zielID: Int = 0
        for (x in parkplaetze) {
            zielID = zielID + 1
            x.id = zielID
        }
    }

    fun autoHier(car: Car): Int? {
        for (i in 0 until maxPlaetze){
            if (parkplaetze[i].car == car) {return i}
        }
        return null
    }

    fun ausparken(car: Car){
        val abholung = LocalDateTime.now().plusHours(Random.nextLong(0, 10))
        val platz = autoHier(car)
        parkplaetze[platz!!].car?.preis = round((abholung.hour - (parkplaetze[platz].abgabe!!.hour)) * sqrt(id.toDouble())*100)/100
        parkplaetze[platz].car = null
        parkplaetze[platz].abgabe = null
        parkplaetze[platz].abholung = null
    }
}



