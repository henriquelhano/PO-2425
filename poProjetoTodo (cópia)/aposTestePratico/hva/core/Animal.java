package hva.core;

import java.io.Serializable;
import java.util.List;

public class Animal extends HotelEntity implements Serializable{

    private static final long serialVersionUID = 202208091753L;
    private String _speciesId;
    private String _habitatId;
    private List<String> _historialSaude;

    // Construtor
    public Animal(String animalId, String name, List<String> historialSaude, String speciesId, String habitatId) {
        // Chama o construtor da classe HotelEntity
        super(animalId, name);
        _speciesId = speciesId;
        _habitatId = habitatId;
        _historialSaude = historialSaude;
    }


    // Getters
    public String getSpeciesId() {
        return _speciesId;
    }

    public String getHabitatId() {
        return _habitatId;
    }

    public void alterarHabitat(String idNovoHabitat){
        _habitatId = idNovoHabitat;
    }

    public List<String> getHistorialSaude(){
        return _historialSaude;
    }

    public void setHistorialSaude(List<String> historialSaude) {
        _historialSaude = historialSaude;
    }

    

    // Metodo que serve para imprimir no formato desejado
    public String animalStringed() {
        String historialSaudeString;
        if (_historialSaude != null) {
            historialSaudeString = String.join(",", _historialSaude); 
            return String.join(
                "|",
                "ANIMAL",
                getID(),
                getName(),
                getSpeciesId(),
                historialSaudeString,
                getHabitatId());
        }else {
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
}
