package ir.shahinsorkh.texthighlighter.service.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class HighLightResponseDTO implements Serializable {

    private List<String> words;

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HighLightResponseDTO that = (HighLightResponseDTO) o;
        return Objects.equals(words, that.words);
    }

    @Override
    public int hashCode() {
        return Objects.hash(words);
    }

    @Override
    public String toString() {
        return "HighLightResponseDTO{" +
                "words=" + words +
                '}';
    }
}
