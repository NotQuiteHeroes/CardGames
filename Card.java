import javafx.scene.image.*;
import java.util.*;
import javafx.scene.shape.Rectangle;
/**
 * Card
 *
 * Contructors:
 *     Default:      Card()
 *     Non-Default:  Card(Suit suit, Value value)
 *     Non-Default:  Card(String blankCard)
 *
 * Measurements:
 *      -get card height and width
 *          [Int getCardHeight(), Int getCardWidth()]
 * Holds single card information
 *      -suit and value
 *          [Suit suit, Value value]
 *      -if the card is face up or not
 *          [Boolean faceUp]
 *      -front and back image
 *          [Image cardFace, cardBack]
 *      -blank card for drawing purposes
 * Includes methods to act upon card
 *      -get suit and value
 *          [Suit getSuit(), Value getValue()]
 *      -get string format "VALUE of SUIT"
 *          [OVERRIDDEN: String toString()]
 *      -get position of card face (face down or not)
 *          [Boolean isFaceUp()]
 *      -set image for front and back of card
 *          [Void setFaceImage(), Void setBackImage()]
 *      -flip card (face down or face up)
 *          [Void flipCard()]
 *      -retrieve color of card
 *           [Boolean isRed()]
 *      -overrides comparison of cards(compareTo and equals)
 *          [OVERRIDDEN: Int compareTo(Card card)]
 *          [OVERRIDDEN: Boolean equals(Object obj)]
 */
class Card extends Rectangle implements Comparable<Card>
{

//image path resources
private static final String IMAGE_FOLDER = "Resources/imgs/Cards/";
private static final String IMAGE_TYPE = ".png";
private static final String CARD_BACKGROUND = "Resources/imgs/Cards/background.png";

//card variables
private Suit suit;
private Value value;
private boolean faceUp;
private boolean draggable;
private static final int HEIGHT = 150;
private static final int WIDTH = 99;

//Images for front and back of card
private Image cardFace;
private Image cardBack;

/**
 * Card: default constructor
 * set card in face down position by default
 */
public Card() {
        faceUp = false;
        draggable = false;
}

/**
 * Card: non-default constructor
 * set card in face down position by default
 * set correct front and back images of card
 * @param suit  [Enum: Suit of the card]
 * @param value [Enum: Face value of the card]
 */
public Card(Suit suit, Value value)
{
        this.suit = suit;
        this.value = value;
        faceUp = false;
        draggable = false;

        setFaceImage();
        setBackImage();
}

/**
 * Card: non-default constructor
 * create blank card for drawing purposes
 * @param blankCard [String: BLANK]
 */
public Card(String blankCard)
{
        faceUp = false;
        draggable = false;
        setBackImage();
}

public int getCardHeight()
{
        return HEIGHT;
}

public int getCardWidth()
{
        return WIDTH;
}

/**
 * getSuit: return the suit of the card
 * @return [Enum: suit of the card]
 */
public Suit getSuit()
{
        return suit;
}

/**
 * getValue: return the face value of the card
 * @return [Enum: value of the card]
 */
public Value getValue()
{
        return value;
}

/**
 * getCardFace: return the face image of the card
 * @return [Image: face image of the card]
 */
public Image getCardFace()
{
        return cardFace;
}

/**
 * getCardBack: return the back image of the card
 * @return [Image: back image of the card]
 */
public Image getCardBack()
{
        return cardBack;
}

/**
 * isFaceUp: is the card face down or face up
 * @return [true if card is face up, false if card is face down]
 */
public boolean isFaceUp()
{
        return faceUp;
}

/**
 * isRed: is the card a red suit
 * @return [true if card is red, false if card is black]
 */
public boolean isRed()
{
        return (suit.equals(Suit.HEARTS) || suit.equals(Suit.DIAMONDS));
}

/**
 * isDraggable: can the current card be dragged (moved)
 * @return [true if card can be moved, false otherwise]
 */
public boolean isDraggable()
{
        return draggable;
}

/**
 * flipCard: flip the card over - face up or face down
 */
public void flipCard()
{
        faceUp = !faceUp;
}

/**
 * setFaceImage: set the appropriate face image for the card
 */
public void setFaceImage()
{
        String path = getImagePath();

        try
        {
                cardFace = new Image(path);
        } catch (Exception ex)
        {
                System.out.println("Cannot load image " + path);
                cardFace = null;
        }
}

/**
 * setDraggable: set if a card can be moved or not
 * @param draggable [boolean: if card can be moved or not]
 */
public void setDraggable(boolean draggable)
{
        this.draggable = draggable;
}

/**
 * setBackImage: set the background image for the card
 */
public void setBackImage()
{
        try
        {
                cardBack = new Image(CARD_BACKGROUND);
        }
        catch (Exception ex)
        {
                System.out.println("Cannot load background " + CARD_BACKGROUND);
                cardBack = null;
        }
}

/**
 * getImagePath: build the path to the appropriate image file
 * @return [String: path to image file used for card face]
 */
public String getImagePath()
{
        return new StringBuilder()
               .append(IMAGE_FOLDER)
               .append(value.name())
               .append("_")
               .append(suit.name())
               .append(IMAGE_TYPE)
               .toString();
}

/**
 * compareTo: overrides. Compare cards
 * @param  card [Card: Card to compare current card with]
 * @return      [int:
 *                  n > 0: current card is higher than provided card
 *                  n < 0: current card is lower than provided card
 *                  n = 0: cards are of equal value]
 */
//@Override
public int compareTo(Card card)
{
        return this.suit.compareTo(card.suit);
}

/**
 * equals: overrides. Check if two cards are the same
 * @param  obj [Card to check equality of]
 * @return     [true if current card and provided card
 *              are of same value and suit]
 */
@Override
public boolean equals(Object obj)
{
        Card card = (Card) obj;
        return value == card.value && suit == card.suit;
}

/**
 * toString: overrides. Provide a to string method for Card objects
 * @return [String: VALUE of SUIT, ex: QUEEN of HEARTS]
 */
@Override
public String toString()
{
        return (value + " of " + suit);
}

}
