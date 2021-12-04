package application;

import java.util.ArrayList;

class CartData 
{
	private LinkList shopCart = new LinkList();
	private static CartData instance = null;
	
	public static CartData getInstance()
	{
		if (instance == null) 
		{
			instance = new CartData();
        }
		return instance;
	}
	
	public LinkList getShopList()
	{
		return this.shopCart;
	}
	
	public void setShopList(LinkList cart)
	{
		this.shopCart = cart;
	}
}
