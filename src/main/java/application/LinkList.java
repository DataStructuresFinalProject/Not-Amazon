package application;

public class LinkList<T> implements ListInterface<T> {

    private int Index;
    Node<T> FirstNode;
    private int CurrentAmount;

    public LinkList() {
        CurrentAmount = 0;
    }

    @Override
    public void add(T newEntry) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void add(int newPosition, T newEntry) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public T remove(int givenPosition) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
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
