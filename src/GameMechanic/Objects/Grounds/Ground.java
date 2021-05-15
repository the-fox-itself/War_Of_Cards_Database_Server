package GameMechanic.Objects.Grounds;

import java.awt.*;
import java.io.Serializable;

import static GameMechanic.GameMainVariables.playerCurrent;

public abstract class Ground implements Serializable {
    public Ground(int xOnWorld, int yOnWorld) {
        this.xOnWorld = xOnWorld;
        this.yOnWorld = yOnWorld;
    }
    public abstract void recovery();

    public String name;
    public final static String NAME_EMPTY = "empty";
    public final static String NAME_GRASS = "grass";
    public final static String NAME_WATER = "water";

    public int xOnWorld;
    public int yOnWorld;

    public int getXOfFrame() {
        return (int)(this.xOnWorld*10-playerCurrent.xOfPlayer);
    }
    public int getYOfFrame() {
        return (int)(this.yOnWorld*10-playerCurrent.yOfPlayer);
    }

    public transient Image icon;
}
