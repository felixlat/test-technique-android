package com.beeldi.beelding.entity

data class Equipement(val name: String? = null, val domain: String? = null, val nbFaults: Long? = null, val photo: String? = null) {
	fun writeNewEquipement(name: String, domain: String, nbFaults: Long, photo: String) {
		val equipement = Equipement(name, domain, nbFaults, photo)
	}
}