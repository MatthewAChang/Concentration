package GUI;

import Deck.CardButton;
import Deck.Player;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Frame implements GUI
{
    private static Frame instance;
    private static Deck deck;
    private static ArrayList<Player> players;
    private static int turn;

    private static ArrayList<JLabel> playerLabels;
    private static ArrayList<JLabel> scoreLabels;

    private final Font font = new Font("Courier New", Font.PLAIN, 18);
    private final Font fontBold = new Font("Courier New", Font.BOLD, 18);

    private final static int NUM_OF_PLAYERS = 2;

    public static Frame getInstance() {
        if(instance == null) {
            instance = new Frame();
        }
        return instance;
    }

    private Frame()
    {
        deck = new Deck();
        players = new ArrayList<>();
        turn = 0;
        playerLabels = new ArrayList<>();
        scoreLabels = new ArrayList<>();
        createPlayers();
        createFrame();
        updatePanel();
    }

    private void updatePanel()
    {
        for (int i = 0; i < NUM_OF_PLAYERS; i++)
        {
            playerLabels.get(i).setFont(font);
            scoreLabels.get(i).setFont(font);
            scoreLabels.get(i).setText(players.get(i).getScore());
        }
        playerLabels.get(turn).setFont(fontBold);
        scoreLabels.get(turn).setFont(fontBold);
    }

    // Create the players
    private void createPlayers()
    {
        for(int i = 0; i < NUM_OF_PLAYERS; i++)
        {
            players.add(new Player(i + 1));
        }
    }

    // Create JFrame
    private void createFrame()
    {
        JFrame frame = new JFrame();
        frame.setTitle("Love Live Concentration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Add the panel to the frame
        JPanel panel = createContainerPanel();
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    // Create JPanel Container
    private JPanel createContainerPanel()
    {
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());

        JPanel playerPanel = createPlayerPanel();
        JPanel cardPanel = CreateCardsPanel();

        containerPanel.add(playerPanel, BorderLayout.NORTH);
        containerPanel.add(cardPanel, BorderLayout.CENTER);

        return containerPanel;
    }

    // Create the player panel of the JPanel
    private JPanel createPlayerPanel()
    {
        JPanel playerContainerPanel = new JPanel();
        playerContainerPanel.setLayout(new BorderLayout());

        // Creates for the number of players, should always be 2
        for (int i = 0; i < NUM_OF_PLAYERS; i++)
        {
            Border padding = BorderFactory.createEmptyBorder(10,10,10,10);
            JPanel playerPanel = new JPanel();
            playerPanel.setLayout(new BorderLayout());

            playerLabels.add(new JLabel(players.get(i).getName()));
            playerLabels.get(i).setFont(font);
            playerLabels.get(i).setBorder(padding);

            scoreLabels.add(new JLabel(players.get(i).getScore()));
            scoreLabels.get(i).setFont(font);
            scoreLabels.get(i).setBorder(padding);

            // Creates the left side of the score board
            if (i % 2 == 0)
            {
                playerPanel.add(playerLabels.get(i), BorderLayout.WEST);
                playerPanel.add(scoreLabels.get(i), BorderLayout.EAST);
                playerContainerPanel.add(playerPanel, BorderLayout.WEST);
            }
            // Creates the right side of the score board
            else
            {
                playerPanel.add(playerLabels.get(i), BorderLayout.EAST);
                playerPanel.add(scoreLabels.get(i), BorderLayout.WEST);
                playerContainerPanel.add(playerPanel, BorderLayout.EAST);
            }
        }
        return playerContainerPanel;
    }

    // Create the cards
    private JPanel CreateCardsPanel()
    {
        return deck.addCardButton();
    }

    private class Deck
    {
        private boolean pause;
        private ArrayList<CardButton> flippedUpCards;
        private ArrayList<CardButton> buttons;

        private int[] cardsBucket;

        private Deck()
        {
            pause = false;
            flippedUpCards = new ArrayList<>();
            buttons = new ArrayList<>();
            cardsBucket = new int[(NUM_OF_BUTTON_WIDTH * NUM_OF_BUTTON_HEIGHT) / 2];
        }

        private JPanel addCardButton()
        {
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(NUM_OF_BUTTON_HEIGHT, NUM_OF_BUTTON_WIDTH));
            buttonPanel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
            for(int i = 0; i < NUM_OF_BUTTON_HEIGHT * NUM_OF_BUTTON_WIDTH; i++)
            {
                CardButton cardButton = new CardButton(createRandomCard());
                buttonPanel.add(cardButton);
                buttons.add(cardButton);
                cardButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!pause) {
                            // A card is picked
                            if (!cardButton.isMatched() && !cardButton.isFaceUp()) {
                                cardButton.setFaceUp();
                                // The card is the second card
                                if (!flippedUpCards.isEmpty()) {
                                    // A match is found
                                    if (flippedUpCards.get(0).equals(cardButton)) {
                                        flippedUpCards.get(0).setMatched();
                                        cardButton.setMatched();
                                        flippedUpCards.clear();
                                        players.get(turn).addScore();
                                    // A match is not found
                                    } else {
                                        flippedUpCards.add(cardButton);
                                        pause = true;
                                    }
                                // The card is the first card
                                } else {
                                    flippedUpCards.add(cardButton);
                                }
                            }
                        } else {
                            pause = false;
                            flippedUpCards.get(0).setFaceDown();
                            flippedUpCards.get(1).setFaceDown();
                            flippedUpCards.clear();
                            nextTurn();
                        }
                        updatePanel();
                    }
                });
            }
            return buttonPanel;
        }

        private int createRandomCard()
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

        private void nextTurn()
        {
            turn++;
            if (turn >= NUM_OF_PLAYERS)
            {
                turn = turn % NUM_OF_PLAYERS;
            }
        }

    }
}
