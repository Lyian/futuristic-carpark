package energy.uniper.FP.model

import kotlin.math.floor
import kotlin.math.max
import kotlin.random.Random

class CarPark(val maxLevels: Int){
        private val levels = mutableListOf<Level>()
        private val noSlotsCars = mutableListOf<Car>()
        private val calculateLevelId = {maxLevels: Int, value: Double -> max((maxLevels - floor(value / 10_000)).toInt(),0)}
        
        init{
                for (i in 0..maxLevels){
                        levels.add(Level(i))
                }
        }

        fun carLeaving(car:Car){
                calculatePrice(car)
        }
        
        fun calculatePrice(car:Car){
                val levelId = calculateLevelId(maxLevels, car.value)
                for(i in levelId until maxLevels){
                        if(levels[i].carIsInLevel(car)){
                                val price = levels[i].calculatePriceForLevel(car)
                                car.status = CarStatus.LEFT
                                println("Hi ${car.owner} =). Please pay $price.")
                        }
                }
        }

        fun parkCar(car: Car){
                val levelId = calculateLevelId(maxLevels, car.value)
                val newStatus = addCarToLevel(levelId, car)

                // Idee: Nach einiger Zeit, müssten alle Ebenen besetzt sein, das würde bedeuten
                // das nach einiger Zeit nur noch Autos mit dem Status "NOSLOTS" auftauchen
                //println("Level: $levelId, CurrentlyParking: ${levels[levelId].getCountOccupiedParkingLots()} Car:$car")

                if(newStatus == CarStatus.NOSLOTS){
                        println("IdPlate: ${car.idPlate} Level: $levelId Es tut uns leid. Uns sind derzeit alle Parkplätze ausgegangen. =(")
                }
        }

        private fun addCarToLevel(levelId: Int, car: Car) : CarStatus {
                for (i in levelId until maxLevels){
                        if(levels[i].getCountOccupiedParkingLots() <= 19){
                                car.status = CarStatus.PARKED
                                
                                val eps = Random.nextDouble(0.0,1.0)
                                if(levelId >= 5 && !car.isPartyMember &&  eps < 0.1*levelId){
                                        car.status = CarStatus.CONTROLL
                                }

                                if(levelId < 5 && !car.isPartyMember && eps < 0.01*levelId){
                                        car.status = CarStatus.CONTROLL
                                }
                                levels[i].addCar(car)
                                //Sortierte Liste?
                                //println(levels[i].listParkinglots)
                                return CarStatus.PARKED
                        }
                }

                car.status = CarStatus.NOSLOTS
                noSlotsCars.add(car)
                return CarStatus.NOSLOTS
        }
}