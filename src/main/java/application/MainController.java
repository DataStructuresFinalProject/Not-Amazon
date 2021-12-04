package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainController implements Initializable{

    @FXML
    private Button cartButton;

    @FXML
    private ImageView item1;

    @FXML
    private ImageView item2;

    @FXML
    private ImageView item3;

    @FXML
    private ImageView item4;

    @FXML
    private Button leftButton;

    @FXML
    private ImageView logo;

    @FXML
    private Button rightButton;

    @FXML
    private TextField searchBar;

    @FXML
    private Button searchButton;

    @FXML
    private ChoiceBox<String> sortDrop;

    @FXML
    private ImageView topBar;
    
    @FXML
    private Text info1;
    
    @FXML
    private Text info2;
    
    @FXML
    private Text info3;
    
    @FXML
    private Text info4;
    
    private int currentPage = 0;
    private ArrayList<Item> itemStorage = new ArrayList<Item>();
    // item list that changes
    
    private ArrayList<Item> originalItemStorage = new ArrayList<Item>(itemStorage);
    // item list that holds same values as original itemStorage from initialize
    
    private ArrayList<Item> searchStorage = new ArrayList<Item>();
    // item list that holds original searched items
    
    private boolean searchFlag = false;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
    	
		File file = new File("ItemInv.txt");
	    Scanner sc = null;
	    sortDrop.getItems().addAll("No sort", "Price Ascending", "Price Descending", "Alphabetical");
	    sortDrop.setValue("No sort");
	    
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	    while (sc.hasNextLine())
	    {
	    	String temp = sc.nextLine();
	    	String name = temp.substring(0, temp.indexOf("'"));
	    	temp = temp.substring(temp.indexOf("'") + 1);
	    	
	    	double price = Double.parseDouble(temp.substring(0, temp.indexOf("'")));
	    	temp = temp.substring(temp.indexOf("'") + 1);
	    	
	    	int quantity = Integer.parseInt(temp.substring(0, temp.indexOf("'")));
	    	temp = temp.substring(temp.indexOf("'") + 1);
	    	
	    	String image = temp.substring(0);
	    	
	    	Item tempItem = new Item(name, price, quantity, image);
	    	itemStorage.add(tempItem);
	    }
	    originalItemStorage = itemStorage;
	    setPage(0, itemStorage);   
	}
    
    @FXML
    private void itemClick(MouseEvent event) throws IOException
    /**
     * Switches to itemPage.fxml
     * @param event any mouse event
     */
    {
    	String id = event.getPickResult().getIntersectedNode().getId();
    	try
    	{
	    	Item currentItem = itemStorage.get(((Integer.parseInt(id.substring(id.length()-1)))-1)+(4*currentPage));
	    	ChosenItemData itemStore = ChosenItemData.getInstance();
	    	itemStore.setCurrentItem(currentItem);
	    	
	    	Parent root2 = FXMLLoader.load(getClass().getClassLoader().getResource("itemPage.fxml"));
			Scene itemScene = new Scene(root2);
			Stage window = (Stage) item1.getScene().getWindow();
			window.setResizable(false);
			window.setScene(itemScene);
			window.show();
    	}
    	catch (Exception e)
    	{
    		
    	}
    }
    
    @FXML
    private void goRight()
    /**
     * Moves to next page
     */
    {
    	currentPage++;
    	setPage(currentPage, itemStorage);
    }
    
    @FXML
    private void goLeft()
    /**
     * Moves to previous page
     */
    {
    	currentPage--;
    	setPage(currentPage, itemStorage);
    }
    
    @FXML
    private void goSearch()
    /**
     * Takes String from search bar, searches for Strings that include
     * it or its pieces, and displays them
     */
    {
    	searchFlag = true;
    	if (!searchBar.getText().trim().isEmpty())
    	{
        	ArrayList<Item> itemStorageClone = new ArrayList<>(originalItemStorage);
        	ArrayList<Item> newList = new ArrayList<Item>();
	    	ArrayList<Item> searchArrayList = searchString(searchBar.getText(), itemStorageClone, newList);
	    	if (!searchArrayList.isEmpty())
	    	{
		    	currentPage = 0;
		    	itemStorage = searchArrayList;
		    	searchStorage = itemStorage;
		    	setPage(currentPage, itemStorage);
	    	}
    	}
    	sortDrop.setValue("No sort");
    }
    
    @FXML
    private void goHome()
    /**
     * Returns to first page
     */
    {
    	searchFlag = false;
    	sortDrop.setValue("No sort");
    	itemStorage = originalItemStorage;
    	currentPage = 0;
    	setPage(currentPage, itemStorage);
    }
    
    private int findNumOfPages(ArrayList<Item> items)
    /**
     * Finds the number of pages an ArrayList of items will take up
     * @param items arraylist of item classes
     * @return int number of pages data will take up
     */
    {
    	return Math.abs((int) Math.ceil(items.size()/4.0)-1);
    }
    
    private ArrayList<Item> searchString(String input, ArrayList<Item> itemStorageClone, ArrayList<Item> newList)
    /**
     * 
     * @param input
     * @return ArrayList<Item>
     */
    {	
    	for(Item x : itemStorageClone)
    	{
    		if((x.getName().length() >= input.length()) && (x.getName().substring(0, input.length()).equalsIgnoreCase(input)))
    		{
    			
    			newList.add(x);
    		}
    	}
    	
    	for(Item x : newList)
    	{
    		if(itemStorageClone.contains(x))
    		{
    			itemStorageClone.remove(x);
    		}
    	}
    	
    	if (input.length() > 1)
    	{
    		newList = searchString(input.substring(0, (input.length()-1)), itemStorageClone, newList);
    	}
    	return newList;
    }
    
    @FXML
    private void goCart() throws IOException
    {
    	searchFlag = false;
    	CartData shopCart = CartData.getInstance();
		if (!shopCart.getShopList().isEmpty())
		{
	    	Parent root2 = FXMLLoader.load(getClass().getClassLoader().getResource("cartPage.fxml"));
			Scene itemScene = new Scene(root2);
			Stage window = (Stage) item1.getScene().getWindow();
			window.setResizable(false);
			window.setScene(itemScene);
			window.show();
		}
    }
    
    @SuppressWarnings("unchecked")
	@FXML
    private void dropSort()
    {
    	sortDrop.setOnAction((e) -> {
    		Double[] priceArray = new Double[itemStorage.size()];
    		ArrayList<Item> tempItemStorage = (ArrayList<Item>) itemStorage.clone();
    		ArrayList<Item> sortedList = new ArrayList<Item>();
    		ArrayList<Double> priceArrayList = new ArrayList<Double>();
    		boolean exit = false;
    		int count = 0;
    		
    		switch(sortDrop.getValue()) {
    			case "No sort":
    				if (searchFlag == true)
    				{
    					itemStorage = searchStorage;
    				}
    				else
    				{
    					itemStorage = originalItemStorage;
    				}
    				setPage(currentPage, itemStorage);
    				break;
    			case "Price Ascending":
    				for(Item x : tempItemStorage)
    				{
    					priceArrayList.add(x.getPrice());
    				}
    				priceArrayList.toArray(priceArray);
    				
    				sorting.sortSmallToLarge(priceArray, priceArray.length);
    				
    				for(double x : priceArray)
    				{
    					while (exit != true)
    					{
    						if (tempItemStorage.get(count).getPrice() == x)
    						{
    							sortedList.add(tempItemStorage.get(count));
    							tempItemStorage.remove(tempItemStorage.get(count));
    							exit = true;
    						}
    						count++;
    					}
    					count = 0;
    					exit = false;
    				}
    				itemStorage = sortedList;
    				setPage(currentPage, itemStorage);
    				break;
    				
    			case "Price Descending":
    				for(Item x : tempItemStorage)
    				{
    					priceArrayList.add(x.getPrice());
    				}
    				priceArrayList.toArray(priceArray);
    				
    				sorting.sortLargeToSmall(priceArray, priceArray.length);
    				
    				for(double x : priceArray)
    				{
    					while (exit != true)
    					{
    						if (tempItemStorage.get(count).getPrice() == x)
    						{
    							sortedList.add(tempItemStorage.get(count));
    							tempItemStorage.remove(tempItemStorage.get(count));
    							exit = true;
    						}
    						count++;
    					}
    					count = 0;
    					exit = false;
    				}
    				itemStorage = sortedList;
    				setPage(currentPage, itemStorage);
    				break; 	
    				
    			case "Alphabetical":
    				String[] nameArray = new String[itemStorage.size()];
    	    		ArrayList<String> nameArrayList = new ArrayList<String>();
    	    		
    				for(Item x : tempItemStorage)
    				{
    					nameArrayList.add(x.getName());
    				}
    				nameArrayList.toArray(nameArray);
    				
    				sorting.sortSmallToLarge(nameArray, nameArray.length);
    				
    				for(String x : nameArray)
    				{
    					while (exit != true)
    					{
    						if (tempItemStorage.get(count).getName().equalsIgnoreCase(x))
    						{
    							sortedList.add(tempItemStorage.get(count));
    							tempItemStorage.remove(tempItemStorage.get(count));
    							exit = true;
    						}
    						count++;
    					}
    					count = 0;
    					exit = false;
    				}
    				itemStorage = sortedList;
    				setPage(currentPage, itemStorage);
    				break;
    			}
    	});
    }
    
	private void setPage(int page, ArrayList<Item> items)
	{
		int numOfPages = findNumOfPages(items);
		
		int numOfBackpage = items.size()%4;
		
		Item first;
		Item second;
		Item third;
		Item fourth;
		
		item1.setImage(null);
    	item2.setImage(null);
    	item3.setImage(null);
    	item4.setImage(null);
		
		if (numOfPages == 0)
		{
			leftButton.setDisable(true);
			rightButton.setDisable(true);
		}
		else if (page == numOfPages)
		{
			leftButton.setDisable(false);
			rightButton.setDisable(true);
		}
		else if (page == 0)
		{
			rightButton.setDisable(false);
			leftButton.setDisable(true);
		}
		else
		{
			leftButton.setDisable(false);
			rightButton.setDisable(false);
		}
		
		if ((page == numOfPages) && (numOfBackpage != 0))
	       {
	           switch(numOfBackpage) {
	               case 1: numOfBackpage = 1;
	                   first = items.get((page*4));
	                  
	                   item1.setImage(new Image(first.getImage()));
	                   info1.setText(first.getName() + " - $" + String.format("%.2f", first.getPrice()));
	                  
	                   info2.setText("");
	                  
	                   info3.setText("");
	                  
	                   info4.setText("");
	                   break;
	                      
	               case 2: numOfBackpage = 2;
	                   first = items.get((page*4));
	                   second = items.get((page*4)+1);
	                  
	                   item1.setImage(new Image(first.getImage()));
	                   info1.setText(first.getName() + " - $" + String.format("%.2f", first.getPrice()));
	                  
	                   item2.setImage(new Image(second.getImage()));
	                   info2.setText(second.getName() + " - $" + String.format("%.2f", second.getPrice()));
	                  
	                   info3.setText("");
	                  
	                   info4.setText("");
	                   break;
	              
	               case 3: numOfBackpage = 3;
	                   first = items.get((page*4));
	                   second = items.get((page*4)+1);
	                   third = items.get((page*4)+2);
	                  
	                   item1.setImage(new Image(first.getImage()));
	                   info1.setText(first.getName() + " - $" + String.format("%.2f", first.getPrice()));
	                  
	                   item2.setImage(new Image(second.getImage()));
	                   info2.setText(second.getName() + " - $" + String.format("%.2f", second.getPrice()));
	                  
	                   item3.setImage(new Image(third.getImage()));
	                   info3.setText(third.getName() + " - $" + String.format("%.2f", third.getPrice()));
	                  
	                   info4.setText("");
	                   break;
	           }
	       }
	       else
	       {
	           first = items.get((page*4));
	           second = items.get((page*4)+1);
	           third = items.get((page*4)+2);
	           fourth = items.get((page*4)+3);
	          
	           item1.setImage(new Image(first.getImage()));
	           info1.setText(first.getName() + " - $" + String.format("%.2f", first.getPrice()));
	          
	           item2.setImage(new Image(second.getImage()));
	           info2.setText(second.getName() + " - $" + String.format("%.2f", second.getPrice()));
	          
	           item3.setImage(new Image(third.getImage()));
	           info3.setText(third.getName() + " - $" + String.format("%.2f", third.getPrice()));
	          
	           item4.setImage(new Image(fourth.getImage()));
	           info4.setText(fourth.getName() + " - $" + String.format("%.2f", fourth.getPrice()));
	       }
	}
}


