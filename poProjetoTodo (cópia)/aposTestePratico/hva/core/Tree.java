package hva.core;

import java.io.Serializable;

public abstract class Tree extends HotelEntity implements Serializable {

  private static final long serialVersionUID = 202208091753L;
  private int _age;
  private int _difficultyCleaning;
  private String _treeType;
  private String _cicloBiologico;
  private int esforcoLimpeza;
  private int esforcoSazonal = 0;
  private Season _seasonAtual = null;

  public Tree(String idTree, String nameTree, String treetype, int age, int difficultyCleaning, Season seasonAtual) {
    super(idTree, nameTree);
    _age = age;
    _difficultyCleaning = difficultyCleaning;
    _treeType = treetype;
    _seasonAtual = seasonAtual;
    esforcoLimpeza = 0;
  }

  public int getAge() {
    return _age;
  }

  public int getDifficultyCleaning() {
    return _difficultyCleaning;
  }
  
  public String getTreeType() {
    return _treeType;
  }

  public String getSeasonAtual() {
    if (_seasonAtual.getSeason().equals("3")){
      _age += 1;
    }
    return _seasonAtual.getSeason();
  }

  public int esforcoLimpeza(String tree){
    double resultado = 0;
    resultado = _difficultyCleaning * Math.log(_age + 1) * esforcoSazonal;
    return esforcoLimpeza;
  }
  

  public String getCicloBiologico() {

    if (getSeasonAtual().equals("SPRING")) {
      _cicloBiologico = "GERARFOLHAS";
      esforcoSazonal = 1;
    }
    if (getSeasonAtual().equals("SUMMER")) {
      if (getTreeType().equals("CADUCA")) {
        _cicloBiologico = "COMFOLHAS";
        esforcoSazonal = 2;
      } else {
        _cicloBiologico = "COMFOLHAS";
        esforcoSazonal = 1;
      }
    }
    if (getSeasonAtual().equals("AUTUMN")) {
      if (getTreeType().equals("CADUCA")) {
        _cicloBiologico = "LARGARFOLHAS";
        esforcoSazonal = 5;
      } else {
        _cicloBiologico = "COMFOLHAS";
        esforcoSazonal = 1;
      }
    }
    if (getSeasonAtual().equals("WINTER")) {
      if (getTreeType().equals("CADUCA")) {
        _cicloBiologico = "SEMFOLHAS";
        esforcoSazonal = 0;
      } else {
        _cicloBiologico = "LARGARFOLHAS";
        esforcoSazonal = 2;
      }
    }
    return _cicloBiologico;
  }

  public String treeStringed() {
    return String.join(
        "|",
        "ÁRVORE",
        getID(),
        getName(),
        String.valueOf(getAge()),
        String.valueOf(getDifficultyCleaning()),
        getTreeType(),
        getCicloBiologico());
  }

}
