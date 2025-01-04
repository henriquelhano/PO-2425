package hva.app.employee;

import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Adds a new employee to this zoo hotel.
 **/
//Pergunta 2 teste Prático
class DoShowEmployeesSatisfaction extends Command<Hotel> {

    DoShowEmployeesSatisfaction(Hotel receiver) {
    super("Funcionários com satisfação superior:", receiver);
    addIntegerField("numero", "Escreva o numero cujo a satisfação seja maior");
  }
  @Override
  protected void execute() throws CommandException {
    _display.popup(_receiver.showEmployeeSatisfaction(integerField("numero")));
  }
}
