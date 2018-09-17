package Card;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class CardButton extends JButton
{
    private boolean isFaceUp;
    private boolean isMatched;
    private int character;
    private Image characterImage;
    private Image baseImage;


    public CardButton(int character)
    {
        try {
            String imgCharacter = "/images/" + CharacterENum.toString(character) + ".jpg";
            characterImage = ImageIO.read(getClass().getResource(imgCharacter));
            baseImage = ImageIO.read(getClass().getResource("/images/Base.jpg"));
            this.setIcon(new ImageIcon(baseImage));
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        //this.setText(Integer.toString(character));
        isFaceUp = false;
        isMatched = false;
        this.character = character;
    }

    public void setFaceUp()
    {
        if(isFaceUp)
        {
            isFaceUp = false;
            this.setIcon(new ImageIcon(baseImage));
        }
        else
        {
            isFaceUp = true;
            this.setIcon(new ImageIcon(characterImage));
        }
    }

    public void setMatched()
    {
        if(isMatched)
        {
            isMatched = false;
        }
        else
        {
            isMatched = true;
            this.setEnabled(false);
        }
    }

    public boolean isFaceUp()
    {
        return isFaceUp;
    }

    public boolean isMatched()
    {
        return isMatched;
    }

    public int getCharacter()
    {
        return character;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        CardButton that = (CardButton) o;
        return character == that.character;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(character);
    }
}
