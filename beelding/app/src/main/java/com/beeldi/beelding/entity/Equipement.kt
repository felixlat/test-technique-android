package com.beeldi.beelding.entity

data class Equipement(val key: String? = null, val name: String? = null, val domain: String? = null, val nbFaults: Long? = null, val photo: String? = null/*, val notes: String?, val quantity: Long?, val serialNumber: Long?, val niveau: String?, val building: String?, val local: String, val station: String, val model: String, val brand: String, val status: String*/) {
	fun writeNewEquipement(key: String, name: String, domain: String, nbFaults: Long, photo: String/*, notes: String?, quantity: Long?, serialNumber: Long?, niveau: String?, building: String?, local: String, station: String, model: String, brand: String, status: String*/) {
		val equipement = Equipement(key, name, domain, nbFaults, photo/*, notes, quantity, serialNumber, niveau, building, local, station, model, brand, status*/)
	}
}