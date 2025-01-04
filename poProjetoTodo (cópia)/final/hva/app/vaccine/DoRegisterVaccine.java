package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.exception.DuplicateVaccineKeyExceptionCore;
import hva.core.exception.UnknownSpeciesKeyExceptionCore;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.app.exception.DuplicateVaccineKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Apply a vaccine to a given animal.
 **/
class DoRegisterVaccine extends Command<Hotel> {

  DoRegisterVaccine(Hotel receiver) {
    super(Label.REGISTER_VACCINE, receiver);
    addStringField("vacineKey", hva.app.vaccine.Prompt.vaccineKey());
    addStringField("vacineName", hva.app.vaccine.Prompt.vaccineName());
    addStringField("listOfSpeciesKeys", hva.app.vaccine.Prompt.listOfSpeciesKeys());
  }

  @Override
  protected final void execute() throws CommandException {
    try{
      _receiver.registerVaccine(
        stringField("vacineKey"),
        stringField("vacineName"),
        stringField("listOfSpeciesKeys").split(",")
        );
    } catch (UnknownSpeciesKeyExceptionCore e){
      throw new UnknownSpeciesKeyException(e.getKey());
    } catch (DuplicateVaccineKeyExceptionCore e){
      throw new DuplicateVaccineKeyException(e.getKey());
    }
}
}
