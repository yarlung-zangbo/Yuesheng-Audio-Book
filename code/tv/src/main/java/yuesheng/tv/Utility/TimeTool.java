package yuesheng.tv.Utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeTool {
    static public String now(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return df.format(new Date());
    }
}
