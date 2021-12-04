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
import javafx.scene.image.Image;
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
    
    @FXML
    private Text quantity;

    Item currentItem;
    
    @FXML
    private void goCart() throws IOException
    {
    	CartData shopCart = CartData.getInstance();
		if (!shopCart.getShopList().isEmpty())
		{
	    	Parent root2 = FXMLLoader.load(getClass().getClassLoader().getResource("cartPage.fxml"));
			Scene itemScene = new Scene(root2);
			Stage window = (Stage) productImage.getScene().getWindow();
			window.setResizable(false);
			window.setScene(itemScene);
			window.show();
		}
    }
    
    @FXML
    private void addCart() throws IOException
    {
	    CartData shopCart = CartData.getInstance();
	    ChosenItemData itemStore = ChosenItemData.getInstance();
		currentItem = itemStore.getCurrentItem();
    	itemStore.setCurrentItem(currentItem);
	    shopCart.getShopList().add(currentItem);
    }
    
    @FXML
    private void goHome() throws IOException
    {
    	/*FileWriter fileWriter = new FileWriter("ItemInv.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        
        printWriter.printf("Product name is %s and its price is %d $", "iPhone", 1000);
        printWriter.close();
    	*/
    	Parent root2 = FXMLLoader.load(getClass().getClassLoader().getResource("mainPage.fxml"));
		Scene itemScene = new Scene(root2);
		Stage window = (Stage) name.getScene().getWindow();
		window.setResizable(false);
		window.setScene(itemScene);
		window.show();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ChosenItemData itemStore = ChosenItemData.getInstance();
		currentItem = itemStore.getCurrentItem();
		if (!currentItem.inStock())
    	{
			cartAdd.setDisable(true);
    	}
		else
		{
			cartAdd.setDisable(false);
		}
		name.setText(currentItem.getName());
		price.setText("Price: $"+ String.format("%.2f", currentItem.getPrice()));
		quantity.setText("Quantity: " + currentItem.getAmount());
		productImage.setImage(new Image(currentItem.getImage()));
	}
}

