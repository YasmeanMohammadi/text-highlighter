package ir.shahinsorkh.texthighlighter.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.shahinsorkh.texthighlighter.domain.enumoration.PatternType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "patterns")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Pattern {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false , unique = true)
    private PatternType name;

    @Column(name = "regex", nullable = false)
    private String regex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PatternType getName() {
        return name;
    }

    public void setName(PatternType name) {
        this.name = name;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pattern)) {
            return false;
        }
        return id != null && id.equals(((Pattern) o).id);    }

    @Override
    public String toString() {
        return "Pattern{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                ", regex='" + getRegex() + "'" +
                "}";
    }
    }