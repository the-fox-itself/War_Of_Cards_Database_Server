package GameMechanic.Objects.Cards; //Пакет класса.

import java.io.Serializable;
import java.util.ArrayList; //Импорт класса ArrayList.

public abstract class Card implements Serializable { //Класс-родитель для всех карт игры.
    public Card() {

    }

    public String name; //Название карты.
    public final static String NAME_WOOD = "wood";
    public final static String NAME_STONE = "stone";
    public final static String NAME_SMALL_STONE = "small_stone";
    public final static String NAME_GOLD = "gold";
    public final static String NAME_DIAMOND = "diamond";
    public final static String NAME_POVERTY_PANTS = "poverty_pants";
    public final static String NAME_POVERTY_SHIRT = "poverty_shirt";

    public boolean isWear; //Переменная, по которой можно определить, надета ли вещь на игрока или снята (характерна для классов пакета WeaponCards).

    public int level; //Уровень прокачки карты.
    public static final int LEVEL_0 = 0;
    public static final int LEVEL_1 = 1;
    public static final int LEVEL_2 = 2;
    public static final int LEVEL_3 = 3;
    public static final int LEVEL_4 = 4;
    public static final int LEVEL_5 = 5;

    public String rarity; //Вид, к которому принадлежит данная карта.
    public static final String RARITY_STARTING = "starting";
    public static final String RARITY_POVERTY = "poverty";
    public static final String RARITY_PEASANT = "peasant";
    public static final String RARITY_CIVIL = "civil";
    public static final String RARITY_NOBILITY = "nobility";
    public static final String RARITY_SPIRITUAL = "spiritual";

    public String type; //Тип, к которому принадлежит данная карта.
    public static final String TYPE_TRADING = "trading";
    public static final String TYPE_NATURAL = "natural";
    public static final String TYPE_INSTRUMENTAL = "instrumental";
    public static final String TYPE_CONSTRUCTION = "construction";
    public static final String TYPE_WEAPON = "weapon";
    public static final String TYPE_ALCHEMICAL = "alchemical";

    public String description; //Описание карты.
    public ArrayList specifications = new ArrayList(); //ArrayList, состоящий из вспомогательных характеристик карты.
}