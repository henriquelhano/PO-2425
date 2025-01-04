package hva.core;

import java.io.Serializable;

public class Animal extends HotelEntity implements Serializable{

    private static final long serialVersionUID = 202208091753L;
    private String _speciesId;
    private String _habitatId;

    // Construtor
    public Animal(String animalId, String name, String speciesId, String habitatId) {
        // Chama o construtor da classe HotelEntity
        super(animalId, name);
        _speciesId = speciesId;
        _habitatId = habitatId;
    }


    // Getters
    public String getSpeciesId() {
        return _speciesId;
    }

    public String getHabitatId() {
        return _habitatId;
    }

    // Metodo que serve para imprimir no formato desejado
    public String animalStringed() {
        return String.join(
                "|",
                "ANIMAL",
                getID(),
                getName(),
                getSpeciesId(),
                "VOID",
                getHabitatId());
    }

}
