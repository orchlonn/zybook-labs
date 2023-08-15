import java.util.ArrayList;

public class InsertAtCommand extends UndoCommand {
    // Your field declarations here
    private ArrayList<String> sourceList;
    private String item;
    private int index;
  
    // Your constructor code here
    public InsertAtCommand(ArrayList<String> lst, String item, int index) {
        this.sourceList = lst;
        this.item = item;
        this.index = index;
    }
  
    @Override
    public void execute() {
        // Your code here
        // add the item at the given index to the source list
        sourceList.add(index, item);
    }
}
