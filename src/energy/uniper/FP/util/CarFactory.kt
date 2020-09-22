package energy.uniper.FP.util

import energy.uniper.FP.model.Car
import energy.uniper.FP.model.CarStatus
import kotlin.random.Random

class CarFactory (){
    val seed = 1
    val Random = Random(seed)
    val names = listOf("Holger", "Tori", "Kevin", "Hannes", "Eren", "Hans", "Mert", "Peter", "Martin")
    val lastnames = listOf("Meister", "Bäcker", "Schuster", "Handwerker", "Albrecht", "Vogel")
    val autoBrand = listOf("Mercedes", "BMW", "Porsche", "VW", "Ford", "Audi")
    val städte = listOf("B","D", "Lev", "K", "Gl", "Me", "M")
    val kennzeichen = listOf("AB", "CA", "DD", "EF", "ZA", "TT", "SA")
    val typ = listOf("1", "2", "3", "4")



    fun createNewCar(count: Int = 250) : List<Car> {
        val listOfCar = mutableListOf<Car>()
        for (i in 1..count){
            val combi = this.kennzeichen.random();
            val stadt = städte.random()
            val auto = autoBrand.random()
            val name1 = lastnames.random()
            val name2 = names.random()
            val intKennzeichen = Random.nextInt(1, 9999)
            val costOfCar = Random.nextDouble(2000.0, 150000.0)
            val eps = Random.nextDouble(0.0,1.0)

            var isP : Boolean = false
            if(eps < 0.1) {
                isP = true
            }

            listOfCar.add(Car(auto, typ.random(), "$stadt $combi $intKennzeichen", "$name2 $name1", costOfCar, isP, false, 0.0, CarStatus.willRein))
        }

        return listOfCar
    }

}