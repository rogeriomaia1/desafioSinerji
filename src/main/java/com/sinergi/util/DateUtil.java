package com.sinergi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public final class DateUtil {
	
	
	public static String formatarData(Date data, String pattner) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattner);
		
		return formatter.format(data);
	}
	
	public static Date converterDataStringParaDate(String dataString, String formato) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        return sdf.parse(dataString);
    }
}
