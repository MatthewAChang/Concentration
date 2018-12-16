package GUI;

import Card.Deck;
import Players.Player;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class Frame implements GUI
{
    private static Frame instance;
    private static Deck deck;
    private static ArrayList<Player> players;
    private static int turn;

    private final Font font = new Font("Courier New", Font.PLAIN, 18);

    private final static int NUM_OF_PLAYERS = 2;

    public static Frame getInstance() {
        if(instance == null) {
            instance = new Frame();
        }
        return instance;
    }

    private Frame()
    {
        deck = deck.getInstance();
        players = new ArrayList<>();
        createPlayers();
        CreateFrame();
        turn = 1;
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
    private void CreateFrame()
    {
        JFrame frame = new JFrame();
        frame.setTitle("Love Live Concentration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Add the panel to the frame
        JPanel panel = CreateContainerPanel();
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    // Create JPanel Container
    private JPanel CreateContainerPanel()
    {
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());

        JPanel playerPanel = CreatePlayerPanel();
        JPanel cardPanel = CreateCardsPanel();

        containerPanel.add(playerPanel, BorderLayout.NORTH);
        containerPanel.add(cardPanel, BorderLayout.CENTER);

        return containerPanel;
    }

    // Create the player panel of the JPanel
    private JPanel CreatePlayerPanel()
    {
        JPanel playerContainerPanel = new JPanel();
        playerContainerPanel.setLayout(new BorderLayout());

        // Creates for the number of players, should always be 2
        for (int i = 0; i < NUM_OF_PLAYERS; i++)
        {
            Border padding = BorderFactory.createEmptyBorder(10,10,10,10);
            JPanel playerPanel = new JPanel();
            playerPanel.setLayout(new BorderLayout());

            JLabel playerLabel = new JLabel(players.get(i).getName());
            playerLabel.setFont(font);
            playerLabel.setBorder(padding);

            JLabel playerScore = new JLabel(players.get(i).getScore());
            playerScore.setFont(font);
            playerScore.setBorder(padding);


            // Creates the left side of the score board
            if (i % 2 == 0)
            {
                playerPanel.add(playerLabel, BorderLayout.WEST);
                playerPanel.add(playerScore, BorderLayout.EAST);
                playerContainerPanel.add(playerPanel, BorderLayout.WEST);
            }
            // Creates the right side of the score board
            else
            {
                playerPanel.add(playerLabel, BorderLayout.EAST);
                playerPanel.add(playerScore, BorderLayout.WEST);
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



}
