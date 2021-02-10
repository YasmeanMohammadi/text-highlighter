package ir.shahinsorkh.texthighlighter.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.shahinsorkh.texthighlighter.domain.enumoration.PatternType;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HighLightResponseDTO implements Serializable {

    private List<String> words;

    private Map<PatternType, List<String>> wordsByRegex;

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public Map<PatternType, List<String>> getWordsByRegex() {
        return wordsByRegex;
    }

    public void setWordsByRegex(Map<PatternType, List<String>> wordsByRegex) {
        this.wordsByRegex = wordsByRegex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HighLightResponseDTO that = (HighLightResponseDTO) o;
        return Objects.equals(getWords(), that.getWords()) &&
                Objects.equals(getWordsByRegex(), that.getWordsByRegex());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWords(), getWordsByRegex());
    }
}
