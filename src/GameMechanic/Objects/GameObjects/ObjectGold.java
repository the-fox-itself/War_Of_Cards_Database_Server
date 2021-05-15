package GameMechanic.Objects.GameObjects; //Пакет класса.

import static GameMechanic.GameMainVariables.*;

public class ObjectGold extends GameObject { //Золото.
    public ObjectGold(int xOnWorld, int yOnWorld) { //Конструктор для установления начальных характеристик.
        System.out.println("Creating object of class ObjectGold...");
        this.xOnWorld = xOnWorld;
        this.yOnWorld = yOnWorld;
        this.iconOfFar = ICON_OBJECT_UNDERGROUND;
        this.iconOfNearby = ICON_OBJECT_GOLD;
        this.name = NAME_GOLD; //Его название.
        System.out.println("Finished creating object of class ObjectGold.");
        System.out.println("Created object "+name+" [width: "+width+", height: "+height+", xOnWorld: "+this.xOnWorld+", yOnWorld: "+this.yOnWorld+", color: "+color+", iconOfFar: "+iconOfFar+", iconOfNearby: "+iconOfNearby+"]");
    }

    @Override
    public void recovery() {
        iconOfFar = ICON_OBJECT_UNDERGROUND;
        iconOfNearby = ICON_OBJECT_GOLD;
    }
}
