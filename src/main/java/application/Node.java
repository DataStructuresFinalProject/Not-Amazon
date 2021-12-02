package application;

public class Node<T> {
    T Data;
    Node<T> NextNode;
    int Index;
    
    public Node(T data){
        Data = data;
        Index=1;
    }

    public Node(T data, int index){
        Data = data;
        Index = index;
    }

    public void setNextNode(Node<T> nextNode) {
        NextNode = nextNode;
    }

    public Node<T> getNextNode() {
        return NextNode;
    }

    public void setIndex(int index) {
        Index = index;
    }
}
