package es.uplace.uplace.domain.enumeration;

public enum ApartmentType {
    // HOUSES, RURALS, FLATS, APARTMENTS, TOWERS, LOFTS
    // MAPPING (Not necessary or may be yes...?)
    HOUSES("houses"), RURALS("rurals"), FLATS("flats"), APARTMENTS("apartments"), TOWERS("towers"), LOFTS("lofts");

    private String apartmentType;

    ApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }

    public String getTypeName() {
        return apartmentType;
    }

    public static ApartmentType fromTypeName(String name) {
        switch (name) {
            case "houses":
                return ApartmentType.HOUSES;
            case "rurals":
                return ApartmentType.RURALS;
            case "flats":
                return ApartmentType.FLATS;
            case "apartments":
                return ApartmentType.APARTMENTS;
            case "towers":
                return ApartmentType.TOWERS;
            case "lofts":
                return ApartmentType.LOFTS;
            default:
                throw new IllegalArgumentException("ApartmentType [" + name
                    + "] not supported.");
        }
    }

}
