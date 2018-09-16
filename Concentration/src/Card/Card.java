package Card;

import javax.swing.*;

public class Card extends JButton
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
