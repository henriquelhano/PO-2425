package hva.core;

import java.io.Serializable;

public class Vaccine extends HotelEntity implements Serializable {

    private static final long serialVersionUID = 202208091753L;
    private String[] _especiesCompativeis;
    private int _numeroAplicacoes = 0;

    //Construtor
    public Vaccine(String vaccineId, String name, int numeroAplicacoes, String[] especiesCompativeis) {
        // Chama o construtor da classe HotelEntity
        super(vaccineId, name);
        _especiesCompativeis = especiesCompativeis;
        _numeroAplicacoes = numeroAplicacoes;
    }

    // Getters
    public String getVaccineId() {
        return getID();
    }

    public String[] getEspeciesCompativeis() {
        return _especiesCompativeis;
    }

    public int getNumeroAplicacoes(){
        return _numeroAplicacoes;
    }

    public void addNumeroAplicacoes(){
        _numeroAplicacoes += 1;
    }


    // Metodos que servem para imprimir no formato desejado
    public String vaccineStringed() {
        return String.join(
                "|",
                "VACINA",
                getVaccineId(),
                getName(),
                String.valueOf(getNumeroAplicacoes()));
    }

    public String vaccineCompativeisStringed() {
        return String.join(
                "|",
                "VACINA",
                getVaccineId(),
                getName(),
                String.valueOf(getNumeroAplicacoes()),
                String.join(",", getEspeciesCompativeis()));
    }
}