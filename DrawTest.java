import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.geometry.*;
import java.util.Collections;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class DrawTest extends Application
{
Deck deck;

StackPane rootStack = new StackPane();
Group row = new Group();
Card selectedCard;

public void start(Stage stage)
{
        stage.setTitle("Draw Test");
        deck = new Deck();
        Button buttonToDeal = new Button("DEAL");
        buttonToDeal.setOnAction((ActionEvent event)->
                {
                        Card cornerCard = deck.drawOne();
                        double cardX = 45;
                        double cardY = 25;
                        ImageView cornerImg = new ImageView();
                        cornerImg.setFitWidth(cornerCard.getCardWidth());
                        cornerImg.setFitHeight(cornerCard.getCardHeight());
                        cornerImg.setLayoutX(cardX);
                        cornerImg.setLayoutY(cardY);
                        cornerImg.setImage(cornerCard.getCardBack());
                        row.getChildren().add(cornerImg);
                        for(int i = 0; i < 7; i++)
                        {
                                Card newCard = deck.drawOne();
                                if(newCard.isRed())
                                {
                                        System.out.println("Red Card: ");
                                        System.out.println(newCard.toString());
                                }
                                if(i == 0)
                                {
                                        cardX = (newCard.getCardWidth() * i) + 45;
                                }
                                else
                                {
                                        cardX = ((newCard.getCardWidth() * i) + 20 * i) + 45;
                                }
                                cardY = newCard.getCardHeight() + 45;
                                ImageView img = new ImageView();
                                img.setFitWidth(newCard.getCardWidth());
                                img.setFitHeight(newCard.getCardHeight());
                                img.setLayoutX(cardX);
                                img.setLayoutY(cardY);
                                img.setImage(newCard.getCardFace());
                                row.getChildren().add(img);
                        }
                });
        Button buttonToShuffle = new Button("Shuffle");
        buttonToShuffle.setOnAction((ActionEvent event)->
                {
                        deck.shuffle();
                });

        HBox buttonsPane = new HBox(16);
        buttonsPane.getChildren().addAll(buttonToDeal, buttonToShuffle);
        buttonsPane.setAlignment(Pos.CENTER);
        buttonsPane.setPadding(new Insets(0, 0, 20, 0));

        BorderPane borderPane = new BorderPane();
        borderPane.setBottom(buttonsPane);

        Group mainGroup = new Group();
        mainGroup.setManaged(false);
        mainGroup.getChildren().add(row);
        rootStack.getChildren().addAll(borderPane, mainGroup);


        Scene scene = new Scene(rootStack, 910, 700);
        rootStack.setBackground(null);
        scene.setFill(Color.rgb(2, 75, 48));
        stage.setScene(scene);
        stage.show();

}

public static void main(String[] args)
{
        launch(args);
}
}
