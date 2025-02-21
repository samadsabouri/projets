package g53960.atl.anagram.common.model;

import java.io.Serializable;

/**
 * 
 * @author Samad g53960
 */
public class GameState implements Serializable {
    
        private final Statistics stats ; 
        
        public GameState(int nbRemainingsWords, int nbSolvedWords,int nbUnsolvedWords, int nbProposal, Word wordShuffle, boolean currentIsSolved,
                      int allWordSize,String answer, boolean gameIsFinish){
            
            stats= new Statistics(nbRemainingsWords, nbSolvedWords, nbUnsolvedWords, nbProposal, wordShuffle, 
                    currentIsSolved, allWordSize, answer, gameIsFinish);
        }
        
        
    public int getNbRemainingsWords() {
        return stats.getNbRemainingsWords();
    }

    public int getNbSolvedWords() {
        return stats.getNbSolvedWords();
    }

    public int getNbUnsolvedWords() {
        return stats.getNbUnsolvedWords();
    }

    public int getNbProposal() {
        return stats.getNbProposal();
    }

    public Word getWordShuffle() {
        return stats.getWordShuffle();
    }

    public boolean isCurrentIsSolved() {
        return stats.isCurrentIsSolved();
    }

    public int getAllWordSize() {
        return stats.getAllWordSize();
    }

    public String getAnswer() {
        return stats.getAnswer();
    }

    public boolean isGameIsFinish() {
        return stats.isGameIsFinish();
    }
    
    public String toString(){
        return "Il reste " + this.getNbRemainingsWords() + " mot(s) à deviner sur les " + this.getAllWordSize() + " mots disponibles"
                + "Vous avez trouvé " + this.getNbSolvedWords() + " mot(s) et échoué sur " + this.getNbUnsolvedWords() + " mot(s) ";
    }        
}
