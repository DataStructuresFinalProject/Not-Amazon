package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
		ArrayListBag<Item> singShopCart = shopCart.getShopList();
		//setPage(0, singShopCart);	
	}
	
	private void setPage(int page, ArrayListBag<Item> items)
	{
		int numOfPages = Math.abs((int) Math.ceil(items.size()/3.0)-1);
		int numOfBackpage = items.size()%3;
		
		Item first;
		Item second;
		Item third;
		
		if (page == numOfPages)
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
					first = items.get((page*3));
					name1.setText(first.getName());
					price1.setText("Price: " + first.getPrice());
					break;
						
				case 2: numOfBackpage = 2;
					first = items.get((page*3));
					second = items.get((page*3)+1);
					name1.setText(first.getName());
					price1.setText("Price: " + first.getPrice());
					
					name2.setText(second.getName());
					price2.setText("Price: " + first.getPrice());
					
					break;
			}
		}
		else
		{
			first = items.get((page*3));
			second = items.get((page*3)+1);
			third = items.get((page*3)+2);
			
			name1.setText(first.getName());
			price1.setText("Price: " + first.getPrice());
			
			name2.setText(second.getName());
			price2.setText("Price: " + second.getPrice());
			
			name3.setText(third.getName());
			price3.setText("Price: " + third.getPrice());
		}
	}
}
