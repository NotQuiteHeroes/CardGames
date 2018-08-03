class Deck
{
private Stack<Card> deck = new Stack<Card>();
int size;

public Deck()
{
        size = 52;
        for(Suit suit: Suit.values())
        {
                for(Value value: Value.values())
                {
                        deck.push(new Card(suit, value));
                }
        }
}

public int getSize()
{
        return size;
}

public void shuffle()
{
        Collections.shuffle(deck);
}

public
}
