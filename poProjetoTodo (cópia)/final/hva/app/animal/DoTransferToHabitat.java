package hva.app.animal;

import hva.core.Hotel;
import hva.core.exception.UnknownAnimalKeyExceptionCore;
import hva.core.exception.UnknownHabitatKeyExceptionCore;
import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Transfers a given animal to a new habitat of this zoo hotel.
 */
class DoTransferToHabitat extends Command<Hotel> {

  DoTransferToHabitat(Hotel hotel) {
    super(Label.TRANSFER_ANIMAL_TO_HABITAT, hotel);
    addStringField("animalKey", hva.app.animal.Prompt.animalKey() );
    addStringField("habitatId", hva.app.habitat.Prompt.habitatKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    try{
      _receiver.transfereAnimal(stringField("animalKey"), stringField("habitatId"));
    } catch (UnknownAnimalKeyExceptionCore e){
      throw new UnknownAnimalKeyException(e.getKey());
    }catch (UnknownHabitatKeyExceptionCore e){
      throw new UnknownHabitatKeyException(e.getKey());
    }
}
}