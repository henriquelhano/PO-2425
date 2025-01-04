package hva.core;

import java.io.Serializable;

public class Habitat extends HotelEntity implements Serializable{

    private static final long serialVersionUID = 202208091753L;
    private int _area;

    // Construtor
    public Habitat(String habitatId, String name, int area) {
        // Chama o construtor da classe HotelEntity
        super(habitatId, name);
        _area = area;
    }

    // Getters
    public int getArea() {
        return _area;
    }

    // Metodo que serve para imprimir no formato desejado
    public String habitatStringed() {
        return String.join(
                "|",
                "HABITAT",
                getID(), // Usando o método herdado de HotelEntity
                getName(), // Usando o método herdado de HotelEntity
                String.valueOf(getArea()),
                "0");
    }
}
