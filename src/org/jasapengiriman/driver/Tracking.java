package org.jasapengiriman.driver;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Polma E. Tambunan
 */
public class Tracking extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception 
    {
        Parent parent = FXMLLoader.load(getClass().getResource("/org/jasapengiriman/view/Login.fxml"));
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //stop running
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() 
        {
            @Override
            public void handle(WindowEvent t) 
            {
                Platform.exit();
                System.exit(0);
            }
        });
    }
}
