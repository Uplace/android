package es.uplace.uplace.domain;

import es.uplace.uplace.domain.enumeration.ApartmentType;
import es.uplace.uplace.domain.enumeration.EnergyCertificate;
import es.uplace.uplace.domain.enumeration.Select;

public class Apartment extends Property {

    private Integer numberBedrooms;
    private Integer numberBathrooms;
    private Integer m2Edified;
    private Integer m2Usable;
    private Integer height;
    private Select elevator;
    private Select ac;
    private Select heat;
    private Select parking;
    private Select terrace;
    private Select balcony;
    private Integer surfaceTerrace;
    private Integer surfaceSaloon;
    private ApartmentType type;
    private Select office;
    private Select kitchenOffice;
    private Select storage;
    private Select sharedPool;
    private Select nearTransport;
    private Select reformed;
    private EnergyCertificate energyCertificate;
    private Select certificateHabitability;
}
