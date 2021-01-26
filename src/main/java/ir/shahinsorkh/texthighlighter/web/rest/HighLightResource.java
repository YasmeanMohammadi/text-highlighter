package ir.shahinsorkh.texthighlighter.web.rest;

import ir.shahinsorkh.texthighlighter.service.dto.HighLightRequestDTO;
import ir.shahinsorkh.texthighlighter.service.dto.HighLightResponseDTO;
import ir.shahinsorkh.texthighlighter.service.impl.HighLightServiceImpl;
import ir.shahinsorkh.texthighlighter.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class HighLightResource {

    private final Logger log = LoggerFactory.getLogger(HighLightResource.class);

    private static final String ENTITY_NAME = "textHighLighter";

    private final HighLightServiceImpl highLightService;

    public HighLightResource(HighLightServiceImpl highLightService) {
        this.highLightService = highLightService;
    }

    private Status responseStatus = Status.OK;

    @PostMapping("/term-high-lighter")
    public ResponseEntity<HighLightResponseDTO> termHighLighter(@Valid @RequestBody HighLightRequestDTO highLightDTO) throws URISyntaxException {
        log.debug("REST request to highlight a term: [{}]", highLightDTO);
        if( highLightDTO.getTerm() == null || highLightDTO.getTerm().isEmpty()
            ||  highLightDTO.getSource() == null || highLightDTO.getSource().isEmpty())
            responseStatus = Status.BAD_REQUEST;
        if (responseStatus.equals(Status.BAD_REQUEST)) {
            throw Problem.builder()
                    .withStatus(Status.BAD_REQUEST)
                    .withTitle("TermIsNullOrEmpty")
                    .with("error message " , " term can not be null or empty. " + "status: "+ responseStatus.getStatusCode())
                    .build();
        }
        HighLightResponseDTO responseDTO = highLightService.findSingleTerm(highLightDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/regex-high-lighter")
    public ResponseEntity<HighLightResponseDTO> regexHighLighter(@Valid @RequestBody HighLightRequestDTO highLightDTO) throws URISyntaxException {
        log.debug("REST request to highlight by patterns: [{}]", highLightDTO);
        if( highLightDTO.getSource() == null || highLightDTO.getSource().isEmpty())
            responseStatus = Status.BAD_REQUEST;
        if (responseStatus.equals(Status.BAD_REQUEST)) {
            throw Problem.builder()
                    .withStatus(Status.BAD_REQUEST)
                    .withTitle("PatternIsNullOrEmpty")
                    .with("error message " , " pattern can not be null or empty. " + "status: "+ responseStatus.getStatusCode())
                    .build();
        }
        HighLightResponseDTO responseDTO = highLightService.findByRegEx(highLightDTO);
        return ResponseEntity.status(responseStatus.getStatusCode()).body(responseDTO);
    }
}
