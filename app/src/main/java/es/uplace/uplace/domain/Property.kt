package es.uplace.uplace.domain

import es.uplace.uplace.domain.enumeration.Select
import es.uplace.uplace.domain.enumeration.TransactionType

open class Property(
        val title: String,
        val reference: String,
        val propertyType: String,
        val transaction: TransactionType,
        val surface: Int,
        val location: Location
)

//        Appartment Properties
//    val numberBedrooms: Int
//    val numberBathrooms: Int
//    val m2Edified: Int
//    val m2Usable: Int
//    val height: Int
//    val elevator: Select
//    val ac: Select
//    val heat: Select
//    val parking: Select
//    val terrace: Select
//    val balcony: Select
//    val surfaceTerrace: Int
//    val surfaceSalon: Int
//    val type: ApartmentType
//    val office: Select
//    val kitchenOffice: Select
//    val storage: Select
//    val sharedPool: Select
//    val nerTransport: Select
//    val reformed: Select
//    val energyCertificate: EnergyCertificate
//    val certificateHabitability: Select
