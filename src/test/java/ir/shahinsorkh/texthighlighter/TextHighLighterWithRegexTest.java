package ir.shahinsorkh.texthighlighter;

import ir.shahinsorkh.texthighlighter.domain.Pattern;
import ir.shahinsorkh.texthighlighter.domain.enumoration.PatternType;
import ir.shahinsorkh.texthighlighter.repository.PatternRepository;
import ir.shahinsorkh.texthighlighter.service.dto.HighLightRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = { TextHighlighterApplication.class })
@AutoConfigureMockMvc
public class TextHighLighterWithRegexTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    PatternRepository patternRepository;
    @BeforeEach
    public void deleteData() {
        patternRepository.deleteAll();
    }
    @Test
    public void findRegex() throws Exception {
        HighLightRequestDTO highLightRequestDTO = new HighLightRequestDTO();
        highLightRequestDTO.setSource("خداوند تمام طبیعت‌گرایانی که دیگران را اخلاق‌گرا بودن دعوت میکنند و به ما گرایید را حفظ کند");
        Pattern khodaPattern = new Pattern();
        khodaPattern.setName(PatternType.KHODA_TERM);
        khodaPattern.setRegex("(\\u062E\\u062F\\u0627)");
        Pattern geraPattern = new Pattern();
        geraPattern.setName(PatternType.GERA_TERM);
        geraPattern.setRegex("(\\u06AF\\u0631\\u0627)");
        mockMvc.perform(post("/api/patterns-log")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(geraPattern)))
                .andExpect(status().isOk());
        mockMvc.perform(post("/api/patterns-log")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(khodaPattern)))
                .andExpect(status().isOk());
        mockMvc.perform(post("/api/regex-high-lighter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(highLightRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"wordsByRegex\": {\n" +
                        "        \"GERA_TERM\": [\n" +
                        "            \"طبعت گرایانی\",\n" +
                        "            \"دیگران\",\n" +
                        "            \"خلاق گرا\",\n" +
                        "            \"گرایید\"\n" +
                        "        ],\n" +
                        "        \"KHODA_TERM\": [\n" +
                        "            \"خداوند\"\n" +
                        "        ]\n" +
                        "    }\n" +
                        "}"));
    }

    @Test
    public void badRequestFindRegex() throws Exception {
        HighLightRequestDTO highLightRequestDTO = new HighLightRequestDTO();
        highLightRequestDTO.setSource("");
        mockMvc.perform(post("/api/regex-high-lighter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(highLightRequestDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void notFoundFindRegex() throws Exception {
        HighLightRequestDTO highLightRequestDTO = new HighLightRequestDTO();
        Pattern khodaPattern = new Pattern();
        khodaPattern.setName(PatternType.BI_KHODA_TERM);
        khodaPattern.setRegex("(\\u062E\\u0627)");
        Pattern geraPattern = new Pattern();
        geraPattern.setName(PatternType.GERA_TERM);
        geraPattern.setRegex("(\\u06AF\\u0627)");
        mockMvc.perform(post("/api/patterns-log")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(geraPattern)))
                .andExpect(status().isOk());
        mockMvc.perform(post("/api/patterns-log")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(khodaPattern)))
                .andExpect(status().isOk());
        highLightRequestDTO.setSource("اخلاق‌مداری امری ضروری است");
        mockMvc.perform(post("/api/regex-high-lighter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(highLightRequestDTO)))
                .andExpect(status().isNotFound());
    }
}
