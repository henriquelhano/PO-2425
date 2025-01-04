package hva.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Habitat extends HotelEntity implements Serializable {

    private static final long serialVersionUID = 202208091753L;
    private int _area;
    private List<String> _trees;
    private TreeSet<String> _animais;
    private String _adequacao;
    private List<String> _especies;

    // Construtor
    public Habitat(String habitatId, String name, int area, String adequacao, List<String> trees) {
        super(habitatId, name);
        _area = area;
        _trees = trees;
        _animais = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        _adequacao = adequacao;
        _especies = new ArrayList<>();
    }

   
    public int getArea() {
        return _area;
    }

    
    public void alterarArea(int novaArea) {
        _area = novaArea;
    }

    public String getAdequacao(){
        if (_adequacao == null){
            _adequacao = "NEU";
        }
        return _adequacao;
    }

    public List<String> getEspecies(){
        return _especies;
    }

    public void setAdequecao(String novaAdequacao, String idEspecie){
        _adequacao = novaAdequacao;
        _especies.add(idEspecie);
    }

    public List<String> getTrees() {
        return _trees;
    }

    public void addAnimal(String idAnimal) {
        _animais.add(idAnimal);
    }

    public void removeAnimal(String idAnimalARemover) {
        _animais.remove(idAnimalARemover);
    }

    public TreeSet<String> getAnimais() {
        return _animais;
    }

    public void addTree(String idTree) {
        if(_trees == null) {
            _trees = new ArrayList<>();
        }
        _trees.add(idTree);
    }

    // Metodo que serve para imprimir no formato desejado
    public String habitatStringed() {
        if (_trees != null) {
            // Corrigido para atribuição
            return String.join(
                    "|",
                    "HABITAT",
                    getID(),
                    getName(),
                    String.valueOf(getArea()),
                    String.valueOf(_trees.size()));
        } else {
            return String.join(
                    "|",
                    "HABITAT",
                    getID(),
                    getName(),
                    String.valueOf(getArea()),
                    "0");

        }
    }
}
