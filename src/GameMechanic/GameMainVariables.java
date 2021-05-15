package GameMechanic;

import GameMechanic.Objects.TechnicalObjects.Account;
import GameMechanic.Objects.TechnicalObjects.Player;
import GameMechanic.Objects.TechnicalObjects.World;

import javax.swing.*;
import java.awt.*;

import static Libraries.Methods.*;

public abstract class GameMainVariables {
    GameMainVariables() {
        printNote("Creating an object of class GameMechanicMainVariables", NOTE_TYPE_DONE);
    }

    public final static String version = "v0.3server-test1";
    public static String serverHost = "192.168.2.107";
    public static int serverPort = 5000;

    public static double millisecondsPerUpdate = 1000d / 30;
    public static String[] listOfIncorrectSymbols = {"\\", "/", ".", "\"", "⊠"};

    public static GameThreads.GameLoop gameLoop = new GameThreads.GameLoop();

    public static Account accountCurrent;
    public static World worldCurrent;
    public static Player playerCurrent;

    public static boolean accountRegistration;
    public static boolean registered;
    public static boolean inventoryOpen;
    public static boolean hitBoxModeOn;
    public static boolean fastModeOn;
    public static boolean gameStart;
    public static boolean runnableOn;
    public static boolean resizableOn;
    public static boolean godModeOn;
    public static boolean regenerationOn;
    public static boolean needRegistrationOn;

    static int InventoryOpenPart;
    final static int INVENTORY_CLOTHES = 1;
    final static int INVENTORY_SLOTS = 2;
    final static int INVENTORY_CHARACTER = 3;

    public static boolean w;
    public static boolean a;
    public static boolean s;
    public static boolean d;
    public static boolean W;
    public static boolean A;
    public static boolean S;
    public static boolean D;
    public static boolean _1;
    public static boolean _2;
    public static boolean _3;
    public static boolean _4;

    public final static int REPAINT_MENU = 1;
    public final static int REPAINT_GAME = 2;
    public static int repaintPhase = REPAINT_MENU;

    final static int TIME_PLAYER_TAKE_DAMAGE = 8;
    static int timerPlayerTakeDamage;
    final static int TIME_PLAYER_REGENERATION_AFTER_TAKE_DAMAGE = 200;
    final static int TIME_PLAYER_REGENERATION_AFTER_REGENERATION = 40;
    final static int TIME_PLAYER_REGENERATION_GO = 1;
    static int timerPlayerRegeneration;
    public final static int TIME_DRAW_PANEL_REPAINT_GAME_GO = 5;
    public static int timerDrawPanelRepaintGame;
    final static int TIME_RUNNABLE_DIVIDER_WOLF_PROCESSING = 3;
    final static int TIME_RUNNABLE_DIVIDER_RESOURCE_MINING = 3;
    final static int TIME_RUNNABLE_DIVIDER_SEARCH_NEARBY_OBJECTS = 5;
    final static int TIME_RUNNABLE_END = LCM(TIME_RUNNABLE_DIVIDER_WOLF_PROCESSING, TIME_RUNNABLE_DIVIDER_RESOURCE_MINING, TIME_RUNNABLE_DIVIDER_SEARCH_NEARBY_OBJECTS);
    static int timerMainRunnableActions;

    final public static Color COLOR_OBJECT_BARRIER = new Color(0xFF0428);
    public final static Color COLOR_GAME_PLAYER_SHADOW = new Color(0x25D196);
    public final static Color COLOR_GAME_PLAYER_HEALTH = new Color(0xD80011);
    public final static Color COLOR_GAME_PLAYER_HEALTH_BACKGROUND = new Color(0x6E343634, true);
    public final static Color COLOR_GAME_INTERFACE_BACK = new Color(0x5F2F16);
    public final static Color COLOR_GAME_INTERFACE_FRONT = new Color(0xA06328);
    public final static Color COLOR_GAME_INVENTORY_BACKGROUND = new Color(0xB5E68F41, true);

    public final static Color COLOR_INTERFACE_YELLOW = new Color(0xF9FF7C);
    public final static Color COLOR_INTERFACE_GREEN = new Color(0xFFBBFF7F, true);
    public final static Color COLOR_INTERFACE_TURQUOISE = new Color(0x8F38FF8D, true);
    public final static Color COLOR_INTERFACE_ACTIVATE_GREEN = new Color(0xC85EE14B, true);
    public final static Color COLOR_INTERFACE_ORANGE = new Color(0xFFC500);

    public final static Image ANIMATION_PLAYER_FRONT = new ImageIcon("resources"+SEPARATOR+"images"+SEPARATOR+"ICON_PLAYER_FRONT.png").getImage();
    public final static Image ANIMATION_PLAYER_BACK = new ImageIcon("resources"+SEPARATOR+"images"+SEPARATOR+"ICON_PLAYER_BACK.png").getImage();
    public final static Image ANIMATION_PLAYER_LEFT = new ImageIcon("resources"+SEPARATOR+"images"+SEPARATOR+"ICON_PLAYER_LEFT.png").getImage();
    public final static Image ANIMATION_PLAYER_RIGHT = new ImageIcon("resources"+SEPARATOR+"images"+SEPARATOR+"ICON_PLAYER_RIGHT.png").getImage();
    public final static Image ICON_PLAYER_FRONT = new ImageIcon("resources"+SEPARATOR+"images"+SEPARATOR+"ICON_PLAYER_FRONT.png").getImage();
    public final static Image ICON_PLAYER_BACK = new ImageIcon("resources"+SEPARATOR+"images"+SEPARATOR+"ICON_PLAYER_BACK.png").getImage();
    public final static Image ICON_PLAYER_LEFT = new ImageIcon("resources"+SEPARATOR+"images"+SEPARATOR+"ICON_PLAYER_LEFT.png").getImage();
    public final static Image ICON_PLAYER_RIGHT = new ImageIcon("resources"+SEPARATOR+"images"+SEPARATOR+"ICON_PLAYER_RIGHT.png").getImage();
    public static Image iconPlayerCurrent = ICON_PLAYER_FRONT;

    public final static Image ICON_WOLF_RIGHT = new ImageIcon("resources"+SEPARATOR+"images"+SEPARATOR+"ICON_WOLF_RIGHT.png").getImage();
    public final static Image ICON_WOLF_LEFT = new ImageIcon("resources"+SEPARATOR+"images"+SEPARATOR+"ICON_WOLF_LEFT.png").getImage();
    public final static Image ICON_OBJECT_WOOD_1 = new ImageIcon("resources"+SEPARATOR+"images"+SEPARATOR+"ICON_OBJECT_WOOD_1.png").getImage();
    public final static Image ICON_OBJECT_WOOD_2 = new ImageIcon("resources"+SEPARATOR+"images"+SEPARATOR+"ICON_OBJECT_WOOD_2.png").getImage();
    final public static Image ICON_OBJECT_STONE = new ImageIcon("resources"+SEPARATOR+"images"+SEPARATOR+"ICON_OBJECT_STONE.png").getImage();
    final public static Image ICON_OBJECT_SMALL_STONE = new ImageIcon("resources"+SEPARATOR+"images"+SEPARATOR+"ICON_OBJECT_SMALL_STONE.png").getImage();
    final public static Image ICON_OBJECT_UNDERGROUND = new ImageIcon("resources"+SEPARATOR+"images"+SEPARATOR+"ICON_OBJECT_UNDERGROUND.png").getImage();
    final public static Image ICON_OBJECT_GOLD = new ImageIcon("resources"+SEPARATOR+"images"+SEPARATOR+"ICON_OBJECT_GOLD.png").getImage();
    final public static Image ICON_OBJECT_DIAMOND = new ImageIcon("resources"+SEPARATOR+"images"+SEPARATOR+"ICON_OBJECT_DIAMOND.png").getImage();
    final public static Image ICON_GROUND_GRASS = new ImageIcon("resources"+SEPARATOR+"images"+SEPARATOR+"ICON_GROUND_GRASS.png").getImage();
    final public static Image ICON_GROUND_WATER = new ImageIcon("resources"+SEPARATOR+"images"+SEPARATOR+"ICON_GROUND_WATER.png").getImage();
    final public static Image ICON_OBJECT_FRAME = new ImageIcon("resources"+SEPARATOR+"images"+SEPARATOR+"ICON_OBJECT_FRAME.png").getImage();

    final public static Image ICON_MENU_BACKGROUND = new ImageIcon("resources"+SEPARATOR+"images"+SEPARATOR+"ICON_MENU_BACKGROUND.png").getImage();
    final public static Image ICON_MENU_TITLE = new ImageIcon("resources"+SEPARATOR+"images"+SEPARATOR+"ICON_MENU_TITLE.png").getImage();
    final public static Image ICON_FRAME = new ImageIcon("resources"+SEPARATOR+"images"+SEPARATOR+"ICON_FRAME.png").getImage();

    public final static int FRAME_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public final static int FRAME_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    public static int playerXFrame = FRAME_WIDTH / 2;
    public static int playerYFrame = FRAME_HEIGHT / 2;

    public final static JFrame frame = getFrame("War Of Cards: " + version, ICON_FRAME, FRAME_WIDTH, FRAME_HEIGHT,
            null, null, resizableOn);

    public final static JButton buttonSinglePlayer = getButtonNullLayout("Одиночная игра", f40, frame,
            frame.getWidth()/2 - (frame.getWidth()/4+40)/2, frame.getHeight()/8*4-60,
            frame.getWidth()/4+40, 70, new GameGUIListeners.SinglePlayer(), COLOR_INTERFACE_GREEN, null);
    public final static JButton buttonMultiPlayer = getButtonNullLayout("Серверная игра", f40, frame,
            frame.getWidth()/2 - frame.getWidth()/4/2, frame.getHeight()/8*4+80,
            frame.getWidth()/4, 70, new GameGUIListeners.MultiPlayer(), COLOR_INTERFACE_GREEN, null);
    public final static JButton buttonRegistration = getButtonNullLayout("Регистрация", f20, frame,
            30, frame.getHeight()-100, 180, 40, new GameGUIListeners.NewAccount(), COLOR_INTERFACE_GREEN, null);
    public final static JButton buttonEntry = getButtonNullLayout("Вход", f20, frame,
            230, frame.getHeight()-100, 180, 40, new GameGUIListeners.LoadAccount(), COLOR_INTERFACE_GREEN, null);
    public final static JButton buttonLoadAccountFromNewAccount = getButtonNullLayout("Уже есть аккаунт?", f20, frame,
            90, 625, 240, 35, new GameGUIListeners.LoadAccount(), COLOR_INTERFACE_GREEN, null);
    public final static JButton buttonNewAccountFromLoadAccount = getButtonNullLayout("Ещё нет аккаунта?", f20, frame,
            90, 625, 240, 35, new GameGUIListeners.NewAccount(), COLOR_INTERFACE_GREEN, null);
    public final static JButton buttonNewWorld = getButtonNullLayout("Создать новый мир", f40, frame,
            frame.getWidth()/2-500/2, frame.getHeight()/7*4, 500, 60,
            new GameGUIListeners.NewWorld(), COLOR_INTERFACE_GREEN, null);
    public final static JButton buttonLoadWorld = getButtonNullLayout("Загрузить", f50, frame,
            frame.getWidth()/2-400/2, 400, 400, 70, new GameGUIListeners.LoadWorld(), COLOR_INTERFACE_GREEN, null);
    public final static JButton buttonExit = getButtonNullLayout("Выйти из игры", f30, frame,
            frame.getWidth()/2+40+ frame.getWidth()/4, frame.getHeight()-100, frame.getWidth()/5, 50,
            new GameGUIListeners.Exit(), COLOR_INTERFACE_GREEN, null);
    public final static JButton buttonExitAccount = getButtonNullLayout("Выйти", f20, frame,
            30, frame.getHeight()-100, 150, 40, new GameGUIListeners.ExitAccount(), COLOR_INTERFACE_GREEN, null);
    public final static JButton buttonSaveAccount = getButtonNullLayout("Сохранить", f20, frame,
            200, frame.getHeight()-100, 150, 40, new GameGUIListeners.SaveAccount(), COLOR_INTERFACE_GREEN, null);
    public final static JButton buttonSettings = getButtonNullLayout("Настройки", f30, frame,
            frame.getWidth()/2+40, frame.getHeight()-100, frame.getWidth()/5, 50,
            new GameGUIListeners.Settings(), COLOR_INTERFACE_GREEN, null);
    public final static JButton buttonEndRegistration = getButtonNullLayout("Зарегестрироваться", f20, frame,
            75, 680, 270, 45, new GameGUIListeners.EndOfRegistrationAccount(), COLOR_INTERFACE_GREEN, null);

    public final static JButton buttonRight = getButtonNullLayout(">", f50, frame,
            frame.getWidth()-270-90, frame.getHeight()/2 - 50, 90, 80,
            new GameGUIListeners.InventoryRight(), COLOR_INTERFACE_GREEN, null);
    public final static JButton buttonLeft = getButtonNullLayout("<", f50, frame,
            270, frame.getHeight()/2 - 50, 90, 80,
            new GameGUIListeners.InventoryLeft(), COLOR_INTERFACE_GREEN, null);

    public final static JButton buttonPantsClothes = getButtonNullLayout(null, null, frame,
            0, 0, 0, 0, null, COLOR_INTERFACE_GREEN, null);
    public final static JButton buttonShirtClothes = getButtonNullLayout(null, null, frame,
            0, 0, 0, 0, null, COLOR_INTERFACE_GREEN, null);

    public final static JButton buttonPantsSlots = getButtonNullLayout(null, f25, frame,
            frame.getWidth() /2 - 200, 500, 400, 50,
            new GameGUIListeners.InventorySlotsPants(), COLOR_INTERFACE_GREEN, null);
    public final static JButton buttonShirtSlots = getButtonNullLayout(null, f25, frame,
            frame.getWidth() /2 - 200, 300, 400, 50,
            new GameGUIListeners.InventorySlotsShirt(), COLOR_INTERFACE_GREEN, null);
    public final static JButton buttonHandsSlots = getButtonNullLayout("Ручной инвентарь", f25, frame,
            frame.getWidth() /2 - 200, 400, 400, 50,
            new GameGUIListeners.InventorySlotsHands(), COLOR_INTERFACE_GREEN, null);


    public final static JLabel labelSettings = getLabelNullLayout("Настройки", f35, frame,
            frame.getWidth()/2-180/2, 250, 180, 60, COLOR_INTERFACE_YELLOW);
    public final static JButton buttonSettingsServer = getButtonNullLayout("Подключение к серверу", f30, frame,
            frame.getWidth()/2-200, 350, 400, 55,
            new GameGUIListeners.SettingsServer(), COLOR_INTERFACE_YELLOW, null);

    public final static JLabel labelWarningNeedRegistration = getLabelNullLayout("Необходимо зарегестрироваться!", f30, frame,
            frame.getWidth()/3+45, frame.getHeight()/7*5, 500, 40, COLOR_INTERFACE_GREEN);

    public final static JLabel labelRegisterNick = getLabelNullLayout("Логин", f25, frame,
            165, 420, 300, 30, COLOR_INTERFACE_GREEN);
    public final static JLabel labelRegisterPassword = getLabelNullLayout("Пароль", f25, frame,
            165, 520, 200, 30, COLOR_INTERFACE_GREEN);
    public final static JLabel labelAccount = getLabelNullLayout("Вы не авторизованы", f30, frame,
            60, frame.getHeight()-190, 400, 60, COLOR_INTERFACE_GREEN);
    public final static JLabel labelWarning = getLabelNullLayout(null, f20, frame,
            100, 740, 2000, 30, COLOR_INTERFACE_GREEN);
    public final static JLabel labelSlots = getLabelNullLayout("Слоты", f50, frame,
            frame.getWidth()/2-85, 120, 180, 50, COLOR_INTERFACE_ORANGE);
    public final static JLabel labelNick = getLabelNullLayout(null, f15, frame,
            playerXFrame - 40, playerYFrame - 60, 1000, 20, COLOR_INTERFACE_ORANGE);
    public final static JLabel labelSaveAccount = getLabelNullLayout(null, f20, frame,
            370, frame.getHeight()-100, 1000, 40, COLOR_INTERFACE_GREEN);

    public final static JTextField textNick = getTextFieldNullLayout(null, f30, frame,
            80, 465, 250, 40, COLOR_INTERFACE_TURQUOISE, null);
    public final static JTextField textPassword = getTextFieldNullLayout(null, f30, frame,
            80, 565, 250, 40, COLOR_INTERFACE_TURQUOISE, null);

    public final static JTextField textNameForNewWorld = getTextFieldNullLayout(null, f50, frame,
            frame.getWidth()/2-100/2, 235, 400, 70, COLOR_INTERFACE_TURQUOISE, null);

    public final static JTextArea textQuests = getTextAreaNullLayout(null, f20, frame,
            frame.getWidth() - 238, 35, 200, 180, COLOR_GAME_INTERFACE_FRONT, COLOR_INTERFACE_ORANGE);

    public final static JTextArea textWorlds = new JTextArea("Тест\nТест2\nТест3\nТест4\nТест5",10, 20);
    public final static JScrollPane scrollPaneWorlds = new JScrollPane(textWorlds);
    public final static JPanel scrollPanel = new JPanel();
}
