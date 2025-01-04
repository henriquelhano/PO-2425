package hva.app.search;

import hva.core.Hotel;
import hva.core.exception.UnknownVeterinarianKeyExceptionCore;
import hva.app.exception.UnknownVeterinarianKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

//FIXME add more imports if needed

/**
 * Show all medical acts of a given veterinarian.
 **/
class DoShowMedicalActsByVeterinarian extends Command<Hotel> {

  DoShowMedicalActsByVeterinarian(Hotel receiver) {
    super(Label.MEDICAL_ACTS_BY_VET, receiver);
    addStringField("employeeKey", hva.app.employee.Prompt.employeeKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    try{
      _display.popup(_receiver.hasNoVaccinationsApplied(stringField("employeeKey")));
    }catch (UnknownVeterinarianKeyExceptionCore e){
      throw new UnknownVeterinarianKeyException(e.getKey());
    }
}
}
