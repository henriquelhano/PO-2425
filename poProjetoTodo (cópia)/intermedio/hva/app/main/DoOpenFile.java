package hva.app.main;

import hva.core.HotelManager;
import hva.app.exception.FileOpenFailedException;
import hva.core.exception.UnavailableFileException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Abre um ficheiro
 */
class DoOpenFile extends Command<HotelManager> {
  DoOpenFile(HotelManager receiver) {
    super(Label.OPEN_FILE, receiver);
    addStringField("fileName", Prompt.openFile());
  }

  @Override
  protected final void execute() throws CommandException {
    try {
      String fileName = stringField("fileName");
      _receiver.load(fileName);
    } catch (UnavailableFileException | ClassNotFoundException e) {
      throw new FileOpenFailedException(e);
    }
  }
}
