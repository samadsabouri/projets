package g53960.atl.anagram.common.model;

import g53960.atl.anagram.common.exception.ModelException;
import static g53960.atl.anagram.common.model.Status.READ;
import static g53960.atl.anagram.common.model.Status.SOLVED;
import static g53960.atl.anagram.common.model.Status.UNREAD;
import static g53960.atl.anagram.common.model.Status.UNSOLVED;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 
 * @author Samad g53960 &&  jlc
 */
public class Word implements Serializable {
    /**
     * The text of the word.
     */
    private final String text;

    /**
     * Game's status of the word. A word starts into an unread status and evolve
     * to a solved or an unsolved status.
     */
    private Status status;

    /**
     * Amount of user's proposal.
     */
    private int nbProposal;

    /**
     * Constructs a <code> Word </code> of anagram game. Th status is set to its
     * default value UNREAD.
     *
     * @param text the text of the word.
     */
    public Word(String text) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Aucun texte pour le mot");
        }
        this.text = text;
        this.status = UNREAD;
        this.nbProposal = 0;
    }

    /**
     * Returns a scramble version of the word. The letters are mixed. E.G : the
     * word HELLO can be return as LOLHE.
     *
     * @return a scramble version of the word.
     */
    public String getScramble() {
        List<String> chars = Arrays.asList(text.split(""));
        Collections.shuffle(chars);
        StringBuilder sb = new StringBuilder();
        chars.forEach(c -> sb.append(c));
        return sb.toString();
    }

    /**
     * Returns true if the input string is an anagram of the word and false
     * otherwise. The test is not case sensitive.
     *
     * @param other proposal of the user.
     * @return true if the input string is an anagram of the word and false
     * otherwise.
     * @throws g53960.atl.anagram.server.exception.ModelException if the proposal is null or
     * empty.
     */
    public boolean isAnagram(String other) throws ModelException {
        if (other == null || other.isEmpty()) {
            throw new ModelException("Aucun texte a comparer");
        }
        return other.equalsIgnoreCase(text);
    }

    /**
     * Returns the text.
     *
     * @return the text.
     */
    public String getText() {
        return text;
    }

    /**
     * Turns the status from UNREAD to READ.
     *
     * @throws ModelException if the current status is not UNREAD.
     */
    public void setRead() throws ModelException {
        if (status != UNREAD) {
            throw new ModelException("Le status ne peut pas etre changé en READ");
        }
        status = READ;
    }

    /**
     * Turns the status from READ to SOLVED.
     *
     * @throws ModelException if the current status is not READ.
     */
    public void solved() throws ModelException {
        if (status != READ) {
            throw new ModelException("Le status ne peut pas etre changé en SOLVED");
        }
        status = SOLVED;
    }

    /**
     * Turns the status from READ to UNSOLVED.
     *
     * @throws ModelException if the current status is not READ.
     */
    public void unsolved() throws ModelException {
        if (status != READ) {
            throw new ModelException("Le status ne peut pas etre changé en UNSOLVED");
        }
        status = UNSOLVED;
    }

    /**
     * Returns true if the status is UNREAD and false otherwise.
     *
     * @return true if the status is UNREAD and false otherwise.
     */
    public boolean isUnread() {
        return status == UNREAD;
    }

    /**
     * Returns true if the status is SOLVED and false otherwise.
     *
     * @return true if the status is SOLVED and false otherwise.
     */
    public boolean isSolved() {
        return status == SOLVED;
    }

    /**
     * Returns true if the status is UNSOLVED and false otherwise.
     *
     * @return true if the status is UNSOLVED and false otherwise.
     */
    public boolean isUnSolved() {
        return status == UNSOLVED;
    }

    /**
     * Adds a proposal to the counter.
     *
     */
    public void addProposal() {
        nbProposal++;
    }

    /**
     * Returns the amount of user's proposal.
     *
     * @return the amount of user's proposal.
     */
    public int getNbProposal() {
        return nbProposal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.text);
        hash = 37 * hash + Objects.hashCode(this.status);
        hash = 37 * hash + this.nbProposal;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Word other = (Word) obj;
        if (this.nbProposal != other.nbProposal) {
            return false;
        }
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Word " + text + " , " + nbProposal + " , " + status;
    }
}
