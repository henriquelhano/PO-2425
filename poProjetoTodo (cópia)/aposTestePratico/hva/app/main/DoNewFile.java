package hva.app.main;

import hva.app.exception.UnknownEmployeeKeyException;
import hva.core.HotelManager;
import hva.core.exception.TestePraticoException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Cria um hotel
 **/
class DoNewFile extends Command<HotelManager> {
  DoNewFile(HotelManager receiver) {
    super(Label.NEW_FILE, receiver);
    addStringField("stringKey","Escreva um nome para salvar");
  }

  @Override
  protected final void execute() throws UnknownEmployeeKeyException {
    try {
      _receiver.criar("stringKey");
    } catch (TestePraticoException e) {
      throw new UnknownEmployeeKeyException(e.getKey());
    }
    
  }
}
