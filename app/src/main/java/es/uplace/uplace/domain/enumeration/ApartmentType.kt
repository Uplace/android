package es.uplace.uplace.domain.enumeration

enum class ApartmentType(val typeName: String) {
    // HOUSES, RURALS, FLATS, APARTMENTS, TOWERS, LOFTS
    // MAPPING (Not necessary or may be yes...?)
    HOUSES("houses"),
    RURALS("rurals"),
    FLATS("flats"),
    APARTMENTS("apartments"),
    TOWERS("towers"),
    LOFTS("lofts");


    companion object {
        fun fromTypeName(name: String): ApartmentType {
            when (name) {
                "houses" -> return ApartmentType.HOUSES
                "rurals" -> return ApartmentType.RURALS
                "flats" -> return ApartmentType.FLATS
                "apartments" -> return ApartmentType.APARTMENTS
                "towers" -> return ApartmentType.TOWERS
                "lofts" -> return ApartmentType.LOFTS
                else -> throw IllegalArgumentException("ApartmentType [" + name + "] not supported.")
            }
        }
    }

}
