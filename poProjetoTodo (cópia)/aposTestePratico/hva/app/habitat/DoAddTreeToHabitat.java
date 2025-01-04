package hva.app.habitat;

import hva.core.Hotel;
import hva.core.exception.DuplicateTreeKeyExceptionCore;
import hva.core.exception.UnknownHabitatKeyExceptionCore;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.DuplicateTreeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Add a new tree to a given habitat of the current zoo hotel.
 **/
class DoAddTreeToHabitat extends Command<Hotel> {

  DoAddTreeToHabitat(Hotel receiver) {
    super(Label.ADD_TREE_TO_HABITAT, receiver);
    String [] options = {"PERENE", "CADUCA"};
    addStringField("habitatKey", hva.app.habitat.Prompt.habitatKey());
    addStringField("treeKey", hva.app.habitat.Prompt.treeKey());
    addStringField("treeName", hva.app.habitat.Prompt.treeName());
    addIntegerField("treeAge", hva.app.habitat.Prompt.treeAge());
    addIntegerField("treeDifficulty", hva.app.habitat.Prompt.treeDifficulty());
    addOptionField("treeType", hva.app.habitat.Prompt.treeType(), options);
    
  }
  
  @Override
  protected void execute() throws CommandException {
    try{_display.popup(_receiver.registerTreeHabitat(
      stringField("habitatKey"),
      stringField("treeKey"),
      stringField("treeName"),
      integerField("treeAge"),
      integerField("treeDifficulty"),
      stringField("treeType")
      ));
    } catch (UnknownHabitatKeyExceptionCore e){
      throw new UnknownHabitatKeyException(e.getKey());
    } catch (DuplicateTreeKeyExceptionCore e){
      throw new DuplicateTreeKeyException(e.getKey());
    }
}
}
