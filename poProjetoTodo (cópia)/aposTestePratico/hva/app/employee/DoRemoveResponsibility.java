package hva.app.employee;

import hva.core.Hotel;
import hva.core.exception.NoResponsibilityExceptionCore;
import hva.core.exception.UnknownEmployeeKeyExceptionCore;
import hva.app.exception.NoResponsibilityException;
import hva.app.exception.UnknownEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Remove a given responsability from a given employee of this zoo hotel.
 **/
class DoRemoveResponsibility extends Command<Hotel> {

  DoRemoveResponsibility(Hotel receiver) {
    super(Label.REMOVE_RESPONSABILITY, receiver);
    addStringField("employeeKey", hva.app.employee.Prompt.employeeKey());
    addStringField("responsibilityKey", hva.app.employee.Prompt.responsibilityKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    try{
      _receiver.removeResponsibility(stringField("employeeKey"),stringField("responsibilityKey") );
    }catch(UnknownEmployeeKeyExceptionCore e){
      throw new UnknownEmployeeKeyException(e.getKey());
    }catch (NoResponsibilityExceptionCore e){
      throw new NoResponsibilityException(e.getKey(), e.getName());
    }
  }
}   
