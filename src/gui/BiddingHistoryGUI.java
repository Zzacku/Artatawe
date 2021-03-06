package gui;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;
import data.Data;
import users.Bid;
public class BiddingHistoryGUI extends Application {

    FlowPane root;
    BorderPane border;
    VBox root2;
    VBox root3;
    
    private TableView table = new TableView();
    private ArrayList<Bid> bids = new ArrayList<Bid>();
    
    SystemController s = new SystemController();
   // String usersName = s.user.getFirstName() + " " + s.user.getLastName();
     ArrayList<Bid> findBidder = Data.getBidsByBidderName("Corey");
    private final ObservableList<users.Bid> data = FXCollections.observableArrayList(findBidder);

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        border = new BorderPane();
        root = new FlowPane();
        root2 = new VBox();
        root3 = new VBox();
        
        //setup buttons
        Button btnProfile = new Button("Back To Profile ");
        btnProfile.setPadding(new Insets(10,10,10,10));
        
        
        
        //tableView
        table.setEditable(true);
        
        TableColumn artworkNameCol = new TableColumn("Artwork Name");
        artworkNameCol.setCellValueFactory(
                new PropertyValueFactory<users.Bid, String>("artworkName"));
        
        TableColumn sellerNameCol = new TableColumn("Seller Name");
        sellerNameCol.setCellValueFactory(
                new PropertyValueFactory<users.Bid, String>("sellerName"));
        
        TableColumn yourBidCol = new TableColumn("Your Bid");
        yourBidCol.setCellValueFactory(
                new PropertyValueFactory<users.Bid, String>("newBid"));
        
        TableColumn wonBid = new TableColumn("Won Bid");
        wonBid.setCellValueFactory(
                new PropertyValueFactory<users.Bid, String>("wonBid"));
        
        TableColumn bidTime = new TableColumn("Bid Time");
        bidTime.setCellValueFactory(
                new PropertyValueFactory<users.Bid, String>("bidTime"));
        
        table.getColumns().addAll(artworkNameCol, sellerNameCol, yourBidCol, wonBid,bidTime);
        table.setItems(data);
        table.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY );
        
        //table.setPadding(new Insets(100,10,10,10));
        //add everything to vbox
        root2.setPadding(new Insets(400,750,20,50));
        root2.setSpacing(15);
        root2.getChildren().addAll(btnProfile);
        root3.setPadding(new Insets(50,100,150,-800));
        root3.getChildren().addAll(table); 
        
        border.setLeft(root2);
        border.setCenter(root3);


        Scene showArt = new Scene(border, 900, 500);
        
        primaryStage.setScene(showArt);
        primaryStage.setTitle("Bidding History");
        primaryStage.show();
    }

    public static void main(String[] args) {
    	Data.populateBid();
        launch(args);
    }

    //Show the Painting or Sculpture or both

            
}

