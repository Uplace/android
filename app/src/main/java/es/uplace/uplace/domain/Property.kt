package es.uplace.uplace.domain

import android.location.Location
import es.uplace.uplace.domain.enumeration.ApartmentType
import es.uplace.uplace.domain.enumeration.EnergyCertificate
import es.uplace.uplace.domain.enumeration.Select

import java.time.ZonedDateTime
import java.util.HashSet

import es.uplace.uplace.domain.enumeration.TransactionType

class Property(
//        Property attributes
        val title: String, val reference: String?, val propertyType: String?,
        val created: ZonedDateTime?, val updated: ZonedDateTime?,
        val description: String?, val transaction: TransactionType?,
        val price: Double?, val priceSell: Double?, val priceRent: Double?,
        val priceTransfer: Double?, val yearConstruction: Int?, val newCreation: Boolean?,
        val surface: Int?, val location: Location?, val photos: Set<Photo>?,

//        Appartment Properties
        val numberBedrooms: Int?, val numberBathrooms: Int?, val m2Edified: Int?,
        val m2Usable: Int?, val height: Int?, val elevator: Select?, val ac: Select?,
        val heat: Select?, val parking: Select?, val terrace: Select?, val balcony: Select?,
        val surfaceTerrace: Int?, val surfaceSalon: Int?, val type: ApartmentType?,
        val office: Select?, val kitchenOffice: Select, val storage: Select, val sharedPool: Select?,
        val nerTransport: Select?, val reformed: Select?, val energyCertificate: EnergyCertificate?,
        val certificateHabitability: Select?
)
