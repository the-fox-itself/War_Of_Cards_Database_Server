package GameMechanic.Objects.GameObjects; //Пакет класса.

import static GameMechanic.GameMainVariables.*;

public class ObjectSmallStone extends GameObject { //Маленький камень.
    public ObjectSmallStone(int xOnWorld, int yOnWorld) { //Конструктор для установления начальных характеристик.
        System.out.println("Creating object of class ObjectSmallStone...");
        this.xOnWorld = xOnWorld;
        this.yOnWorld = yOnWorld;
        this.iconOfFar = ICON_OBJECT_SMALL_STONE;
        this.name = NAME_SMALL_STONE; //Его название.
        System.out.println("Finished creating object of class ObjectSmallStone.");
        System.out.println("Created object "+name+" [width: "+width+", height: "+height+", xOnWorld: "+this.xOnWorld+", yOnWorld: "+this.yOnWorld+", color: "+color+", iconOfFar: "+iconOfFar+", iconOfNearby: "+iconOfNearby+"]");
    }

    @Override
    public void recovery() {
        iconOfFar = ICON_OBJECT_SMALL_STONE;
    }
}
