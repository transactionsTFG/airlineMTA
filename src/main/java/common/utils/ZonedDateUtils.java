package common.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import common.consts.ValidatorMessage;
import common.dto.result.Result;

public class ZonedDateUtils {
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss VV");
	private ZonedDateUtils() {}
	
	public static Result<ZonedDateTime> getZonedTime(String time) {
		Result<ZonedDateTime> zoned = null;
		try {
			zoned = Result.success(ZonedDateTime.parse(time, FORMATTER));
		} catch (Exception e) {
			zoned = Result.failure(ValidatorMessage.BAD_TIME);
		}
		return zoned;
	}
	
	public static String parseString(ZonedDateTime time) {
		return time.format(FORMATTER);
	}
}
