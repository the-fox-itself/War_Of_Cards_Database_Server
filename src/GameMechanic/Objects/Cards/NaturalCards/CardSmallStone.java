package GameMechanic.Objects.Cards.NaturalCards; //Пакет класса.

import GameMechanic.Objects.Cards.Card; //Импорт класса-родителя.

public class CardSmallStone extends Card { //Карта Маленький камень.
    public CardSmallStone() { //Конструктор для установления начальных характеристик.
        System.out.println("Creating object of class CardSmallStone...");
        this.level = LEVEL_0;
        this.rarity = RARITY_POVERTY;
        this.type = TYPE_NATURAL;
        this.name = NAME_SMALL_STONE;
        this.description = "Маленький камушек,  который можно поднять с земли руками.";
        int strength = (int) (Math.random() * 100); //Создание дополнительной характеристики - прочности и присваивание её specifications.
        if (strength > 50) {
            strength -= 30;
        }
        this.specifications.add(strength);
        System.out.println("Finished creating object of class CardSmallStone.");
        System.out.println("Created card " + name + ": level: " + level + ", sort: " + rarity + ", type: " + type + ", isWear: " + isWear + ", specifications: " + specifications + ", description: " + description);
    }
}
