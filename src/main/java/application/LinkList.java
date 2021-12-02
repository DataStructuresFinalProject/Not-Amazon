package application;

public class LinkList<Item> implements ListInterface<Item> {

    Node<Item> FirstNode;
    private int CurrentAmount;

    /**
     * Default Constructor that initializes a list with no nodes.
     */
    public LinkList() {
        CurrentAmount = 0;
    }

    /**
     * Alternate constructor that creates a LinkedList with a First Node.
     * @param newEntry Data entry to be assigned to the first node in the chain.
     */
    public LinkList(Item newEntry) {
        FirstNode = new Node<Item>(newEntry);
        FirstNode.NextNode = null;
    }

    
    /** 
     * Creates a new node at the beginning of the chain and adds the data value to the node.
     * @param newEntry Data value to be added to the list.
     */
    @Override
    public void add(Item newEntry) {
        Node<Item> newNode = new Node<Item>(newEntry);
        if (CurrentAmount == 0) {
            newNode.NextNode = null;
        } else {
            newNode.NextNode = FirstNode;   
        }
        CurrentAmount++;
        FirstNode=newNode;
    }

    
    /** 
     * Creates a new node at the specified index and adds the data value to it.
     * @param newPosition Index of the new node.
     * @param newEntry Data entry for the node.
     */
    @Override
    public void add(int newPosition, Item newEntry) {
        if ((newPosition >= 1) && (newPosition <= CurrentAmount + 1)) {
            Node<Item> newNode = new Node<Item>(newEntry);

            if (newPosition == 1) {
                newNode.setNextNode(FirstNode);
                FirstNode = newNode;
            } else {
                Node<Item> nodeBefore = getNode(newPosition-1);
                Node<Item> nodeAfter = nodeBefore.getNextNode();
                newNode.setNextNode(nodeAfter);
                nodeBefore.setNextNode(newNode);
            }

            CurrentAmount ++;
        } else {
            throw new IndexOutOfBoundsException("Illegal position given to add operation.");
        }
    }

    
    /** 
     * Removes a node at a specified index and returns the data from it.
     * @param givenPosition Index of the node to be deleted.
     * @return T Data value attached to the removed node.
     */
    @Override
    public Item remove(int givenPosition) {
        Item result;

        if ((givenPosition >= 1) && (givenPosition <= CurrentAmount)) {
            if (givenPosition == 1) {
                result = FirstNode.getData();
                FirstNode = FirstNode.getNextNode();
            } else {
                Node<Item> nodeBefore = getNode(givenPosition-1);
                Node<Item> nodeToRemove = nodeBefore.getNextNode();
                result = nodeToRemove.getData();
                Node<Item> nodeAfter = nodeToRemove.getNextNode();
                nodeBefore.setNextNode(nodeAfter);
            }
            CurrentAmount--;
            return result;
        } else {
            throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
        }
    }

    /**
     * Clears out the LinkedList
     */
    @Override
    public void clear() {
        FirstNode = null;
        CurrentAmount = 0;
    }

    
    /** 
     * Replaces the data value at the node with a new entry.
     * @param givenPosition The index of the data to be replaced.
     * @param newEntry New data value to be assigned to the node.
     * @return T Old data value from the node.
     */
    @Override
    public Item replace(int givenPosition, Item newEntry) {
        if ((givenPosition >= 1) && (givenPosition <= CurrentAmount)) {
            Node<Item> replacedNode = getNode(givenPosition);
            Item originalData = replacedNode.getData();
            replacedNode.setData(newEntry);
            return originalData;
        } else {
            throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
        }

    }

    
    /** 
     * Returns the data value from the node at a given index.
     * @param givenPosition The index of the node.
     * @return T The data value from the indexed node.
     */
    @Override
    public Item getEntry(int givenPosition) {

        if ((givenPosition >= 1) && (givenPosition <= CurrentAmount)) {
            return getNode(givenPosition).getData();
        } else {
            throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
        }
    }

    
    /** 
     * Casts all the data values into an array.
     * @return T[] The compiled array filed with the data values.
     */
    @Override
    public Item[] toArray() {

        @SuppressWarnings("unchecked")
        Item[] result = (Item[])new Object[CurrentAmount];

        int index = 0;
        Node<Item> currentNode = FirstNode;
        while ((index < CurrentAmount) && (currentNode != null)) {
            result[index] = currentNode.getData();
            currentNode = currentNode.getNextNode();
            index++;
        }

        return result;
    }

    
    /** 
     * Takes in a data value and checks to see if the 
     * @param anEntry The data entry for the method to looks for.
     * @return boolean Returns true if the linked list contains the entry.
     */
    @Override
    public boolean contains(Item anEntry) {
        boolean found = false;
        Node<Item> currentNode = FirstNode;
        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode)) {
                found = true;
            } else {
                currentNode = currentNode.getNextNode();
            }
        }
        return found;
    }

    
    /** 
     * Returns the current length of the LinkedList.
     * @return int Current amount of entries in the list.
     */
    @Override
    public int getLength() {
        return CurrentAmount;
    }

    
    /** 
     * Checks if the list is empty.
     * @return boolean Returns true if the list has no entries.
     */
    @Override
    public boolean isEmpty() {

        if (CurrentAmount == 0) {
            return true;
        } else {
            return false;
        }
    }  
    
    
    /** 
     * Gets the node packet at the index.
     * @param index Index of the node to retrieve.
     * @return Node<T> Node being returned.
     */
    public Node<Item> getNode(int index) {

        Node<Item> currentNode = FirstNode;

        for (int i = 1; i < index; i++) {
            currentNode = currentNode.getNextNode();
        }

        return currentNode;
    }
}
