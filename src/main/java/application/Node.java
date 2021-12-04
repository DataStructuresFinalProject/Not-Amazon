package application;


public class Node<T> {
    private T data;
    private Node<T> nextNode;
    private int index;
    
    /**
     * Default constructor
     * Accepts data for the current node.
     * @param data Data of the current node.
     */
    public Node(){
        data = null;
        nextNode = null;
        index = 0;
    }

    /**
     * Alternate constructor
     * Accepts data, neighboring node, and index of the current node.
     * @param data Data of the current node.
     * @param nextNode The next neighboring node in the chain.
     * @param index The index of the current node in the chain.
     */
    public Node(T d, Node<T> nxtNd){
        data = d;
        index++;
        nextNode = nxtNd;
    }

    
    /** 
     * Sets the next node relative to the current node.
     * @param nextNode The next neighboring node in the chain.
     */
    public void setNextNode(Node<T> nxtNd) {
        nextNode = nxtNd;
    }

    
    /** 
     * Retrieves the next node in the chain.
     * @return Node<T> The next neighboring node in the chain.
     */
    public Node<T> getNextNode() {
        return nextNode;
    }

    
    /** 
     * Sets the index of the current node in the list as a whole.
     * @param index The new index to be assigned to the node.
     */
    public void setIndex(int i) {
        index = i;
    }

    
    /** 
     * Retrieves the index of the current node.
     * @return int Current index of the Node.
     */
    public int getIndex() {
        return index;
    }

    
    /** 
     * Sets the data of the current node.
     * @param data The data to be assigned to the node.
     */
    public void setData(T d) {
        data = d;
    }

    
    /** 
     * Retrieves the data of the current node.
     * @return T The data held by the current node.
     */
    public T getData() {
        return data;
    }
    
    
    public Node<T> getNodeAt(int index, Node<T> head)
    {
    	Node<T> currentNode = head;
    	for (int counter = 1; counter < index; counter++)
    	{
    		currentNode = currentNode.getNextNode();
    	}
    	return currentNode;
    }
    
    public int size(Node<T> head)
    {
    	int count = 0;
    	Node<T> position = head;
    	while (position != null)
    	{
    		count++;
    		position = position.getNextNode();
    	}
    	return count;
    }
    
    public void addStart(T data, Node<T> head) {
    		head = new Node<T>(data, head);
	  }
	  
	  public void addBetween(T newEntry, int index, Node<T> head)
	  {
		  if ((index >= 1) && (index <= size(head) + 1))
			{
				Node<T> newNode = new Node<T>(newEntry, head);
				if (index == 1)
				{
					newNode.setNextNode(head);
					head = newNode;
				}
				else
				{
					Node<T> nodeBefore = head.getNodeAt(index-1, head);
					Node<T> nodeAfter = nodeBefore.getNextNode();
					newNode.setNextNode(nodeAfter);
					nodeBefore.setNextNode(newNode);
				}
			}
			else
			{
				throw new IndexOutOfBoundsException(
						"Illegal position given to add operation.");
			}
	  }
	  
	  public void addEnd(T data, Node<T> head)
	  {
		  Node<T> lastNode = head.getNodeAt(size(head), head);
		  lastNode.setNextNode(new Node<T>(data, head));
	  }
	  
	  public void remove(int nodePosition, Node<T> head)
	  {
		  Node<T> nodeBefore = getNodeAt(nodePosition - 1, head);
		  Node<T> nodeToRemove = nodeBefore.getNextNode();
		  Node<T> nodeAfter = nodeToRemove.getNextNode();
		  nodeBefore.setNextNode(nodeAfter);
		  nodeToRemove = null;
	  }
	  
	  public void removeFirst(Node<T> head)
	  {
		  head = head.getNextNode();
	  }
}