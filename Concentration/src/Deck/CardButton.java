package Deck;

import GUI.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class CardButton extends JButton implements GUI
{
    private Icon characterIcon;
    private Icon baseIcon;
    private int character;
    private boolean isFaceUp;
    private boolean isMatched;

    public CardButton(int character)
    {
        this.character = character;
        isFaceUp = false;
        isMatched = false;
        characterIcon = CreateIcon("/images/" + CharacterENum.toString(character) + ".jpg");
        baseIcon = CreateIcon("/images/Base.jpg");
        this.setIcon(baseIcon);
    }

    public void setFaceUp()
    {
        isFaceUp = true;
        this.setIcon(characterIcon);
    }

    public void setFaceDown()
    {
        isFaceUp = false;
        this.setIcon(baseIcon);

    }

    public void setMatched()
    {
        isMatched = true;
    }

    public boolean isFaceUp()
    {
        return isFaceUp;
    }

    public boolean isMatched()
    {
        return isMatched;
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


    private Icon CreateIcon(String imageLocation)
    {
        try {
            BufferedImage image = ImageIO.read(getClass().getResource(imageLocation));
            return new ImageIcon(
                    image.getScaledInstance(FRAME_WIDTH / NUM_OF_BUTTON_WIDTH,
                    FRAME_HEIGHT / NUM_OF_BUTTON_HEIGHT,
                    Image.SCALE_SMOOTH));
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        return null;
    }
}
