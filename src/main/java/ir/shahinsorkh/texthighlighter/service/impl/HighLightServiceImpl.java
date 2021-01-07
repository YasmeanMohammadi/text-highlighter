package ir.shahinsorkh.texthighlighter.service.impl;

import ir.shahinsorkh.texthighlighter.service.HighLightService;
import ir.shahinsorkh.texthighlighter.service.dto.HighLightRequestDTO;
import ir.shahinsorkh.texthighlighter.service.dto.HighLightResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HighLightServiceImpl implements HighLightService {

    private final Logger log = LoggerFactory.getLogger(HighLightServiceImpl.class);

    @Override
    public HighLightResponseDTO findSingleTerm(HighLightRequestDTO highLightRequestDTO) {
        log.debug("request to find term : [{}] ", highLightRequestDTO.getTerm());
        HighLightResponseDTO highLightResponseDTO = new HighLightResponseDTO();
        String [] source = highLightRequestDTO.getSource().split(" ");
        List<String> result = Arrays.stream(source).filter(s -> s.contains(highLightRequestDTO.getTerm())).collect(Collectors.toList());
        highLightResponseDTO.setWords(result);
        return highLightResponseDTO;
    }
}
