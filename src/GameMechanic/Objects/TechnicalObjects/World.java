package GameMechanic.Objects.TechnicalObjects;

import GameMechanic.Objects.Essences.Essence;
import GameMechanic.Objects.GameObjects.GameObject;
import GameMechanic.Objects.Grounds.Ground;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static Libraries.Methods.*;

public class World implements Serializable {
    public World() {
        printNote("Creating an object of class World", NOTE_TYPE_DONE);
    }

    public String name;
    public double percentComplete;

    public int startAmountOfDiamonds = 100;

    public ArrayList<Player> listOfPlayers = new ArrayList<>();
    public ArrayList<Essence> listOfEssences = new ArrayList<>();
    public ArrayList<Quest> listOfQuests = new ArrayList<>();

    public LinkedHashMap<GameObject, int[]> listOfObjects = new LinkedHashMap<>();
    public LinkedHashMap<int[], Ground> listOfGrounds = new LinkedHashMap<>();
}
