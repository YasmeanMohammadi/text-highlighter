package ir.shahinsorkh.texthighlighter.service.impl;

import ir.shahinsorkh.texthighlighter.service.PersianNormalizer;
import ir.shahinsorkh.texthighlighter.service.utilities.NormalizerUtil;
import org.springframework.stereotype.Service;

@Service
public class PersianNormalizerImpl implements PersianNormalizer {

    @Override
    public String normalize(String s) {
        String normalTxt = NormalizerUtil.convertEnDigitToFaDigit(s);
        normalTxt = NormalizerUtil.convertEnPunToFaPun(normalTxt);
        normalTxt = NormalizerUtil.convertCharToNormal(normalTxt);
        normalTxt = NormalizerUtil.removeDuplicateChar(normalTxt);
        return normalTxt;
    }

}
