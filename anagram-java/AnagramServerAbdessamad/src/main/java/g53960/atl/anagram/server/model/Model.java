package g53960.atl.anagram.server.model;

import g53960.atl.anagram.common.dto.WordDto;
import g53960.atl.anagram.common.model.GameState;
import g53960.atl.anagram.common.model.Word;
import g53960.atl.anagram.server.business.WordModel;
import g53960.atl.anagram.server.exception.BusinessException;
import g53960.atl.anagram.server.exception.ModelException;
import g53960.atl.anagram.server.model.converter.WordDtoConverter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The anagram game.
 *
 */
public class Model implements Facade {

    
    
    private WordModel wordModel= new WordModel(); 
    
    
    /**
     * All the words to play.
     */
    private Words words;
    /**
     * The current word in play.
     */
    private Word currentWord;
    /**
     * A converter from WordDto to Word. This converter is used to transform the
     * data read from a file to a real object (with some methods).
     */
    private final WordDtoConverter wordDtoConverter;
    
    
    private GameState gameState; 

    /**
     * Constructs the <code> Model </code> of anagram game. The word converter
     * is ready.
     */
    public Model() {
        wordDtoConverter = new WordDtoConverter();
    }

    @Override
    public void initialize() throws ModelException {
        List<WordDto> wordDtos;
        try {
            wordDtos= wordModel.getWords();
        } catch (BusinessException ex) {
            throw new ModelException(ex.getMessage());
        }
        List<Word> wordList = wordDtoConverter.convert(wordDtos);
        words = new Words(wordList);
    }

    @Override
    public void start() throws ModelException {
        if (words == null || words.isEmpty()) {
            throw new ModelException("la liste de mots est vide");
        }
        words.shuffle();
    }

    @Override
    public String nextWord() throws ModelException {
        if (isOver()) {
            throw new ModelException("Le jeu est terminé");
        }
        if (currentWord != null && currentWord.isUnread()) {
            throw new ModelException("Un mot est encore en cours de jeu.");
        }
        currentWord = words.next();
        try {
            currentWord.setRead();
        } catch (g53960.atl.anagram.common.exception.ModelException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return currentWord.getScramble();
    }

    @Override
    public boolean propose(String proposal) throws ModelException {
        System.out.println(currentWord);
        if (currentWord == null) {
            throw new ModelException("Aucun mot en jeu");
        }
        boolean isAnagram= false;
        try {
            isAnagram = currentWord.isAnagram(proposal);
            if (isAnagram) {
                currentWord.solved();   
            }
            currentWord.addProposal();
            return isAnagram;
        } catch (g53960.atl.anagram.common.exception.ModelException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return isAnagram;
    }

    @Override
    public String pass() throws ModelException {
        if (currentWord == null) {
            throw new ModelException("Aucun mot en jeu");
        }
        String answer = currentWord.getText();
        try {
            currentWord.unsolved();
        } catch (g53960.atl.anagram.common.exception.ModelException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answer;
    }

    @Override
    public boolean isOver() throws ModelException {
        if (words == null || words.isEmpty()) {
            throw new ModelException("la liste de mots est vide");
        }
        return !words.hasNext();
    }

    @Override
    public boolean canAskNextWord() {
        return currentWord == null
                || currentWord.isSolved()
                || currentWord.isUnSolved();
    }

    @Override
    public int getNbWords() throws ModelException {
        if (words == null || words.isEmpty()) {
            throw new ModelException("la liste de mots est vide");
        }
        return words.size();
    }

    @Override
    public int getNbRemainingWords() throws ModelException {
        if (words == null || words.isEmpty()) {
            throw new ModelException("la liste de mots est vide");
        }
        return words.getNbRemainingWords();
    }

    @Override
    public int getNbSolvedWords() throws ModelException {
        if (words == null || words.isEmpty()) {
            throw new ModelException("la liste de mots est vide");
        }
        return words.getNbSolvedWords();
    }

    @Override
    public int getNbUnsolvedWords() throws ModelException {
        if (words == null || words.isEmpty()) {
            throw new ModelException("la liste de mots est vide");
        }
        return words.getNbUnsolvedWords();
    }

    @Override
    public int getNbProposal() throws ModelException {
        if (currentWord == null) {
            throw new ModelException("Aucun mot en jeu");
        }
        return currentWord.getNbProposal();
    }
    
    public Word getWord(){
        return this.currentWord;
    }
    
    //g53960
    public GameState getGameState() throws ModelException{
        
        this.gameState = new GameState(this.getNbRemainingWords(),this.getNbSolvedWords(), this.getNbUnsolvedWords(), this.getNbProposal(),
        currentWord, this.canAskNextWord(), this.getNbWords(), currentWord.getScramble(), this.isOver());
        
        return this.gameState;
    }

}
