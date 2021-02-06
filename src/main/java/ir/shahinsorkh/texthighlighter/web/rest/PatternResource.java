package ir.shahinsorkh.texthighlighter.web.rest;

import ir.shahinsorkh.texthighlighter.domain.Pattern;
import ir.shahinsorkh.texthighlighter.repository.PatternRepository;
import ir.shahinsorkh.texthighlighter.service.PatternService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class PatternResource {

    private final Logger log = LoggerFactory.getLogger(PatternResource.class);

    private final PatternService patternService;


    public PatternResource(PatternService patternService ) {
        this.patternService = patternService;
    }

    /**
     * {@code POST  /pattern} : Create a new pattern.
     *
     * @param pattern the pattern to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bankChangeLogDTO, or with status {@code 400 (Bad Request)} if the bankChangeLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping(path = "/patterns-log", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pattern> createBankChangeLog(@Valid @RequestBody Pattern pattern) throws URISyntaxException {
        log.debug("REST request to save pattern : {}", pattern);
        Pattern result = patternService.save(pattern);
        return ResponseEntity.ok()
                .body(result);
    }


}
