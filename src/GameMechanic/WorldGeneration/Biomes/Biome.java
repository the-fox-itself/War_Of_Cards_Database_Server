package GameMechanic.WorldGeneration.Biomes;

import GameMechanic.Objects.Grounds.Ground;

import java.util.ArrayList;

public abstract class Biome {
    public Biome(ArrayList<Ground> listOfGrounds) {
        this.listOfGrounds = listOfGrounds;
    }

    public String name;
    public final static String NAME_FOREST = "forest";
    public final static String NAME_POND = "pond";

    public String typicalGround;
    public ArrayList<Ground> listOfGrounds;
}
