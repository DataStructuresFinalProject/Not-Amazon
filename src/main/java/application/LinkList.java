package application;

public class LinkList<T> implements ListInterface<T> {

    private int Index;
    Node<T> FirstNode;
    private int CurrentAmount;

    public LinkList() {
        CurrentAmount = 0;
    }

    public LinkList(T newEntry) {
        FirstNode = new Node<T>(newEntry);
        FirstNode.NextNode = null;
    }

    @Override
    public void add(T newEntry) {
        Node<T> newNode = new Node<T>(newEntry);
        if (CurrentAmount == 0) {
            newNode.NextNode = null;
        } else {
            newNode.NextNode = FirstNode;   
        }
        CurrentAmount++;
        FirstNode=newNode;
    }

    @Override
    public void add(int newPosition, T newEntry) {
        if (newPosition > CurrentAmount-1) {
            
        }
        // TODO Auto-generated method stub
        
    }

    @Override
    public T remove(int givenPosition) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void clear() {
        for (int i = 0; i < CurrentAmount-1; i++) {
            FirstNode = FirstNode.getNextNode();
        }
        FirstNode = null;
    }

    @Override
    public T replace(int givenPosition, T newEntry) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T getEntry(int givenPosition) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T[] toArray() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean contains(T anEntry) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getLength() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }    
}
