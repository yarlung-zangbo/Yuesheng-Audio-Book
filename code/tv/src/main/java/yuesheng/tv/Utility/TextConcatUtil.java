package yuesheng.tv.Utility;

public class TextConcatUtil {
    public static String ConcatText(String finalResult, String appendage,int traceBack) {
        int hindLength = appendage.length()<traceBack ? appendage.length():traceBack;
        int FRLength = finalResult.length();
        int foreLength = FRLength<traceBack ? FRLength:traceBack;
        if(finalResult=="")return appendage;
        for(int i = 0; i<hindLength;i++){
            char cH = appendage.charAt(i);
            for(int j = 2;j<foreLength;j++){
                char cF = finalResult.charAt(FRLength-j-1);
                if(cF==cH) {
                    if(finalResult.substring(FRLength-j-1,FRLength-j+1).equals(appendage.substring(i,i+2)));
                        return finalResult.substring(0,FRLength-j-1)+appendage.substring(i);
                }
            }
        }
        return finalResult;
    }
}
