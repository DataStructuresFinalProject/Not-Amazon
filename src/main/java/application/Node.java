package application;

public class Node<T> {
    T Data;
    Node<T> NextNode;
    int Index;
    
    /**
     * Default constructor
     * Accepts data for the current node.
     * @param data Data of the current node.
     */
    public Node(T data){
        Data = data;
        Index=1;
    }

    /**
     * Alternate constructor
     * Accepts data, neighboring node, and index of the current node.
     * @param data Data of the current node.
     * @param nextNode The next neighboring node in the chain.
     * @param index The index of the current node in the chain.
     */
    public Node(T data, Node<T> nextNode, int index){
        Data = data;
        Index = index;
        NextNode = nextNode;
    }

    
    /** 
     * Sets the next node relative to the current node.
     * @param nextNode The next neighboring node in the chain.
     */
    public void setNextNode(Node<T> nextNode) {
        NextNode = nextNode;
    }

    
    /** 
     * Retrieves the next node in the chain.
     * @return Node<T> The next neighboring node in the chain.
     */
    public Node<T> getNextNode() {
        return NextNode;
    }

    
    /** 
     * Sets the index of the current node in the list as a whole.
     * @param index The new index to be assigned to the node.
     */
    public void setIndex(int index) {
        Index = index;
    }

    
    /** 
     * Retrieves the index of the current node.
     * @return int Current index of the Node.
     */
    public int getIndex() {
        return Index;
    }

    
    /** 
     * Sets the data of the current node.
     * @param data The data to be assigned to the node.
     */
    public void setData(T data) {
        Data = data;
    }

    
    /** 
     * Retrieves the data of the current node.
     * @return T The data held by the current node.
     */
    public T getData() {
        return Data;
    }
}
