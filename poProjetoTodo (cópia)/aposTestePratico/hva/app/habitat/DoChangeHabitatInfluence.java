package hva.app.habitat;

import hva.core.Hotel;
import hva.core.exception.UnknownHabitatKeyExceptionCore;
import hva.core.exception.UnknownSpeciesKeyExceptionCore;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Associate (positive or negatively) a species to a given habitat.
 **/
class DoChangeHabitatInfluence extends Command<Hotel> {

  DoChangeHabitatInfluence(Hotel receiver) {
    super(Label.CHANGE_HABITAT_INFLUENCE, receiver);
    String [] options = {"POS", "NEG", "NEU"};
    addStringField("habitatKey", hva.app.habitat.Prompt.habitatKey());
    addStringField("speciesKey", hva.app.animal.Prompt.speciesKey());
    addOptionField("habitatInfluence", hva.app.habitat.Prompt.habitatInfluence(), options);
  }
  
  @Override
  protected void execute() throws CommandException {
    try{
      _receiver.alterarAdequacao(stringField("habitatKey"), stringField("speciesKey"), stringField("habitatInfluence"));
    }catch (UnknownSpeciesKeyExceptionCore e){
      throw new UnknownSpeciesKeyException(e.getKey());
    }catch (UnknownHabitatKeyExceptionCore e){
      throw new UnknownHabitatKeyException(e.getKey());
    }
}
}
