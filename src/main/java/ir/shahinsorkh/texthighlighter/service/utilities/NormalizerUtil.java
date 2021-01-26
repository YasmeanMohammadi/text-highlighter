package ir.shahinsorkh.texthighlighter.service.utilities;

import org.apache.lucene.analysis.util.StemmerUtil;
import org.springframework.stereotype.Component;

@Component
public class NormalizerUtil {
    public static String convertEnDigitToFaDigit(String value) {
        return (value == null) ? value : value.replaceAll("0", "\u06F0")
                .replaceAll("1", "\u06F1").replaceAll("2", "\u06F2").replaceAll("3", "\u06F3")
                .replaceAll("4", "\u06F4").replaceAll("5", "\u06F5").replaceAll("6", "\u06F6")
                .replaceAll("7", "\u06F7").replaceAll("8", "\u06F8").replaceAll("9", "\u06F9");
    }

//    public static final char YEH = 'ي';
//    public static final char FARSI_YEH = 'ی';
//    public static final char YEH_BARREE = 'ے';
//    public static final char KEHEH = 'ک';
//    public static final char KAF = 'ك';
//    public static final char HAMZA_ABOVE = 'ٔ';
//    public static final char HEH_YEH = 'ۀ';
//    public static final char HEH_GOAL = 'ہ';
//    public static final char HEH = 'ه';
//
//    public int charNormalizer(String text) {
//        for(int i = 0; i < len; ++i) {
//            switch(s[i]) {
//                case 'ٔ':
//                    len = StemmerUtil.delete(s, i, len);
//                    --i;
//                    break;
//                case 'ک':
//                    s[i] = 1603;
//                    break;
//                case 'ۀ':
//                case 'ہ':
//                    s[i] = 1607;
//                    break;
//                case 'ی':
//                case 'ے':
//                    s[i] = 1610;
//            }
//        }
//
//        return len;
//    }

//    CHARMAP = str.maketrans(
//    {'\u064a': '\u06cc',  # yeh
//        '\u0649': '\u06cc',  # yeh
//        '\u0643': '\u06a9',  # keh
//         # '\xa0': ' ', # non-break space
//        '\u0651': None,  # tashdid
//        '\u0652': None,  # sukon (gerd)
//        '\u064b': None,  # fathatan
//        '\u064f': None,  # oh
//        '\u064e': None,  # fathe
//        '\u0650': None,  # kasre
//        '\u0640': None,  # kashida __
//        '\u0623': '\u0627',  # Alef hamza to alef
//         # half spaces'
//        '\u200c': ' ',
//            '\u200e': ' ',
//            '\u200f': ' ',
//         # bad spaces
//        '\xa0': ' ',
//            '\r': None,
//         # numbers
//        '۱': '1', '۲': '2', '۳': '3', '۴': '4', '۵': '5', '۶': '6',
//            '۷': '7', '۸': '8', '۹': '9', '۰': '0',
//         # punct
//        '،': ',', '!': '!', '؟': '?', '؛': ';',
//            '٪': '%', '٬': '\"', 'ـ': '_', '»': '\"',
//            '«': '\"', '”': '\"', '“': '\"', '‘': '\"',
//            '’': '\"', '|': None})

}
