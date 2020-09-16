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


    fun parklatzZuordnen(car: Car) {
        cars.add(car)
        cars.find { it.kennzeichen == car.kennzeichen }?.status = CarStatus.willRein
        val wunschebene = ebeneWaehlen(car)
        carAbweisen(car)
        for (zielebene in wunschebene..10) {
            var aktuelleEbeneParkplatz = carEbenePlatz.filter { it.parkebeneId == zielebene }?.count() + 1
            if (aktuelleEbeneParkplatz < 21) {
                einparken(car, wunschebene, zielebene, aktuelleEbeneParkplatz)
                carAlphbetischSortieren(zielebene, wunschebene)
                return
            } else {
            }
        }
        kontrolleSetzen(car)
    }


    fun carAbweisen(car: Car) {
        if (carEbenePlatz.count() > 220) {
            println("Verpiss Dich, is voll!!!!!!!!")
            cars?.find { it.kennzeichen == car.kennzeichen }?.status = CarStatus.Abgewiesen
        }
    }

    fun kontrolleSetzen(car: Car) {
        var potKontrolle = carEbenePlatz.filter { it.kennzeichen == car.kennzeichen && it.parkebeneUrsprünglichId > 4 }

        if (potKontrolle.count() > 0) {
            if (0.01 * potKontrolle[0].parkebeneUrsprünglichId > Random.nextDouble(0.0, 1.0)) {
                cars?.find { it.kennzeichen == car.kennzeichen }?.kontrolle = true
            } else {
                cars?.find { it.kennzeichen == car.kennzeichen }?.kontrolle = false
            }
        }

    }


    fun ebeneWaehlen(car: Car): Int {
        if (car.parteipersönlichkeit == true) {
            return 0
        }
        var zielebene = car.wert / 100000
        zielebene = 10 - Math.floor(zielebene * 10)
        if (zielebene > 10) {
            zielebene = 10.0
        }
        if (zielebene < 0) {
            zielebene = 0.0
        }
        return zielebene.toInt()
    }

    fun einparken(car: Car, wunschEbene: Int, ebene: Int, platz: Int) {
        val neuZuordnenParklatz = Parkplatz(LocalDateTime.now(), platz, car)
        val neuZuordnenEbene = Parkebene(ebene, neuZuordnenParklatz)
        val neuZuordnenCarEbenePlatz = CarEbenePlatz(car.kennzeichen, ebene, platz, wunschEbene)
        parkebenen.add(neuZuordnenEbene)
        parkplaetze.add(neuZuordnenParklatz)
        carEbenePlatz.add(neuZuordnenCarEbenePlatz)
        cars?.find { it.kennzeichen == car.kennzeichen }?.status = CarStatus.Parkplatz

    }

    fun carAlphbetischSortieren(zielebene: Int, wunschEbene: Int) {
        var parkebeneIst = carEbenePlatz.filter { it.parkebeneId == zielebene }
        var parkebeneSoll = parkebeneIst.sortedWith(compareBy { it.kennzeichen })
        var zielID: Int = 0
        carEbenePlatz.removeIf { it.parkebeneId == zielebene }
        parkebenen.removeIf { it.id == zielebene }
        for (parkebeneZiel in parkebeneSoll) {
            zielID = zielID + 1
            val zielCar = cars.find { it.kennzeichen == parkebeneZiel.kennzeichen }
            val neuZuordnenCarEbenePlatz = CarEbenePlatz(parkebeneZiel.kennzeichen, zielebene, zielID, wunschEbene)
            val neuZuordnenParklatz = Parkplatz(LocalDateTime.now(), zielID, zielCar)
            carEbenePlatz.add(neuZuordnenCarEbenePlatz)
            parkplaetze.add(neuZuordnenParklatz)

        }
    }

    /*   fun carAlphbetischSortieren(car: Car, zielebene: Int, wunschPlatz: Int): Int {
        val parkebeneIst = carEbenePlatz.filter { it.parkebeneId == zielebene }
        var zielID: Int = 0
        var schonPassiert: Boolean
        for (zielplatz in parkebeneIst) {
            schonPassiert=false
            if (car.kennzeichen<zielplatz.kennzeichen) {
                if (schonPassiert==false) {
                    zielID = carsVerschieben(zielebene, zielplatz.parkplatzId+1)
                    schonPassiert = true
                }
            }else
            {
                zielID=wunschPlatz
            }
        }
        return zielID
    }



    fun carsVerschieben(zielebene: Int, zielparkplatzId: Int): Int {
        val parkebeneSoll = carEbenePlatz.filter { it.parkebeneId == zielebene }

        var zielID = 0
        for (neuerPlatz in parkebeneSoll) {
            zielID = zielID + 1
            if (zielID < zielparkplatzId) {
                carEbenePlatz?.find { it.kennzeichen == neuerPlatz.kennzeichen }?.parkplatzId = zielID

 //               parkplaetze?.find { it.car.kennzeichen == neuerPlatz.kennzeichen }?.id = zielID
            }
        }
        return zielID
    }
*/

    fun carAusparken(car: Car) {
        val zielebene = carEbenePlatz.filter { it.kennzeichen == car.kennzeichen }.get(0).parkebeneId
        val listOfParkplaetze: MutableList <Parkplatz> = parkplaetze.filter { it.car != car } as MutableList<Parkplatz>
        parkebenen.removeIf { it.id == zielebene }
        for (parklatz in listOfParkplaetze){
            val neuZuordnenEbene = Parkebene(zielebene, parklatz)
            parkebenen.add(neuZuordnenEbene)
        }
        parkplaetze?.find { it.car?.kennzeichen ?: String == car.kennzeichen }?.id = 0
        parkplaetze?.find { it.car?.kennzeichen ?: String == car.kennzeichen }?.abholung = LocalDateTime.now().plusHours(Random.nextLong(0, 10))
        cars?.find { it.kennzeichen == car.kennzeichen }?.status = CarStatus.Garage
    }


    fun parkplatzAbrechnen(car: Car){
        val zielebene = carEbenePlatz.filter { it.kennzeichen == car.kennzeichen }.get(0).parkebeneId
        val beginnParken = parkplaetze.filter { it.car?.kennzeichen ?: String == car.kennzeichen }.get(0).abgabe
        val endeParken = parkplaetze.filter { it.car?.kennzeichen ?: String == car.kennzeichen }.get(0).abholung
        if (endeParken != null) {
            cars?.find { it.kennzeichen == car.kennzeichen }?.preis = round((endeParken.hour - beginnParken.hour).toDouble() * sqrt(zielebene.toDouble())*100)/100
        }
        cars?.find { it.kennzeichen == car.kennzeichen }?.status = CarStatus.Abgeholt
        carEbenePlatz.removeIf { it.kennzeichen == car.kennzeichen }
    }
}