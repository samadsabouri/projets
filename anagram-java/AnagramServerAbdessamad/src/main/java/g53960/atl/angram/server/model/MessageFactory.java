package g53960.atl.angram.server.model;

import g53960.atl.anagram.common.message.Message;
import g53960.atl.anagram.common.message.MessageGameState;
import g53960.atl.anagram.common.message.MessageProfile;
import g53960.atl.anagram.common.message.Type;
import g53960.atl.anagram.common.model.GameState;
import g53960.atl.anagram.common.model.User;
import g53960.atl.anagram.common.model.Word;
import g53960.atl.anagram.server.exception.BusinessException;
import g53960.atl.anagram.server.exception.ModelException;
import g53960.atl.anagram.server.model.Model;
import g53960.atl.anagram.server.server.ConnectionToClient;
import static g53960.atl.angram.server.model.AnagramServer.ID_MAPINFO;

/**
 *
 * @author Samad g53960
 */
public class MessageFactory {
    
    static void build(Message message, ConnectionToClient client, AnagramServer anagramServer) throws ModelException, BusinessException {
        Type type = message.getType();
        switch (type) {
            case PROFILE:
                anagramServer.checkDb(message.getAuthor(), client);
                anagramServer.displayDb();
                Message msg= new MessageProfile(new User((int) client.getInfo(ID_MAPINFO),message.getAuthor().getName()));
                anagramServer.sendToClient(msg, message.getAuthor());
                break;
            case NEWGAME:
                   anagramServer.sendNewGameToClients(client);
                   anagramServer.startTime();
                break;
            case CHECK:
                    System.out.println("1"+ message.getContent());
                    Word word = (Word) message.getContent();
                    String proposal =word.getText();
                    Model model = (Model) client.getInfo(anagramServer.GAME_MAPINFO);
                    Boolean test= model.propose(proposal);
                    
                    if(test == true){
                        anagramServer.addGameWord(anagramServer.getLastGame()+1, model.getWord().getText(), "SOLVED");
                        model.nextWord();
                        GameState gm= model.getGameState();
                        MessageGameState GM= new MessageGameState(message.getAuthor(), gm);
                        anagramServer.sendToClient(GM, client);
                    }else{
                        GameState gm= model.getGameState();
                        MessageGameState GM= new MessageGameState(message.getAuthor(), gm);
                        anagramServer.sendToClient(GM, client);
                    }
                   
                break;
                case QUIT:
                   anagramServer.clientDisconnected(client);
                break;
                
                case PASS:
                   Model model2 = (Model) client.getInfo(anagramServer.GAME_MAPINFO);
                   String answer = model2.pass();
                   anagramServer.addGameWord(anagramServer.getLastGame()+1, answer, "UNSOLVED");
                   model2.nextWord();
                   GameState gm= model2.getGameState();
                   MessageGameState GM= new MessageGameState(message.getAuthor(), gm);
                   anagramServer.sendToClient(GM, client);
                   
                break;
            default:
                throw new IllegalArgumentException("Message type unknown " + type);
        }
    }
}
