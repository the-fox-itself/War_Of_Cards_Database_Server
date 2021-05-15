package GameMechanic;

import GameMechanic.Objects.Cards.Card;
import GameMechanic.Objects.Cards.WeaponCards.CardPovertyPants;
import GameMechanic.Objects.Cards.WeaponCards.CardPovertyShirt;
import GameMechanic.Objects.GameObjects.*;
import GameMechanic.Objects.Grounds.GroundGrass;
import GameMechanic.Objects.Grounds.GroundWater;
import GameMechanic.Objects.TechnicalObjects.Account;
import GameMechanic.Objects.TechnicalObjects.Player;
import GameMechanic.Objects.TechnicalObjects.Quest;
import GameMechanic.Objects.TechnicalObjects.World;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import static GameMechanic.GameMainVariables.*;
import static GameMechanic.GameMechanic.*;
import static Libraries.Methods.*;

public class GameGUIListeners {
    GameGUIListeners() {
        printNote("Creating an object of class GUIListeners", NOTE_TYPE_DONE);
    }

    public static class SinglePlayer implements ActionListener { //Обработка нажатия кнопки buttonSinglePlayer.
        @Override //Переопределение метода actionPerformed(ActionEvent actionEvent).
        public void actionPerformed(ActionEvent actionEvent) {
            if (registered) {
                visFalse(labelWarningNeedRegistration);
                menuSinglePlayerWorlds();
            } else {
                needRegistrationOn = true;
                if (!labelWarningNeedRegistration.isVisible()) {
                    visTrue(labelWarningNeedRegistration);
                }
                new GameThreads.LabelNeedRegistrationThread().start();
            }
        }
    }
    public static class MultiPlayer implements ActionListener { //Обработка нажатия кнопки buttonMultiPlayer.
        @Override //Переопределение метода actionPerformed(ActionEvent actionEvent).
        public void actionPerformed(ActionEvent actionEvent) {
            if (registered) {
                //
            } else {
                needRegistrationOn = true;
                if (!labelWarningNeedRegistration.isVisible()) {
                    visTrue(labelWarningNeedRegistration);
                }
                new GameThreads.LabelNeedRegistrationThread().start();
            }
        }
    }

    public static class NewWorld implements ActionListener { //Обработка нажатия кнопки buttonNewWorld.
        @Override //Переопределение метода actionPerformed(ActionEvent actionEvent).
        public void actionPerformed(ActionEvent actionEvent) {
            worldCurrent = new World();
            playerCurrent = new Player(accountCurrent, 0, 0);
            worldCurrent.listOfPlayers.add(playerCurrent);
            accountCurrent.listOfWorlds.add(worldCurrent);

            for (int x = -501; x <= 501; x++) {
                ObjectBarrier barrier = new ObjectBarrier(x, 501);
                worldCurrent.listOfObjects.put(barrier, new int[] {barrier.xOnWorld, barrier.yOnWorld});
            }
            for (int x = -501; x <= 501; x++) {
                ObjectBarrier barrier = new ObjectBarrier(x, -501);
                worldCurrent.listOfObjects.put(barrier, new int[] {barrier.xOnWorld, barrier.yOnWorld});
            }
            for (int y = -500; y <= 500; y++) {
                ObjectBarrier barrier = new ObjectBarrier(501, y);
                worldCurrent.listOfObjects.put(barrier, new int[] {barrier.xOnWorld, barrier.yOnWorld});
            }
            for (int y = -500; y <= 500; y++) {
                ObjectBarrier barrier = new ObjectBarrier(-501, y);
                worldCurrent.listOfObjects.put(barrier, new int[] {barrier.xOnWorld, barrier.yOnWorld});
            }
            System.out.println("All ObjectBarriers have created.");

//            for (int x = worldCurrent.startAmountOfWoods; x > 0; x--) {
//                GameObject wood = new ObjectWood();
//                wood.setLocations(wood);
//                worldCurrent.listOfObjects.put(wood, new int[]{wood.xOnWorld, wood.yOnWorld});
//            }
//            System.out.println("All ObjectWoods have created.");
//            for (int x = worldCurrent.startAmountOfStones; x > 0; x--) {
//                GameObject stone = new ObjectStone();
//                stone.setLocations(stone);
//                worldCurrent.listOfObjects.put(stone, new int[] {stone.xOnWorld, stone.yOnWorld});
//            }
//            System.out.println("All ObjectStones have created.");
//            for (int x = worldCurrent.startAmountOfSmallStones; x > 0; x--) {
//                GameObject smallStone = new ObjectSmallStone();
//                smallStone.setLocations(smallStone);
//                worldCurrent.listOfObjects.put(smallStone, new int[] {smallStone.xOnWorld, smallStone.yOnWorld});
//            }
//            System.out.println("All ObjectsSmallStones have created.");
//            for (int x = worldCurrent.startAmountOfGolds; x > 0; x--) {
//                GameObject gold = new ObjectGold();
//                gold.setLocations(gold);
//                worldCurrent.listOfObjects.put(gold, new int[] {gold.xOnWorld, gold.yOnWorld});
//            }
//            System.out.println("All ObjectGolds have created.");
//            for (int x = worldCurrent.startAmountOfDiamonds; x > 0; x--) {
//                GameObject diamond = new ObjectDiamond();
//                diamond.setLocations(diamond);
//                worldCurrent.listOfObjects.put(diamond, new int[] {diamond.xOnWorld, diamond.yOnWorld});
//            }
//            System.out.println("All ObjectDiamonds have created.");
//            for (int x = worldCurrent.startAmountOfWolfs; x > 0; x--) {
//                Essence wolf = new EssenceWolf();
//                worldCurrent.listOfEssences.add(wolf);
//            }
//            System.out.println("All EssenceWolfs have created.");

            for (int x = -500; x < 500; x+=5) {
                for (int y = -500; y < 500; y+=5) {
                    int rand = (int) (Math.random()*100);
                    if (rand == 0) {
                        GroundWater groundWater = new GroundWater(x, y);
                        worldCurrent.listOfGrounds.put(new int[] {groundWater.xOnWorld, groundWater.yOnWorld}, groundWater);
                    } else {
                        GroundGrass groundGrass = new GroundGrass(x, y);
                        worldCurrent.listOfGrounds.put(new int[] {groundGrass.xOnWorld, groundGrass.yOnWorld}, groundGrass);
                    }
                }
            }
            System.out.println("All Grounds have created.");

            Quest quest1 = new Quest(1, "Собрать 10 карт\n\n Дерево", 10, Card.NAME_WOOD);
            quest1.runnableOn();
            Quest quest2 = new Quest(2, "Собрать 10 карт\n\n Камень", 10, Card.NAME_STONE);
            Quest quest3 = new Quest(3, "Собрать 5 карт\n\n Мальнький камень", 5, Card.NAME_SMALL_STONE);
            Quest quest4 = new Quest(4, "Собрать 10 карт\n\n Золото", 10, Card.NAME_GOLD);
            Quest quest5 = new Quest(5, "Собрать 10 карт\n\n Алмаз", 10, Card.NAME_DIAMOND);
            worldCurrent.listOfQuests.add(quest1);
            worldCurrent.listOfQuests.add(quest2);
            worldCurrent.listOfQuests.add(quest3);
            worldCurrent.listOfQuests.add(quest4);
            worldCurrent.listOfQuests.add(quest5);

            menuGame();

            runnableOn = true;
            regenerationOn = true;
            gameStart = true;

            repaintPhase = REPAINT_GAME;

            if (playerCurrent.slots.isEmpty()) {
                CardPovertyPants cardPovertyPants = new CardPovertyPants();
                CardPovertyShirt cardPovertyShirt = new CardPovertyShirt();
                cardPovertyPants.isWear = true;
                cardPovertyShirt.isWear = true;
                playerCurrent.slots.add(cardPovertyPants);
                playerCurrent.slots.add(cardPovertyShirt);
            }
            setNotification("Мир успешно создан!");
            iconPlayerCurrent = ICON_PLAYER_FRONT;
        } //Конец переопредлённого метода actionPerformed().
    } //Конец внутреннего класса NewWorld.
    public static class LoadWorld implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            textQuests.requestFocus();
            if (worldCurrent != null) {
                for (Quest quest : worldCurrent.listOfQuests) {
                    quest.runnableOn();
                }

                repaintPhase = REPAINT_GAME;

                menuGame();
                visFalse(labelSaveAccount);

                runnableOn = true;
                regenerationOn = true;
                gameStart = true;
            }
        }
    }
    public static class SaveAccount implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttonSaveAccount.getBackground().equals(COLOR_INTERFACE_GREEN)) {
                String serverReturn = save();
                if (serverReturn.equals("Аккаунт успешно сохранен")) {
                    buttonSaveAccount.setBackground(COLOR_INTERFACE_ACTIVATE_GREEN);
                    labelSaveAccount.setText("Аккаунт успешно сохранен");
                } else {
                    labelSaveAccount.setText(serverReturn);
                }
                visTrue(labelSaveAccount);
                new GameThreads.LabelSaveAccountThread().start();
            }
        }
    }

    public static class Exit implements ActionListener { //Обработка нажатия кнопки buttonExit.
        @Override //Переопределение метода actionPerformed(ActionEvent actionEvent).
        public void actionPerformed(ActionEvent actionEvent) {
            System.exit(0); //Завершение игры.
        } //Конец переопредлённого метода actionPerformed().
    } //Конец внутреннего класса Exit.

    public static class ExitAccount implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            accountCurrent = null;
            registered = false;
            gameStart = false;
            labelAccount.setText("Вы не авторизованы");
            menuStartNotRegistered();
        }
    }

    public static class Settings implements ActionListener { //Обработка нажатия кнопки buttonSettings.
        @Override //Переопределение метода actionPerformed(ActionEvent actionEvent).
        public void actionPerformed(ActionEvent actionEvent) {
            if (buttonSettings.getBackground().equals(COLOR_INTERFACE_GREEN)) {
                buttonSettings.setBackground(COLOR_INTERFACE_ACTIVATE_GREEN);
                menuStartEnableSettings(true);
            } else if (buttonSettings.getBackground().equals(COLOR_INTERFACE_ACTIVATE_GREEN)){
                buttonSettings.setBackground(COLOR_INTERFACE_GREEN);
                menuStartEnableSettings(false);
            }
        } //Конец переопредлённого метода actionPerformed().
    }

    public static class SettingsServer implements ActionListener {
        @Override //Переопределение метода actionPerformed(ActionEvent actionEvent).
        public void actionPerformed(ActionEvent actionEvent) {

        }
    }

    public static class NewAccount implements ActionListener { //Обработка нажатия кнопки buttonRegistration.
        @Override //Переопределение метода actionPerformed(ActionEvent actionEvent).
        public void actionPerformed(ActionEvent actionEvent) {
            textNick.setText("");
            textPassword.setText("");
            visFalse(labelWarning);
            visFalse(buttonNewAccountFromLoadAccount);
            buttonEndRegistration.setText("Зарегестрироваться");
            buttonEndRegistration.setFont(f20);
            if (buttonRegistration.getBackground().equals(COLOR_INTERFACE_GREEN)) {
                buttonRegistration.setBackground(COLOR_INTERFACE_ACTIVATE_GREEN);
                menuStartEnableRegistration(true);
                accountRegistration = true;
                visTrue(buttonLoadAccountFromNewAccount);
                if (buttonEntry.getBackground().equals(COLOR_INTERFACE_ACTIVATE_GREEN))
                    buttonEntry.setBackground(COLOR_INTERFACE_GREEN);
            } else if (buttonRegistration.getBackground().equals(COLOR_INTERFACE_ACTIVATE_GREEN)){
                buttonRegistration.setBackground(COLOR_INTERFACE_GREEN);
                menuStartEnableRegistration(false);
                accountRegistration = false;
                visFalse(buttonLoadAccountFromNewAccount);
            }
        } //Конец переопредлённого метода actionPerformed().
    } //Конец внутреннего класса NewAccount.

    public static class LoadAccount implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            textNick.setText("");
            textPassword.setText("");
            visFalse(labelWarning);
            visFalse(buttonLoadAccountFromNewAccount);
            buttonEndRegistration.setText("Войти");
            buttonEndRegistration.setFont(f30);
            if (buttonEntry.getBackground().equals(COLOR_INTERFACE_GREEN)) {
                buttonEntry.setBackground(COLOR_INTERFACE_ACTIVATE_GREEN);
                menuStartEnableRegistration(true);
                visTrue(buttonNewAccountFromLoadAccount);
                accountRegistration = false;
                if (buttonRegistration.getBackground().equals(COLOR_INTERFACE_ACTIVATE_GREEN))
                    buttonRegistration.setBackground(COLOR_INTERFACE_GREEN);
            } else if (buttonEntry.getBackground().equals(COLOR_INTERFACE_ACTIVATE_GREEN)){
                buttonEntry.setBackground(COLOR_INTERFACE_GREEN);
                menuStartEnableRegistration(false);
                visFalse(buttonNewAccountFromLoadAccount);
            }
        }
    }

    public static class EndOfRegistrationAccount implements ActionListener { //Обработка нажатия кнопки buttonEndRegistration.
        @Override //Переопределение метода actionPerformed(ActionEvent actionEvent).
        public void actionPerformed(ActionEvent actionEvent) {
            boolean isNormal = true;
            //Если все поля регистрации чем-то заполнены то это условие срабатывает.
            visFalse(labelWarningNeedRegistration);
            if (!textNick.getText().equals("") && !textPassword.getText().equals("")) {
                if (accountRegistration) {
                    if (textNick.getText().split("").length > 20 || textPassword.getText().split("").length > 20) {
                        labelWarning.setFont(f17);
                        labelWarning.setText("Логин или пароль слишком длинные!");
                        visFalse(labelWarning);
                        visTrue(labelWarning);
                        isNormal = false;
                    } else {
                        for (String sym : listOfIncorrectSymbols) {
                            for (String letter : textNick.getText().split("")) {
                                if (sym.equals(letter)) {
                                    labelWarning.setFont(f17);
                                    labelWarning.setText("Нельзя включать в свой ник символ \"" + sym + "\"");
                                    visFalse(labelWarning);
                                    visTrue(labelWarning);
                                    isNormal = false;
                                }
                            }
                            for (String letter : textPassword.getText().split("")) {
                                if (sym.equals(letter)) {
                                    labelWarning.setFont(f17);
                                    labelWarning.setText("Нельзя включать в свой пароль символ \"" + sym + "\"");
                                    visFalse(labelWarning);
                                    visTrue(labelWarning);
                                    isNormal = false;
                                }
                            }
                        }
                    }
                    if (isNormal) {
                        sendToServer(serverHost, serverPort, version+"⊠reg⊠"+textNick.getText()+"⊠"+textPassword.getText());
                        String serverAnswer = (String) Objects.requireNonNull(getFromServer(serverHost, serverPort, "reg"));
                        switch (serverAnswer) {
                            case "already exist":
                                labelWarning.setFont(f17);
                                labelWarning.setText("Аккаунт с таким логином уже существует!");
                                visFalse(labelWarning);
                                visTrue(labelWarning);
                                break;
                            case "error":
                                labelWarning.setFont(f17);
                                labelWarning.setText("При создании аккаунта произошли сбои!");
                                visFalse(labelWarning);
                                visTrue(labelWarning);
                                break;
                            case "success":
                                sendToServer(serverHost, serverPort, "");
                                accountCurrent = (Account) getFromServer(serverHost, serverPort, "reg");
                                registered = true;
                                buttonRegistration.setBackground(COLOR_INTERFACE_GREEN);
                                buttonEntry.setBackground(COLOR_INTERFACE_GREEN);
                                menuStartRegistered();
                                break;
                            case "incorrect version":
                                labelWarning.setFont(f20);
                                labelWarning.setText("Текущая версия игры: "+version+". Требуемая версия: "+getFromServer(serverHost, serverPort, null)+".");
                                visFalse(labelWarning);
                                visTrue(labelWarning);
                                break;
                            default:
                                labelWarning.setFont(f17);
                                labelWarning.setText("Сервер не отвечает!");
                                visFalse(labelWarning);
                                visTrue(labelWarning);
                                break;
                        }
                    }
                } else {
                    sendToServer(serverHost, serverPort, version+"⊠login⊠"+textNick.getText()+"⊠"+textPassword.getText());
                    switch ((String) Objects.requireNonNull(getFromServer(serverHost, serverPort, "login"))) {
                        case "not exist":
                            labelWarning.setFont(f20);
                            labelWarning.setText("Данные введены неверно!");
                            visFalse(labelWarning);
                            visTrue(labelWarning);
                            break;
                        case "success":
                            sendToServer(serverHost, serverPort, "");
                            accountCurrent = (Account) getFromServer(serverHost, serverPort, "login");
                            if (accountCurrent == null) {
                                labelWarning.setFont(f20);
                                labelWarning.setText("При входе произошли сбои!");
                                visFalse(labelWarning);
                                visTrue(labelWarning);
                            } else {
                                accountCurrent.recovery();
                                if (accountCurrent.listOfWorlds.size() > 0) {
                                    worldCurrent = accountCurrent.listOfWorlds.get(0);
                                    System.out.println(worldCurrent);
                                    playerCurrent = worldCurrent.listOfPlayers.get(0);
                                }
                                registered = true;
                                buttonRegistration.setBackground(COLOR_INTERFACE_GREEN);
                                buttonEntry.setBackground(COLOR_INTERFACE_GREEN);
                                menuStartRegistered();
                            }
                            break;
                        case "incorrect version":
                            labelWarning.setFont(f20);
                            labelWarning.setText("Текущая версия игры: "+version+". Требуемая версия: "+getFromServer(serverHost, serverPort, null)+".");
                            visFalse(labelWarning);
                            visTrue(labelWarning);
                            break;
                        default:
                            labelWarning.setFont(f20);
                            labelWarning.setText("Сервер не отвечает!");
                            visFalse(labelWarning);
                            visTrue(labelWarning);
                            break;
                    }
                }
            } else if (textNick.getText().equals("") && textPassword.getText().equals("")) {
                labelWarning.setFont(f20);
                labelWarning.setText("Введите логин и пароль!");
                visFalse(labelWarning);
                visTrue(labelWarning);
            } else if (textNick.getText().equals("")) {
                labelWarning.setFont(f20);
                labelWarning.setText("Введите логин!");
                visFalse(labelWarning);
                visTrue(labelWarning);
            } else if (textPassword.getText().equals("")) {
                labelWarning.setFont(f20);
                labelWarning.setText("Введите пароль!");
                visFalse(labelWarning);
                visTrue(labelWarning);
            }
        } //Конец переопредлённого метода actionPerformed().
    } //Конец внутреннего класса EndOfRegistrationAccount.


    public static class InventoryLeft implements ActionListener {
        @Override //Переопределение метода actionPerformed(ActionEvent actionEvent).
        public void actionPerformed(ActionEvent actionEvent) {
            textQuests.requestFocus();
            if (buttonRight.isVisible()) {
                visFalse(buttonLeft);
//                labelSlots.setText();
            } else {
                visTrue(buttonRight);
            }

        } //Конец переопредлённого метода actionPerformed().
    } //Конец внутреннего класса InventoryLeft.

    public static class InventoryRight implements ActionListener {
        @Override //Переопределение метода actionPerformed(ActionEvent actionEvent).
        public void actionPerformed(ActionEvent actionEvent) {
            textQuests.requestFocus();
            if (buttonLeft.isVisible()) {
                visFalse(buttonRight);
            } else {
                visTrue(buttonLeft);
            }

        } //Конец переопредлённого метода actionPerformed().
    } //Конец внутреннего класса InventoryRight.

    public static class InventorySlotsPants implements ActionListener {
        @Override //Переопределение метода actionPerformed(ActionEvent actionEvent).
        public void actionPerformed(ActionEvent actionEvent) {
            textQuests.requestFocus();
        } //Конец переопредлённого метода actionPerformed().
    } //Конец внутреннего класса InventorySlotsPants.
    public static class InventorySlotsShirt implements ActionListener {
        @Override //Переопределение метода actionPerformed(ActionEvent actionEvent).
        public void actionPerformed(ActionEvent actionEvent) {
            textQuests.requestFocus();
        } //Конец переопредлённого метода actionPerformed().
    } //Конец внутреннего класса InventorySlotsShirt.
    public static class InventorySlotsHands implements ActionListener {
        @Override //Переопределение метода actionPerformed(ActionEvent actionEvent).
        public void actionPerformed(ActionEvent actionEvent) {
            textQuests.requestFocus();
        } //Конец переопредлённого метода actionPerformed().
    } //Конец внутреннего класса InventorySlotsHands.

    //Событие нажатий клавиатуры
    public static class MainFrameKeyListener extends KeyAdapter implements KeyListener {
        @Override //Переопределение методов keyPressed(KeyEvent button), keyReleased(KeyEvent e) и keyTyped(KeyEvent e).
        public void keyPressed(KeyEvent button) {
            switch (button.getKeyChar()) { //Сравнивание нажатой клавиши с её возможными значениями.
                case '0':
                    if (!inventoryOpen) {
                        if (hitBoxModeOn) {
                            hitBoxModeOn = false;
                        } else {
                            hitBoxModeOn = true;
                            Color.RGBtoHSB(COLOR_GAME_PLAYER_SHADOW.getRed(), COLOR_GAME_PLAYER_SHADOW.getGreen(), COLOR_GAME_PLAYER_SHADOW.getBlue(), null);
                        }
                    }
                    break;
                case '1':
                    if (!inventoryOpen) {
                        if (!_1) {
                            _1 = true;
                        }
                    }
                    break;
                case '2':
                    if (!inventoryOpen) {
                        if (!_2) {
                            _2 = true;
                        }
                    }
                    break;
                case '3':
                    if (!inventoryOpen) {
                        if (!_3) {
                            _3 = true;
                        }
                    }
                    break;
                case '4':
                    if (!inventoryOpen) {
                        if (!_4) {
                            _4 = true;
                        }
                    }
                    break;
                case 'w':
                case 'ц':
                    if (!inventoryOpen) {
                        if (!w && !W) {
                            w = true;
                            if (!a && !A && !d && !D && !s && !S) {
                                iconPlayerCurrent = ANIMATION_PLAYER_BACK;
                            }
                        }
                    }
                    break;

                case 'W':
                case 'Ц':
                    if (!inventoryOpen) {
                        if (!w && !W) {
                            W = true;
                            if (!a && !A && !d && !D && !s && !S) {
                                iconPlayerCurrent = ANIMATION_PLAYER_BACK;
                            }
                        }
                    }
                    break;

                case 'a':
                case 'ф':
                    if (!inventoryOpen) {
                        if (!a && !A) {
                            a = true;
                            if (!d && !D) {
                                iconPlayerCurrent = ANIMATION_PLAYER_LEFT;
                            }
                        }
                    }
                    break;
                case 'A':
                case 'Ф':
                    if (!inventoryOpen) {
                        if (!a && !A) {
                            A = true;
                            if (!d && !D) {
                                iconPlayerCurrent = ANIMATION_PLAYER_LEFT;
                            }
                        }
                    }
                    break;

                case 's':
                case 'ы':
                    if (!inventoryOpen) {
                        if (!s && !S) {
                            s = true;
                            if (!a && !A && !d && !D && !w && !W) {
                                iconPlayerCurrent = ANIMATION_PLAYER_FRONT;
                            }
                        }
                    }
                    break;
                case 'S':
                case 'Ы':
                    if (!inventoryOpen) {
                        if (!s && !S) {
                            S = true;
                            if (!a && !A && !d && !D && !w && !W) {
                                iconPlayerCurrent = ANIMATION_PLAYER_FRONT;
                            }
                        }
                    }
                    break;

                case 'd':
                case 'в':
                    if (!inventoryOpen) {
                        if (!d && !D) {
                            d = true;
                            if (!a && !A) {
                                iconPlayerCurrent = ANIMATION_PLAYER_RIGHT;
                            }
                        }
                    }
                    break;
                case 'D':
                case 'В':
                    if (!inventoryOpen) {
                        if (!d && !D) {
                            D = true;
                            if (!a && !A) {
                                iconPlayerCurrent = ANIMATION_PLAYER_RIGHT;
                            }
                        }
                    }
                    break;

                case 0x1B: //Escape
                    if (!inventoryOpen) {
                        _1 = false;
                        _2 = false;
                        _3 = false;
                        _4 = false;
                        w = false;
                        W = false;
                        a = false;
                        A = false;
                        s = false;
                        S = false;
                        d = false;
                        D = false;
                        gameStart = false;
                        runnableOn = false;

                        repaintPhase = REPAINT_MENU;
                        if (registered) {
                            menuStartRegistered();
                        } else {
                            menuStartNotRegistered();
                        }
                        frame.repaint();
                    }
                    break;

                case 'e':
                case 'E':
                case 'у':
                case 'У':
                    _1 = false;
                    _2 = false;
                    _3 = false;
                    _4 = false;
                    w = false;
                    W = false;
                    a = false;
                    A = false;
                    s = false;
                    S = false;
                    d = false;
                    D = false;

                    if (!inventoryOpen) {
                        System.out.println("\nУведомления. Ваши слоты:");
                        System.out.println("Древесина: " + playerCurrent.amountOfAllGettingCardWoods);
                        System.out.println("Камни: " + playerCurrent.amountOfAllGettingCardStones);
                        System.out.println("Маленькие камни: " + playerCurrent.amountOfAllGettingCardSmallStones);
                        System.out.println("Золото: " + playerCurrent.amountOfAllGettingCardGolds);
                        System.out.println("Алмаз: " + playerCurrent.amountOfAllGettingCardDiamonds);

                        inventoryOpen = true;

                        for (Card card : playerCurrent.slots) {
                            if (card.name.equals(Card.NAME_POVERTY_PANTS) && card.isWear) {
                                buttonPantsSlots.setText(card.name);
                                buttonPantsClothes.setText(card.name);
                            }
                        }
                        for (Card card : playerCurrent.slots) {
                            if (card.name.equals(Card.NAME_POVERTY_SHIRT) && card.isWear) {
                                buttonShirtSlots.setText(card.name);
                                buttonShirtClothes.setText(card.name);
                            }
                        }

                        visFalse(labelNick);

                        visTrue(labelSlots);
                        visTrue(buttonRight);
                        visTrue(buttonLeft);
                        visTrue(buttonPantsSlots);
                        visTrue(buttonShirtSlots);
                        visTrue(buttonHandsSlots);

                        frame.repaint();
                    } else {
                        visTrue(labelNick);

                        visFalse(labelSlots);
                        visFalse(buttonRight);
                        visFalse(buttonLeft);
                        visFalse(buttonPantsSlots);
                        visFalse(buttonShirtSlots);
                        visFalse(buttonHandsSlots);

                        System.out.println("Уведомление. Выход из инвенторя.");
                        inventoryOpen = false;
                    }
                    break;
                case 't':
                case 'T':
                case 'е':
                case 'Е':
                    if (!inventoryOpen) {
                        double x = (int) ((Math.random() * 10) + 0.5) * 100 - 500; //450, 350, 250, 150, 50, -50, -150, -250, -350, -450
                        double y = (int) ((Math.random() * 10) + 0.5) * 100 - 500; //450, 350, 250, 150, 50, -50, -150, -250, -350, -450
                        playerCurrent.xOfPlayer -= x;
                        playerCurrent.yOfPlayer -= y;
                        setNotification("Использован телепорт радиусом 20 полей");
                    }
                    break;
                case 'k':
                case 'K':
                case 'л':
                case 'Л':
                    if (!inventoryOpen) {
                        deathAndRespawn();
                    }
                    break;
                case 'p':
                case 'P':
                case 'з':
                case 'З':
                    if (fastModeOn) {
                        setNotification("Высокая скорость выключена");
                        playerCurrent.walkSpeed = 1;
                        playerCurrent.runSpeed = 2;
                        fastModeOn = false;
                    } else {
                        setNotification("Высокая скорость включена");
                        playerCurrent.walkSpeed = 10;
                        playerCurrent.runSpeed = 20;
                        fastModeOn = true;
                    }
                    break;
                case '[':
                case '{':
                case 'х':
                case 'Х':
                    if (godModeOn) {
                        setNotification("Бессмертие выключено");
                        godModeOn = false;
                        if (collisionWithBarrier(playerXFrame, playerYFrame)) {
                            deathAndRespawn();
                        }
                    } else {
                        setNotification("Бесмертие включено");
                        godModeOn = true;
                    }
                    break;
                default:
                    System.out.println(button.getKeyChar() + " " + button.getKeyCode());
                    break;
            }
        }
        public void keyReleased(KeyEvent button) {
            switch (button.getKeyChar()) {
                case 'w':
                case 'ц':
                    if (!inventoryOpen) {
                        w = false;
                        if (s || S) {
                            iconPlayerCurrent = ANIMATION_PLAYER_FRONT;
                        } else if (!a && !A && !d && !D) {
                            iconPlayerCurrent = ICON_PLAYER_BACK;
                        }
                    }
                    break;
                case 'W':
                case 'Ц':
                    if (!inventoryOpen) {
                        W = false;
                        if (s || S) {
                            iconPlayerCurrent = ANIMATION_PLAYER_FRONT;
                        } else if (!a && !A && !d && !D) {
                            iconPlayerCurrent = ICON_PLAYER_BACK;
                        }
                    }
                    break;
                case 'a':
                case 'ф':
                    if (!inventoryOpen) {
                        a = false;
                        if (s || S) {
                            iconPlayerCurrent = ANIMATION_PLAYER_FRONT;
                        } else if (w || W) {
                            iconPlayerCurrent = ANIMATION_PLAYER_BACK;
                        } else if (d || D) {
                            iconPlayerCurrent = ANIMATION_PLAYER_RIGHT;
                        } else {
                            iconPlayerCurrent = ICON_PLAYER_LEFT;
                        }
                    }
                    break;
                case 'A':
                case 'Ф':
                    if (!inventoryOpen) {
                        A = false;
                        if (s || S) {
                            iconPlayerCurrent = ANIMATION_PLAYER_FRONT;
                        } else if (w || W) {
                            iconPlayerCurrent = ANIMATION_PLAYER_BACK;
                        } else if (d || D) {
                            iconPlayerCurrent = ANIMATION_PLAYER_RIGHT;
                        } else {
                            iconPlayerCurrent = ICON_PLAYER_LEFT;
                        }
                    }
                    break;
                case 's':
                case 'ы':
                    if (!inventoryOpen) {
                        s = false;
                        if (w || W) {
                            iconPlayerCurrent = ANIMATION_PLAYER_BACK;
                        } else if (!a && !A && !d && !D) {
                            iconPlayerCurrent = ICON_PLAYER_FRONT;
                        }
                    }
                    break;
                case 'S':
                case 'Ы':
                    if (!inventoryOpen) {
                        S = false;
                        if (w || W) {
                            iconPlayerCurrent = ANIMATION_PLAYER_BACK;
                        } else if (!a && !A && !d && !D) {
                            iconPlayerCurrent = ICON_PLAYER_FRONT;
                        }
                    }
                    break;
                case 'd':
                case 'в':
                    if (!inventoryOpen) {
                        d = false;
                        if (s || S) {
                            iconPlayerCurrent = ANIMATION_PLAYER_FRONT;
                        } else if (w || W) {
                            iconPlayerCurrent = ANIMATION_PLAYER_BACK;
                        } else if (a || A) {
                            iconPlayerCurrent = ANIMATION_PLAYER_LEFT;
                        } else {
                            iconPlayerCurrent = ICON_PLAYER_RIGHT;
                        }
                    }
                    break;
                case 'D':
                case 'В':
                    if (!inventoryOpen) {
                        D = false;
                        if (s || S) {
                            iconPlayerCurrent = ANIMATION_PLAYER_FRONT;
                        } else if (w || W) {
                            iconPlayerCurrent = ANIMATION_PLAYER_BACK;
                        } else if (a || A) {
                            iconPlayerCurrent = ANIMATION_PLAYER_LEFT;
                        } else {
                            iconPlayerCurrent = ICON_PLAYER_RIGHT;
                        }
                    }
                    break;
                case 0x31: //1
                    if (!inventoryOpen) {
                        _1 = false;
                    }
                    break;
                case 0x32: //2
                    if (!inventoryOpen) {
                        _2 = false;
                    }
                    break;
                case 0x33: //3
                    if (!inventoryOpen) {
                        _3 = false;
                    }
                    break;
                case 0x34: //4
                    if (!inventoryOpen) {
                        _4 = false;
                    }
                    break;
            }
        }

        public void keyTyped(KeyEvent button) {

        }
    }
    public static class MainFrameMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class MainFrameMouseMotionListener implements MouseMotionListener {
        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }
}
