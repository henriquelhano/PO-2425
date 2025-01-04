package hva.core;

import java.io.Serializable;

public abstract class Employee extends HotelEntity implements Serializable {

    private static final long serialVersionUID = 202208091753L;
    private String _empType;

    //Construtor
    public Employee(String idEmployee, String nameEmployee, String empType) {
        // Chama o construtor da classe HotelEntity
        super(idEmployee, nameEmployee);
        _empType = empType;
    }

    public String getEmpType() {
        return _empType;
    }

    // Metodo que serve para imprimir no formato desejado
    public String employeeStringed() {
        return String.join(
                "|",
                getEmpType(),
                getID(), 
                getName());
    }
}