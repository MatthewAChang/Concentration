package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class GUI extends JPanel
{
    private static GUI instance;
    private static ArrayList<JButton> buttons;

    private static JLabel playerScoreOne;
    private static JLabel playerScoreTwo;

    private final Font font = new Font("Courier New", Font.PLAIN, 18);

    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 500;

    private final int NUM_OF_BUTTON_WIDTH = 6;
    private final int NUM_OF_BUTTON_HEIGHT = 3;

    private GUI()
    {
        buttons = new ArrayList<>();
        playerScoreOne = new JLabel("0");
        playerScoreTwo = new JLabel("0");
        CreateFrame();
    }

    public static GUI getInstance() {
        if(instance == null) {
            instance = new GUI();
        }
        return instance;
        // Matthew's comment
    }

    // Create JFrame
    private void CreateFrame()
    {
        JFrame fr = new JFrame();
        fr.setTitle("Love Live Concentration");
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setResizable(true);

        // Add the panel to the frame
        JPanel panel = CreateContainerPanel();
        fr.getContentPane().add(panel);
        fr.pack();
        fr.setVisible(true);
    }

    // Create JPanel
    private JPanel CreateContainerPanel()
    {
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());

        JPanel scorePanel = CreateScorePanel();
        JPanel cardPanel = CreateCardsPanel();

        containerPanel.add(scorePanel, BorderLayout.NORTH);
        containerPanel.add(cardPanel, BorderLayout.CENTER);

        return containerPanel;
    }

    // Create the score panel of the JPanel
    private JPanel CreateScorePanel()
    {
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BorderLayout());

        scorePanel.add(CreatePlayerPanel(true, "Catherine"), BorderLayout.WEST);
        scorePanel.add(CreatePlayerPanel(false, "Matthew"), BorderLayout.EAST);

        JPanel scores = new JPanel();
        scores.setLayout(new BorderLayout());

        playerScoreOne.setFont(font);
        playerScoreTwo.setFont(font);
        scores.add(playerScoreOne, BorderLayout.WEST);
        scores.add(playerScoreTwo, BorderLayout.EAST);
        scorePanel.add(scores, BorderLayout.CENTER);

        return scorePanel;
    }

    // Create the player panels
    private JPanel CreatePlayerPanel(boolean west, String name)
    {
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BorderLayout());

        JLabel player = new JLabel(name);
        player.setFont(font);

        Border padding = BorderFactory.createEmptyBorder(10,10,10,10);
        if(west)
        {
            playerPanel.add(player, BorderLayout.WEST);
        }
        else
        {
            playerPanel.add(player, BorderLayout.EAST);
        }
        playerPanel.setBorder(padding);

        return playerPanel;
    }

    // Create the cards
    private JPanel CreateCardsPanel()
    {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(NUM_OF_BUTTON_HEIGHT, NUM_OF_BUTTON_WIDTH));
        for(int i = 0; i < NUM_OF_BUTTON_HEIGHT * NUM_OF_BUTTON_WIDTH; i++)
        {
            JButton button = new JButton("Card");
            buttonPanel.add(button);
            buttons.add(button);
        }
        buttonPanel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        return buttonPanel;
    }
}

