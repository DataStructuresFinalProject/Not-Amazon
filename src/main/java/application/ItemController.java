package application;

import java.io.IOException;
import java.net.URL;
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

public class ItemController implements Initializable{

    @FXML
    private Button cartAdd;

    @FXML
    private Button cartButton;

    @FXML
    private ImageView logo;

    @FXML
    private Text name;

    @FXML
    private Text price;

    @FXML
    private ImageView productImage;

    @FXML
    private TextField searchBar;

    @FXML
    private Button searchButton;

    @FXML
    private ImageView topBar;

    Item currentItem;
    
    @FXML
    private void addCart()
    {
    	CartData shopCart = CartData.getInstance();
		ArrayListBag<Item> singShopCart = shopCart.getShopList();
		singShopCart.add(currentItem);
    }
    
    @FXML
    private void goHome() throws IOException
    {
    	Parent root2 = FXMLLoader.load(getClass().getClassLoader().getResource("application/mainPage.fxml"));
		Scene itemScene = new Scene(root2);
		Stage window = (Stage) name.getScene().getWindow();
		window.setScene(itemScene);
		window.show();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ChosenItemData itemStore = ChosenItemData.getInstance();
		currentItem = itemStore.getCurrentItem();
		name.setText(currentItem.getName());
		price.setText("Price: "+ currentItem.getPrice());
		
	}
}

