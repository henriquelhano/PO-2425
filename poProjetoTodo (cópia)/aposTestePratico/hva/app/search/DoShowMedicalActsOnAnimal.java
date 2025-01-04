package hva.app.search;

import hva.core.Hotel;
import hva.core.exception.UnknownAnimalKeyExceptionCore;
import hva.app.exception.UnknownAnimalKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all medical acts applied to a given animal.
 **/
class DoShowMedicalActsOnAnimal extends Command<Hotel> {

  DoShowMedicalActsOnAnimal(Hotel receiver) {
    super(Label.MEDICAL_ACTS_ON_ANIMAL, receiver);
    addStringField("animalKey", hva.app.animal.Prompt.animalKey());
  }

  @Override
  protected void execute() throws CommandException {
    try{
      _display.popup(_receiver.animalHasNoVaccinationsApplied(stringField("animalKey")));
    } catch (UnknownAnimalKeyExceptionCore e){
      throw new UnknownAnimalKeyException(e.getKey());
    }
}
}
