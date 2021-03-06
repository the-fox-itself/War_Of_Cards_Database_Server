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
                            IncorrectCommandUseException("account", "account add \"??????_??????\" \"??????_????????????\" ?????? account delete \"????????????????_????????????????\" ?????? account get \"????????????????_????????????????\"");
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
                            printNote(ANSI_RESET+"\"" + ANSI_BLUE + userCommand.split(" ")[0] + ANSI_RESET + "\""+ANSI_RED+" ???? ???????????????? ????????????????"+ANSI_RESET, NOTE_TYPE_ERROR);
                        } else {
                            printNote("\"" + userCommand.split(" ")[0] + "\" ???? ???????????????? ????????????????", NOTE_TYPE_ERROR);
                        }
                        break;
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                ex.printStackTrace();
                printNote("???????????????????????? ????????", NOTE_TYPE_ERROR);
            }
        }
        printNote("???????????????????? ??????????????????", NOTE_TYPE_PROCESS);
        printNote("?????????????????? ???????????????????????? ??????????????????", NOTE_TYPE_DONE);
    }

    void help() {
        if (colorful_string) {
            System.out.println(ANSI_YELLOW +"<Helper>"+ ANSI_RESET +" ???????????? ???????????? ????????????:\n" +
                    ANSI_CYAN+"   ??????????????       ???????????????? ??????????????????????????                ????????????????\n"+
                    ANSI_BLUE+"-  help          "+ANSI_YELLOW+"help"+ANSI_RESET+"                                  ???????????? ???????????? ???????????? ???????????? ?? ???????????????????? ?????????????????????????? ?? ????????????????????.\n" +
                    ANSI_BLUE+"-  color         "+ANSI_YELLOW+"color [true/false]"+ANSI_RESET+"                    ????????????????/?????????????????? ???????????????????????? ?????????? ??????????????.\n" +
                    ANSI_BLUE+"-  exit          "+ANSI_YELLOW+"exit"+ANSI_RESET+"                                  ?????????????????? ?????????????????? War Of Cards Server.\n" +
                    ANSI_BLUE+"-  start         "+ANSI_YELLOW+"start *????????*"+ANSI_RESET+"                          ?????????????????? ???????????? ???? ???????????????? ??????????.\n" +
                    ANSI_BLUE+"-  stop          "+ANSI_YELLOW+"stop"+ANSI_RESET+"                                  ?????????????????????????? ???????????? ???????? ???????????????????? ????????????????.\n" +
                    ANSI_BLUE+"                 "+ANSI_YELLOW+"stop *????????*"+ANSI_RESET+"                           ?????????????????????????? ???????????? ?????????????? ???? ???????????????? ??????????.\n" +
                    ANSI_BLUE+"-  list          "+ANSI_YELLOW+"list"+ANSI_RESET+"                                  ?????????????? ???????????? ?????????????????????? ????????????????.\n" +
                    ANSI_BLUE+"-  account       "+ANSI_YELLOW+"account add \"??????\" \"????????????\""+ANSI_RESET+"            ?????????????? ?? ???????????????????????? ?? ???????? ???????????? ?????????? ???????????? ?? ?????????????????? ?????????? ?? ??????????????.\n" +
                    ANSI_BLUE+"                 "+ANSI_YELLOW+"account delete \"??????\" \"????????????\""+ANSI_RESET+"         ?????????????? ?????????????? ???? ???????? ???????????? ???? ???????????????????? ????????????????.\n" +
                    ANSI_BLUE+"                 "+ANSI_YELLOW+"account get \"??????\" \"????????????\""+ANSI_RESET+"            ???????????????????? ???????????? ???????????????? ???? ???????? ???????????? ???? ???????????????????? ????????????????.");
        } else {
            System.out.println("<Helper> ???????????? ???????????? ????????????:\n" +
                    "   ??????????????       ???????????????? ??????????????????????????                 ????????????????\n" +
                    "-  help          help                                   ???????????? ???????????? ???????????? ???????????? ?? ???????????????????? ?????????????????????????? ?? ????????????????????.\n" +
                    "-  color         color [true/false]                     ????????????????/?????????????????? ???????????????????????? ?????????? ??????????????.\n" +
                    "-  exit          exit                                   ?????????????????? ?????????????????? War Of Cards Server.\n" +
                    "-  start         start *????????*                           ?????????????????? ???????????? ???? ???????????????? ??????????.\n" +
                    "-  stop          stop                                   ?????????????????????????? ???????????? ???????? ???????????????????? ????????????????.\n" +
                    "                 stop *????????*                            ?????????????????????????? ???????????? ?????????????? ???? ???????????????? ??????????.\n" +
                    "-  list          list                                   ?????????????? ???????????? ?????????????????????? ????????????????.\n" +
                    "-  account       account add \"??????\" \"????????????\"             ?????????????? ?? ???????????????????????? ?? ???????? ???????????? ?????????? ???????????? ?? ?????????????????? ?????????? ?? ??????????????.\n" +
                    "                 account delete \"??????\" \"????????????\"          ?????????????? ?????????????? ???? ???????? ???????????? ???? ???????????????????? ????????????????.\n" +
                    "                 account get \"??????\" \"????????????\"             ???????????????????? ???????????? ???????????????? ???? ???????? ???????????? ???? ???????????????????? ????????????????.");
        }
    }
    void start(String userCommand) {
        try {
            boolean noException = true;
            int port = 0;
            try {
                port = Integer.parseInt(userCommand.split(" ")[1]);
            } catch (NumberFormatException ex) {
                IncorrectCommandUseException("start", "start *????????*");
                noException = false;
            }
            for (ServerSocket serverSocket : listOfServers) {
                if (serverSocket.getLocalPort() == port) {
                    printNote("???????????? ?????? ???????????? ???? ?????????? " + port + ": " + serverSocket, NOTE_TYPE_ERROR);
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
                            printNote("?????? ?????????????????? ?????????? ????????????", NOTE_TYPE_DONE);
                            if (!message.split("???")[0].equals(serverVersion)) {
                                printNote("???????????? ???????????????? ?????????????? ???????????????????????? ?? ?????????????? ??????????????", NOTE_TYPE_ERROR);
                                sendToClient(serverSocket, "incorrect version");
                                sendToClient(serverSocket, serverVersion);
                            } else {
                                String file_name;
                                String nick;
                                String password;
                                switch (Objects.requireNonNull(message).split("???")[1]) {
                                    case "reg":
                                        printData("?????????????? ????????????????????????", "reg");
                                        nick = message.split("???")[2];
                                        password = code(message.split("???")[3] + "???");
                                        file_name = code(message.split("???")[2] + message.split("???")[2] + "???") + password;
                                        boolean noAccountsWithSameName = false;
                                        try {
                                            ObjectInputStream objectOutputStream = new ObjectInputStream(new FileInputStream("database" + SEPARATOR + "accounts" + SEPARATOR + file_name + ".ser"));
                                            objectOutputStream.readObject();
                                            printNote("?????????????? ?? ???????????? " + nick + " ?????? ????????????????????", NOTE_TYPE_ERROR);
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
                                                printNote("?????????????? " + nick + " ?????????????? ??????????????????????????????!", NOTE_TYPE_DONE);
                                                sendToClient(serverSocket, "success");
                                                getFromClient(serverSocket);
                                                sendToClient(serverSocket, account);
                                            } catch (Exception ex) {
                                                ex.printStackTrace();
                                                printNote("?????? ?????????????????????? ???????????????? " + nick + " ?????????????????? ????????", NOTE_TYPE_ERROR);
                                                sendToClient(serverSocket, "error");
                                            }
                                        }
                                        break;
                                    case "login":
                                        printData("?????????????? ????????????????????????", "login");
                                        nick = message.split("???")[1];
                                        password = code(message.split("???")[3] + "???");
                                        file_name = code(message.split("???")[2] + message.split("???")[2] + "???") + password;
                                        try {
                                            ObjectInputStream objectOutputStream = new ObjectInputStream(new FileInputStream("database" + SEPARATOR + "accounts" + SEPARATOR + file_name + ".ser"));
                                            Account account = (Account) objectOutputStream.readObject();
                                            if (account.nick.equals(nick) && account.password.equals(password)) {
                                                printNote("???????????? ?????????????? ?? ???????????? " + nick + ": " + account, NOTE_TYPE_DONE);
                                                sendToClient(serverSocket, "success");
                                                getFromClient(serverSocket);
                                                sendToClient(serverSocket, account);
                                            }
                                        } catch (IOException | ClassNotFoundException e) {
                                            printNote("?????????????? ?? ???????????? " + nick + " ???? ??????????????????????????????", NOTE_TYPE_ERROR);
                                            sendToClient(serverSocket, "not exist");
                                        }
                                        break;
                                    case "save":
                                        printData("?????????????? ????????????????????????", "save");
                                        password = message.split("???")[3];
                                        file_name = code(message.split("???")[2] + message.split("???")[2] + "???") + password;
                                        sendToClient(serverSocket, "success");
                                        Account account = (Account) getFromClient(serverSocket);
                                        boolean noException1 = true;
                                        try {
                                            ObjectInputStream objectOutputStream = new ObjectInputStream(new FileInputStream("database" + SEPARATOR + "accounts" + SEPARATOR + file_name + ".ser"));
                                            objectOutputStream.readObject();
                                            assert account != null;
                                            printNote("?????????????? ?? ???????????? " + account.nick + " ?????????????? ????????????", NOTE_TYPE_DONE);
                                        } catch (IOException | ClassNotFoundException e) {
                                            sendToClient(serverSocket, "not exist");
                                            noException1 = false;
                                        }
                                        if (noException1) {
                                            try {
                                                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("database" + SEPARATOR + "accounts" + SEPARATOR + file_name + ".ser"));
                                                objectOutputStream.writeObject(account);
                                                objectOutputStream.close();
                                                printNote("?????????????? " + account.nick + " ?????????????? ????????????????", NOTE_TYPE_DONE);
                                                sendToClient(serverSocket, "success");
                                            } catch (Exception ex) {
                                                ex.printStackTrace();
                                                printNote("?????? ???????????????????? ???????????????? " + account.nick + " ?????????????????? ????????", NOTE_TYPE_ERROR);
                                                sendToClient(serverSocket, "error");
                                            }
                                        }
                                        break;
                                    default:
                                        printData("?????????????? ????????????????????????", "-");
                                        printNote("?????????????????? ???????????????????? ?????????????? ????????????????????????", NOTE_TYPE_ERROR);
                                        sendToClient(serverSocket, "command don't exist");
                                        break;
                                }
                            }
                        }
                        printNote("???????????? ???? ?????????? " + serverSocket.getLocalPort() + " ?????????????????? ???????? ????????????", NOTE_TYPE_DONE);
                    };
                    Thread threadServer = new Thread(runnableServer);
                    threadServer.start();
                    printNote("???????????? ?????????????? ???????????? ???? ?????????? " + port, NOTE_TYPE_DONE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (IndexOutOfBoundsException ex) {
            IncorrectCommandUseException("start", "start *????????*");
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
            printNote("???????????? ???????? ???????????????? ?????????????? ????????????????????", NOTE_TYPE_DONE);
        } else {
            ServerSocket serverSocket = null;
            int port = Integer.parseInt(userCommand.split(" ")[1]);
            for (ServerSocket socket : listOfServers) {
                if (socket.getLocalPort() == port) {
                    serverSocket = socket;
                }
            }
            if (serverSocket == null) {
                printNote("???????????? ???? ????????????????????. ???????????? ???? ?????????? " + port + " ???? ?????? ????????????", NOTE_TYPE_ERROR);
            } else {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                listOfServers.remove(serverSocket);
                printNote("???????????? ???? ?????????? "+port+" ?????????????? ????????????", NOTE_TYPE_DONE);
            }
        }
    }
    void list() {
        printNote("???????????? ?????????????????????? ????????????????: " + listOfServers, NOTE_TYPE_INFO);
    }
    void color(String userCommand) {
        try {
            if (userCommand.split(" ")[1].equals("true")) {
                colorful_string = true;
                printNote("?????????????? ?????????? ??????????????", NOTE_TYPE_INFO);
            } else if (userCommand.split(" ")[1].equals("false")) {
                colorful_string = false;
                printNote("?????????????? ?????????? ????????????????", NOTE_TYPE_INFO);
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
                String password = code(userCommand.split("\"")[3]+"???");
                String filename = code(nick+nick+"???")+password;
                File file = new File("database" + SEPARATOR + "accounts" + SEPARATOR + filename + ".ser");
                if (file.delete()) {
                    printNote("?????????????? " + nick + " ?????????????? ???????????? ???? ???????? ????????????", NOTE_TYPE_DONE);
                } else {
                    printNote("???????????? ???? ????????????????????. ?????????????? " + nick + " ???? ???????????? ?? ???????? ????????????", NOTE_TYPE_ERROR);
                }
            }
        } else {
            IncorrectCommandUseException("account delete", "account delete \"??????\" \"????????????\"");
        }
    }
    void account_add(String userCommand) {
        if (userCommand.split(" ").length == 4) {
            if (userCommand.split("\"").length < 4) {
                DataNoQuotesException();
            } else {
                String nick = userCommand.split("\"")[1];
                String password = userCommand.split("\"")[3];
                String filename = code(nick+nick+"???")+code(userCommand.split("\"")[3]+"???");;
                Account account = new Account(nick, password);
                try {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("database" + SEPARATOR + "accounts" + SEPARATOR + filename + ".ser"));
                    objectOutputStream.writeObject(account);
                    objectOutputStream.close();
                    printNote("?????????????? " + account.nick + " ?????????????? ??????????????????????????????", NOTE_TYPE_DONE);
                } catch (IOException e) {
                    printNote("?????? ???????????????? ???????????????? " + nick + " " + password + " ?????????????????? ????????", NOTE_TYPE_ERROR);
                    e.printStackTrace();
                }
            }
        } else {
            IncorrectCommandUseException("account add", "account add \"??????\" \"????????????\"");
        }
    }
    void account_get(String userCommand) {
        if (userCommand.split(" ").length == 4) {
            if (userCommand.split("\"").length < 4) {
                DataNoQuotesException();
            } else {
                String nick = userCommand.split("\"")[1];
                String password = code(userCommand.split("\"")[3]+"???");
                String filename = code(nick+nick+"???")+password;
                try {
                    ObjectInputStream objectOutputStream = new ObjectInputStream(new FileInputStream("database" + SEPARATOR + "accounts" + SEPARATOR + filename + ".ser"));
                    Account account = (Account) objectOutputStream.readObject();
                    printNote("???????????? ?????????????? ?? ???????????? " + nick + ": " + account + "; ???????????????? ?????????? ???????????????????? ????????????????: " + filename + ".ser", NOTE_TYPE_DONE);
                } catch (IOException | ClassNotFoundException e) {
                    printNote("?????????????? ?? ???????????? " + nick + " ???? ???????????? ?? ???????? ????????????", NOTE_TYPE_ERROR);
                    e.printStackTrace();
                }
            }
        } else {
            IncorrectCommandUseException("account get", "account get \"??????\" \"????????????\"");
        }
    }

    void printUsedCommand(String command) {
        if (colorful_string) {
            printNote("???????????????????????? ??????????????: \""+ ANSI_BLUE +command+ ANSI_RESET +"\"", NOTE_TYPE_INFO);
        } else {
            printNote("???????????????????????? ??????????????: \""+command+"\"", NOTE_TYPE_INFO);
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
            printNote(ANSI_RED+"???????????????????????? ?????????????????????????? ?????????????? "+ANSI_RESET+"\""+ ANSI_BLUE +command+ANSI_RESET+ "\""+ANSI_RED +". ???????????????????? ??????????????????????????: "+ANSI_RESET+"\""+ ANSI_BLUE +correctUse+ ANSI_RESET +"\""+ANSI_RESET, NOTE_TYPE_ERROR);
        } else {
            printNote("???????????????????????? ?????????????????????????? ?????????????? \""+command+"\". ???????????????????? ??????????????????????????: \""+correctUse+"\"", NOTE_TYPE_ERROR);
        }
    }
    void DataNoQuotesException() {
        if (colorful_string) {
            printNote(ANSI_RED+"???????????????? ???????????? ???????????????????? ???????????????? ?? ??????????????. ????????????????: "+ ANSI_RESET +"\""+ ANSI_BLUE +"????????_????????????"+ ANSI_RESET +"\""+ ANSI_RESET, NOTE_TYPE_ERROR);
        } else {
            printNote("???????????????? ???????????? ???????????????????? ???????????????? ?? ??????????????. ????????????????: \"????????_????????????\"", NOTE_TYPE_ERROR);
        }
    }

    String code(String string) {
        return String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(string.hashCode()).hashCode()).hashCode()).hashCode()).hashCode()).hashCode()).hashCode()).hashCode()).hashCode());
    }
}
