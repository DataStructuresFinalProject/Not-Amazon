package application;

public interface ListInterface<Item> {
    public void add(Item newEntry);

    public void add(int newPosition, Item newEntry);

    public Item remove(int givenPosition);

    public void clear();

    public Item replace(int givenPosition, Item newEntry);

    public Item getEntry(int givenPosition);

    public Item[] toArray();

    public boolean contains(Item anEntry);

    public int getLength();

    public boolean isEmpty();
}
