package ir.shahinsorkh.texthighlighter.web.rest;

import ir.shahinsorkh.texthighlighter.service.dto.HighLightRequestDTO;
import ir.shahinsorkh.texthighlighter.service.dto.HighLightResponseDTO;
import ir.shahinsorkh.texthighlighter.service.impl.HighLightServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api")
public class HighLightResource {

    private final Logger log = LoggerFactory.getLogger(HighLightResource.class);

    private static final String ENTITY_NAME = "textHighLighter";

    private final HighLightServiceImpl highLightService;

    public HighLightResource(HighLightServiceImpl highLightService) {
        this.highLightService = highLightService;
    }


    @PostMapping(path = "/term-high-lighter", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HighLightResponseDTO> termHighLighter(@Valid @RequestBody HighLightRequestDTO highLightDTO) {
        log.debug("REST request to highlight a term: [{}]", highLightDTO);
        Status responseStatus = Status.OK;
        if( highLightDTO.getTerm() == null || highLightDTO.getTerm().isEmpty()
            ||  highLightDTO.getSource() == null || highLightDTO.getSource().isEmpty()) {
            responseStatus = Status.BAD_REQUEST;
            return ResponseEntity.status(responseStatus.getStatusCode()).body(null);
        }
        HighLightResponseDTO responseDTO = highLightService.findSingleTerm(highLightDTO);
        if (responseDTO.getWords().size() == 0)
            responseStatus = Status.NOT_FOUND;
        return ResponseEntity.status(responseStatus.getStatusCode()).body(responseDTO);
    }

    @PostMapping(value = "/regex-high-lighter", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HighLightResponseDTO> regexHighLighter(@Valid @RequestBody HighLightRequestDTO highLightDTO) {
        log.debug("REST request to highlight by patterns: [{}]", highLightDTO);
        Status responseStatus = Status.OK;
        if( highLightDTO.getSource() == null || highLightDTO.getSource().isEmpty()) {
            responseStatus = Status.BAD_REQUEST;
            return ResponseEntity.status(responseStatus.getStatusCode()).body(null);
        }
        AtomicReference<Boolean> flag = new AtomicReference<>(false);
        HighLightResponseDTO responseDTO = highLightService.findByRegEx(highLightDTO);
        responseDTO.getWordsByRegex().entrySet().stream().forEach(w -> {
            if(w.getValue().size() != 0)
                flag.set(true);
        });
        if(!flag.get()){
            responseStatus = Status.NOT_FOUND;
        }
        return ResponseEntity.status(responseStatus.getStatusCode()).body(responseDTO);
    }
}
