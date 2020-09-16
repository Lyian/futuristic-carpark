package energy.uniper.FP.model

data class CarEbenePlatz(
        val kennzeichen: String,
        val parkebeneId: Int,
        var parkplatzId: Int,
        val parkebeneUrsprünglichId: Int,
) {
}