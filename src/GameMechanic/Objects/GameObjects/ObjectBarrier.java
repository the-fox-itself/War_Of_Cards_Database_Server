package GameMechanic.Objects.GameObjects;

import static GameMechanic.GameMainVariables.*;

public class ObjectBarrier extends GameObject {
    public ObjectBarrier(int xOnWorld, int yOnWorld) {
        System.out.println("Creating object of class ObjectBarrier...");
        this.width = 10;
        this.height = 10;
        this.xOnWorld = xOnWorld;
        this.yOnWorld = yOnWorld;
        this.color = COLOR_OBJECT_BARRIER;
        this.name = NAME_BARRIER;
        System.out.println("Finished creating object of class ObjectBarrier.");
        System.out.println("Created object "+name+" [width: "+width+", height: "+height+", xOnWorld: "+this.xOnWorld+", yOnWorld: "+this.yOnWorld+", color: "+color+", iconOfFar: "+iconOfFar+", iconOfNearby: "+iconOfNearby+"]");
    }

    @Override
    public void recovery() {
        iconOfFar = null;
        iconOfNearby = null;
    }
}
