package ir.shahinsorkh.texthighlighter.web.rest;

import ir.shahinsorkh.texthighlighter.service.dto.HighLightRequestDTO;
import ir.shahinsorkh.texthighlighter.service.dto.HighLightResponseDTO;
import ir.shahinsorkh.texthighlighter.service.impl.HighLightServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class HighLightResource {

    private final Logger log = LoggerFactory.getLogger(HighLightResource.class);

    private final HighLightServiceImpl highLightService;

    public HighLightResource(HighLightServiceImpl highLightService) {
        this.highLightService = highLightService;
    }


    @PostMapping("/term-high-lighter")
    public ResponseEntity<HighLightResponseDTO> TermHighLighter(@Valid @RequestBody HighLightRequestDTO highLightDTO) throws URISyntaxException {
        log.debug("REST request to highlight a term: [{}]", highLightDTO);
        HighLightResponseDTO responseDTO = highLightService.findSingleTerm(highLightDTO);
        return ResponseEntity.ok().body(responseDTO);
    }
}
