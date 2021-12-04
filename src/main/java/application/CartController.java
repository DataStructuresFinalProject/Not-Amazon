package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CartController implements Initializable{

    @FXML
    private Button cartButton;

    @FXML
    private Button delete1;

    @FXML
    private Button delete2;

    @FXML
    private Button delete3;

    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    private ImageView image3;

    @FXML
    private Button upButton;

    @FXML
    private ImageView logo;

    @FXML
    private Text name1;

    @FXML
    private Text name2;

    @FXML
    private Text name3;

    @FXML
    private Text price1;

    @FXML
    private Text price2;

    @FXML
    private Text price3;

    @FXML
    private Button downButton;

    @FXML
    private TextField searchBar;

    @FXML
    private Button searchButton;

    @FXML
    private Text subtotal;

    @FXML
    private ImageView topBar;

    private int currentPage = 0;
    LinkList singShopCart;
    
    @FXML
    private void goHome() throws IOException
    {
    	Parent root2 = FXMLLoader.load(getClass().getClassLoader().getResource("mainPage.fxml"));
		Scene itemScene = new Scene(root2);
		Stage window = (Stage) image1.getScene().getWindow();
		window.setResizable(false);
		window.setScene(itemScene);
		window.show();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		CartData shopCart = CartData.getInstance();
		singShopCart = shopCart.getShopList();
		
		subtotal.setText("Subtotal: $"+String.format("%.2f", total()));
		setPage(0, singShopCart);	
	}
	
	private double total()
	{
		double total=0;
		for (Item x : singShopCart.toArray())
		{
			total+=x.getPrice();
		}
		return total;
	}
	
	@FXML
	private void remove1()
	{
		Item first = singShopCart.getEntry(currentPage*3);
		if ((currentPage*3 % 3 == 0) && (currentPage != 0))
		{
			currentPage--;
		}
		singShopCart.remove(first);
		setPage(currentPage, singShopCart);
		subtotal.setText("Subtotal: $"+String.format("%.2f", total()));
	}
	
	@FXML
	private void remove2()
	{		
		Item second = singShopCart.getEntry((currentPage*3)+1);
		if ((currentPage*3 % 3 == 0) && (currentPage != 0))
		{
			currentPage--;
		}
		
		singShopCart.remove(second);
		setPage(currentPage, singShopCart);
		subtotal.setText("Subtotal: $"+String.format("%.2f", total()));
	}
	
	@FXML
	private void remove3()
	{
		Item third = singShopCart.getEntry((currentPage*3)+2);
		if ((currentPage*3 % 3 == 0) && (currentPage != 0))
		{
			currentPage--;
		}
		
		singShopCart.remove(third);
		setPage(currentPage, singShopCart);
		subtotal.setText("Subtotal: $"+String.format("%.2f", total()));
	}
	
	@FXML
    private void goUp()
    /**
     * Moves to previous page
     */
    {
    	currentPage--;
    	setPage(currentPage, singShopCart);
    }
    
    @FXML
    private void goDown()
    /**
     * Moves to next page
     */
    {
    	currentPage++;
    	setPage(currentPage, singShopCart);
    }
	
	private void setPage(int page, LinkList items)
	{
		int numOfPages = Math.abs((int) Math.ceil(items.getLength()/3.0)-1);
		
		int numOfBackpage = items.getLength()%3;
		
		Item first;
		Item second;
		Item third;
		
		image1.setImage(null);
		image2.setImage(null);
		image3.setImage(null);
		
		if (numOfPages == 0)
		{
			upButton.setDisable(true);
			downButton.setDisable(true);
		}
		else if (page == numOfPages)
		{
			upButton.setDisable(false);
			downButton.setDisable(true);
		}
		else if (page == 0)
		{
			downButton.setDisable(false);
			upButton.setDisable(true);
		}
		else
		{
			upButton.setDisable(false);
			downButton.setDisable(false);
		}
		
		if ((page == numOfPages) && (numOfBackpage != 0))
		{
			switch(numOfBackpage) {
				case 1: numOfBackpage = 1;
					first = items.getEntry((page*3));
					name1.setText(first.getName());
					price1.setText("Price: $"+ String.format("%.2f", first.getPrice()));
					image1.setImage(new Image(first.getImage()));
					delete1.setVisible(true);
					
					name2.setText("");
					price2.setText("");
					delete2.setVisible(false);
					
					name3.setText("");
					price3.setText("");
					delete3.setVisible(false);
					
					break;
						
				case 2: numOfBackpage = 2;
					first = items.getEntry((page*3));
					second = items.getEntry((page*3)+1);
					name1.setText(first.getName());
					price1.setText("Price: $"+ String.format("%.2f", first.getPrice()));
					image1.setImage(new Image(first.getImage()));
					delete1.setVisible(true);
					
					name2.setText(second.getName());
					price2.setText("Price: $"+ String.format("%.2f", second.getPrice()));
					image2.setImage(new Image(second.getImage()));
					delete2.setVisible(true);
					
					name3.setText("");
					price3.setText("");
					delete3.setVisible(false);
					break;
			}
		}
		else if ((numOfPages == 1) && (numOfBackpage == 0))
		{
			try {
				goHome();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			first = items.getEntry((page*3));
			second = items.getEntry((page*3)+1);
			third = items.getEntry((page*3)+2);
			
			name1.setText(first.getName());
			price1.setText("Price: $"+ String.format("%.2f", first.getPrice()));
			image1.setImage(new Image(first.getImage()));
			delete1.setVisible(true);
			
			name2.setText(second.getName());
			price2.setText("Price: $"+ String.format("%.2f", second.getPrice()));
			image2.setImage(new Image(second.getImage()));
			delete2.setVisible(true);
			
			name3.setText(third.getName());
			price3.setText("Price: $"+ String.format("%.2f", third.getPrice()));
			image3.setImage(new Image(third.getImage()));
			delete3.setVisible(true);
		}
	}
}
