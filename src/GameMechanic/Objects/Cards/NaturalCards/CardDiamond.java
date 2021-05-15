package GameMechanic.Objects.Cards.NaturalCards; //Пакет класса.

import GameMechanic.Objects.Cards.Card; //Импорт класса-родителя.

public class CardDiamond extends Card { //Карта Алмаз.
    public CardDiamond() { //Конструктор для установления начальных характеристик.
        System.out.println("Creating object of class CardDiamond...");
        this.level = LEVEL_0;
        this.rarity = RARITY_CIVIL;
        this.type = TYPE_NATURAL;
        this.name = NAME_DIAMOND;
        this.description = "АЛМАЗ!!!";
        int strength = (int) (Math.random() * 100); //Создание дополнительной характеристики - прочности и присваивание её specifications.
        if (strength > 50) {
            strength -= 30;
        }
        this.specifications.add(strength);
        System.out.println("Finished creating object of class CardDiamond.");
        System.out.println("Created card " + name + ": level: " + level + ", sort: " + rarity + ", type: " + type + ", isWear: " + isWear + ", specifications: " + specifications + ", description: " + description);
    }
}
