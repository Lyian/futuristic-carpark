package energy.uniper.FP.model

data class Car(
        val marke: String,
        val typ: String,
        val kennzeichen: String,
        val halter: String,
        val wert: Double,
        val parteipersönlichkeit: Boolean,
        var kontrolle: Boolean,
        var preis: Double,
        var status: CarStatus,
        ){

}