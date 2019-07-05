package yuesheng.tv;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;


public class WordsParser {
    public static String[]  LineToWords(String line) {
        String Terms = ToAnalysis.parse(line).toStringWithOutNature();
        String[] words = Terms.split(",");
        return words;
    }

    public static void main(String[] args) {
        WordsParser.LineToWords("昨天下大雨，我晚上失眠了，感觉不太好。");
    }
}
