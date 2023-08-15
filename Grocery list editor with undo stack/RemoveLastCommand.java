import java.util.ArrayList;

public class RemoveLastCommand extends UndoCommand {
    private ArrayList<String> sourceList;
    
    public RemoveLastCommand(ArrayList<String> lst) {
        this.sourceList = lst;
    }
    
    @Override
    public void execute() {
        // remove the string ArrayList's last element
        sourceList.remove(sourceList.size() - 1);
    }
}
