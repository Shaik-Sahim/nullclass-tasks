package seleniumJava;

import java.time.LocalTime;

public class TimeCheck {
    public static boolean isValidTime() {
    	
        LocalTime now = LocalTime.now();
        
        return now.isAfter(LocalTime.of(18, 0)) && now.isBefore(LocalTime.of(19, 0));
        
    }
}

