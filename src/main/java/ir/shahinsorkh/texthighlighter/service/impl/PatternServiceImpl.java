package ir.shahinsorkh.texthighlighter.service.impl;

import ir.shahinsorkh.texthighlighter.domain.Pattern;
import ir.shahinsorkh.texthighlighter.repository.PatternRepository;
import ir.shahinsorkh.texthighlighter.service.PatternService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PatternServiceImpl implements PatternService {

    private final Logger log = LoggerFactory.getLogger(HighLightServiceImpl.class);

    private final PatternRepository patternRepository;


    public PatternServiceImpl(PatternRepository patternRepository) {
        this.patternRepository = patternRepository;
    }

    @Override
    public Pattern save(Pattern pattern) {
        log.debug("Request to save Pattern : {}", pattern);
        return patternRepository.save(pattern);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pattern> findAll() {
        log.debug("Request to get all patterns");
        return patternRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pattern> findOne(Long id) {
        log.debug("Request to get pattern : {}", id);
        return patternRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete pattern : {}", id);
        patternRepository.deleteById(id);
    }



}
