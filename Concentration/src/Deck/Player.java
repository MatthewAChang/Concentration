package Deck;

public class Player {
    private String name;
    private int score;

    public Player(int id)
    {
        this.name = "Player " + id;
        this.score = 0;
    }
    public void addScore()
    {
        this.score++;
    }

    public int getScore()
    {
        return this.score;
    }

    public String getName()
    {
        return this.name;
    }
}
