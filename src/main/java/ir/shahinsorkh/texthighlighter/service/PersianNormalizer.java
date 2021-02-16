package ir.shahinsorkh.texthighlighter.service;

public interface PersianNormalizer {
    /**
     * normalize persian string
     *
     * @param s as entry string.
     * @return normalized string.
     */
    String normalize(String s);
}
