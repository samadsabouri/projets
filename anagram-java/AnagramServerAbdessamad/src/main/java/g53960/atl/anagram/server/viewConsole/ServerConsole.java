package g53960.atl.anagram.server.viewConsole;

import g53960.atl.anagram.common.model.User;
import g53960.atl.angram.server.model.AnagramServer;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Samad g53960
 */
public class ServerConsole implements Observer {
    
    public static void main(String[] args) {
        try {
            AnagramServer model = new AnagramServer();
            ServerConsole console = new ServerConsole(model);
            model.addObserver(console);
            System.out.println("Server started");
            System.out.println("");
        } catch (IOException ex) {
            Logger.getLogger(ServerConsole.class.getName()).log(Level.SEVERE, "Erreur connexion serveur", ex);
            System.exit(0);
        }
    }
    private final AnagramServer model;
    
    public ServerConsole(AnagramServer model) {
        this.model = model;
    }


    @Override
    public void update(Observable o, Object arg) {
        updateUser();
    }
 
    private void updateUser() {
        System.out.println("");
        StringBuilder builder = new StringBuilder();
        builder.append("\n---- ---- Liste Users ---- ----\n");
        builder.append("Nombre d'utilisateurs connectes : ")
                .append(model.getUsers().size()).append("\n");
        builder.append("ID").append("\t");
        builder.append("IP").append("\t\t");
        builder.append("NAME").append("\n");
        for (User user : model.getUsers()) {
            builder.append(user.getId()).append("\t");
            builder.append(user.getAddress()).append("\t");
            builder.append(user.getName()).append("\n");
        }
        System.out.println(builder);
        System.out.println("");
    }

}

