package ir.shahinsorkh.texthighlighter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableWebMvc
public class TextHighlighterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TextHighlighterApplication.class, args);
    }
//    @Bean
//    public Docket swaggerConfiguration(){
//        return new Docket(DocumentationType.SWAGGER_2).select()
//                .paths(PathSelectors.ant("/api/v1/**"))
//                .apis(RequestHandlerSelectors.basePackage("ir.shahinsorkh.texthighlighter"))
//                .build();
//    }
}
