package ir.shahinsorkh.texthighlighter.service.impl;

import ir.shahinsorkh.texthighlighter.domain.enumoration.PatternType;
import ir.shahinsorkh.texthighlighter.repository.PatternRepository;
import ir.shahinsorkh.texthighlighter.service.HighLightService;
import ir.shahinsorkh.texthighlighter.service.dto.HighLightRequestDTO;
import ir.shahinsorkh.texthighlighter.service.dto.HighLightResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class HighLightServiceImpl implements HighLightService {

    @Value("${splitString.regex}")
    private String splitRegex;

    private final Logger log = LoggerFactory.getLogger(HighLightServiceImpl.class);

    private final PatternRepository patternRepository;

    public HighLightServiceImpl(PatternRepository patternRepository) {
        this.patternRepository = patternRepository;
    }

    @Override
    public HighLightResponseDTO findSingleTerm(HighLightRequestDTO highLightRequestDTO) {
        log.debug("request to find term : [{}] ", highLightRequestDTO.getTerm());
        HighLightResponseDTO highLightResponseDTO = new HighLightResponseDTO();
        String [] source = highLightRequestDTO.getSource().split(splitRegex);
        List<String> result = Arrays.stream(source).filter(s -> s.contains(highLightRequestDTO.getTerm()))
                .collect(Collectors.toList());
        highLightResponseDTO.setWords(result);
        log.debug("response to find term [{}] is [{}] ", highLightRequestDTO.getTerm(), highLightResponseDTO);
        return highLightResponseDTO;
    }

    @Override
    public HighLightResponseDTO findByRegEx(HighLightRequestDTO highLightRequestDTO) {
        log.debug("request to find by regular expressions");
        HighLightResponseDTO highLightResponseDTO = new HighLightResponseDTO();
        Map<PatternType, List<String>> wordsByRegex = new HashMap<>();
        String [] strings = highLightRequestDTO.getSource().split(splitRegex);
        List<ir.shahinsorkh.texthighlighter.domain.Pattern> patterns = patternRepository.findAll();
        patterns.parallelStream().forEach(p -> {
            List<String> result = Arrays.stream(strings).filter(s -> Pattern.compile(p.getRegex()).matcher(s)
                    .find()).collect(Collectors.toList());
            wordsByRegex.put(p.getName(), result);
        });
        highLightResponseDTO.setWordsByRegex(wordsByRegex);
        log.debug("response to find by regular expression is [{}] ", highLightResponseDTO);
        return highLightResponseDTO;
    }

}
