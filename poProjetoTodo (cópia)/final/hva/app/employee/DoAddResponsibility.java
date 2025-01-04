package hva.app.employee;

import hva.core.Hotel;
import hva.core.exception.NoResponsibilityExceptionCore;
import hva.core.exception.UnknownEmployeeKeyExceptionCore;
import hva.app.exception.UnknownEmployeeKeyException;
import hva.app.exception.NoResponsibilityException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Add a new responsability to an employee: species to veterinarians and 
 * habitats to zookeepers.
 **/
class DoAddResponsibility extends Command<Hotel> {

  DoAddResponsibility(Hotel receiver) {
    super(Label.ADD_RESPONSABILITY, receiver);
    addStringField("employeeKey", hva.app.employee.Prompt.employeeKey());
    addStringField("responsibilityKey", hva.app.employee.Prompt.responsibilityKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    try{
      _receiver.addResponsibility(stringField("employeeKey"),stringField("responsibilityKey") );
    }catch(UnknownEmployeeKeyExceptionCore e){
      throw new UnknownEmployeeKeyException(e.getKey());
    }catch (NoResponsibilityExceptionCore e){
      throw new NoResponsibilityException(e.getKey(), e.getName());
    }
}
}
