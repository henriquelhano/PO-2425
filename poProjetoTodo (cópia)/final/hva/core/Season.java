package hva.core;

import java.io.Serializable;

public class Season implements Serializable {
  private static final long serialVersionUID = 202208091753L;
  private String[] _seasons = {"SPRING", "SUMMER", "AUTUMN", "WINTER"};
  private int _seasonAtual;
  
  public Season() {
    _seasonAtual = 0;
  }

  
  public int nextSeasons(){
    _seasonAtual += 1;
    if (_seasonAtual == 4){
      _seasonAtual = 0;
    }
    return _seasonAtual;
  }


  public String getSeason(){
    return _seasons[_seasonAtual];
  }
  
}
