package hva.app.search;

import hva.app.exception.UnknownVaccineKeyException;
import hva.app.exception.UnknownVeterinarianKeyException;
import hva.app.exception.VeterinarianNotAuthorizedException;
import hva.core.Hotel;
import hva.core.exception.UnknownVaccineKeyExceptionCore;
import hva.core.exception.UnknownVeterinarianKeyExceptionCore;
import hva.core.exception.VeterinarianNotAuthorizedExceptionCore;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all vaccines applied to animals belonging to an invalid species.
 **/
class DoShowWrongVaccinations extends Command<Hotel> {

  DoShowWrongVaccinations(Hotel receiver) {
    super(Label.WRONG_VACCINATIONS, receiver);
  }

  @Override
  protected void execute() throws CommandException {
    try{
      _display.popup(_receiver.isWellAplied());
    }catch (UnknownVaccineKeyExceptionCore e){
      throw new UnknownVaccineKeyException(e.getKey());
    } catch (UnknownVeterinarianKeyExceptionCore e){
      throw new UnknownVeterinarianKeyException(e.getKey());
    } catch (VeterinarianNotAuthorizedExceptionCore e){
      throw new VeterinarianNotAuthorizedException(e.getKey(), e.getName());
    }
}
}
