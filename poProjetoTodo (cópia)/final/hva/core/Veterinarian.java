package hva.core;

import java.util.List;

public class Veterinarian extends Employee{
    
    public Veterinarian(String id, String name, List<String> responsibilitys){
        super(id, name, "VET", responsibilitys);
    }
    
}