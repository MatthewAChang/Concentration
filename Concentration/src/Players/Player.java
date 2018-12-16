package Players;

public class Player {
    private String name;
    private int score;
    private int id;


    public Player(int id)
    {
        this.id = id;
        this.name = "Player " + id;
        this.score = 0;
    }
    public void addScore()
    {
        this.score++;
    }

    public int getId()
    {
        return this.id;
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
