package hva.app.main;

import hva.app.exception.UnknownAnimalKeyException;
import hva.core.HotelManager;
import hva.core.exception.UnknownAnimalKeyExceptionCore;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for show the global satisfation of the current zoo hotel.
 **/
class DoShowGlobalSatisfaction extends Command<HotelManager> {
  DoShowGlobalSatisfaction(HotelManager receiver) {
    super(hva.app.main.Label.SHOW_GLOBAL_SATISFACTION, receiver);
  }
  
  @Override
  protected final void execute() throws CommandException {
   try{
    _display.popup(_receiver.mostrarSatisfacaoGlobal());
    }catch (UnknownAnimalKeyExceptionCore e){
      throw new UnknownAnimalKeyException(e.getKey());
    }
}
}
