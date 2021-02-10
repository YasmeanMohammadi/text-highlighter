package ir.shahinsorkh.texthighlighter.service;

import ir.shahinsorkh.texthighlighter.service.dto.HighLightRequestDTO;
import ir.shahinsorkh.texthighlighter.service.dto.HighLightResponseDTO;

public interface HighLightService {
    HighLightResponseDTO findSingleTerm(HighLightRequestDTO highLightRequestDTO);

    HighLightResponseDTO findByRegEx(HighLightRequestDTO highLightRequestDTO);

}
