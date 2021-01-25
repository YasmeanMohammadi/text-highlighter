package ir.shahinsorkh.texthighlighter.service;

import ir.shahinsorkh.texthighlighter.domain.Pattern;

import java.util.List;
import java.util.Optional;

public interface PatternService {
    /**
     * Save a bankChangeLog.
     *
     * @param pattern the entity to save.
     * @return the persisted entity.
     */
    Pattern save(Pattern pattern);

    /**
     * Get all the bankChangeLogs.
     *
     * @return the list of entities.
     */
    List<Pattern> findAll();


    /**
     * Get the "id" pattern.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Pattern> findOne(Long id);

    /**
     * Delete the "id" pattern.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
