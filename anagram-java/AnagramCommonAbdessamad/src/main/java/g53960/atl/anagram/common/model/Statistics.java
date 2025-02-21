
package g53960.atl.anagram.common.model;

import java.io.Serializable;

/**
 *
 * @author Samad g53960
 */
public class Statistics implements Serializable{
    
    private final int nbRemainingsWords;
    
    private final int nbSolvedWords;
    
    private final int nbUnsolvedWords;
    
    private final int nbProposal;
    
    private final Word wordShuffle;
    
    private final boolean currentIsSolved;
    
    private final int allWordSize;
    
    private final String answer;
    
    private final boolean gameIsFinish;
    
    
    public Statistics(int nbRemainingsWords, int nbSolvedWords,int nbUnsolvedWords, int nbProposal, Word wordShuffle, boolean currentIsSolved,
                      int allWordSize,String answer, boolean gameIsFinish){
        
        this.nbRemainingsWords = nbRemainingsWords;
        this.nbSolvedWords = nbSolvedWords;
        this.nbUnsolvedWords= nbUnsolvedWords;
        this.nbProposal= nbProposal;
        this.wordShuffle= wordShuffle;
        this.currentIsSolved= currentIsSolved;
        this.allWordSize= allWordSize;
        this.answer= answer;
        this.gameIsFinish= gameIsFinish;
    }

    public int getNbRemainingsWords() {
        return nbRemainingsWords;
    }

    public int getNbSolvedWords() {
        return nbSolvedWords;
    }

    public int getNbUnsolvedWords() {
        return nbUnsolvedWords;
    }

    public int getNbProposal() {
        return nbProposal;
    }

    public Word getWordShuffle() {
        return wordShuffle;
    }

    public boolean isCurrentIsSolved() {
        return currentIsSolved;
    }

    public int getAllWordSize() {
        return allWordSize;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isGameIsFinish() {
        return gameIsFinish;
    }
    
    
    
    
    
    
    
}
