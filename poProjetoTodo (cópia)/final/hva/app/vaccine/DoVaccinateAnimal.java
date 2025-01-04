package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.exception.UnknownVaccineKeyExceptionCore;
import hva.core.exception.UnknownVeterinarianKeyExceptionCore;
import hva.core.exception.VeterinarianNotAuthorizedExceptionCore;
import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownVaccineKeyException;
import hva.app.exception.UnknownVeterinarianKeyException;
import hva.app.exception.VeterinarianNotAuthorizedException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Vaccinate by a given veterinarian a given animal with a given vaccine.
 **/
class DoVaccinateAnimal extends Command<Hotel> {
  DoVaccinateAnimal(Hotel receiver) {
    super(Label.VACCINATE_ANIMAL, receiver);
    addStringField("vaccineKey", hva.app.vaccine.Prompt.vaccineKey());
    addStringField("veterinarianKey", hva.app.vaccine.Prompt.veterinarianKey());
    addStringField("animalKey", hva.app.animal.Prompt.animalKey());
    

  }

  @Override
  protected final void execute() throws CommandException {
    try{
      _display.popup(_receiver.vaccineAplication(stringField("vaccineKey"), stringField("veterinarianKey"), stringField("animalKey")));
    }catch (UnknownVaccineKeyExceptionCore e){
      throw new UnknownVaccineKeyException(e.getKey());
    }catch (UnknownVeterinarianKeyExceptionCore e){
      throw new UnknownVeterinarianKeyException(e.getKey());
    }catch (VeterinarianNotAuthorizedExceptionCore e){
      throw new VeterinarianNotAuthorizedException(e.getKey(), e.getName());
    }
}
}
