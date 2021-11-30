package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
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
    private ArrayList<Item> copyItemStorage = new ArrayList<Item>(itemStorage);
    
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
	    	
	    	int itemId = Integer.parseInt(temp.substring(0));
	    	
	    	Item tempItem = new Item(name, price, quantity, itemId);
	    	itemStorage.add(tempItem);
	    }
	    copyItemStorage = itemStorage;
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
    	Item currentItem = itemStorage.get(((Integer.parseInt(id.substring(id.length()-1)))-1)+(4*currentPage));
    	ChosenItemData itemStore = ChosenItemData.getInstance();
    	itemStore.setCurrentItem(currentItem);
    	
    	Parent root2 = FXMLLoader.load(getClass().getClassLoader().getResource("application/itemPage.fxml"));
		Scene itemScene = new Scene(root2);
		Stage window = (Stage) item1.getScene().getWindow();
		window.setResizable(false);
		window.setScene(itemScene);
		window.show();
    }
    
    @FXML
    private void goRight()
    /**
     * Moves to next page
     */
    {
    	currentPage++;
    	setPage(currentPage, itemStorage);
    	System.out.println(currentPage);
    }
    
    @FXML
    private void goLeft()
    /**
     * Moves to previous page
     */
    {
    	currentPage--;
    	setPage(currentPage, itemStorage);
    	System.out.println(currentPage);
    }
    
    @FXML
    private void goSearch()
    /**
     * Takes String from search bar, searches for Strings that include
     * it or its pieces, and displays them
     */
    {
    	if (!searchBar.getText().trim().isEmpty())
    	{
	    	ArrayList<Item> searchArrayList = searchString(searchBar.getText());
	    	if (!searchArrayList.isEmpty())
	    	{
		    	currentPage = 0;
		    	copyItemStorage = itemStorage;
		    	itemStorage = searchArrayList;
		    	setPage(currentPage, itemStorage);
		    	/*if (findNumOfPages(searchArrayList) == 1 || findNumOfPages(searchArrayList) == 0)
		    	{
		    		setPage(currentPage, searchArrayList, true);
		    	}
		    	else
		    	{
		    		setPage(currentPage, searchArrayList);
		    	}*/
	    	}
    	}
    }
    
    @FXML
    private void goHome()
    /**
     * Returns to first page
     */
    {
    	sortDrop.setValue("No sort");
    	itemStorage = copyItemStorage;
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
    
    private ArrayList<Item> searchString(String input)
    /**
     * 
     * @param input
     * @return ArrayList<Item>
     */
    {
    	ArrayList<Item> itemStorageClone = new ArrayList<>(itemStorage);
    	ArrayList<Item> newList = new ArrayList<Item>();
    	
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
    		newList = searchString(input.substring(0, (input.length()-1)));
    	}
    	
    	return newList;
    }
    
    @FXML
    private void goCart() throws IOException
    {
    	Parent root2 = FXMLLoader.load(getClass().getClassLoader().getResource("application/cartPage.fxml"));
		Scene itemScene = new Scene(root2);
		Stage window = (Stage) item1.getScene().getWindow();
		window.setResizable(false);
		window.setScene(itemScene);
		window.show();

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
    				itemStorage = copyItemStorage;
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
	
   /* private void setPage(int page, ArrayList<Item> items, boolean lock)
	{
    	setPage(page, items);
    	if (lock == true)
    	{
	    	leftButton.setDisable(true);
			rightButton.setDisable(true);
    	}
    	else
    	{
    		leftButton.setDisable(false);
			rightButton.setDisable(false);
    	}
	}*/
    
	private void setPage(int page, ArrayList<Item> items)
	{
		int numOfPages = findNumOfPages(items);
		System.out.print(numOfPages);
		int numOfBackpage = items.size()%4;
		
		Item first;
		Item second;
		Item third;
		Item fourth;
		
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
					info1.setText(first.getName() + " - " + first.getPrice());
					
					info2.setText("");
					
					info3.setText("");
					
					info4.setText("");
					break;
						
				case 2: numOfBackpage = 2;
					first = items.get((page*4));
					second = items.get((page*4)+1);
					info1.setText(first.getName() + " - " + first.getPrice());
					
					info2.setText(second.getName() + " - " + second.getPrice());
					
					info3.setText("");
					
					info4.setText("");
					break;
				
				case 3: numOfBackpage = 3;
					first = items.get((page*4));
					second = items.get((page*4)+1);
					third = items.get((page*4)+2);
					info1.setText(first.getName() + " - " + first.getPrice());
					
					info2.setText(second.getName() + " - " + second.getPrice());
					
					info3.setText(third.getName() + " - " + third.getPrice());
					
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
			
			info1.setText(first.getName() + " - " + first.getPrice());
			
			info2.setText(second.getName() + " - " + second.getPrice());
			
			info3.setText(third.getName() + " - " + third.getPrice());
			
			info4.setText(fourth.getName() + " - " + fourth.getPrice());
		}
	}
}


