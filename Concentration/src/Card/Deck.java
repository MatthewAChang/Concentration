package Card;

import GUI.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Deck implements GUI
{
    private static Deck instance;
    private static CardButton flippedUpCard;
    private static ArrayList<CardButton> buttons;

    private static int[] cardsBucket;

    public static Deck getInstance() {
        if(instance == null) {
            instance = new Deck();
        }
        return instance;
    }

    public JPanel addCardButton()
    {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(NUM_OF_BUTTON_HEIGHT, NUM_OF_BUTTON_WIDTH));
        buttonPanel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        for(int i = 0; i < NUM_OF_BUTTON_HEIGHT * NUM_OF_BUTTON_WIDTH; i++)
        {
            CardButton cardButton = new CardButton(CreateRandomCard());
            buttonPanel.add(cardButton);
            buttons.add(cardButton);
            cardButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardButton.setFaceUp();
                    if(flippedUpCard != null)
                    {
                        if(flippedUpCard.equals(cardButton))
                        {

                        }
                        else
                        {
                            flippedUpCard.setFaceUp();
                            cardButton.setFaceUp();
                        }
                        flippedUpCard = null;
                    }
                    else
                    {
                        flippedUpCard = cardButton;
                    }
                }
            });
        }
        return buttonPanel;
    }

    private Deck()
    {
        flippedUpCard = null;
        buttons = new ArrayList<>();
        cardsBucket = new int[(NUM_OF_BUTTON_WIDTH * NUM_OF_BUTTON_HEIGHT) / 2];
    }

    private int CreateRandomCard()
    {
        while (true)
        {
            Random rand = new Random();
            int card = rand.nextInt(9);
            if (cardsBucket[card] < 2)
            {
                cardsBucket[card]++;
                return card;
            }
        }
    }
}
