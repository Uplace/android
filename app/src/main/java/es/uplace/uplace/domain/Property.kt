package es.uplace.uplace.domain

class Property(
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
        val priceRent: Double)

data class Photo(val name: String, val description: String,
                 val photoUrl: String, val photoContentType: String)

data class Location (val id: Int, val latitude: Double, val longitude: Double, val postalCode: String,
                     val city: String, val fullAddress: String)

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
