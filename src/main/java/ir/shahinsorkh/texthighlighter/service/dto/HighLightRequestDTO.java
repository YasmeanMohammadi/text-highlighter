package ir.shahinsorkh.texthighlighter.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class HighLightRequestDTO implements Serializable {

    @NotNull
    private String source;

    private String term;

    private String pattern;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HighLightRequestDTO highLight1 = (HighLightRequestDTO) o;
        return Objects.equals(source, highLight1.source) &&
                Objects.equals(term, highLight1.term) &&
                Objects.equals(pattern, highLight1.pattern);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, term, pattern);
    }

    @Override
    public String toString() {
        return "Term{" +
                "source='" + source + '\'' +
                ", term='" + term + '\'' +
                ", pattern='" + pattern + '\'' +
                '}';
    }
}
