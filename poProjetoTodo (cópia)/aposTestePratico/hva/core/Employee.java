package hva.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Employee extends HotelEntity implements Serializable {

    private static final long serialVersionUID = 202208091753L;
    private String _empType;
    private List<String> _responsabilitys;

    //Construtor
    public Employee(String idEmployee, String nameEmployee, String empType, List<String> responsibilitys) {
        // Chama o construtor da classe HotelEntity
        super(idEmployee, nameEmployee);
        _empType = empType;
        _responsabilitys = responsibilitys;
    }

    public String getEmpType() {
        return _empType;
    }

    public List<String> getResponsabilitys(){
        if (_responsabilitys == null){
            _responsabilitys = new ArrayList<>();
        }
        return _responsabilitys;
    }

    public void setResponsibilities(List<String> responsibilities) {
        _responsabilitys = responsibilities;
    }


    // Metodo que serve para imprimir no formato desejado
    public String employeeStringed() {
        String responsabilitysString; // Renomeando para refletir o correto
        if (_responsabilitys != null && _responsabilitys.size() != 0) {
            responsabilitysString = String.join(",", _responsabilitys); // Corrigido para atribuição
            return String.join(
                "|",
                getEmpType(),
                getID(), 
                getName(),
                responsabilitysString);
        }else{
            return String.join(
                "|",
                getEmpType(),
                getID(), 
                getName());
    }
        
}
}
