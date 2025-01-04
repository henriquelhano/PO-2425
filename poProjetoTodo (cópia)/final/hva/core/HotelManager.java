package hva.core;

import hva.app.exception.UnknownAnimalKeyException;
import hva.core.exception.*;
import java.io.*;
import java.util.ArrayList;

public class HotelManager {

  private Hotel _hotel = null;
  private String _fileName = "";
  

  public HotelManager(){
  }

  public void criar() {
    this._hotel = new Hotel();
  }

  /**
   * @throws FileNotFoundException
   * @throws MissingFileAssociationException
   * @throws IOException
   **/

  public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
    if (_fileName == null || _fileName.equals(""))
      throw new MissingFileAssociationException();
    try (ObjectOutputStream saveFile = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream(_fileName)))) {
      saveFile.writeObject(_hotel);
    }
  }

  /**
   * @param filename
   * @throws FileNotFoundException
   * @throws MissingFileAssociationException
   * @throws IOException
   **/
  public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
    _fileName = filename;
    save();
  }

  /**
   * @param filename
   * @throws UnavailableFileException
   * @throws ClassNotFoundException
   **/

  public void load(String filename) throws UnavailableFileException, ClassNotFoundException {
    _fileName = filename;
    try (ObjectInputStream fileName = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
      this._hotel = (Hotel) fileName.readObject();
    } catch (IOException e) {
      throw new UnavailableFileException(filename);
    }
  }

  /**
   *
   * @param filename
   * @throws ImportFileException
   **/
  public void importFile(String filename) throws ImportFileException {
    try {
      getHotel().importFile(filename);
    } catch (IOException | UnrecognizedEntryException e) {
      throw new ImportFileException(filename, e);
    }
  }

  /**
   * @return o hotel atual
   **/

  public final Hotel getHotel() {
    if (_hotel == null) {
      criar();
    }
    return _hotel;
  }

      /**
   * ##########################
   * Metodos SatisfacaoGlobal
   * ##########################
     * @throws UnknownAnimalKeyException 
       * @throws UnknownAnimalKeyExceptionCore 
   */

  public int mostrarSatisfacaoGlobal() throws UnknownAnimalKeyExceptionCore {
    int resultado = 0;
    int animaisSatisfacao = 0;
    int employeeSatisfacao = 0;

    for (String animal : getHotel()._animals.keySet()){
      animaisSatisfacao += getHotel().calculaSatisfacaoAnimal(animal);
    }

    for (String employee : getHotel()._employees.keySet()){
      employeeSatisfacao += getHotel().calcularSatisfacaoEmployee(employee);
    }

    resultado = animaisSatisfacao + employeeSatisfacao;

    return resultado;

  }


}
