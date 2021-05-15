package GameMechanic.Objects.GameObjects; //Пакет класса.

import static GameMechanic.GameMainVariables.*;

public class ObjectWood extends GameObject { //Древесина.
    public ObjectWood(int xOnWorld, int yOnWorld) { //Конструктор для установления начальных характеристик.
        System.out.println("Creating object of class ObjectWood...");
        this.xOnWorld = xOnWorld;
        this.yOnWorld = yOnWorld;
        int rand = (int) (Math.random()*2);
        switch (rand) {
            case 0:
                this.iconOfFar = ICON_OBJECT_WOOD_1;
                break;
            case 1:
                this.iconOfFar = ICON_OBJECT_WOOD_2;
                break;
        }
        this.iconType = rand;
        this.name = NAME_WOOD; //Её название.
        System.out.println("Finished creating object of class ObjectWood.");
        System.out.println("Created object "+name+" [width: "+width+", height: "+height+", xOnWorld: "+this.xOnWorld+", yOnWorld: "+this.yOnWorld+", color: "+color+", iconOfFar: "+iconOfFar+", iconOfNearby: "+iconOfNearby+"]");
    }

    @Override
    public void recovery() {
        switch (iconType) {
            case 0:
                iconOfFar = ICON_OBJECT_WOOD_1;
                break;
            case 1:
                iconOfFar = ICON_OBJECT_WOOD_2;
                break;
        }
    }
}
