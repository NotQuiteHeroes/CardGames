import javafx.application.Application;
import javafx.application.Platform;
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

  //Initialize deck
Deck deck;

//Card measurements
Card blankCard = new Card("BLANK");
int cardHeight = blankCard.getCardHeight();
int cardWidth = blankCard.getCardWidth();
double cardX = 45;
double cardY = 25;

//Stack all groups and panes are added to
StackPane rootStack = new StackPane();
//row for cards
Group row = new Group();
//buttons
Button buttonToDeal, buttonToShuffle, buttonToRestart;

public void start(Stage stage)
{
        //window title
        stage.setTitle("Draw Test");
        drawDeck();
        Card activeCard = deck.drawOne();
        cardX = cardWidth + 65;
        drawCardImage(activeCard);
        cardX = 45;

        //deal button --- draws seven cards on screen
        buttonToDeal = new Button("DEAL");
        buttonToDeal.setOnAction((ActionEvent event)->
                {
                        drawSeven();
                });

        //shuffle button -- shuffles the deck
        buttonToShuffle = new Button("Shuffle");
        buttonToShuffle.setOnAction((ActionEvent event)->
                {
                        deck.shuffle();
                });

        //restart button -- TESTING ONLY -> Relaunches application
        buttonToRestart = new Button("Restart");
        buttonToRestart.setOnAction((ActionEvent event)->
                {
                      System.out.println("TESTING PURPOSES ONLY --- RESTART");
                      stage.close();
                      Platform.runLater(() -> new DrawTest().start(new Stage()));
                }
        );

        setUpStack();
        //make visible
        stage.setScene(setUpScene());
        stage.show();

}

public void drawDeck()
{
  //create deck object
  deck = new Deck();

  //'deck' in upper left hand corner
  ImageView cornerImg = new ImageView();
  setMeasurementsAndLayouts(cornerImg);
  cornerImg.setImage(blankCard.getCardBack());

  //add 'deck' to row
  row.getChildren().add(cornerImg);
}

public void setMeasurementsAndLayouts(ImageView img)
{
        img.setFitWidth(cardWidth);
        img.setFitHeight(cardHeight);
        img.setLayoutX(cardX);
        img.setLayoutY(cardY);
}

public void setCardImage(ImageView img, Card card)
{
        img.setImage(card.getCardFace());
}

public void drawCardImage(Card card)
{
        ImageView imgView = new ImageView();
        setMeasurementsAndLayouts(imgView);
        setCardImage(imgView, card);
        row.getChildren().add(imgView);
}

public void drawSeven()
{
  //draw other seven cards on screen
  for(int i = 0; i < 7; i++)
  {
          Card newCard = deck.drawOne();
          logging(newCard);
          //if first card in row
          if(i == 0)
          {
                  cardX = (cardWidth * i) + 45;
          }
          //if not first card in row
          else
          {
                  cardX = ((cardWidth * i) + 20 * i) + 45;
          }
          cardY = cardHeight + 45;

          //set image for new card
          drawCardImage(newCard);
  }
}

public BorderPane setUpButtonRow()
{
  HBox buttonsPane = new HBox(16);
  buttonsPane.getChildren().addAll(buttonToDeal, buttonToShuffle, buttonToRestart);
  buttonsPane.setAlignment(Pos.CENTER);
  buttonsPane.setPadding(new Insets(0, 0, 20, 0));

  //Add row of buttons to pane, position at bottom
  BorderPane borderPane = new BorderPane();
  borderPane.setBottom(buttonsPane);
  return borderPane;
}

public Group setUpCardGroup()
{
  //add row with cards to main group
  Group mainGroup = new Group();
  mainGroup.setManaged(false);
  mainGroup.getChildren().add(row);
  return mainGroup;
}

public void logging(Card card)
{
  if(card.isRed())
  {
          System.out.println("Red Card: ");
          System.out.println(card.toString());
  }
}

public void setUpStack()
{
  //Row of buttons
  BorderPane buttonPane = setUpButtonRow();

  //card group
  Group cardGroup = setUpCardGroup();

  //set Up Stack Scene And Stage
  rootStack.getChildren().addAll(buttonPane, cardGroup);
  rootStack.setBackground(null);
}

public Scene setUpScene()
{
  //create scene with main stack
  Scene scene = new Scene(rootStack, 910, 700);
  //background for scene
  scene.setFill(Color.rgb(2, 75, 48));
  return scene;
}

public static void main(String[] args)
{
        launch(args);
}
}
