package ir.shahinsorkh.texthighlighter.service.utilities;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class NormalizerUtil {

    public static final Map<String, String> charMap = new HashMap<>();

    @PostConstruct
    private void init(){
        charMap.put("\u064a", "\u06cc"); //yeh
        charMap.put("\u0649", "\u06cc"); //yeh
        charMap.put("\u0643", "\u06a9"); //keh
//        charMap.put("\xa0", ""); //non-break space
        charMap.put("\u0651", ""); //tashdid
        charMap.put("\u0652", ""); //sukon
        charMap.put("\u064b", ""); //fathatan
        charMap.put("\u064f", ""); //zame
        charMap.put("\u064e", ""); //fathe
        charMap.put("\u0650", ""); //kasre
        charMap.put("\u0640", ""); //keshide
        charMap.put("\u0623", "\u0627"); //Alef hamza to alef
        charMap.put("\u200c", " "); //half spaces
        charMap.put("\u200e", " "); //half spaces
        charMap.put("\u200f", " "); //half spaces
        charMap.put("\r", " "); //tab
    }

    public static String convertCharToNormal(String str){
        StringBuilder normalString = new StringBuilder(str);
        for(int i = 0 ; i < normalString.length() ; i++){
            int finalI = i;
            charMap.entrySet().stream().forEach(c -> {
                if (c.getKey().charAt(0) == normalString.charAt(finalI)){
                    normalString.setCharAt(finalI, c.getValue().charAt(0));
                }
            });
        }
        return normalString.toString();
    }

    public static String removeDuplicateChar(String string){
        StringBuilder normalString = new StringBuilder(string);
        for(int i = 1 ; i < normalString.length() ; i++) {
            if(normalString.charAt(i) == normalString.charAt(i-1)){
                normalString.deleteCharAt(i);
            }
        }
            return normalString.toString();
    }

    /**
     * this method convert numbers in english form to persian one
     * @Param String (english numbers)
     * @return String (persian numbers)
     */
    public static String convertEnDigitToFaDigit(String value) {
        return (value == null) ? value : value.replaceAll("0", "\u06F0")
                .replaceAll("1", "\u06F1").replaceAll("2", "\u06F2").replaceAll("3", "\u06F3")
                .replaceAll("4", "\u06F4").replaceAll("5", "\u06F5").replaceAll("6", "\u06F6")
                .replaceAll("7", "\u06F7").replaceAll("8", "\u06F8").replaceAll("9", "\u06F9");
    }

    /**
     * this method convert punctuation in english form to persian one
     * @Param String (english punctuation)
     * @return String (persian punctuation)
     */
        public static String convertEnPunToFaPun(String value) {
        return (value == null) ? value : value.replaceAll(";", "\u06F0")
                .replaceAll("\\?", "\u061F") //
                .replaceAll("-", "\u06D4") //
                .replaceAll("_", "\u06D4") //
                .replaceAll("%", "\u066A") //
                .replaceAll("\\|", " ") //
                .replaceAll("\\.", "\u06F0") //
                .replaceAll(",", "\u060C") //
                .replaceAll(";", "\u061B"); //
    }

}
