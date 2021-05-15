package GameMechanic.Objects.Grounds;

import static GameMechanic.GameMainVariables.*;

public class GroundWater extends Ground {
    public GroundWater(int xOnWorld, int yOnWorld) {
        super(xOnWorld, yOnWorld);
        this.icon = ICON_GROUND_WATER;
        this.name = NAME_WATER;
        System.out.println("New ground has created: " + name + ".");
    }

    @Override
    public void recovery() {
        icon = ICON_GROUND_WATER;
    }
}
