package hva.app.habitat;

import hva.core.Hotel;
import hva.core.exception.UnknownHabitatKeyExceptionCore;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Change the area of a given habitat.
 **/
class DoChangeHabitatArea extends Command<Hotel> {

  DoChangeHabitatArea(Hotel receiver) {
    super(Label.CHANGE_HABITAT_AREA, receiver);
    addStringField("habitatKey", hva.app.habitat.Prompt.habitatKey());
    addIntegerField("habitatArea", hva.app.habitat.Prompt.habitatArea());
  }
  
  @Override
  protected void execute() throws CommandException {
    try{
      _receiver.setArea(stringField("habitatKey"), integerField("habitatArea"));
    }catch (UnknownHabitatKeyExceptionCore e){
      throw new UnknownHabitatKeyException(e.getKey());
    }
}
}
