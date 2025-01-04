package hva.app.habitat;

import hva.core.Hotel;
import hva.core.exception.DuplicateHabitatKeyExceptionCore;
import hva.app.exception.DuplicateHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Add a new habitat to this zoo hotel.
 **/
class DoRegisterHabitat extends Command<Hotel> {

  DoRegisterHabitat(Hotel receiver) {
    super(Label.REGISTER_HABITAT, receiver);
    addStringField("habitatKey", hva.app.habitat.Prompt.habitatKey());
    addStringField("habitatName", hva.app.habitat.Prompt.habitatName());
    addIntegerField("habitatArea", hva.app.habitat.Prompt.habitatArea());
  }
  
  @Override
  protected void execute() throws CommandException {
    try {_receiver.registerHabitat(
      stringField("habitatKey"),
      stringField("habitatName"),
      integerField("habitatArea"),
      null
      );
  }catch(DuplicateHabitatKeyExceptionCore e){
    throw new DuplicateHabitatKeyException(e.getKey());
  }
}
}
