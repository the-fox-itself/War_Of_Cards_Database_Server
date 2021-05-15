package GameMechanic.Objects.Grounds;

import static GameMechanic.GameMainVariables.*;

public class GroundGrass extends Ground {
    public GroundGrass(int xOnWorld, int yOnWorld) {
        super(xOnWorld, yOnWorld);
        this.icon = ICON_GROUND_GRASS;
        this.name = NAME_GRASS;
        System.out.println("New ground has created: " + name + ".");
    }

    @Override
    public void recovery() {
        icon = ICON_GROUND_GRASS;
    }
}
