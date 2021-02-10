package ir.shahinsorkh.texthighlighter;

import ir.shahinsorkh.texthighlighter.service.dto.HighLightRequestDTO;
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
public class TextHighLightingTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findSingleEnglishTerm() throws Exception {
        HighLightRequestDTO highLightRequestDTO = new HighLightRequestDTO();
        highLightRequestDTO.setTerm("hel");
        highLightRequestDTO.setSource("hello everyone, I hope it is still funny when you are burning in hel, see you at bebehel. bebehel");
        mockMvc.perform(post("/api/term-high-lighter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(highLightRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"words\": [\n" +
                        "        \"helo\",\n" +
                        "        \"hel،\",\n" +
                        "        \"bebehel۰\",\n" +
                        "        \"bebehel\"\n" +
                        "    ]\n" +
                        "}"));
    }

    @Test
    public void findSinglePersianTerm() throws Exception {
        HighLightRequestDTO highLightRequestDTO = new HighLightRequestDTO();
        highLightRequestDTO.setTerm("گرا");
        highLightRequestDTO.setSource("ما برای تمام طبیعت‌گرایانی که دیگران را اخلاق‌گرا بودن دعوت میکنند و به ما گراییدن احترام قایلیم");
        mockMvc.perform(post("/api/term-high-lighter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(highLightRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"words\": [\n" +
                        "        \"گرایانی\",\n" +
                        "        \"دیگران\",\n" +
                        "        \"گرا\",\n" +
                        "        \"گرایدن\"\n" +
                        "    ]\n" +
                        "}"));
    }

}
