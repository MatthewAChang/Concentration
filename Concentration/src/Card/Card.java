package Card;

public class Card
{
    private boolean isFaceUp;
    private int character;

    public Card()
    {
        isFaceUp = false;
        character = 0;
    }

    public Card(int character)
    {
        this.character = character;
    }

    public boolean isFaceUp()
    {
        return isFaceUp;
    }

    public int getCharacter()
    {
        return character;
    }
}
