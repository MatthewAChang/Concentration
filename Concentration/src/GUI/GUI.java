package GUI;

import Card.CardButton;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class GUI extends JPanel
{
    private static GUI instance;
    private static ArrayList<CardButton> buttons;
    private static ArrayList<JLabel> playerScores;
    private static ArrayList<String> playerNames;
    private static int clicks;

    private static int[] cardsBucket;

    private final static int NUM_OF_PLAYERS = 2;

    private final Font font = new Font("Courier New", Font.PLAIN, 18);

    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 500;

    private final int NUM_OF_BUTTON_WIDTH = 6;
    private final int NUM_OF_BUTTON_HEIGHT = 3;

    private GUI()
    {
        buttons = new ArrayList<>();
        playerScores = new ArrayList<>();
        playerNames = new ArrayList<>();
        clicks = 0;
        cardsBucket = new int[(NUM_OF_BUTTON_WIDTH * NUM_OF_BUTTON_HEIGHT) / 2];
        AddNames();
        CreateFrame();
    }

    // Add the player names
    private void AddNames()
    {
        playerNames.add("Matthew");
        playerNames.add("Catherine");
    }

    public static GUI getInstance() {
        if(instance == null) {
            instance = new GUI();
        }
        return instance;
    }

    // Create JFrame
    private void CreateFrame()
    {
        JFrame frame = new JFrame();
        frame.setTitle("Love Live Concentration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);

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

            JLabel playerLabel = new JLabel(playerNames.get(i));
            playerLabel.setFont(font);
            playerLabel.setBorder(padding);

            playerScores.add(new JLabel("0"));
            playerScores.get(i).setFont(font);

            // Creates the left side of the score board
            if (i == 0)
            {
                playerPanel.add(playerLabel, BorderLayout.WEST);
                playerPanel.add(playerScores.get(i), BorderLayout.EAST);
                playerContainerPanel.add(playerPanel, BorderLayout.WEST);
            }
            // Creates the right side of the score board
            else if (i == 1)
            {
                playerPanel.add(playerLabel, BorderLayout.EAST);
                playerPanel.add(playerScores.get(i), BorderLayout.WEST);
                playerContainerPanel.add(playerPanel, BorderLayout.EAST);
            }
        }

        return playerContainerPanel;
    }

    // Create the cards
    private JPanel CreateCardsPanel()
    {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(NUM_OF_BUTTON_HEIGHT, NUM_OF_BUTTON_WIDTH));
        for(int i = 0; i < NUM_OF_BUTTON_HEIGHT * NUM_OF_BUTTON_WIDTH; i++)
        {
            CardButton button = new CardButton(CreateRandomCard());
            buttonPanel.add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!button.isFaceUp() && clicks < 2)
                    {
                        button.setFaceUp();
                        clicks++;
                        if(clicks == 2 && CardsEqual() != null)
                        {
                            clicks = 0;

                        }

                    }
                }
            });
            buttons.add(button);
        }
        buttonPanel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        return buttonPanel;
    }

    private ArrayList<CardButton> CardsEqual()
    {
        ArrayList<CardButton> buttonsToBeCompared = new ArrayList<>();
        for (CardButton b : buttons)
        {
            if (b.isFaceUp())
            {
                buttonsToBeCompared.add(b);
            }
        }
        if (buttonsToBeCompared.get(0).equals(buttonsToBeCompared.get(1)))
        {
            return buttonsToBeCompared;
        }
        return null;
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

    // Increase the score of a player
    public boolean increaseScore(int player)
    {
        try
        {
            int score = Integer.parseInt(playerScores.get(player).getText()) + 1;
            playerScores.get(player).setText(Integer.toString(score));
            return true;
        }
        catch(Exception e)
        {
            Error("Error - " + e.getMessage());
        }
        return false;
    }

    // Outputs an Error Prompt
    public void Error(String err)
    {
        JOptionPane.showMessageDialog(null, err);
    }
}

