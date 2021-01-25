package ir.shahinsorkh.texthighlighter.service.utilities;

import org.springframework.stereotype.Component;

@Component
public class NormalizerUtil {
    public static String convertEnDigitToFaDigit(String value) {
        return (value == null) ? value : value.replaceAll("0", "\u06F0")
                .replaceAll("1", "\u06F1").replaceAll("2", "\u06F2").replaceAll("3", "\u06F3")
                .replaceAll("4", "\u06F4").replaceAll("5", "\u06F5").replaceAll("6", "\u06F6")
                .replaceAll("7", "\u06F7").replaceAll("8", "\u06F8").replaceAll("9", "\u06F9");
    }


}
