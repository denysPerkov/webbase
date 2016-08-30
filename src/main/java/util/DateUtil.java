package util;

public class DateUtil {

    static public boolean checkTimeout(long startTime, long finishTime, long range){
        return finishTime - startTime < range && finishTime - startTime > 0;
    }
}
