package hva.app.habitat;

import hva.core.Hotel;
import hva.core.exception.UnknownHabitatKeyExceptionCore;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all trees in a given habitat.
 **/
class DoShowAllTreesInHabitat extends Command<Hotel> {

  DoShowAllTreesInHabitat(Hotel receiver) {
    super(Label.SHOW_TREES_IN_HABITAT, receiver);
    addStringField("habitatKey", hva.app.habitat.Prompt.habitatKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    try{
      _display.popup(_receiver.doShowAllTreesInHabitat(stringField("habitatKey")));
    }catch (UnknownHabitatKeyExceptionCore e){
      throw new UnknownHabitatKeyException(e.getKey());
    }
}
}
