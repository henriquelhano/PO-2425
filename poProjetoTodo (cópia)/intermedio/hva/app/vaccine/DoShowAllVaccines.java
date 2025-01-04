package hva.app.vaccine;

import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;

/**
 * 4.5.1 Mostra todas as vacinas
 **/
class DoShowAllVaccines extends Command<Hotel> {

  DoShowAllVaccines(Hotel receiver) {
    super(Label.SHOW_ALL_VACCINES, receiver);
  }

  @Override
  protected final void execute() {
    _display.popup(_receiver.getAllVaccines());
  }
}
