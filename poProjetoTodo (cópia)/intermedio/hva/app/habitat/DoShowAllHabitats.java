package hva.app.habitat;

import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;

/**
 * 4.4.1 Mostra todos os habitats do hotel
 **/
class DoShowAllHabitats extends Command<Hotel> {

  DoShowAllHabitats(Hotel receiver) {
    super(Label.SHOW_ALL_HABITATS, receiver);
  }

  @Override
  protected void execute() {
    _display.popup(_receiver.getAllHabitats());
  }
}
