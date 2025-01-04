package hva.app.employee;

import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;

/**
 * 4.3.1 Mostra todos os funcionarios do hotel
 **/
class DoShowAllEmployees extends Command<Hotel> {

  DoShowAllEmployees(Hotel receiver) {
    super(Label.SHOW_ALL_EMPLOYEES, receiver);
  }

  @Override
  protected void execute() {
    _display.popup(_receiver.getAllEmployees());
  }
}
