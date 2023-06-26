package com.beeldi.beelding.entity

data class Checkpoint(val equipmentKey: String, val recommandation: String, val name: String?, val fault: String?, val photo: String? = null) {
}