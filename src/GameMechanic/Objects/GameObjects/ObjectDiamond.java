package GameMechanic.Objects.GameObjects; //Пакет класса.

import static GameMechanic.GameMainVariables.*;

public class ObjectDiamond extends GameObject { //Алмаз.
    public ObjectDiamond(int xOnWorld, int yOnWorld) { //Конструктор для установления начальных характеристик.
        System.out.println("Creating object of class ObjectDiamond...");
        this.xOnWorld = xOnWorld;
        this.yOnWorld = yOnWorld;
        this.iconOfFar = ICON_OBJECT_UNDERGROUND;
        this.iconOfNearby = ICON_OBJECT_DIAMOND;
        this.name = NAME_DIAMOND; //Его название.
        System.out.println("Finished creating object of class ObjectDiamond.");
        System.out.println("Created object "+name+" [width: "+width+", height: "+height+", xOnWorld: "+this.xOnWorld+", yOnWorld: "+this.yOnWorld+", color: "+color+", iconOfFar: "+iconOfFar+", iconOfNearby: "+iconOfNearby+"]");
    }

    @Override
    public void recovery() {
        iconOfFar = ICON_OBJECT_UNDERGROUND;
        iconOfNearby = ICON_OBJECT_DIAMOND;
    }
}
