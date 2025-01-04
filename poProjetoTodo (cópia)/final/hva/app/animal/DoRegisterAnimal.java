package hva.app.animal;

import hva.core.Hotel;
import hva.core.exception.DuplicateAnimalKeyExceptionCore;
import hva.core.exception.UnknownHabitatKeyExceptionCore;
import hva.app.exception.DuplicateAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


//FIXME add more imports if needed

/**
 * Register a new animal in this zoo hotel.
 */
class DoRegisterAnimal extends Command<Hotel> {

  DoRegisterAnimal(Hotel receiver) {
    super(Label.REGISTER_ANIMAL, receiver);
    addStringField("animalKey", hva.app.animal.Prompt.animalKey());
    addStringField("animalName", hva.app.animal.Prompt.animalName());
    addStringField("speciesId", hva.app.animal.Prompt.speciesKey());
    addStringField("habitatId", hva.app.habitat.Prompt.habitatKey());
  }
  
  @Override
  protected final void execute() throws CommandException {    
    try {
      _receiver.registerAnimal(
      stringField("animalKey"),
      stringField("animalName"),
      stringField("speciesId"),
      stringField("habitatId")
      );
    }catch (DuplicateAnimalKeyExceptionCore e){
      throw new DuplicateAnimalKeyException(e.getKey());
    }catch(UnknownHabitatKeyExceptionCore e){
      throw new UnknownHabitatKeyException(e.getKey());
    }
  }
}

