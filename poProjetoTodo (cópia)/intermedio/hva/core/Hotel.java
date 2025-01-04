package hva.core;

import hva.app.exception.DuplicateAnimalKeyException;
import hva.app.exception.DuplicateEmployeeKeyException;
import hva.app.exception.DuplicateHabitatKeyException;
import hva.app.exception.DuplicateVaccineKeyException;
import hva.app.exception.UnknownAnimalKeyException;
import hva.core.exception.*;
import java.io.*;
import java.util.*;

public class Hotel implements Serializable {

  @Serial
  private static final long serialVersionUID = 202208091753L;
  private TreeMap<String, Animal> _animals;
  private TreeMap<String, Habitat> _habitats;
  private TreeMap<String, Vaccine> _vaccines;
  private TreeMap<String, Employee> _employees;

  // Construtor
  public Hotel() {
    _animals = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    _habitats = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    _vaccines = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    _employees = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
  }

  /**
   * Metodo responsavel por importar um ficheiro
   * 
   * @param filename
   * @throws UnrecognizedEntryException
   * @throws IOException
   */

  void importFile(String filename) throws UnrecognizedEntryException, IOException {
    Parser parser = new Parser(this);
    parser.parseFile(filename);
  }

  /**
   * ##########################
   * Metodos do Animal
   * ##########################
   */

  /**
   * Metodo que regista um animal no hotel
   * 
   * @param animalId
   * @param name
   * @param habitatId
   * @param speciesId
   * @throws DuplicateAnimalKeyException
   */

  public void registerAnimal(String animalId, String name, String habitatId, String speciesId)
      throws DuplicateAnimalKeyException {
    if (_animals.containsKey(animalId)) {
      throw new DuplicateAnimalKeyException("Animal com ID " + animalId + " já está registado.");
    }

    Animal animal = new Animal(animalId, name, speciesId, habitatId);
    _animals.put(animalId, animal);
  }

  /**
   * 
   * @return uma ArrayList com todos os animais do hotel
   */
  public ArrayList<String> getAllAnimals() {
    ArrayList<String> stringAnimals = new ArrayList<>();

    for (String animalKey : _animals.keySet()) {
      Animal animal = _animals.get(animalKey);
      stringAnimals.add(animal.animalStringed());
    }

    return stringAnimals;
  }

  /**
   * ##########################
   * Metodos do Habitat
   * ##########################
   */

  /**
   * Metodo que regista um habitat no hotel
   * 
   * @param habitatId
   * @param name
   * @param area
   * @throws DuplicateHabitatKeyException
   */

  public void registerHabitat(String habitatId, String name, int area) throws DuplicateHabitatKeyException {
    if (_habitats.containsKey(habitatId)) {
      throw new DuplicateHabitatKeyException("Habitat com ID " + habitatId + " já está registado.");
    }

    Habitat habitat = new Habitat(habitatId, name, area);
    _habitats.put(habitatId, habitat);

  }

  /**
   * @return uma ArrayList com todos os habitats do hotel
   */
  public ArrayList<String> getAllHabitats() {
    ArrayList<String> stringHabitat = new ArrayList<>();

    for (String habitatKey : _habitats.keySet()) {
      Habitat habitat = _habitats.get(habitatKey);
      stringHabitat.add(habitat.habitatStringed());
    }

    return stringHabitat;
  }

  /**
   * ##########################
   * Metodos da Vacina
   * ##########################
   */

  /**
   * Metodo que regista uma vacina no hotel
   * 
   * @param vacineId
   * @param name
   * @param especiesCompativeis
   * @throws DuplicateVaccineKeyException
   */
  public void registerVaccine(String vacineId, String name, String[] especiesCompativeis)
      throws DuplicateVaccineKeyException {
    if (_vaccines.containsKey(vacineId)) {
      throw new DuplicateVaccineKeyException("Vacina com ID " + vacineId + " já está registada.");
    }

    Vaccine vaccine = new Vaccine(vacineId, name, especiesCompativeis);
    _vaccines.put(vacineId, vaccine);

  }

  /**
   * 
   * @return uma ArrayList com todos os habitats do hotel
   */
  public ArrayList<String> getAllVaccines() {
    ArrayList<String> stringVaccine = new ArrayList<>();

    for (String vaccineKey : _vaccines.keySet()) {
      Vaccine vaccine = _vaccines.get(vaccineKey);

      String[] especies = vaccine.getEspeciesCompativeis();
      if (especies.length == 0) {
        stringVaccine.add(vaccine.vaccineStringed());
      } else {
        stringVaccine.add(vaccine.vaccineCompativeisStringed());
      }
    }

    return stringVaccine;
  }

  /**
   * ##########################
   * Metodos do Funcionario
   * ##########################
   */
  /**
   * Metodo que regista um funcionario no hotel
   * 
   * @param employeeId
   * @param name
   * @param empType
   * @throws DuplicateEmployeeKeyException
   */
  public void registerEmployee(String employeeId, String name, String empType)
      throws DuplicateEmployeeKeyException {
    if (_employees.containsKey(employeeId)) {
      throw new DuplicateEmployeeKeyException("Employee com ID " + employeeId + " já está registado.");
    }
    if (empType == "VET") {
      Veterinarian veterinarian = new Veterinarian(employeeId, name);
      _employees.put(employeeId, veterinarian);
    }
    if (empType == "TRT") {
      ZooKeeper zooKeeper = new ZooKeeper(employeeId, name);
      _employees.put(employeeId, zooKeeper);
    }
  }

  /**
   * 
   * @return uma ArrayList com todos os funcionarios do hotel
   */
  public ArrayList<String> getAllEmployees() {
    ArrayList<String> stringEmployee = new ArrayList<>();

    for (String employeeKey : _employees.keySet()) {
      Employee employee = _employees.get(employeeKey);
      stringEmployee.add(employee.employeeStringed());
    }

    return stringEmployee;
  }
}
