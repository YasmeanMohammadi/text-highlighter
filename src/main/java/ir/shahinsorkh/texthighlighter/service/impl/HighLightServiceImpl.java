package ir.shahinsorkh.texthighlighter.service.impl;

import ir.shahinsorkh.texthighlighter.service.HighLightService;
import ir.shahinsorkh.texthighlighter.service.dto.HighLightRequestDTO;
import ir.shahinsorkh.texthighlighter.service.dto.HighLightResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Override
    public HighLightResponseDTO findByRegEx(HighLightRequestDTO highLightRequestDTO) {
        log.debug("request to find regular expression : [{}] ", highLightRequestDTO.getPattern());
        HighLightResponseDTO highLightResponseDTO = new HighLightResponseDTO();
        String [] strings = highLightRequestDTO.getSource().split(" ");
        List<String> result = Arrays.stream(strings).filter(s -> Pattern.compile(highLightRequestDTO.getPattern()).matcher(s).find()).collect(Collectors.toList());
        highLightResponseDTO.setWords(result);
        return highLightResponseDTO;
    }


}
