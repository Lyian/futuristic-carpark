package energy.uniper.FP.model

data class Vehicle(
        val marke: String,
        val typ: String,
        val kennzeichen: String,
        val halter: String,
        val wert: Double,
        val parteipersönlichkeit: Boolean,
        var kontrolle: Boolean,
        val preis: Double,
        var status: String,
        ){

}