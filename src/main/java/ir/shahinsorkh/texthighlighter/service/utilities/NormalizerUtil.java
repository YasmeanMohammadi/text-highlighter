package ir.shahinsorkh.texthighlighter.service.utilities;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class NormalizerUtil {

    public static final Map<String, String> charMap = new HashMap<>();
    public static final Map<String, String> wordMap = new HashMap<>();

    @PostConstruct
    private void wordInit(){
        wordMap.put("سسلام", "سلام");
        wordMap.put("سلاام", "سلام");
        wordMap.put("سلامم", "سلام");
        wordMap.put("سلاامم", "سلام");
        wordMap.put("سسلاامم", "سلام");
        wordMap.put("سسلامم", "سلام");
    }


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

    /**
     * this method remove arabic or redundant characters from string
     */
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

    /**
     * this method remove duplicated alphabets from string
     * @Param string (string with duplicate alphabets)
     * @return String (string without duplicate alphabets)
     */
    public static String removeDuplicateChar(String string){
        char[] chars = string.toCharArray();
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean repeatedChar;
        for (int i = 0; i < chars.length; i++) {
            repeatedChar = false;
            for (int j = i + 1; j < chars.length; j++) {
                for (int h = j +1 ; h < chars.length; h++) {
                    if (chars[i] == chars[j] && chars[j]== chars[h]) {
                        repeatedChar = true;
                        break;
                    }
                }
            }
            if (!repeatedChar) {
                sb.append(chars[i]);
            }
        }
        wordMap.entrySet().stream().forEach(w -> {
            if (w.getKey().equals(sb.toString())){
                result.add(w.getValue());
            }
        });
        if (result.size() != 0)
            return result.get(0);
        return sb.toString();
    }

    /**
     * this method convert numbers in english form to persian one
     * @Param String (english numbers)
     * @return String (persian numbers)
     */
    public static String convertEnDigitToFaDigit(String value) {
        return (value == null) ? value : value.replace("0", "\u06F0")
                .replace("1", "\u06F1").replace("2", "\u06F2").replace("3", "\u06F3")
                .replace("4", "\u06F4").replace("5", "\u06F5").replace("6", "\u06F6")
                .replace("7", "\u06F7").replace("8", "\u06F8").replace("9", "\u06F9");
    }

    /**
     * this method convert punctuation in english form to persian one
     * @Param String (english punctuation)
     * @return String (persian punctuation)
     */
        public static String convertEnPunToFaPun(String value) {
        return (value == null) ? value : value.replace(";", "\u06F0")
                .replace("\\?", "\u061F") //
                .replace("-", "\u06D4") //
                .replace("_", "\u06D4") //
                .replace("%", "\u066A") //
                .replace("\\|", " ") //
                .replace("\\.", "\u06F0") //
                .replace(",", "\u060C") //
                .replace(";", "\u061B"); //
    }

}
