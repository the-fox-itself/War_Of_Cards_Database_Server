package GameMechanic;

import GameMechanic.Objects.Cards.Card;
import GameMechanic.Objects.Cards.NaturalCards.*;
import GameMechanic.Objects.GameObjects.GameObject;

import java.util.Map;
import java.util.Objects;

import static GameMechanic.GameMainVariables.*;
import static Libraries.Methods.*;
import static Libraries.Methods.visFalse;

public class GameMechanic {
    public static void menuStartNotRegistered() {
        visFalseAll();

        visTrue(buttonSinglePlayer);
        visTrue(buttonMultiPlayer);
        visTrue(buttonExit);
        visTrue(buttonSettings);

        visTrue(buttonRegistration);
        visTrue(buttonEntry);
        visTrue(labelAccount);

        visTrue(frame);
    }
    public static void menuStartRegistered() {
        visFalseAll();

        visTrue(buttonSinglePlayer);
        visTrue(buttonMultiPlayer);
        visTrue(buttonExit);
        visTrue(buttonSettings);
        buttonSettings.setBackground(COLOR_INTERFACE_GREEN);

        visTrue(buttonExitAccount);
        visTrue(buttonSaveAccount);
        visTrue(labelAccount);
        if (accountCurrent != null) {
            labelAccount.setText("Ваш аккаунт: " + accountCurrent.nick);
        }

        visTrue(frame);
    }
    public static void menuStartEnableRegistration(boolean isActivate) {
        if (isActivate) {
            visTrue(labelRegisterNick);
            visTrue(textNick);
            visTrue(labelRegisterPassword);
            visTrue(textPassword);
            visTrue(buttonEndRegistration);

            labelNick.requestFocus();
        } else {
            visFalse(labelRegisterNick);
            visFalse(textNick);
            visFalse(labelRegisterPassword);
            visFalse(textPassword);
            visFalse(buttonEndRegistration);
        }
    }
    public static void menuStartEnableSettings(boolean isActivate) {
        if (isActivate) {
            visTrue(labelSettings);
            visTrue(buttonSettingsServer);
        } else {
            visFalse(labelSettings);
            visFalse(buttonSettingsServer);
        }
    }
    public static void menuSinglePlayerWorlds() {
        visFalseAll();

        visTrue(labelAccount);
        visTrue(buttonExitAccount);
        visTrue(buttonSaveAccount);

        visTrue(buttonExit);
        visTrue(buttonSettings);
        visTrue(buttonLoadWorld);
        visTrue(buttonNewWorld);

        visTrue(frame);
    }
    public static void menuGame() {
        visFalseAll();

        labelNick.setText(accountCurrent.nick);
        visTrue(labelNick);
        visTrue(textQuests);
    }

    public static void resourceMining(String nameOfSearchObject) {
        int indexOfNearbyObject = playerCurrent.listOfNearbyGameObjects.size() - 1;
        if (indexOfNearbyObject > -1) { //Если он не пустой, то это условие срабатывает.
            while (!playerCurrent.listOfNearbyGameObjects.get(indexOfNearbyObject).name.equals(nameOfSearchObject)) {
                indexOfNearbyObject--;
                if (indexOfNearbyObject == -1) {
                    break;
                }
            }
            if (indexOfNearbyObject > -1) { //Повторный условный оператор, т.к. после не нахождения деревьев поблизости переменная n могла стать -1.
                Card card = null;
                switch (playerCurrent.listOfNearbyGameObjects.get(indexOfNearbyObject).name){
                    case GameObject.NAME_WOOD:
                        card = new CardWood();
                        playerCurrent.amountOfAllGettingCardWoods++;
                        setNotification("Добыт ресурс: Дерево");
                        System.out.println("Добыт ресурс: Дерево");
                        break;
                    case GameObject.NAME_STONE:
                        card = new CardStone();
                        playerCurrent.amountOfAllGettingCardStones++;
                        setNotification("Добыт ресурс: Камень");
                        System.out.println("Добыт ресурс: Камень");
                        break;
                    case GameObject.NAME_SMALL_STONE:
                        card = new CardSmallStone();
                        playerCurrent.amountOfAllGettingCardSmallStones++;
                        setNotification("Добыт ресурс: Маленький камень");
                        System.out.println("Добыт ресурс: Маленький камень");
                        break;
                    case GameObject.NAME_GOLD:
                        card = new CardGold();
                        playerCurrent.amountOfAllGettingCardGolds++;
                        setNotification("Добыт ресурс: Золото");
                        System.out.println("Добыт ресурс: Золото");
                        break;
                    case GameObject.NAME_DIAMOND:
                        card = new CardDiamond();
                        playerCurrent.amountOfAllGettingCardDiamonds++;
                        setNotification("Добыт ресурс: Алмаз");
                        System.out.println("Добыт ресурс: Алмаз");
                        break;
                }
                playerCurrent.slots.add(card);
                playerCurrent.amountOfAllGettingCards++;
                worldCurrent.listOfObjects.remove(playerCurrent.listOfNearbyGameObjects.get(indexOfNearbyObject));
                playerCurrent.listOfNearbyGameObjects.remove(indexOfNearbyObject);
            }
        }
    }

    public static void playerMove(char XOnFrameOrYOnFrame, int numOfPixelsToMove) {
        if (!collisionWithBarrier(playerXFrame, playerYFrame) || godModeOn) {
            switch (XOnFrameOrYOnFrame) {
                case 'x':
                    playerCurrent.xOfPlayer -= numOfPixelsToMove;
                    break;
                case 'y':
                    playerCurrent.yOfPlayer -= numOfPixelsToMove;
                    break;
            }
        } else {
            deathAndRespawn();
        }
    }

    public static void setNotification(String notification) {
        new GameThreads.NotificationThread(notification).start();
    }

    public static void searchForNearbyGameObjects() {
        playerCurrent.listOfNearbyGameObjects.subList(0, playerCurrent.listOfNearbyGameObjects.size()).clear();
        for (Map.Entry<GameObject, int[]> gameObjectSet : worldCurrent.listOfObjects.entrySet()) {
            GameObject gameObject = gameObjectSet.getKey();
            gameObject.isNearby = false;
            for (int x = gameObject.getXOfFrame() - 50; x <= gameObject.getXOfFrame() + 50; x++) {
                if (x == playerXFrame) {
                    for (int y = gameObject.getYOfFrame() - 50; y <= gameObject.getYOfFrame() + 50; y++) {
                        if (y == playerYFrame) {
                            playerCurrent.listOfNearbyGameObjects.add(gameObject);
                            switch (gameObject.name) {
                                case GameObject.NAME_GOLD:
                                case GameObject.NAME_DIAMOND:
                                    gameObject.isNearby = true;
                                    break;
                            }
                        }
                    }
                }
            }
        }
    }

    public static void deathAndRespawn() {
        System.out.println("Уведомление. Вы умерли");
        playerCurrent.amountOfDeaths++;
        System.out.println("Уведомление. Ваше количество смертей: " + playerCurrent.amountOfDeaths);
        playerCurrent.health = playerCurrent.maxHealth;
        playerCurrent.slots.subList(0, playerCurrent.slots.size()).clear();
        playerCurrent.amountOfCompletedQuests = 0;
        playerCurrent.xOfPlayer = 0;
        playerCurrent.yOfPlayer = 0;

        setNotification("Вы умерли! Ваше текущее количество смертей: " + playerCurrent.amountOfDeaths);

        iconPlayerCurrent = ICON_PLAYER_FRONT;
    }

    public static boolean collisionWithBarrier(int x, int y) {
        for (Map.Entry<GameObject, int[]> gameObjectSet : worldCurrent.listOfObjects.entrySet()) {
            GameObject gameObject = gameObjectSet.getKey();
            if (gameObject.name.equals(GameObject.NAME_BARRIER)) {
                if ((gameObject.getXOfFrame()+4-x < 6 && gameObject.getXOfFrame()+4-x > -6) && (gameObject.getYOfFrame()+4-y < 6 && gameObject.getYOfFrame()+4-y > -6)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String save() {
        sendToServer(serverHost, serverPort, version+"⊠save⊠"+accountCurrent.nick+"⊠"+accountCurrent.password);
        if (Objects.equals(getFromServer(serverHost, serverPort, "save"), "success")) {
            sendToServer(serverHost, serverPort, accountCurrent);
            switch ((String) Objects.requireNonNull(getFromServer(serverHost, serverPort, "save"))) {
                case "not exist":
                    return "Данного аккаунта нет в базе данных сервера. Сохранение не было совершено";
                case "error":
                    return "При сохранении произошли сбои";
                case "success":
                    return "Аккаунт успешно сохранен";
                case "incorrect version":
                    labelWarning.setFont(f20);
                    labelWarning.setText("Текущая версия игры: "+version+". Требуемая версия: "+getFromServer(serverHost, serverPort, null)+".");
                    visFalse(labelWarning);
                    visTrue(labelWarning);
                    break;
            }
        } else {
            return "Сервер не отвечает. При сохранении произошли сбои";
        }
        return "Сервер не отвечает. При сохранении произошли сбои";
    }

    private static void visFalseAll() {
        visFalse(buttonSinglePlayer);
        visFalse(buttonMultiPlayer);

        visFalse(buttonRegistration);
        visFalse(buttonEntry);
        visFalse(buttonLoadAccountFromNewAccount);
        visFalse(buttonNewAccountFromLoadAccount);
        visFalse(buttonNewWorld);
        visFalse(buttonLoadWorld);
        visFalse(buttonExit);
        visFalse(buttonExitAccount);
        visFalse(buttonSaveAccount);
        visFalse(buttonSettings);
        visFalse(buttonEndRegistration);

        visFalse(buttonRight);
        visFalse(buttonLeft);

        visFalse(buttonPantsClothes);
        visFalse(buttonShirtClothes);

        visFalse(buttonPantsSlots);
        visFalse(buttonShirtSlots);
        visFalse(buttonHandsSlots);

        visFalse(labelSettings);
        visFalse(buttonSettingsServer);

        visFalse(labelRegisterNick);
        visFalse(labelRegisterPassword);
        visFalse(labelAccount);
        visFalse(labelWarning);
        visFalse(labelSlots);
        visFalse(labelNick);
        visFalse(labelSaveAccount);

        visFalse(labelWarningNeedRegistration);

        visFalse(textNick);
        visFalse(textPassword);

        visFalse(textNameForNewWorld);

        visFalse(textQuests);
    }
}
