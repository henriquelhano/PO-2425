package hva.app.animal;

import hva.core.Hotel;
import hva.core.exception.UnknownAnimalKeyExceptionCore;
import hva.app.exception.UnknownAnimalKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Shows the satisfaction of a given animal.
 */
class DoShowSatisfactionOfAnimal extends Command<Hotel> {

  DoShowSatisfactionOfAnimal(Hotel receiver) {
    super(Label.SHOW_SATISFACTION_OF_ANIMAL, receiver);
    addStringField("animalKey", hva.app.animal.Prompt.animalKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    try{
      _display.popup(_receiver.calculaSatisfacaoAnimal(stringField("animalKey")));
    }catch (UnknownAnimalKeyExceptionCore e){
      throw new UnknownAnimalKeyException(e.getKey());
    }
    }
}
