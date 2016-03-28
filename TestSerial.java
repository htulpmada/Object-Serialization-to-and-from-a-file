import java.io.*;

class PlayerAttr implements Serializable
{
    private int Strength;
    private int Dexterity;
    private int Constitution;
    private int Intelligence;
    private int Wisdom;
    private int Charisma;

    PlayerAttr(int Str, int Dex, int Con, int Int, int Wis, int Cha) {
    	Strength     = Str;
    	Dexterity    = Dex;
    	Constitution = Con;
    	Intelligence = Int;
    	Wisdom       = Wis;
    	Charisma     = Cha;
    }
    void Print() {
        System.out.printf("Str: %d, Dex: %d, Con: %d\n",
                Strength, Dexterity, Constitution);
        System.out.printf("Int: %d, Wis: %d, Cha: %d\n",
                Intelligence, Wisdom, Charisma);    
    }
};

// -------- Player class ---------------------------------------

class Player implements Serializable
{
	private String Name;
	private String Race;
	private String Class;
	private PlayerAttr pattr;


    Player (String Name, String Race, String Class,
        int Str, int Dex, int Con, int Int, int Wis, int Cha) {    	
        	this.Name  = Name;
        	this.Race  = Race;
        	this.Class = Class;
        	this.pattr = new PlayerAttr(Str, Dex, Con, Int, Wis, Cha);
        }
    void Print() {
    	System.out.printf("Name:  %s\n", Name);
    	System.out.printf("Race:  %s\n", Race);
    	System.out.printf("Class: %s\n", Class);
    	pattr.Print();   
    }
};

public class TestSerial
{
    public static void main( String[] args )
    	throws ClassNotFoundException, IOException 
    {
	    Player Joe =
	        new Player("Joe", "Human", "Thief", 10, 18, 9, 13, 10, 11);
	
	    System.out.printf("Original Joe\n");
	    Joe.Print();
	
	    ObjectOutputStream output =
	      new ObjectOutputStream(new FileOutputStream((args.length==1)?args[0]:"Player.dat"));
	
	    output.writeObject(Joe);
	
	    output.close();
	
	    ObjectInputStream input =
	        new ObjectInputStream(new FileInputStream((args.length==1)?args[0]:"Player.dat"));
	
	    Player JoeClone = (Player)(input.readObject());
	
	    input.close();
	
	    System.out.printf("\nCloned Joe");
	    JoeClone.Print();
    }

}

