package GameMechanic.WorldGeneration.Biomes;

import GameMechanic.Objects.Grounds.Ground;

import java.util.ArrayList;

public class BiomeForest extends Biome {
    public BiomeForest(ArrayList<Ground> listOfGrounds) {
        super(listOfGrounds);
        this.name = NAME_FOREST;
        this.typicalGround = Ground.NAME_GRASS;
    }
}
