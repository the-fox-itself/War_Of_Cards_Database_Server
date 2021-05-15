package GameMechanic.Objects.TechnicalObjects;

import GameMechanic.Objects.Essences.Essence;
import GameMechanic.Objects.GameObjects.GameObject;
import GameMechanic.Objects.Grounds.Ground;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import static GameMechanic.GameMainVariables.worldCurrent;

public class Account implements Serializable {
    public Account(String nick, String password) {
        this.nick = nick;
        this.password = password;
    }

    public void recovery() {
        for (World world : listOfWorlds) {
            for (Map.Entry<GameObject, int[]> gameObjectSet : worldCurrent.listOfObjects.entrySet()) {
                GameObject gameObject = gameObjectSet.getKey();
                gameObject.recovery();
            }
            for (Map.Entry<int[], Ground> groundSet : world.listOfGrounds.entrySet()) {
                Ground ground = groundSet.getValue();
                ground.recovery();
            }
            for (Essence essence : world.listOfEssences) {
                essence.recovery();
            }
            for (GameObject gameObject : world.listOfPlayers.get(0).listOfNearbyGameObjects) {
                gameObject.recovery();
            }
        }
        System.out.println("<War_Of_Cards> Восстановка данных совершена успешно");
    }

    public String nick;
    public String password;

    public ArrayList<World> listOfWorlds = new ArrayList<>();
}
