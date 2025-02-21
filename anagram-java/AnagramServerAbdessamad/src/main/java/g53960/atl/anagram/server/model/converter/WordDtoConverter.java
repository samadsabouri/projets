package g53960.atl.anagram.server.model.converter;

import g53960.atl.anagram.common.dto.WordDto;
import g53960.atl.anagram.common.model.Word;

/**
 * Turns a WordDto object into a Word Object.
 *
 * @author jlc
 */
public class WordDtoConverter implements GenericConverter<WordDto, Word> {

    @Override
    public Word apply(WordDto input) {
        Word output = new Word(input.getText());
        return output;
    }

}
