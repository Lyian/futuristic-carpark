package energy.uniper.FP.model

data class Parkebene(val id: Int, val maxPlätze: Int, var parkplätze: List<Parkplatz>) {
}