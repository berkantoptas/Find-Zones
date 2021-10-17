package berkan.bt;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {

    @FXML
    private AnchorPane anchorPane_App;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button buttonCreateRandomMap_App;

    @FXML
    private Label labelZones_App;

    private ZoneCounter zoneCounter;
    private MapInterface mapInterface;

    private Thread mapThread;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        zoneCounter = new ZoneCounter();
        mapInterface = new Map();
    }

    @FXML
    private void actionCreateRandomMap(ActionEvent event){
        gridPane.getChildren().clear();
        try {
            mapThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    checkMap();
                }
            });
            mapThread.start();
            zoneCounter.Init(mapInterface);
            labelZones_App.setText(String.valueOf(zoneCounter.Solve()) + " Zones");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkMap() {
        while(Map.map == null){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Platform.runLater(new Runnable() {
            public void run() {
                try{
                    for (int i = 0; i < Map.map.length; i++)
                    {
                        for (int j = 0; j < Map.map[i].length; j++)
                        {
                            Button b = new Button("");
                            b.setMinHeight(25);
                            b.setMinWidth(25);
                            if(mapInterface.IsBorder(i,j))
                                b.setStyle("-fx-background-color: #808080;");
                            gridPane.add(b, j, i);
                        }
                    }
                } catch (Exception ex){
                    System.out.println(ex.toString());
                }
            }
        });
    }
}