package hva.app.main;

import hva.app.exception.FileOpenFailedException;
import hva.core.HotelManager;
import hva.core.exception.MissingFileAssociationException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import java.io.IOException;

/**
 * Salva o ficheiro com o nome atual, se não tiver, pede o nome
 */
class DoSaveFile extends Command<HotelManager> {
  DoSaveFile(HotelManager receiver) {
    super(Label.SAVE_FILE, receiver, r -> r.getHotel() != null);
  }

  @Override
  protected final void execute() throws FileOpenFailedException {
    try {
      _receiver.save();
    } catch (MissingFileAssociationException e) {
      try {
        _receiver.saveAs(Form.requestString(Prompt.newSaveAs()));
      } catch (MissingFileAssociationException | IOException e1) {
        throw new FileOpenFailedException(e1);
      }
    } catch (IOException e) {
      throw new FileOpenFailedException(e);
    }
  }

}
