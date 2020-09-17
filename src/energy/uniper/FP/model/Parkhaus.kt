package energy.uniper.FP.model

import java.time.Duration
import java.time.LocalDateTime
import java.util.*
import kotlin.math.sqrt
import kotlin.random.Random

class Parkhaus {

    var parkebenen = mutableListOf<Parkebene>()
    var parkplätze = mutableListOf<Parkplatz>()
    var carEbenePlatzList = mutableListOf<CarEbenePlatz>()
    var cars = mutableListOf<Car>()


    fun parkplatzZuordnen(car: Car){

        var gewählteEbene = ebeneWählen(car)

        if (gewählteEbene != 99) {

            alphabetischParken(car, gewählteEbene)
            println("Auto geparkt")
        }

    }



    fun ebeneWählen(car: Car): Int{

        var ebene: Int = 99

        if(car.wert > 100000 || car.parteipersönlichkeit){
            ebene = 0
        }
        else{
            ebene = ((10 - kotlin.math.floor(car.wert / 10000)).toInt())
        }

        if (isParkebeneVoll(ebene) && ebene < 10) {
            ebene += 1

            if (isParkebeneVoll(ebene)) {
                carAbweisen(car)
                return 99
            }
            return ebene
        }
        else if (isParkebeneVoll(ebene) && ebene == 10){
            carAbweisen(car)
            return 99
        }

        kontrolleSetzen(car, ebene)

       return ebene
    }



    fun isParkebeneVoll(ebene: Int): Boolean{

        for (parkebene in parkebenen){
            if (parkebene.id == ebene && parkebene.maxPlätze == parkebene.parkplätze.filter { it.car != null }.count()){
                return true
            }
        }
        return false
    }



    fun carAbweisen(car: Car){

        cars.remove(car)
        println("Auto abgewiesen")
        
    }


    fun alphabetischParken(zuParkendesCar: Car, ebene: Int ){

        var currentParkebene = Parkebene(999, 999, mutableListOf())

        for (parkebene in parkebenen) {
            if (parkebene.id == ebene){
                currentParkebene = parkebene
            }
        }

        var kennzeichenMap = mutableMapOf<String, Car>()

        for (parkplatz in currentParkebene.parkplätze ){
            if (parkplatz.car != null) {
                kennzeichenMap.put(parkplatz.car!!.kennzeichen, parkplatz.car!!)
                parkplatz.car = null
            }
        }


        carEbenePlatzList.removeAll({ it.parkebeneId == currentParkebene.id})

        kennzeichenMap[zuParkendesCar.kennzeichen] = zuParkendesCar

        kennzeichenMap.toSortedMap()

        for ( (key, car) in kennzeichenMap){

            val neuerEintragCarEbenePlatz = CarEbenePlatz(car.kennzeichen, currentParkebene.id, currentParkebene.parkplätze.filter{ it.car == null }[0].id)
            currentParkebene.parkplätze.filter{ it.id == neuerEintragCarEbenePlatz.parkplatzId }[0].car = car
            carEbenePlatzList.add(neuerEintragCarEbenePlatz)
            currentParkebene.parkplätze.filter { it.id == neuerEintragCarEbenePlatz.parkplatzId}[0].abgabe = LocalDateTime.now()
        }

        zuParkendesCar.status = CarStatus.PARKPLATZ



    }


    fun kontrolleSetzen(zuParkendesCar: Car, ebene: Int){

        if (ebene == 0 && !zuParkendesCar.parteipersönlichkeit){

            val eps = Random.nextDouble(0.0,1.0)
            if (eps <= 0.001){
                zuParkendesCar.kontrolle = true
            }

        }

        if (ebene > 4 ){

            val eps = Random.nextDouble(0.0,1.0)
            if (eps <= 0.01*ebene){
                zuParkendesCar.kontrolle = true
            }

        }


    }






    fun parkplatzAbrechnen(car: Car, carEbenePlatzList: List<CarEbenePlatz>) {

        var currentEbene: Double = 0.0
        var parkdauer = Random.nextInt(1,10)

        for (carEbenePlatz in carEbenePlatzList) {

            if (carEbenePlatz.kennzeichen == car.kennzeichen) {
                currentEbene = carEbenePlatz.parkebeneId.toDouble()

                car.preis = parkdauer * sqrt(currentEbene)
            }
        }



        //for (parkplatz in parkplätze){
        //    if (parkplatz.car == car){
        //        parkdauer = Duration.between(parkplatz.abholung, parkplatz.abgabe).toHours().toDouble()
        //    }
        //}




    }






}