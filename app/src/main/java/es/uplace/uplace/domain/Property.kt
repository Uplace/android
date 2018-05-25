package es.uplace.uplace.domain

import android.os.Parcel
import android.os.Parcelable

data class Property(
        val title: String,
        val photos: Array<Photo>,
        val reference: String,
        val description: String,
        val propertyType: String,
        val transaction: String,
        val surface: Int,
        val location: Location,
        val visible: Boolean,
        val yearConstruction: Int,
        val priceTransfer: Double,
        val priceSell: Double,
        val priceRent: Double) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.createTypedArray(Photo),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readParcelable(Location::class.java.classLoader),
            parcel.readByte() != 0.toByte(),
            parcel.readInt(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readDouble()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeTypedArray(photos, flags)
        parcel.writeString(reference)
        parcel.writeString(description)
        parcel.writeString(propertyType)
        parcel.writeString(transaction)
        parcel.writeInt(surface)
        parcel.writeParcelable(location, flags)
        parcel.writeByte(if (visible) 1 else 0)
        parcel.writeInt(yearConstruction)
        parcel.writeDouble(priceTransfer)
        parcel.writeDouble(priceSell)
        parcel.writeDouble(priceRent)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Property> {
        override fun createFromParcel(parcel: Parcel): Property {
            return Property(parcel)
        }

        override fun newArray(size: Int): Array<Property?> {
            return arrayOfNulls(size)
        }
    }
}

data class Photo(val name: String, val description: String,
                 val photoUrl: String, val photoContentType: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(photoUrl)
        parcel.writeString(photoContentType)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Photo> {
        override fun createFromParcel(parcel: Parcel): Photo {
            return Photo(parcel)
        }

        override fun newArray(size: Int): Array<Photo?> {
            return arrayOfNulls(size)
        }
    }
}

data class Location(val id: Int, val latitude: Double, val longitude: Double, val postalCode: String,
                    val city: String, val fullAddress: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
        parcel.writeString(postalCode)
        parcel.writeString(city)
        parcel.writeString(fullAddress)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Location> {
        override fun createFromParcel(parcel: Parcel): Location {
            return Location(parcel)
        }

        override fun newArray(size: Int): Array<Location?> {
            return arrayOfNulls(size)
        }
    }
}

/*
private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "photo_url")
    @URL(protocol = "https")
    private String photoUrl;

    @Column(name = "photo_public_id")
    private String publicId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Transient
    private byte[] photo;

    @NotNull
    @Column(name = "thumbnail", nullable = false)
    private Boolean thumbnail;
 */

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