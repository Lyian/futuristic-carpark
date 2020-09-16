package energy.uniper.FP.model

import java.time.LocalDateTime

data class Car(
        val brand: String,
        val type: String,
        val idPlate: String,
        val owner: String,
        val value: Double,
        val isPartyMember: Boolean,
        var toControll: Boolean,
        var status: CarStatus,
        val enteredAt : LocalDateTime?,
        var leavedAt : LocalDateTime
)