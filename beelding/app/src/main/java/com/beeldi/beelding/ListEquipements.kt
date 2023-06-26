package com.beeldi.beelding

import com.beeldi.beelding.entity.Equipement
import com.google.android.material.imageview.ShapeableImageView

interface ListEquipements {

	fun clickListener(equipement: Equipement)

	fun getImage(url: String?, photoGlide: ShapeableImageView)

}