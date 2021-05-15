package GameMechanic.Objects.TechnicalObjects;

import GameMechanic.Objects.Cards.Card;
import GameMechanic.Objects.GameObjects.GameObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    public Player(Account account, double xOfPlayer, double yOfPlayer) {
        this.account = account;
        this.xOfPlayer = xOfPlayer;
        this.yOfPlayer = yOfPlayer;
    }

    public Account account;

    public double xOfPlayer;
    public double yOfPlayer;

    public int amountOfDeaths;


    public ArrayList<Card> slots = new ArrayList<>();

    public ArrayList<Card> slotsPants = new ArrayList<>();
    public ArrayList<Card> slotsShirt = new ArrayList<>();
    public ArrayList<Card> slotsHands = new ArrayList<>();


    public int inventorySlots = 20; //Количество слотов в ручном инвенторе.
    public int levelInventory = 1; //Уровень прокачки ручного инвентаря.

    public int walkSpeed = 1; //Скорость передвижения игрока.
    public int runSpeed = 2;
    public int levelSpeed = 1; //Уровень прокачки скорости передвижения.

    public int maxHealth = 10;
    public int health = maxHealth; //Количество текущего здоровья игрока.
    public int levelHealth = 1; //Уровень прокачки количества здоровья.

    public int damage = 1; //Количество среднего наносимового урона игрока.
    public int levelDamage = 1; //Уровень прокачки количества среднего наносимового урона.
    public int criticalDamage = 1; //Количество среднего критического урона игрока.
    public int levelCriticalDamage = 1; //Уровень прокачки среднего критического урона.

    public int protection = 1; //Количество среднего отражаемого урона, а также сила блока ударов игрока.
    public int levelProtection = 1; //Уровень прокачки среднего отражаемого урона, а также силы блока ударов.


    public int amountOfAllGettingCardWoods = 0;
    public int amountOfAllGettingCardStones = 0;
    public int amountOfAllGettingCardSmallStones = 0;
    public int amountOfAllGettingCardGolds = 0;
    public int amountOfAllGettingCardDiamonds = 0;

    public int amountOfAllGettingCards = 0;

    public int amountOfCompletedQuests = 0;

    public ArrayList<GameObject> listOfNearbyGameObjects = new ArrayList<>();
}
