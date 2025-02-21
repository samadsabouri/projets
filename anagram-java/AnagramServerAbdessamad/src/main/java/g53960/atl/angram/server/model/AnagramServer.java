package g53960.atl.angram.server.model;

import g53960.atl.anagram.common.message.Message;
import g53960.atl.anagram.common.message.MessageGameState;
import g53960.atl.anagram.common.model.GameState;
import g53960.atl.anagram.common.model.User;
import g53960.atl.anagram.server.business.GameModel;
import g53960.atl.anagram.server.business.GameWordModel;
import g53960.atl.anagram.server.business.UserModel;
import g53960.atl.anagram.server.dto.GameDto;
import g53960.atl.anagram.server.dto.UserDto;
import g53960.atl.anagram.server.server.AbstractServer;
import g53960.atl.anagram.server.server.ConnectionToClient;
import g53960.atl.anagram.server.exception.BusinessException;
import g53960.atl.anagram.server.exception.ModelException;
import g53960.atl.anagram.server.model.Model;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Samad g53960
 */
public class AnagramServer extends AbstractServer {

    private static final int PORT = 12_345;
    static final String ID_MAPINFO = "ID";
    static final String GAME_MAPINFO = "GAME";
    private int addG = 0; 

    private long startTime;
    private LocalDateTime currentDateTime;
    UserModel userModel = new UserModel();
    GameModel gameModel = new GameModel();
    GameWordModel gameWordModel = new GameWordModel();

    private static InetAddress getLocalAddress() {
        try {
            Enumeration<NetworkInterface> b = NetworkInterface.getNetworkInterfaces();
            while (b.hasMoreElements()) {
                for (InterfaceAddress f : b.nextElement().getInterfaceAddresses()) {
                    if (f.getAddress().isSiteLocalAddress()) {
                        return f.getAddress();
                    }
                }
            }
        } catch (SocketException e) {
            Logger.getLogger(AnagramServer.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    
    private final Users users;

    public AnagramServer() throws IOException {
        super(PORT);
        users = new Users();
        //clientId = 0;
        this.listen();
    }

   

    public void sendNewGameToClients(ConnectionToClient client) throws ModelException {
        Model model = new Model();
        model.initialize();
        model.start();
        model.nextWord();
        client.setInfo(GAME_MAPINFO, model);
        GameState gameState = model.getGameState();
        User user = users.getUser((int) client.getInfo(ID_MAPINFO));
        MessageGameState messageGS = new MessageGameState(user, gameState);
        sendToClient(messageGS, client);
    }

    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        Message message = (Message) msg;
        try {
            MessageFactory.build(message, client, this);
        } catch (ModelException | BusinessException ex) {
            Logger.getLogger(AnagramServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        notifyChange(message);
    }

    public void quit() throws IOException {
        this.stopListening();
        this.close();
    }

    @Override
    protected synchronized void clientDisconnected(ConnectionToClient client) {
        super.clientDisconnected(client);
        int userId = (int) client.getInfo(ID_MAPINFO);
        users.remove(userId);
        notifyChange();
        try {
            if(addG == 0){
                addgame(client.getName(), getTime());
                addG++;
            }else{
                addG =0;
            }
        } catch (BusinessException ex) {
            Logger.getLogger(AnagramServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected synchronized void clientConnected(ConnectionToClient client) {
        super.clientConnected(client);
        notifyChange();
    }

    @Override
    protected synchronized void clientException(ConnectionToClient client, Throwable exception) {
        super.clientException(client, exception);
        try {
            if (client.isConnected()) {
                client.sendToClient(new IllegalArgumentException("Message illisible " + exception.getMessage()));
            }
        } catch (IOException ex) {
            Logger.getLogger(AnagramServer.class.getName()).log(Level.SEVERE, "Impossible d envoyer erreur au client", ex);
        }
    }

    void notifyChange() {
        setChanged();
        notifyObservers();
    }

    void notifyChange(Message message) {
        setChanged();
        notifyObservers(message);
    }

    public Users getUsers() {
        return users;
    }

    public String getIP() {
        if (getLocalAddress() == null) {
            return "Unknown";
        }
        return getLocalAddress().getHostAddress();
    }

    public int getNbConnected() {
        return getNumberOfClients();
    }

    void sendToClient(Message message, User recipient) {
        sendToClient(message, recipient.getId());
    }

    void sendToClient(Message message, ConnectionToClient client) {
        if (client != null) {
            try {
                client.sendToClient(message);
            } catch (IOException ex) {
            }
        }
    }

    void sendToClient(Message message, int clientId) {
        ConnectionToClient client = getConnectionToClient(clientId);
        sendToClient(message, client);
    }
//g53960
    void changeName(String name, int userId) {
        users.changeName(name, userId);
    }

    ConnectionToClient getConnectionToClient(int clientId) {
        ConnectionToClient client = null;
        List<Thread> clientThreadList = getClientConnections();
        int index = 0;
        boolean find = false;
        while (index < clientThreadList.size() && !find) {
            client = (ConnectionToClient) clientThreadList.get(index);
            int id = (int) client.getInfo(ID_MAPINFO);
            if (id == clientId) {
                find = true;
            }
            index++;
        }
        return client;
    }
//g53960
    void startTime() {
        this.currentDateTime = LocalDateTime.now();
    }
//g53960
    String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentDateTime.format(formatter);
    }
//g53960
    void checkDb(User author, ConnectionToClient client) {
        try {
            UserDto user = userModel.getUser(author.getName());
            if (user == null) {
                int id = userModel.addUser(author.getName());
                users.add(id, author.getName(), client.getInetAddress());
                client.setInfo(ID_MAPINFO, id);
                client.setName(author.getName());

            } else {
                users.add(user.getId(), user.getName(), client.getInetAddress());
                client.setInfo(ID_MAPINFO, user.getId());
                client.setName(user.getName());
            }

        } catch (BusinessException e) {
            System.out.println(e.getMessage());
        }
    }
//g53960
    void displayDb() {
        try {
            List<UserDto> users = userModel.getUsers();
            //System.out.println("USERS" + users);

        } catch (BusinessException e) {
            System.out.println(e.getMessage());
        }
    }
//g53960
    void addgame(String userName, String endTime) throws BusinessException {
        gameModel.addGame(userName, endTime);
    }
//g53960
    int getLastGame() throws BusinessException {
        List<GameDto> games = gameModel.getGames();
        int lastGame = games.get(games.size() - 1).getId();

        return lastGame;
    }
//g53960
    void addGameWord(int game, String word, String status) throws BusinessException {
        gameWordModel.addGameWord(game, word, status);
    }

}
