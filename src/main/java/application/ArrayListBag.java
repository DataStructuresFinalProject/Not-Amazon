package application;

import java.util.ArrayList;

// test comment
public class ArrayListBag<T> implements BagInterface<T> {
	private final ArrayList<T> bag;
	
	public ArrayListBag()
	{
		this(25);
	}
	
	public ArrayListBag(int desiredCapacity)
	{
		ArrayList<T> temp = new ArrayList<T>(desiredCapacity);
		bag = temp;
	}
	
    @Override
    public int getCurrentSize() {
        return bag.size();
    }

    @Override
    public boolean add(T newEntry) {
        return bag.add(newEntry);
    }

    @Override
    public T remove() {
        return bag.remove(getCurrentSize()-1);
    }

    @Override
    public boolean remove(T anEntry) {
        return bag.remove(anEntry);
    }

    @Override
    public void clear() {
        bag.clear();
    }

    @Override
    public int getFrequencyOf(T anEntry) {
        int counter = 0;
        for (int index = 0; index < getCurrentSize(); index++) {
            if (anEntry.equals(bag.get(index))) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public boolean contains(T anEntry) {
        return bag.contains(anEntry);
    }

    @SuppressWarnings("unchecked")
	@Override
    public T[] toArray() {
        return (T[]) bag.toArray();
    }
    
}
