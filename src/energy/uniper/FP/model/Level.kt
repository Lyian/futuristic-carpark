package energy.uniper.FP.model

import java.time.Duration
import kotlin.math.sqrt

class Level (val levelId: Int){
    // Die Position in der Liste gibt die id des Parkplatzes an.
     val listParkinglots = mutableListOf<Car>()

    fun calculatePriceForLevel(car: Car) : Double {
        println("You stayed ${Duration.between(car.enteredAt, car.leavedAt).toHours()}h at Level: $levelId")
        return Duration.between(car.enteredAt, car.leavedAt).toHours() * sqrt(levelId.toDouble()) * 0.01
    }

    fun addCar(car: Car){
        listParkinglots.add(car)
        reorderCars()
    }

    // nachdem ein Auto hinzugefügt wurde, soll die Liste wieder nach kennzeichen sortiert sein,
    // da aber gerade die Position in der Liste die Reihenfolge der Parkplätze angibt, müssen wir lediglich
    // die Liste sortieren.
    fun reorderCars(){
        listParkinglots.sortBy { it.idPlate }
    }

    fun getCountOccupiedParkingLots():Int{
        return listParkinglots.size
    }

    fun carIsInLevel(car: Car) : Boolean{
        return listParkinglots.contains(car)
    }
}