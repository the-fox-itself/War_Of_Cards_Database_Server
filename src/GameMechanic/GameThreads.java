package GameMechanic;

import GameMechanic.Objects.Cards.Card;
import GameMechanic.Objects.Essences.EssenceWolf;
import GameMechanic.Objects.GameObjects.GameObject;
import GameMechanic.Objects.TechnicalObjects.Quest;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

import static GameMechanic.GameMechanic.*;
import static GameMechanic.GameMainVariables.*;
import static Libraries.Methods.*;
import static Libraries.Methods.visTrue;

public abstract class GameThreads {
    public static class GameLoop extends Thread {
        public void run() {
            double previous = new Date().getTime();
            double steps = 0;
            while (true) {
                if (runnableOn) {
                    double loopStartTime = new Date().getTime();
                    double elapsed = loopStartTime - previous;
                    previous = new Date().getTime();
                    steps += elapsed;

                    handleInput();
                    if (playerCurrent != null) {
                        System.out.println(playerCurrent.xOfPlayer + " " + playerCurrent.yOfPlayer + "; " + (int) (playerCurrent.xOfPlayer / 10) * 10 + " " + (int) (playerCurrent.yOfPlayer / 10) * 10);
                    }

                    while (steps >= millisecondsPerUpdate) {
                        updateGameStats();
                        steps -= millisecondsPerUpdate;
                    }

                    frame.repaint();

                    double loopSlot = 10;
                    double endTime = loopStartTime + loopSlot;
                    while (new Date().getTime() < endTime) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException ignored) {}
                    }
                } else {
                    previous = new Date().getTime();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void handleInput() {
            if (gameStart) {
                if (_1 && timerMainRunnableActions % TIME_RUNNABLE_DIVIDER_RESOURCE_MINING == 0) {
                    resourceMining(GameObject.NAME_SMALL_STONE);
                }
                if (_2 && timerMainRunnableActions % TIME_RUNNABLE_DIVIDER_RESOURCE_MINING == 0) {
                    resourceMining(GameObject.NAME_WOOD);
                    resourceMining(GameObject.NAME_STONE);
                }
                if (_3 && timerMainRunnableActions % TIME_RUNNABLE_DIVIDER_RESOURCE_MINING == 0) {
                    resourceMining(GameObject.NAME_GOLD);
                    resourceMining(GameObject.NAME_DIAMOND);
                }

                if (w && !s && !S) {
                    playerMove('y', playerCurrent.walkSpeed);
                } else if (W && !S && !s) {
                    playerMove('y', playerCurrent.runSpeed);
                }
                if (s && !w && !W) {
                    playerMove('y', -playerCurrent.walkSpeed);
                } else if (S && !W && !w) {
                    playerMove('y', -playerCurrent.runSpeed);
                }
                if (d && !a && !A) {
                    playerMove('x', -playerCurrent.walkSpeed);
                } else if (D && !A && !a) {
                    playerMove('x', -playerCurrent.runSpeed);
                }
                if (a && !d && !D) {
                    playerMove('x', playerCurrent.walkSpeed);
                } else if (A && !D && !d) {
                    playerMove('x', playerCurrent.runSpeed);
                }
            }
        }
        public void updateGameStats() {
            if (gameStart) {
                searchForNearbyGameObjects();

                if (regenerationOn) {
                    if (timerPlayerRegeneration > 0) {
                        if (timerPlayerRegeneration == TIME_PLAYER_REGENERATION_GO && playerCurrent.health != playerCurrent.maxHealth) {
                            if (!godModeOn) {
                                playerCurrent.health++;
                            }
                            if (playerCurrent.health != playerCurrent.maxHealth) {
                                timerPlayerRegeneration = TIME_PLAYER_REGENERATION_AFTER_REGENERATION;
                            } else {
                                timerPlayerRegeneration = 0;
                            }
                        } else {
                            timerPlayerRegeneration--;
                        }
                    }
                }
            }
        }
    }

    public static class WolfThread extends Thread {
        EssenceWolf wolfCurrent;

        public WolfThread(EssenceWolf wolf) {
            this.wolfCurrent = wolf;
        }

        @Override
        public void run() {
            while (true) {
                if (gameStart) {
                    if (!collisionWithBarrier(wolfCurrent.xOnFrame, wolfCurrent.yOnFrame)) { //Если волк не наступил на барьер.
                        boolean isX = false; //Создаём булеву переменную для проверки близости игрока к волку по оси x.
                        boolean isY = false; //Создаём булеву переменную для проверки близости игрока к волку по оси y.
                        if (wolfCurrent.xOnFrame - playerXFrame < 300 && wolfCurrent.xOnFrame - playerXFrame > -300) { //Если игрок относительно волка находится не дальше 300 пикселей по оси x.
                            isX = true; //То даем переменной isX значение true, чтобы показать, что игрок близок к волку по оси x.
                        } //Конец if.
                        if (wolfCurrent.yOnFrame - playerYFrame < 300 && wolfCurrent.yOnFrame - playerYFrame > -300) { //Если игрок относительно волка находится не дальше 300 пикселей по оси y.
                            isY = true; //То даем переменной isY значение true, чтобы показать, что игрок близок к волку по оси y.
                        } //Конец if.
                        if (isX && isY) { //Если игрок близок к волку и по оси x, и по оси y.
                            if ((playerYFrame - wolfCurrent.yOnFrame < 5 && playerYFrame - wolfCurrent.yOnFrame > -5)
                                    && (playerXFrame - wolfCurrent.xOnFrame < 5 && playerXFrame - wolfCurrent.xOnFrame > -5)) { //Если волк очень близко к игроку.
                                playerXFrame -= 10;
                                try { //try-catch для паузы.
                                    Thread.sleep(2); //Пауза в размере 2 милисекунд.
                                } catch (InterruptedException e) {
                                    e.printStackTrace(); //Обработка ошибки.
                                }
                                playerXFrame += 20;
                                try { //try-catch для паузы.
                                    Thread.sleep(2); //Пауза в размере 2 милисекунд.
                                } catch (InterruptedException e) {
                                    e.printStackTrace(); //Обработка ошибки.
                                }
                                playerXFrame -= 10;
                                if (timerPlayerTakeDamage == 0) { //Если переменная-таймер timerPlayerTakeDamage равна 0.
                                    if (!godModeOn) {
                                        playerCurrent.health--; //У игрока отнимается одна жизнь.
                                    }
                                    timerPlayerRegeneration = TIME_PLAYER_REGENERATION_AFTER_TAKE_DAMAGE;
                                    frame.repaint();
                                } //Конец if.
                                if (playerCurrent.health == 0) { //Если у игрока 0 жизней.
                                    if (!godModeOn) {
                                        deathAndRespawn(); //Он погибает и респавнится.
                                    }
                                } else { //Если у игрока не 0 жизней.
                                    if (timerPlayerTakeDamage == 0) { //И если переменная-таймер timerPlayerTakeDamage равна 0.
                                        timerPlayerTakeDamage = TIME_PLAYER_TAKE_DAMAGE; //Ей придаётся значение переменной TIME_PLAYER_TAKE_DAMAGE.
                                    } else { //Если переменная-таймер timerPlayerTakeDamage не равна 0.
                                        timerPlayerTakeDamage--; //Она уменьшается на единицу.
                                    } //Конец else.
                                } //Конец else.
                            } else { //Если же волк не достаточно близок к игроку, чтобы укусить его.
                                if (playerXFrame - wolfCurrent.xOnFrame < -3) { //Если игрок находится левее волка.
                                    wolfCurrent.xOnFrame -= 2 * wolfCurrent.essenceSpeed; //То волк двигается влево, по направлению к игроку.
                                    wolfCurrent.icon = ICON_WOLF_LEFT;
                                } else if (playerXFrame - wolfCurrent.xOnFrame > 3) { //Если игрок находится правее волка.
                                    wolfCurrent.xOnFrame += 2 * wolfCurrent.essenceSpeed; //То волк двигается вправо, по направлению к игроку.
                                    wolfCurrent.icon = ICON_WOLF_RIGHT;
                                }
                                if (playerYFrame - wolfCurrent.yOnFrame < -3) { //Если игрок находится выше волка.
                                    wolfCurrent.yOnFrame -= 2 * wolfCurrent.essenceSpeed; //То волк двигается вверх, по направлению к игроку.
                                } else if (playerYFrame - wolfCurrent.yOnFrame > 3) { //Если игрок находится ниже волка.
                                    wolfCurrent.yOnFrame += 2 * wolfCurrent.essenceSpeed; //То волк двигается вниз, по направлению к игроку.
                                }
                            }
                        } else if (wolfCurrent.timePassed == 0) { //Если же игрок не достаточно близок к волку, чтобы волк его заметил и при этом настало время для следующего шага.
                            int rand = (int) (Math.random() * 4); //То вычисляем случайное значение для переменной rand в пределах от 0 до 3 включительно.
                            switch (rand) { //Исли переменной rand дали значение...
                                case 0: //...0
                                    wolfCurrent.xOnFrame -= 2 * wolfCurrent.essenceSpeed; //Передвижение волка влево на определённое количество пикселей, в зависимости от скорости волка.
                                    wolfCurrent.icon = ICON_WOLF_LEFT;
                                    break; //Конец кейса.
                                case 1: //...1
                                    wolfCurrent.xOnFrame += 2 * wolfCurrent.essenceSpeed; //Передвижение волка вправо на определённое количество пикселей, в зависимости от скорости волка.
                                    wolfCurrent.icon = ICON_WOLF_RIGHT;
                                    break; //Конец кейса.
                                case 2: //...2
                                    wolfCurrent.yOnFrame -= 2 * wolfCurrent.essenceSpeed; //Передвижение волка вверх на определённое количество пикселей, в зависимости от скорости волка.
                                    break; //Конец кейса.
                                case 3: //...3
                                    wolfCurrent.yOnFrame += 2 * wolfCurrent.essenceSpeed; //Передвижение волка вниз на определённое количество пикселей, в зависимости от скорости волка.
                                    break; //Конец кейса.
                            }
                            wolfCurrent.timePassed = wolfCurrent.timeOfNextWalk; //Заводим новое время для того, чтобы волк не бегал, как сумасшедшиц.
                        } else { //Если же время не настало и запущен таймер.
                            wolfCurrent.timePassed--; //То уменьшаем его.
                        } //Конец else.
                    } else { //Если же волк наступил на барьер.
                        System.out.println(wolfCurrent + " has died."); //Вывод системного уведомления.
                        worldCurrent.listOfEssences.remove(wolfCurrent); //Удаление волка из списка существ.
                        System.out.println("Уведомление. Волк не знал, что в игре есть барьеры и умер от одного из них."); //Вывод пользовательского уведомления.
                        break; //Выход
                    }
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static class NotificationThread extends Thread {
        String notification;

        public NotificationThread(String notification) {
            this.notification = notification;
        }

        @Override
        public void run() {
            JLabel labelNotification = getLabelNullLayout(notification, f25, frame, 1150, frame.getHeight()-120, 1000, 50, COLOR_INTERFACE_ORANGE) ;
            visTrue(labelNotification);
            int sleep = 60;
            while (labelNotification.getY() < frame.getHeight()+30 && sleep > 0) {
                labelNotification.setBounds(labelNotification.getX(), labelNotification.getY()+1, labelNotification.getWidth(), labelNotification.getHeight());
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (sleep < 50 && labelNotification.getForeground().getAlpha() > 10) {
                    labelNotification.setForeground(new Color(labelNotification.getForeground().getRed(), labelNotification.getForeground().getGreen(), labelNotification.getForeground().getBlue(), labelNotification.getForeground().getAlpha()-10));
                }
                if (labelNotification.getY()%3 == 0) {
                    sleep -= 1;
                }
                if (!gameStart) {
                    break;
                }
            }
            visFalse(labelNotification);
        }
    }

    public static class LabelNeedRegistrationThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean noExceptions = true;
            needRegistrationOn = false;
            for (int time = 0; time < 3000; time++) {
                if (needRegistrationOn) {
                    noExceptions = false;
                    break;
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            if (noExceptions) {
                visFalse(labelWarningNeedRegistration);
            }
        }
    }

    public static class LabelSaveAccountThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            visFalse(labelSaveAccount);
            if (buttonSaveAccount.getBackground().equals(COLOR_INTERFACE_ACTIVATE_GREEN)) {
                buttonSaveAccount.setBackground(COLOR_INTERFACE_GREEN);
            }
        }
    }

    public static class QuestThread extends Thread {
        Quest quest;

        public QuestThread(Quest quest) {
            this.quest = quest;
        }

        @Override
        public void run() {
            int startNumber = 0;
            for (Card card : playerCurrent.slots) {
                if (card.name.equals(quest.searchCardName)) {
                    startNumber++;
                }
            }
            final int finalStartNumber = startNumber;
            while (true) {
                if (!quest.isCompleted) {
                    int newNumber = 0;
                    for (Card card : playerCurrent.slots) {
                        if (card.name.equals(quest.searchCardName)) {
                            newNumber++;
                        }
                    }
                    quest.reachedNumber = newNumber - finalStartNumber;
                    if (newNumber - finalStartNumber == quest.goalNumber) {
                        quest.isCompleted = true;
                    }
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            }
        }
    }
}
