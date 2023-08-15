import java.util.ArrayList;

public class SwapCommand extends UndoCommand {
    // Your field declarations here
    private ArrayList<String> sourceList;
    private int index1, index2;
  
    // Your constructor code here
    public SwapCommand(ArrayList<String> lst, int index1, int index2) {
        this.sourceList = lst;
        this.index1 = index1;
        this.index2 = index2;
    }
  
    @Override
    public void execute() {
        // Your code here
        // swap the items back to its original indexes
        String temp = sourceList.get(index1);
        sourceList.set(index1, sourceList.get(index2));
        sourceList.set(index2, temp);
    }

}
