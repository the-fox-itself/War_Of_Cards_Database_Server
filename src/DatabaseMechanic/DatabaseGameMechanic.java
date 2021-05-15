package DatabaseMechanic;

import GameMechanic.Objects.TechnicalObjects.Account;

import java.io.*;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Objects;

import static DatabaseMechanic.DatabaseMainGUIVariables.*;
import static Libraries.Methods.*;

public class DatabaseGameMechanic {
    ArrayList<ServerSocket> listOfServers = new ArrayList<>();
    public static boolean colorful_string = false;

    void preparation() {
        boolean inWhile = true;
        while (inWhile) {
            String userCommand = readLine();
            try {
                switch (userCommand.split(" ")[0]) {
                    case "start":
                        printUsedCommand("start");
                        start(userCommand);
                        break;
                    case "stop":
                        printUsedCommand("stop");
                        stop(userCommand);
                        break;
                    case "list":
                        printUsedCommand("list");
                        list();
                        break;
                    case "account":
                        if (userCommand.split(" ").length > 1) {
                            switch (userCommand.split(" ")[1]) {
                                case "delete":
                                    printUsedCommand("account delete");
                                    account_delete(userCommand);
                                    break;
                                case "add":
                                    printUsedCommand("account add");
                                    account_add(userCommand);
                                    break;
                                case "get":
                                    printUsedCommand("account get");
                                    account_get(userCommand);
                                    break;
                            }
                        } else {
                            IncorrectCommandUseException("account", "account add \"Ваш_ник\" \"Ваш_пароль\" ИЛИ account delete \"Название_аккаунта\" ИЛИ account get \"Название_аккаунта\"");
                        }
                        break;
                    case "color":
                        printUsedCommand("color");
                        color(userCommand);
                        break;
                    case "help":
                        printUsedCommand("help");
                        help();
                        break;
                    case "exit":
                        printUsedCommand("exit");
                        inWhile = false;
                        break;
                    default:
                        if (colorful_string) {
                            printNote(ANSI_RESET+"\"" + ANSI_BLUE + userCommand.split(" ")[0] + ANSI_RESET + "\""+ANSI_RED+" не является командой"+ANSI_RESET, NOTE_TYPE_ERROR);
                        } else {
                            printNote("\"" + userCommand.split(" ")[0] + "\" не является командой", NOTE_TYPE_ERROR);
                        }
                        break;
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                ex.printStackTrace();
                printNote("Некорректный ввод", NOTE_TYPE_ERROR);
            }
        }
        printNote("Выключение программы", NOTE_TYPE_PROCESS);
        printNote("Программа благополучно выключена", NOTE_TYPE_DONE);
    }

    void help() {
        if (colorful_string) {
            System.out.println(ANSI_YELLOW +"<Helper>"+ ANSI_RESET +" Полный список команд:\n" +
                    ANSI_CYAN+"   Команда       Варианты использования                Описание\n"+
                    ANSI_BLUE+"-  help          "+ANSI_YELLOW+"help"+ANSI_RESET+"                                  Выдает полный список команд с вариантами использования и описаниями.\n" +
                    ANSI_BLUE+"-  color         "+ANSI_YELLOW+"color [true/false]"+ANSI_RESET+"                    Включает/выключает разноцветный шрифт консоли.\n" +
                    ANSI_BLUE+"-  exit          "+ANSI_YELLOW+"exit"+ANSI_RESET+"                                  Выключает программу War Of Cards Server.\n" +
                    ANSI_BLUE+"-  start         "+ANSI_YELLOW+"start *Порт*"+ANSI_RESET+"                          Запускает сервер на заданном порту.\n" +
                    ANSI_BLUE+"-  stop          "+ANSI_YELLOW+"stop"+ANSI_RESET+"                                  Останавливает работу всех запущенных серверов.\n" +
                    ANSI_BLUE+"                 "+ANSI_YELLOW+"stop *Порт*"+ANSI_RESET+"                           Останавливает работу сервера на заданном порту.\n" +
                    ANSI_BLUE+"-  list          "+ANSI_YELLOW+"list"+ANSI_RESET+"                                  Выводит список действующих серверов.\n" +
                    ANSI_BLUE+"-  account       "+ANSI_YELLOW+"account add \"Ник\" \"Пароль\""+ANSI_RESET+"            Создает и регистрирует в базе данных новый аккант с указанным ником и паролем.\n" +
                    ANSI_BLUE+"                 "+ANSI_YELLOW+"account delete \"Ник\" \"Пароль\""+ANSI_RESET+"         Удаляет аккаунт из базы данных по введённому названию.\n" +
                    ANSI_BLUE+"                 "+ANSI_YELLOW+"account get \"Ник\" \"Пароль\""+ANSI_RESET+"            Возвращает объект аккаунта из базы данных по введённому названию.");
        } else {
            System.out.println("<Helper> Полный список команд:\n" +
                    "   Команда       Варианты использования                 Описание\n" +
                    "-  help          help                                   Выдает полный список команд с вариантами использования и описаниями.\n" +
                    "-  color         color [true/false]                     Включает/выключает разноцветный шрифт консоли.\n" +
                    "-  exit          exit                                   Выключает программу War Of Cards Server.\n" +
                    "-  start         start *Порт*                           Запускает сервер на заданном порту.\n" +
                    "-  stop          stop                                   Останавливает работу всех запущенных серверов.\n" +
                    "                 stop *Порт*                            Останавливает работу сервера на заданном порту.\n" +
                    "-  list          list                                   Выводит список действующих серверов.\n" +
                    "-  account       account add \"Ник\" \"Пароль\"             Создает и регистрирует в базе данных новый аккант с указанным ником и паролем.\n" +
                    "                 account delete \"Ник\" \"Пароль\"          Удаляет аккаунт из базы данных по введённому названию.\n" +
                    "                 account get \"Ник\" \"Пароль\"             Возвращает объект аккаунта из базы данных по введённому названию.");
        }
    }
    void start(String userCommand) {
        try {
            boolean noException = true;
            int port = 0;
            try {
                port = Integer.parseInt(userCommand.split(" ")[1]);
            } catch (NumberFormatException ex) {
                IncorrectCommandUseException("start", "start *Порт*");
                noException = false;
            }
            for (ServerSocket serverSocket : listOfServers) {
                if (serverSocket.getLocalPort() == port) {
                    printNote("Сервер уже создан на порту " + port + ": " + serverSocket, NOTE_TYPE_ERROR);
                    noException = false;
                }
            }
            if (noException) {
                try {
                    ServerSocket serverSocket = new ServerSocket(port);
                    listOfServers.add(serverSocket);
                    Runnable runnableServer = () -> {
                        boolean isWhile = true;
                        while (isWhile) {
                            String message = (String) getFromClient(serverSocket);
                            printNote("Был подключен новый клиент", NOTE_TYPE_DONE);
                            if (!message.split("⊠")[0].equals(serverVersion)) {
                                printNote("Версия текущего клиента несовместима с версией сервера", NOTE_TYPE_ERROR);
                                sendToClient(serverSocket, "incorrect version");
                                sendToClient(serverSocket, serverVersion);
                            } else {
                                String file_name;
                                String nick;
                                String password;
                                switch (Objects.requireNonNull(message).split("⊠")[1]) {
                                    case "reg":
                                        printData("Команда пользователя", "reg");
                                        nick = message.split("⊠")[2];
                                        password = code(message.split("⊠")[3] + "⊠");
                                        file_name = code(message.split("⊠")[2] + message.split("⊠")[2] + "⊠") + password;
                                        boolean noAccountsWithSameName = false;
                                        try {
                                            ObjectInputStream objectOutputStream = new ObjectInputStream(new FileInputStream("database" + SEPARATOR + "accounts" + SEPARATOR + file_name + ".ser"));
                                            objectOutputStream.readObject();
                                            printNote("Аккаунт с именем " + nick + " уже существует", NOTE_TYPE_ERROR);
                                            sendToClient(serverSocket, "already exist");
                                        } catch (IOException | ClassNotFoundException e) {
                                            noAccountsWithSameName = true;
                                        }
                                        if (noAccountsWithSameName) {
                                            Account account = new Account(nick, password);
                                            try {
                                                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("database" + SEPARATOR + "accounts" + SEPARATOR + file_name + ".ser"));
                                                objectOutputStream.writeObject(account);
                                                objectOutputStream.close();
                                                printNote("Аккаунт " + nick + " успешно зарегестрирован!", NOTE_TYPE_DONE);
                                                sendToClient(serverSocket, "success");
                                                getFromClient(serverSocket);
                                                sendToClient(serverSocket, account);
                                            } catch (Exception ex) {
                                                ex.printStackTrace();
                                                printNote("При регистрации аккаунта " + nick + " произошли сбои", NOTE_TYPE_ERROR);
                                                sendToClient(serverSocket, "error");
                                            }
                                        }
                                        break;
                                    case "login":
                                        printData("Команда пользователя", "login");
                                        nick = message.split("⊠")[1];
                                        password = code(message.split("⊠")[3] + "⊠");
                                        file_name = code(message.split("⊠")[2] + message.split("⊠")[2] + "⊠") + password;
                                        try {
                                            ObjectInputStream objectOutputStream = new ObjectInputStream(new FileInputStream("database" + SEPARATOR + "accounts" + SEPARATOR + file_name + ".ser"));
                                            Account account = (Account) objectOutputStream.readObject();
                                            if (account.nick.equals(nick) && account.password.equals(password)) {
                                                printNote("Найден аккаунт с именем " + nick + ": " + account, NOTE_TYPE_DONE);
                                                sendToClient(serverSocket, "success");
                                                getFromClient(serverSocket);
                                                sendToClient(serverSocket, account);
                                            }
                                        } catch (IOException | ClassNotFoundException e) {
                                            printNote("Аккаунт с именем " + nick + " не зарегестрирован", NOTE_TYPE_ERROR);
                                            sendToClient(serverSocket, "not exist");
                                        }
                                        break;
                                    case "save":
                                        printData("Команда пользователя", "save");
                                        password = message.split("⊠")[3];
                                        file_name = code(message.split("⊠")[2] + message.split("⊠")[2] + "⊠") + password;
                                        sendToClient(serverSocket, "success");
                                        Account account = (Account) getFromClient(serverSocket);
                                        boolean noException1 = true;
                                        try {
                                            ObjectInputStream objectOutputStream = new ObjectInputStream(new FileInputStream("database" + SEPARATOR + "accounts" + SEPARATOR + file_name + ".ser"));
                                            objectOutputStream.readObject();
                                            assert account != null;
                                            printNote("Аккаунт с именем " + account.nick + " успешно найден", NOTE_TYPE_DONE);
                                        } catch (IOException | ClassNotFoundException e) {
                                            sendToClient(serverSocket, "not exist");
                                            noException1 = false;
                                        }
                                        if (noException1) {
                                            try {
                                                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("database" + SEPARATOR + "accounts" + SEPARATOR + file_name + ".ser"));
                                                objectOutputStream.writeObject(account);
                                                objectOutputStream.close();
                                                printNote("Аккаунт " + account.nick + " успешно сохранён", NOTE_TYPE_DONE);
                                                sendToClient(serverSocket, "success");
                                            } catch (Exception ex) {
                                                ex.printStackTrace();
                                                printNote("При сохранении аккаунта " + account.nick + " произошли сбои", NOTE_TYPE_ERROR);
                                                sendToClient(serverSocket, "error");
                                            }
                                        }
                                        break;
                                    default:
                                        printData("Команда пользователя", "-");
                                        printNote("Неудалось распознать команду пользователя", NOTE_TYPE_ERROR);
                                        sendToClient(serverSocket, "command don't exist");
                                        break;
                                }
                            }
                        }
                        printNote("Сервер на порту " + serverSocket.getLocalPort() + " прекратил свою работу", NOTE_TYPE_DONE);
                    };
                    Thread threadServer = new Thread(runnableServer);
                    threadServer.start();
                    printNote("Сервер успешно создан на порту " + port, NOTE_TYPE_DONE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (IndexOutOfBoundsException ex) {
            IncorrectCommandUseException("start", "start *Порт*");
        }
    }
    void stop(String userCommand) {
        if (userCommand.split(" ").length == 1) {
            for (ServerSocket serverSocket : listOfServers) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            listOfServers.subList(0, listOfServers.size()).clear();
            printNote("Работа всех серверов успешно прекращена", NOTE_TYPE_DONE);
        } else {
            ServerSocket serverSocket = null;
            int port = Integer.parseInt(userCommand.split(" ")[1]);
            for (ServerSocket socket : listOfServers) {
                if (socket.getLocalPort() == port) {
                    serverSocket = socket;
                }
            }
            if (serverSocket == null) {
                printNote("Ничего не изменилось. Сервер на порту " + port + " не был создан", NOTE_TYPE_ERROR);
            } else {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                listOfServers.remove(serverSocket);
                printNote("Сервер на порту "+port+" успешно удален", NOTE_TYPE_DONE);
            }
        }
    }
    void list() {
        printNote("Список действующих серверов: " + listOfServers, NOTE_TYPE_INFO);
    }
    void color(String userCommand) {
        try {
            if (userCommand.split(" ")[1].equals("true")) {
                colorful_string = true;
                printNote("Цветной текст включён", NOTE_TYPE_INFO);
            } else if (userCommand.split(" ")[1].equals("false")) {
                colorful_string = false;
                printNote("Цветной текст выключен", NOTE_TYPE_INFO);
            } else {
                IncorrectCommandUseException("color", "color [true/false]");
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            IncorrectCommandUseException("color", "color [true/false]");
        }
    }
    void account_delete(String userCommand) {
        if (userCommand.split(" ").length == 4) {
            if (userCommand.split("\"").length < 4) {
                DataNoQuotesException();
            } else {
                String nick = userCommand.split("\"")[1];
                String password = code(userCommand.split("\"")[3]+"⊠");
                String filename = code(nick+nick+"⊠")+password;
                File file = new File("database" + SEPARATOR + "accounts" + SEPARATOR + filename + ".ser");
                if (file.delete()) {
                    printNote("Аккаунт " + nick + " успешно удален из базы данных", NOTE_TYPE_DONE);
                } else {
                    printNote("Ничего не изменилось. Аккаунт " + nick + " не найден в базе данных", NOTE_TYPE_ERROR);
                }
            }
        } else {
            IncorrectCommandUseException("account delete", "account delete \"Ник\" \"Пароль\"");
        }
    }
    void account_add(String userCommand) {
        if (userCommand.split(" ").length == 4) {
            if (userCommand.split("\"").length < 4) {
                DataNoQuotesException();
            } else {
                String nick = userCommand.split("\"")[1];
                String password = userCommand.split("\"")[3];
                String filename = code(nick+nick+"⊠")+code(userCommand.split("\"")[3]+"⊠");;
                Account account = new Account(nick, password);
                try {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("database" + SEPARATOR + "accounts" + SEPARATOR + filename + ".ser"));
                    objectOutputStream.writeObject(account);
                    objectOutputStream.close();
                    printNote("Аккаунт " + account.nick + " успешно зарегестрирован", NOTE_TYPE_DONE);
                } catch (IOException e) {
                    printNote("При создании аккаунта " + nick + " " + password + " произошли сбои", NOTE_TYPE_ERROR);
                    e.printStackTrace();
                }
            }
        } else {
            IncorrectCommandUseException("account add", "account add \"Ник\" \"Пароль\"");
        }
    }
    void account_get(String userCommand) {
        if (userCommand.split(" ").length == 4) {
            if (userCommand.split("\"").length < 4) {
                DataNoQuotesException();
            } else {
                String nick = userCommand.split("\"")[1];
                String password = code(userCommand.split("\"")[3]+"⊠");
                String filename = code(nick+nick+"⊠")+password;
                try {
                    ObjectInputStream objectOutputStream = new ObjectInputStream(new FileInputStream("database" + SEPARATOR + "accounts" + SEPARATOR + filename + ".ser"));
                    Account account = (Account) objectOutputStream.readObject();
                    printNote("Найден аккаунт с именем " + nick + ": " + account + "; Название файла сохранений аккаунта: " + filename + ".ser", NOTE_TYPE_DONE);
                } catch (IOException | ClassNotFoundException e) {
                    printNote("Аккаунт с именем " + nick + " не найден в базе данных", NOTE_TYPE_ERROR);
                    e.printStackTrace();
                }
            }
        } else {
            IncorrectCommandUseException("account get", "account get \"Ник\" \"Пароль\"");
        }
    }

    void printUsedCommand(String command) {
        if (colorful_string) {
            printNote("Используемая команда: \""+ ANSI_BLUE +command+ ANSI_RESET +"\"", NOTE_TYPE_INFO);
        } else {
            printNote("Используемая команда: \""+command+"\"", NOTE_TYPE_INFO);
        }
    }
    void printData(String data_name, String data) {
        if (colorful_string) {
            printNote(data_name+": "+ ANSI_BLUE +data+ ANSI_RESET, NOTE_TYPE_INFO);
        } else {
            printNote(data_name+": "+data, NOTE_TYPE_INFO);
        }
    }

    void IncorrectCommandUseException(String command, String correctUse) {
        if (colorful_string) {
            printNote(ANSI_RED+"Некорректное использование команды "+ANSI_RESET+"\""+ ANSI_BLUE +command+ANSI_RESET+ "\""+ANSI_RED +". Правильное использование: "+ANSI_RESET+"\""+ ANSI_BLUE +correctUse+ ANSI_RESET +"\""+ANSI_RESET, NOTE_TYPE_ERROR);
        } else {
            printNote("Некорректное использование команды \""+command+"\". Правильное использование: \""+correctUse+"\"", NOTE_TYPE_ERROR);
        }
    }
    void DataNoQuotesException() {
        if (colorful_string) {
            printNote(ANSI_RED+"Вводимые данные необходимо помещать в кавычки. Например: "+ ANSI_RESET +"\""+ ANSI_BLUE +"Ваши_данные"+ ANSI_RESET +"\""+ ANSI_RESET, NOTE_TYPE_ERROR);
        } else {
            printNote("Вводимые данные необходимо помещать в кавычки. Например: \"Ваши_данные\"", NOTE_TYPE_ERROR);
        }
    }

    String code(String string) {
        return String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(string.hashCode()).hashCode()).hashCode()).hashCode()).hashCode()).hashCode()).hashCode()).hashCode()).hashCode());
    }
}
