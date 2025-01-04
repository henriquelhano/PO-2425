package hva.app.animal;

import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;

/**
 * 4.2.1 Mostra todos os animais do hotel
 */
class DoShowAllAnimals extends Command<Hotel> {

  DoShowAllAnimals(Hotel receiver) {
    super(Label.SHOW_ALL_ANIMALS, receiver);
  }

  @Override
  protected final void execute() {
    _display.popup(_receiver.getAllAnimals());
  }
}
