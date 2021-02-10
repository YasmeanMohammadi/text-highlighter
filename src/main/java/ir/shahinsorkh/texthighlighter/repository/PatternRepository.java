package ir.shahinsorkh.texthighlighter.repository;

import ir.shahinsorkh.texthighlighter.domain.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Pattern entity.
 */

@Repository
public interface PatternRepository extends JpaRepository<Pattern, Long> {
}
