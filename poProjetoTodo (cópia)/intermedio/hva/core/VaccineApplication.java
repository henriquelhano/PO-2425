package hva.core;

public class VaccineApplication {
    private int _numberApplications;
    private Veterinarian _veterinarian;
    private Animal _animal;

    public int getNumberApplications() {
        return _numberApplications;
    }

    public Veterinarian getVeterinarian() {
        return _veterinarian;
    }

    public Animal getAnimal() {
        return _animal;
    }
}