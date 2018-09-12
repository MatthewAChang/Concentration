package Card;

public enum CharacterENum
{
    HONOKA(1),
    ELI(2),
    KOTORI(3),
    UMI(4),
    RIN(5),
    MAKI(6),
    NOZOMI(7),
    HANAYO(8),
    NICO(9);

    private int character;

    CharacterENum(int character)
    {
        this.character = character;
    }

    public int getCharacter()
    {
        return character;
    }

    public String toString(int character) {
        switch(character)
        {
        case 1: return "Honoka";
        case 2: return "Eli";
        case 3: return "Kotori";
        case 4: return "Umi";
        case 5: return "Rin";
        case 6: return "Maki";
        case 7: return "Nozomi";
        case 8: return "Hanayo";
        case 9: return "Nico";
        }
        return null;
    }

}
