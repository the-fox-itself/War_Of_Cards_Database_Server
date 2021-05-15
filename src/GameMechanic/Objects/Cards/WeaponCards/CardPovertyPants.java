package GameMechanic.Objects.Cards.WeaponCards; //Пакет класса.

import GameMechanic.Objects.Cards.Card; //Импорт класса-родителя.

public class CardPovertyPants extends Card { //Карта Брюки бродяги.
    public CardPovertyPants() { //Конструктор для установления начальных характеристик.
        System.out.println("Creating object of class CardPovertyPants...");
        this.level = LEVEL_0;
        this.rarity = RARITY_STARTING;
        this.type = TYPE_WEAPON;
        this.name = NAME_POVERTY_PANTS;
        this.description = "Старые, когда-то купленные на базаре, брюки.";
        System.out.println("Finished creating object of class CardPovertyPants.");
        System.out.println("Created card " + name + ": level: " + level + ", sort: " + rarity + ", type: " + type + ", isWear: " + isWear + ", specifications: " + specifications + ", description: " + description);
    }
}
