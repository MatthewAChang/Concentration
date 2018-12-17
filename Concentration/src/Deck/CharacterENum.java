package Deck;

public enum CharacterENum
{
    HONOKA(0),
    ELI(1),
    KOTORI(2),
    UMI(3),
    RIN(4),
    MAKI(5),
    NOZOMI(6),
    HANAYO(7),
    NICO(8);

    private int character;

    CharacterENum(int character)
    {
        this.character = character;
    }

    public static String toString(int character) {
        switch(character)
        {
        case 0: return "Honoka";
        case 1: return "Eli";
        case 2: return "Kotori";
        case 3: return "Umi";
        case 4: return "Rin";
        case 5: return "Maki";
        case 6: return "Nozomi";
        case 7: return "Hanayo";
        case 8: return "Nico";
        }
        return null;
    }

}
