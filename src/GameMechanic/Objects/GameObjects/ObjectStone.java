package GameMechanic.Objects.GameObjects; //Пакет класса.

import static GameMechanic.GameMainVariables.*;

public class ObjectStone extends GameObject { //Камень.
    public ObjectStone(int xOnWorld, int yOnWorld) { //Конструктор для установления начальных характеристик.
        System.out.println("Creating object of class ObjectStone...");
        this.xOnWorld = xOnWorld;
        this.yOnWorld = yOnWorld;
        this.iconOfFar = ICON_OBJECT_STONE;
        this.name = NAME_STONE; //Его название.
        System.out.println("Finished creating object of class ObjectStone.");
        System.out.println("Created object "+name+" [width: "+width+", height: "+height+", xOnWorld: "+this.xOnWorld+", yOnWorld: "+this.yOnWorld+", color: "+color+", iconOfFar: "+iconOfFar+", iconOfNearby: "+iconOfNearby+"]");
    }

    @Override
    public void recovery() {
        iconOfFar = ICON_OBJECT_STONE;
    }
}
