package hva.app.employee;

import hva.core.Hotel;
import hva.core.exception.DuplicateEmployeeKeyExceptionCore;
import hva.app.exception.DuplicateEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Adds a new employee to this zoo hotel.
 **/
class DoRegisterEmployee extends Command<Hotel> {

  DoRegisterEmployee(Hotel receiver) {
    super(Label.REGISTER_EMPLOYEE, receiver);
    String [] options = {"VET", "TRT"};
    addStringField("employeeKey", hva.app.employee.Prompt.employeeKey());
    addStringField("employeeName", hva.app.employee.Prompt.employeeName());
    addOptionField("employeeType", hva.app.employee.Prompt.employeeType(), options);
  }
  
  @Override
  protected void execute() throws CommandException {
    String employeeType = stringField("employeeType"); // Use stringField, não integerField
    String[] responsabilitys;

    try{
      _receiver.registerEmployee(
        stringField("employeeKey"),
        stringField("employeeName"),
        stringField("employeeType"),
        null
        );
    }catch (DuplicateEmployeeKeyExceptionCore e){
      throw new DuplicateEmployeeKeyException(e.getKey());
    }
}
}
