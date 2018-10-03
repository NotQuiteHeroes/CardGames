import java.util.Stack;
import java.util.*;

class Deck
{
private Stack<Card> deck;;
int size;

/**
 * Deck constructor
 * initialize new deck and set size to 52
 * fill deck with standard Cards
 */
public Deck()
{
        size = 52;
        deck = new Stack<>();
        //fill deck with Cards
        for(Suit suit: Suit.values())
        {
                for(Value value: Value.values())
                {
                        deck.push(new Card(suit, value));
                }
        }
}

/**
 * getSize gets current size of deck
 * @return [int: number of cards in the deck]
 */
public int getSize()
{
        return size;
}

/**
 * setSize: updates size of the deck
 * @param sizeChange [int: change in size of deck to apply]
 */
public void setSize(int sizeChange)
{
        this.size += sizeChange;
}

/**
 * drawOne: draw one card from the top of the deck
 * @return [Card: Card object on top of deck]
 */
public Card drawOne()
{
        return deck.pop();
}

/**
 * shuffle: shuffle the deck of cards
 */
public void shuffle()
{
        Collections.shuffle(deck);
}

}
