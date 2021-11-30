package application;

class ChosenItemData 
{
	private Item currentItem;
	private static ChosenItemData instance = null;
	
	public static ChosenItemData getInstance()
	{
		if (instance == null) 
		{
			instance = new ChosenItemData();
        }
		return instance;
	}
	
	public Item getCurrentItem()
	{
		return this.currentItem;
	}
	
	public void setCurrentItem(Item inputItem)
	{
		this.currentItem = inputItem;
	}
}
