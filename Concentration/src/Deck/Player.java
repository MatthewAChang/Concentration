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

    public String getScore()
    {
        return Integer.toString(this.score);
    }

    public String getName()
    {
        return this.name;
    }
}
