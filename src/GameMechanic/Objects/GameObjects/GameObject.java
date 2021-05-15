package GameMechanic.Objects.GameObjects; //Пакет класса.

import static GameMechanic.GameMainVariables.*;

import java.awt.*; //Импорт пакета с классом Color.
import java.io.Serializable;

public abstract class GameObject implements Serializable { //Класс-родитель для всех неживых объектов на местности.
    public int xOnWorld;
    public int yOnWorld;
    public int width; //Длина объекта по оси xOnFrame.
    public int height; //Длина объекта по оси yOnFrame.
    public Color color; //Цвет объекта.

    public int iconType;
    public transient Image iconOfFar; //Иконка объекта при его дальности от игрока.
    public transient Image iconOfNearby; //Иконка объекта при его близости к игроку.
    public boolean isNearby = false;

    public String name; //Название объекта.
    public static final String NAME_EMPTY = "empty";
    public static final String NAME_WOOD = "wood";
    public static final String NAME_STONE = "stone";
    public static final String NAME_SMALL_STONE = "small_stone";
    public static final String NAME_GOLD = "gold";
    public static final String NAME_DIAMOND = "diamond";
    public static final String NAME_BARRIER = "barrier";

    public int getXOfFrame() {
        return (int)(this.xOnWorld*10-playerCurrent.xOfPlayer);
    }
    public int getYOfFrame() {
        return (int)(this.yOnWorld*10-playerCurrent.yOfPlayer);
    }

    public abstract void recovery();
}
