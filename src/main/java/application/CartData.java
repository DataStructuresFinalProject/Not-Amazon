package application;

class CartData 
{
	private ArrayListBag<Item> shopCart = new ArrayListBag<Item>();
	private static CartData instance = null;
	
	public static CartData getInstance()
	{
		if (instance == null) 
		{
			instance = new CartData();
        }
		return instance;
	}
	
	public ArrayListBag<Item> getShopList()
	{
		return this.shopCart;
	}
	
	public void setShopList(ArrayListBag<Item> cart)
	{
		this.shopCart = cart;
	}
}
