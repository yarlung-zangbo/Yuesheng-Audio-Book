package yuesheng.tv.Utility;

public class TextConcatUtil {
    // traceback: How many bytes to be candidate for comparison.
    // overlap: How many bytes in both string need to be the same to let the function decide to execute concatenation
    public static String ConcatText(String finalResult, String appendage,int traceBack,int overlap) {
        int hindLength = appendage.length()<traceBack ? appendage.length():traceBack;
        int FRLength = finalResult.length();
        int foreLength = FRLength<traceBack ? FRLength:traceBack;
        if(finalResult=="")return appendage;
        for(int i = 0; i<hindLength;i++){
            char cH = appendage.charAt(i);
            for(int j = overlap;j<foreLength;j++){
                char cF = finalResult.charAt(FRLength-j-1);
                if(cF==cH) {
                    if(finalResult.substring(FRLength-j-1,FRLength-j-1+overlap).equals(appendage.substring(i,i+overlap)));
                        return finalResult.substring(0,FRLength-j-1)+appendage.substring(i);
                }
            }
        }
        return finalResult;
    }
}
