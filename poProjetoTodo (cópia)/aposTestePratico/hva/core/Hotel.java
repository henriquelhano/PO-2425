package hva.core;

import hva.app.animal.Prompt;
import hva.app.exception.DuplicateAnimalKeyException;
import hva.app.exception.DuplicateEmployeeKeyException;
import hva.app.exception.DuplicateHabitatKeyException;
import hva.app.exception.DuplicateTreeKeyException;
import hva.app.exception.DuplicateVaccineKeyException;
import hva.app.exception.NoResponsibilityException;
import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownEmployeeKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.app.exception.UnknownVaccineKeyException;
import hva.app.exception.UnknownVeterinarianKeyException;
import hva.app.exception.VeterinarianNotAuthorizedException;
import hva.app.vaccine.Message;
import hva.core.exception.*;

import java.io.*;
import java.util.*;

public class Hotel implements Serializable {

  @Serial
  private static final long serialVersionUID = 202208091753L;
  private TreeMap<String, Especie> _especies;
  TreeMap<String, Animal> _animals;
  private TreeMap<String, Habitat> _habitats;
  private TreeMap<String, Vaccine> _vaccines;
  TreeMap<String, Employee> _employees;
  private TreeMap<String, Veterinarian> _veterinarian;
  private TreeMap<String, ZooKeeper> _zooKeeper;
  private TreeMap<String, Tree> _trees;
  private List<String> _historialVacinacao;
  private List<String> _vacinasErradas;
  private Season _seasonAtual = new Season();

  // Construtor
  public Hotel() {
    _especies = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    _animals = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    _habitats = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    _vaccines = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    _employees = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    _trees = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    _veterinarian = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    _zooKeeper = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    _historialVacinacao = new ArrayList<>();
    _vacinasErradas = new ArrayList<>();
  }

  /**
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
   * Metodos da Especie
   * ##########################
   */

  /**
   * 
   * @param id
   * @param name
   */
  public void registerSpecies(String id, String name) {
    Especie especie = new Especie(id, name);
    _especies.put(id, especie);
  }

  /**
   * ##########################
   * Metodos do Animal
   * ##########################
   */

  /**
   * 
   * @param animalId
   * @param name
   * @param speciesId
   * @param habitatId
   * @throws DuplicateAnimalKeyExceptionCore
   * @throws UnknownHabitatKeyExceptionCore
   */
  public void registerAnimal(String animalId, String name, String speciesId, String habitatId)
      throws DuplicateAnimalKeyExceptionCore, UnknownHabitatKeyExceptionCore {
    if (_animals.containsKey(animalId)) {
      throw new DuplicateAnimalKeyExceptionCore(animalId);
    }

    if (!_habitats.containsKey(habitatId)) {
      throw new UnknownHabitatKeyExceptionCore(habitatId);
    }

    boolean speciesExist = false;
    for (String especie : _especies.keySet()) {
      if (especie.equals(speciesId)) {
        speciesExist = true;
        break;
      }
    }
    if (speciesExist == false) {
      registerSpecies(speciesId, name);
    }

    Animal animal = new Animal(animalId, name, null, speciesId, habitatId);
    _animals.put(animalId, animal);
  }

  /**
   * 
   * @return
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
   * 
   * @param idHabitat
   * @param idAnimal
   */
  public void adicionarAnimal(String idHabitat, String idAnimal) {
    if (_habitats.containsKey(idHabitat)) {
      Habitat habitat = _habitats.get(idHabitat);
      if (_animals.containsKey(idAnimal)) {
        habitat.addAnimal(idAnimal);
      }
    }
  }

  /**
   * 
   * @param idHabitat
   * @param idAnimal
   */
  public void removeAnimal(String idHabitat, String idAnimal) {
    if (_habitats.containsKey(idHabitat)) {
      Habitat habitat = _habitats.get(idHabitat);
      habitat.removeAnimal(idAnimal);
    }
  }

  /**
   * 
   * @param idAnimal
   * @param idHabitatNovo
   * @throws UnknownAnimalKeyExceptionCore
   * @throws UnknownHabitatKeyExceptionCore
   */
  public void transfereAnimal(String idAnimal, String idHabitatNovo)
      throws UnknownAnimalKeyExceptionCore, UnknownHabitatKeyExceptionCore {
    if (!_animals.containsKey(idAnimal)) {
      throw new UnknownAnimalKeyExceptionCore(idAnimal);
    }
    Animal animal = _animals.get(idAnimal);
    String idHabitatAtual = animal.getHabitatId();

    if (!_habitats.containsKey(idHabitatNovo)) {
      throw new UnknownHabitatKeyExceptionCore(idHabitatNovo);
    }
    removeAnimal(idHabitatAtual, idAnimal);
    adicionarAnimal(idHabitatNovo, idAnimal);
    animal.alterarHabitat(idHabitatNovo);
  }

  /**
   * Para calcular a satifação do animal com especies iguais extra dai o contador-1 
   * @param a
   * @return
   */
  public int especiesIguais(Animal a) {
    int contador = 0;
    String especie = a.getSpeciesId();

    for (String i : _animals.keySet()) {
      Animal animal = _animals.get(i);
      String habitatDoAnimal = animal.getHabitatId();
      if (a.getHabitatId().equals(habitatDoAnimal)) {
        if (animal.getSpeciesId().equals(especie)) {
          contador += 1;
        }
      }
    }

    return contador - 1;
  }

  /**
   * Para calcular a satifação do animal 
   * @param a
   * @return
   */
  public int especiesDiferentes(Animal a) {
    int contadorDiferentes = 0;
    String especie = a.getSpeciesId();

    for (String i : _animals.keySet()) {
      Animal animal = _animals.get(i);
      String habitatDoAnimal = animal.getHabitatId();
      if (a.getHabitatId().equals(habitatDoAnimal)) {
        if (!animal.getSpeciesId().equals(especie)) {
          contadorDiferentes += 1;
        }
      }
    }
    return contadorDiferentes;
  }

  /**
   * 
   * @param h
   * @return
   */
  public int contaPopulacao(Habitat h) {
    int contador = 0;
    for (String i : _animals.keySet()) {
      Animal animal = _animals.get(i);
      String habitatDoAnimal = animal.getHabitatId();

      if (habitatDoAnimal.equals(h.getID())) {
        contador++;
      }
    }
    return contador;
  }

  /**
   * 
   * @param idHabitat
   * @return
   */
  public int adequacao(String idHabitat) {
    int adequacao = 0;
    Habitat habitat = _habitats.get(idHabitat);
    var stringInfluencia = habitat.getAdequacao();
    if (stringInfluencia.equals("NEU")) {
      adequacao = 0;
    } else if (stringInfluencia.equals("POS")) {
      adequacao = 20;
    } else if (stringInfluencia.equals("NEG")) {
      adequacao = -20;
    }
    return adequacao;
  }

  /**
   * 
   * @param idAnimal
   * @return
   * @throws UnknownAnimalKeyExceptionCore
   */
  public int calculaSatisfacaoAnimal(String idAnimal) throws UnknownAnimalKeyExceptionCore {
    if (!_animals.containsKey(idAnimal)) {
      throw new UnknownAnimalKeyExceptionCore(idAnimal);
    }
    Animal animal = _animals.get(idAnimal);
    var especie = animal.getSpeciesId();
    String habitatDoAnimal = animal.getHabitatId();
    Habitat habitatEmQuestao = _habitats.get(habitatDoAnimal);
    var especiesInfluenciadas = habitatEmQuestao.getEspecies();
    if (!especiesInfluenciadas.contains(especie)) {
      int res = 20 + 3 * especiesIguais(animal) - 2 * especiesDiferentes(animal)
          + (habitatEmQuestao.getArea()) / contaPopulacao(habitatEmQuestao);
      return Math.round(res);
    } else {
      int res = 20 + 3 * especiesIguais(animal) - 2 * especiesDiferentes(animal)
          + (habitatEmQuestao.getArea()) / contaPopulacao(habitatEmQuestao) + adequacao(habitatDoAnimal);
      return Math.round(res);
    }

  }

  /**
   * ##########################
   * Metodos do Habitat e Arvores
   * ##########################
   */

  /**
   * 
   * @param habitatId
   * @param name
   * @param area
   * @param trees
   * @throws DuplicateHabitatKeyExceptionCore
   */
  public void registerHabitat(String habitatId, String name, int area, List<String> trees)
      throws DuplicateHabitatKeyExceptionCore {

    if (_habitats.containsKey(habitatId)) {
      throw new DuplicateHabitatKeyExceptionCore(habitatId);
    }

    Habitat habitat = new Habitat(habitatId, name, area, null, trees);
    _habitats.put(habitatId, habitat);
  }

  /**
   * 
   * @param id
   * @param name
   * @param age
   * @param diff
   * @param type
   * @throws DuplicateTreeKeyExceptionCore
   */
  public void createTree(String id, String name, int age, int diff, String type) throws DuplicateTreeKeyExceptionCore {
    if (_trees.containsKey(id)) {
      throw new DuplicateTreeKeyExceptionCore(id);
    }

    Tree tree = null;
    if (type.equals("CADUCA")) {
      tree = new DeciduousTree(id, name, age, diff, _seasonAtual);

    } else if (type.equals("PERENE")) {
      tree = new EvergreenTree(id, name, age, diff, _seasonAtual);
    }

    _trees.put(id, tree);
  }

  /**
   * 
   * @return
   */
  public List<String> getAllHabitats() {
    List<String> stringHabitatTrees = new ArrayList<>();

    for (String habitatKey : _habitats.keySet()) {
      Habitat habitat = _habitats.get(habitatKey);
      // Obter os habitats todos no TreeMap _habitats
      stringHabitatTrees.add(habitat.habitatStringed());

      var habitatTrees = habitat.getTrees();
      if (null != habitatTrees) {
        for (String treeKey : habitatTrees) {
          // Obter as árvores todas nesse habitat
          Tree tree = _trees.get(treeKey);
          stringHabitatTrees.add(tree.treeStringed());
        }
      }
    }

    return stringHabitatTrees;
  }

  /**
   * 
   * @param habitatId
   * @param novaArea
   * @return
   * @throws UnknownHabitatKeyExceptionCore
   */
  public int setArea(String habitatId, int novaArea) throws UnknownHabitatKeyExceptionCore {
    if (!_habitats.containsKey(habitatId)) {
      throw new UnknownHabitatKeyExceptionCore(habitatId);
    }
    for (String habitatKey : _habitats.keySet()) {
      Habitat habitat = _habitats.get(habitatKey);
      if (habitatKey.equals(habitatId)) {
        habitat.alterarArea(novaArea);
      }
    }
    return novaArea;
  }

  /**
   * 
   * @param idHabitat
   * @param idEspecie
   * @param influenciaHabitat
   * @throws UnknownHabitatKeyExceptionCore
   * @throws UnknownSpeciesKeyExceptionCore
   */
  public void alterarAdequacao(String idHabitat, String idEspecie, String influenciaHabitat)
      throws UnknownHabitatKeyExceptionCore, UnknownSpeciesKeyExceptionCore {
    if (!_habitats.containsKey(idHabitat)) {
      throw new UnknownHabitatKeyExceptionCore(idHabitat);
    }
    if (!_especies.containsKey(idEspecie)) {
      throw new UnknownSpeciesKeyExceptionCore(idEspecie);
    }
    Habitat habitat = _habitats.get(idHabitat);

    habitat.setAdequecao(influenciaHabitat, idEspecie);
  }

  /**
   * 
   * @param idHabitat
   * @param id
   * @param name
   * @param age
   * @param diff
   * @param type
   * @return
   * @throws UnknownHabitatKeyExceptionCore
   * @throws DuplicateTreeKeyExceptionCore
   */
  public String registerTreeHabitat(String idHabitat, String id, String name, int age, int diff, String type)
      throws UnknownHabitatKeyExceptionCore, DuplicateTreeKeyExceptionCore {
    if (!_habitats.containsKey(idHabitat)) {
      throw new UnknownHabitatKeyExceptionCore(idHabitat);
    }

    if (_trees.containsKey(id)) {
      throw new DuplicateTreeKeyExceptionCore(id);
    }

    createTree(id, name, age, diff, type);
    Habitat habitat = _habitats.get(idHabitat);
    habitat.addTree(id);

    Tree tree = _trees.get(id);
    return tree.treeStringed();
  }

  /**
   * 
   * @param habitatId
   * @return
   * @throws UnknownHabitatKeyExceptionCore
   */
  public List<String> doShowAllTreesInHabitat(String habitatId) throws UnknownHabitatKeyExceptionCore {
    if (!_habitats.containsKey(habitatId)) {
      throw new UnknownHabitatKeyExceptionCore(habitatId);
    }

    List<String> treesInHabitat = new ArrayList<>();

    Habitat habitat = _habitats.get(habitatId);
    List<String> trees = habitat.getTrees();
    if (trees != null && trees.size() > 0) {
      for (String treeKey : trees) {
        Tree tree = _trees.get(treeKey);
        treesInHabitat.add(tree.treeStringed());
      }
    }

    return treesInHabitat;
  }

  /**
   * ##########################
   * Metodos da Vacina
   * ##########################
   */

  /**
   * 
   * @param vacineId
   * @param name
   * @param especiesCompativeis
   * @throws DuplicateVaccineKeyExceptionCore
   * @throws UnknownSpeciesKeyExceptionCore
   */
  public void registerVaccine(String vacineId, String name, String[] especiesCompativeis)
      throws DuplicateVaccineKeyExceptionCore, UnknownSpeciesKeyExceptionCore {
    if (_vaccines.containsKey(vacineId)) {
      throw new DuplicateVaccineKeyExceptionCore(vacineId);
    }

    for (String e : especiesCompativeis) {
      if (!_especies.containsKey(e)) {
        throw new UnknownSpeciesKeyExceptionCore(e);
      }
    }

    Vaccine vaccine = new Vaccine(vacineId, name, 0, especiesCompativeis);
    _vaccines.put(vacineId, vaccine);

  }

  /**
   * 
   * @return
   */
  public ArrayList<String> getAllVaccines() {
    ArrayList<String> stringVaccine = new ArrayList<>();

    for (String vaccineKey : _vaccines.keySet()) {
      Vaccine vaccine = _vaccines.get(vaccineKey);

      String[] especies = vaccine.getEspeciesCompativeis();
      if (especies.length == 0) { // Se nenhuma espécie for compativel 
        stringVaccine.add(vaccine.vaccineStringed());
      } else {
        stringVaccine.add(vaccine.vaccineCompativeisStringed()); // Se houver espécies compatíveis
      }
    }

    return stringVaccine;
  }

  /**
   * 
   * @param idVacina
   * @param idAnimal
   * @return
   */
  public int calculateDamage(String idVacina, String idAnimal) {
    int dano = 0;
    int nomeMaior = 0;
    int caracteresEmComum = 0;

    Vaccine vaccine = _vaccines.get(idVacina);
    Animal animal = _animals.get(idAnimal);
    var animalSpecie = animal.getSpeciesId();
    Especie especie = _especies.get(animalSpecie);
    var nameEspecie = especie.getName();
    var especieCompativeis = vaccine.getEspeciesCompativeis();

    for (String e : especieCompativeis) {
      if (animalSpecie.equals(e)) {
        return dano = -1;
      }
      Especie especieCompativel = _especies.get(e);
      var nomeEspecieCompativel = especieCompativel.getName();
      if (nameEspecie.length() > nomeEspecieCompativel.length()) {
        nomeMaior = nameEspecie.length();
      } else if (nameEspecie.length() < nomeEspecieCompativel.length()) {
        nomeMaior = nomeEspecieCompativel.length();
      } else if (nameEspecie.length() == nomeEspecieCompativel.length()) {
        nomeMaior = nameEspecie.length();
      }
    }
    for (String e : especieCompativeis) {
      Especie especieCompativel = _especies.get(e);
      var nomeEspecieCompativel = especieCompativel.getName();
      caracteresEmComum = contarCaracteresEmComum(nameEspecie, nomeEspecieCompativel);
    }
    dano = nomeMaior - caracteresEmComum;
    return dano;
  }

  /**
   * 
   * @param especie1
   * @param especie2
   * @return
   */
  private static int contarCaracteresEmComum(String especie1, String especie2) {
    Set<Character> setEspecie1 = new HashSet<>();
    for (char c : especie1.toCharArray()) {
      setEspecie1.add(c);
    }

    Set<Character> setEspecie2 = new HashSet<>();
    for (char c : especie2.toCharArray()) {
      setEspecie2.add(c);
    }

    setEspecie1.retainAll(setEspecie2);

    return setEspecie1.size();
  }

  /**
   * 
   * @param idVacina
   * @param idVeterinario
   * @param idAnimal
   * @return
   * @throws UnknownVaccineKeyExceptionCore
   * @throws UnknownVeterinarianKeyExceptionCore
   * @throws VeterinarianNotAuthorizedExceptionCore
   */
  public String vaccineAplication(String idVacina, String idVeterinario, String idAnimal)
      throws UnknownVaccineKeyExceptionCore, UnknownVeterinarianKeyExceptionCore,
      VeterinarianNotAuthorizedExceptionCore {
    if (!_vaccines.containsKey(idVacina)) {
      throw new UnknownVaccineKeyExceptionCore(idVacina);
    }
    if (!_veterinarian.containsKey(idVeterinario)) {
      throw new UnknownVeterinarianKeyExceptionCore(idVeterinario);
    }
    Vaccine vaccine = _vaccines.get(idVacina);
    Employee employee = _employees.get(idVeterinario);
    String veterinarianName = employee.getName();
    var resposabilities = employee.getResponsabilitys();
    Veterinarian veterinarian = new Veterinarian(idVeterinario, veterinarianName, resposabilities);
    var veterinarianResponsabilities = veterinarian.getResponsabilitys();
    if (veterinarianResponsabilities.contains(idAnimal)) {
      throw new VeterinarianNotAuthorizedExceptionCore(idVeterinario, idAnimal);
    }

    String mensagem = "";
    Animal animal = _animals.get(idAnimal);
    String idEspecie = animal.getSpeciesId();
    List<String> historicoSaude = new ArrayList<>();
    var estadoSaude = calculateDamage(idVacina, idAnimal);
    if (estadoSaude == -1) {
      historicoSaude.add("NORMAL");
    } else if (estadoSaude == 0) {
      historicoSaude.add("CONFUSÃO");
      mensagem = Message.wrongVaccine(idVacina, idAnimal);
      _vacinasErradas.add("REGISTO-VACINA|" + idVacina + "|" + idVeterinario + "|" + idEspecie);
    } else if (estadoSaude >= 1 && estadoSaude <= 4) {
      historicoSaude.add("ACIDENTE");
      mensagem = Message.wrongVaccine(idVacina, idAnimal);
      _vacinasErradas.add("REGISTO-VACINA|" + idVacina + "|" + idVeterinario + "|" + idEspecie);
    } else if (estadoSaude > 4) {
      historicoSaude.add("ERRO");
      mensagem = Message.wrongVaccine(idVacina, idAnimal);
      _vacinasErradas.add("REGISTO-VACINA|" + idVacina + "|" + idVeterinario + "|" + idEspecie);
    }
    animal.setHistorialSaude(historicoSaude);
    vaccine.addNumeroAplicacoes();
    _historialVacinacao.add("REGISTO-VACINA|" + idVacina + "|" + idVeterinario + "|" + idEspecie);

    return mensagem;

  }

  /**
   * 
   * @return
   */
  public List<String> getHistoricoVacinacao() {
    return _historialVacinacao;
  }

  /**
   * ##########################
   * Metodos do Funcionario
   * ##########################
   */

  /**
   * 
   * @param employeeId
   * @param name
   * @param empType
   * @param responsibilitys
   * @throws DuplicateEmployeeKeyExceptionCore
   */
  public void registerEmployee(String employeeId, String name, String empType, List<String> responsibilitys)
      throws DuplicateEmployeeKeyExceptionCore {
    if (_employees.containsKey(employeeId)) {
      throw new DuplicateEmployeeKeyExceptionCore(employeeId);
    }
    if (empType.equals("VET")) {
      if (responsibilitys == null) {
        Veterinarian veterinarian = new Veterinarian(employeeId, name, null);
        _employees.put(employeeId, veterinarian);
        _veterinarian.put(employeeId, veterinarian);
      } else {
        Veterinarian veterinarian = new Veterinarian(employeeId, name, responsibilitys);
        _employees.put(employeeId, veterinarian);
        _veterinarian.put(employeeId, veterinarian);
      }
    }
    if (empType.equals("TRT")) {
      if (responsibilitys == null) {
        ZooKeeper zooKeeper = new ZooKeeper(employeeId, name, null);
        _employees.put(employeeId, zooKeeper);
        _zooKeeper.put(employeeId, zooKeeper);
      } else {
        ZooKeeper zooKeeper = new ZooKeeper(employeeId, name, responsibilitys);
        _employees.put(employeeId, zooKeeper);
        _zooKeeper.put(employeeId, zooKeeper);
      }

    }
  }

  /**
   * 
   * @return
   */
  public ArrayList<String> getAllEmployees() {
    ArrayList<String> stringEmployee = new ArrayList<>();

    for (String employeeKey : _employees.keySet()) {
      Employee employee = _employees.get(employeeKey);
      stringEmployee.add(employee.employeeStringed());
    }
    return stringEmployee;
  }

  /**
   * 
   * @param employeeId
   * @param idResponsabilidade
   * @throws UnknownEmployeeKeyExceptionCore
   * @throws NoResponsibilityExceptionCore
   */
  public void addResponsibility(String employeeId, String idResponsabilidade)
      throws UnknownEmployeeKeyExceptionCore, NoResponsibilityExceptionCore {
    if (!_employees.containsKey(employeeId)) {
      throw new UnknownEmployeeKeyExceptionCore(employeeId);
    }

    Employee employee = _employees.get(employeeId);
    if (employee.getEmpType().equals("TRT")) {
      if (!_habitats.containsKey(idResponsabilidade)) {
        throw new NoResponsibilityExceptionCore(employeeId, idResponsabilidade);
      }
      var responsabilitys = employee.getResponsabilitys();
      responsabilitys.add(idResponsabilidade);
    }

    if (employee.getEmpType().equals("VET")) {
      if (!_especies.containsKey(idResponsabilidade)) {
        throw new NoResponsibilityExceptionCore(employeeId, idResponsabilidade);
      }
      var responsabilitys = employee.getResponsabilitys();
      responsabilitys.add(idResponsabilidade);
    }

  }

  /**
   * 
   * @param employeeId
   * @param idResponsabilidade
   * @throws UnknownEmployeeKeyExceptionCore
   * @throws NoResponsibilityExceptionCore
   */
  public void removeResponsibility(String employeeId, String idResponsabilidade)
      throws UnknownEmployeeKeyExceptionCore, NoResponsibilityExceptionCore {
    if (!_employees.containsKey(employeeId)) {
      throw new UnknownEmployeeKeyExceptionCore(employeeId);
    }

    Employee employee = _employees.get(employeeId);
    if (employee.getEmpType().equals("TRT")) {
      if (!_habitats.containsKey(idResponsabilidade)) {
        throw new NoResponsibilityExceptionCore(employeeId, idResponsabilidade);
      }
      var responsabilitys = employee.getResponsabilitys();
      responsabilitys.remove(idResponsabilidade);
      if (responsabilitys.isEmpty()) {
        employee.setResponsibilities(null);
      }
    }

    if (employee.getEmpType().equals("VET")) {
      if (!_especies.containsKey(idResponsabilidade)) {
        throw new NoResponsibilityExceptionCore(employeeId, idResponsabilidade);
      }
      var responsabilitys = employee.getResponsabilitys();
      responsabilitys.remove(idResponsabilidade);
      if (responsabilitys.isEmpty()) {
        employee.setResponsibilities(null);
      }
    }

  }

  /**
   * 
   * @param idVet
   * @return
   */
  public int calculaSatisfacaoVet(String idVet) {
    int result = 0;
    int populacao = 0;
    int numeroVet = 0;
    Employee employee = _employees.get(idVet);
    var responsabilidades = employee.getResponsabilitys();
    for (String responsability : responsabilidades) {
      for (String a : _animals.keySet()) {
        Animal animal = _animals.get(a);
        var specieAnimal = animal.getSpeciesId();
        if (specieAnimal.equals(responsability)) {
          populacao += 1;
        }
      }
      int resultadoPopulacao = populacao;
      populacao = 0;
      for (String vet : _veterinarian.keySet()) {
        Employee veterinario = _veterinarian.get(vet);
        var responsabilitys = veterinario.getResponsabilitys();
        if (responsabilitys.contains(responsability)) {
          numeroVet += 1;
        }
      }
      int resultadoVet = numeroVet;
      numeroVet = 0;

      result += (resultadoPopulacao / resultadoVet);
    }

    return 20 - Math.round(result);
  }

  /**
   * 
   * @param idTrt
   * @return
   */
  public int calculaSatisfacaoTrt(String idTrt) {
    int resultado = 0;
    int numeroTratadoresHabitat = 0;
    int trabalhoHabitat = 0;
    int populacao = 0;
    int esforcoLimpeza = 0;

    Employee employee = _employees.get(idTrt);
    var responsabilidades = employee.getResponsabilitys();
    for (String responsability : responsabilidades) {
      Habitat habitat = _habitats.get(responsability);
      var treesInHabitat = habitat.getTrees();
      for (String zoo : _zooKeeper.keySet()) {
        Employee zookeeper = _zooKeeper.get(zoo);
        var responsabilitys = zookeeper.getResponsabilitys();
        if (responsabilitys.contains(responsability)) {
          numeroTratadoresHabitat += 1;
        }
      }
      int resultadoTratadorHabitat = numeroTratadoresHabitat;
      numeroTratadoresHabitat = 0;

      var habitatId = habitat.getID();
      var areaHabitat = habitat.getArea();
      for (String a : _animals.keySet()) {
        Animal animal = _animals.get(a);
        var habitatAnimal = animal.getHabitatId();
        if (habitatAnimal.equals(habitatId)) {
          populacao += 1;
        }
      }
      int resultadoPopulacao = populacao;
      populacao = 0;
      if (treesInHabitat == null) {
        esforcoLimpeza = 0;
      } else {
        for (String a : treesInHabitat) {
          Tree tree = _trees.get(a);
          esforcoLimpeza += tree.esforcoLimpeza(a);
        }
      }

      trabalhoHabitat = areaHabitat + 3 * resultadoPopulacao + esforcoLimpeza;
      resultado += trabalhoHabitat / resultadoTratadorHabitat;

    }
    return 300 - Math.round(resultado);
  }
  // Pergunta 2 teste Prático
  public List<String> showEmployeeSatisfaction(int numero){
    ArrayList<String> animalsInHabitat = new ArrayList<>();
    for (String i : _employees.keySet()) {
      Employee employee = _employees.get(i);
      if (employee.getEmpType().equals("VET")) {
        if(calculaSatisfacaoVet(i) > numero){
          animalsInHabitat.add(employee.employeeStringed());
        }
      }
      if (employee.getEmpType().equals("TRT")) {
        if(calculaSatisfacaoTrt(i) > numero){
          animalsInHabitat.add(employee.employeeStringed());
        }
      }
    }
    return animalsInHabitat;
  }
  /**
   * 
   * @param idEmployee
   * @return
   */
  public int calcularSatisfacaoEmployee(String idEmployee) {
    int result = 0;
    Employee employee = _employees.get(idEmployee);
    var emptype = employee.getEmpType();
    if (emptype.equals("VET")) {
      result = calculaSatisfacaoVet(idEmployee);
    } else if (emptype.equals("TRT")) {
      result = calculaSatisfacaoTrt(idEmployee);
    }
    return result;
  }

  /**
   * ##########################
   * Metodos das Seasons
   * ##########################
   */

  /**
   * 
   * @return
   */
  public String getCurrentSeason() {
    return _seasonAtual.getSeason();
  }

  /**
   * 
   * @return
   */
  public int nextSeason() {
    return _seasonAtual.nextSeasons();
  }

  /**
   * ##########################
   * Metodos da Gestão de Consulta
   * ##########################
   */

  /**
   * 
   * @param habitatId
   * @return
   * @throws UnknownHabitatKeyExceptionCore
   */
  public ArrayList<String> getAnimalsHabitat(String habitatId) throws UnknownHabitatKeyExceptionCore {
    if (!_habitats.containsKey(habitatId)) {
      throw new UnknownHabitatKeyExceptionCore(habitatId);
    }
    ArrayList<String> animalsInHabitat = new ArrayList<>();

    for (String animalKey : _animals.keySet()) {
      Animal animal = _animals.get(animalKey);
      var habitatAnimal = animal.getHabitatId();
      if (habitatAnimal.equals(habitatId)) {
        animalsInHabitat.add(animal.animalStringed());
      }
    }
    return animalsInHabitat;
  }

  /**
   * 
   * @param idVet
   * @return
   * @throws UnknownVeterinarianKeyExceptionCore
   */
  public List<String> hasNoVaccinationsApplied(String idVet) throws UnknownVeterinarianKeyExceptionCore {
    if (!_employees.containsKey(idVet)) {
      throw new UnknownVeterinarianKeyExceptionCore(idVet);
    }

    List<String> appliedVaccinations = new ArrayList<>();

    for (String record : _historialVacinacao) {
      String[] fields = record.split("\\|");
      String veterinarianId = fields[2];

      if (veterinarianId.equals(idVet)) {
        appliedVaccinations.add(record);
      }
    }

    if (appliedVaccinations.size() == 0) {
      appliedVaccinations.add("");
    }

    return appliedVaccinations;
  }


  /**
   * 
   * @param idAnimal
   * @return
   * @throws UnknownAnimalKeyExceptionCore
   */
  public List<String> animalHasNoVaccinationsApplied(String idAnimal) throws UnknownAnimalKeyExceptionCore {
    if (!_animals.containsKey(idAnimal)) {
      throw new UnknownAnimalKeyExceptionCore(idAnimal);
    }
    Animal animal = _animals.get(idAnimal);
    List<String> appliedVaccinations = new ArrayList<>();
    if (appliedVaccinations.size() == 0) {
      appliedVaccinations.add("");
    }
    String especieEmQuestao = animal.getSpeciesId();
    for (String record : _historialVacinacao) {
      String[] fields = record.split("\\|");
      String especieId = fields[3]; 

      if (especieId.equals(especieEmQuestao)) {
        appliedVaccinations.add(record); 
      }
    }

    return appliedVaccinations;
  }


  /**
   * 
   * @return
   * @throws UnknownVaccineKeyExceptionCore
   * @throws UnknownVeterinarianKeyExceptionCore
   * @throws VeterinarianNotAuthorizedExceptionCore
   */
  public List<String> isWellAplied() throws UnknownVaccineKeyExceptionCore, UnknownVeterinarianKeyExceptionCore,
      VeterinarianNotAuthorizedExceptionCore {
        return _vacinasErradas;
      }
}
