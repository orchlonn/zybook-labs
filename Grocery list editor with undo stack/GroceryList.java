import java.util.*;
import java.io.PrintWriter;

public class GroceryList {
    protected ArrayList<String> listItems = new ArrayList<String>();
    protected Stack<UndoCommand> undoStack = new Stack<UndoCommand>();

    public void addWithUndo(String newItemName) {
        // Add the new list item
        listItems.add(newItemName);

        // Make an undo command that removes the last item and push onto stack
        undoStack.push(new RemoveLastCommand(listItems));
    }

    public void removeAtWithUndo(int removalIndex) {
        // Your code here
        // get the item from the given index
        String item = listItems.get(removalIndex);
        // remove the item from the list at the specified index
        listItems.remove(removalIndex);
        
        // Make an undo command that add the removed item and push onto stack
        undoStack.push(new InsertAtCommand(listItems, item, removalIndex));
    }

    public void swapWithUndo(int index1, int index2) {
        // Your code here
        // swap the items at the given indexes
        String temp = listItems.get(index1);
        listItems.set(index1, listItems.get(index2));
        listItems.set(index2, temp);

        // Make an undo command that swaps the swapped items back to its indexes and push onto stack
        undoStack.push(new SwapCommand(listItems, index1, index2));
    }

    // Pop and execute the command at the top of the stack
    public void executeUndo() {
        // pop an UndoCommand off the undo stack
        // execute the popped undo command
        (undoStack.pop()).execute();
    }

    final int getListSize() {
        return listItems.size();
    }

    final int getUndoStackSize() {
        return undoStack.size();
    }

    final ArrayList<String> getVectorCopy() {
        return listItems;
    }

    public void print(PrintWriter outputStream) {
        for (int i = 0; i < listItems.size(); i++) {
            outputStream.write(i + ". " + listItems.get(i) + "\n");
        }
    }
}
