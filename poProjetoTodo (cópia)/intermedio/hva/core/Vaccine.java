package hva.core;

import java.io.Serializable;

public class Vaccine extends HotelEntity implements Serializable {

    private static final long serialVersionUID = 202208091753L;
    private String[] _especiesCompativeis;

    //Construtor
    public Vaccine(String vaccineId, String name, String[] especiesCompativeis) {
        // Chama o construtor da classe HotelEntity
        super(vaccineId, name);
        _especiesCompativeis = especiesCompativeis;
    }

    // Getters
    public String getVaccineId() {
        return getID();
    }

    public String[] getEspeciesCompativeis() {
        return _especiesCompativeis;
    }

    // Metodos que servem para imprimir no formato desejado
    public String vaccineStringed() {
        return String.join(
                "|",
                "VACINA",
                getVaccineId(),
                getName(),
                "0");
    }

    public String vaccineCompativeisStringed() {
        return String.join(
                "|",
                "VACINA",
                getVaccineId(),
                getName(),
                "0",
                String.join(",", getEspeciesCompativeis()));
    }
}