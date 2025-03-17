package common.utils;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import common.consts.ValidatorMessage;
import common.dto.result.Result;

public class ZonedDateUtils {
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss VV");
    private final static DateTimeFormatter FORMATTER_FILTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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

	public static Result<Void> isValidateDateFilter(final String dateString){
		try {
            LocalDate.parse(dateString, FORMATTER_FILTER);
            return Result.success(null); // La fecha es válida
        } catch (Exception e) {
            return Result.failure(ValidatorMessage.BAD_TIME);
        }
	}
	
	public static String parseString(ZonedDateTime time) {
		return time.format(FORMATTER);
	}

	public static ZonedDateTime now() {
		return ZonedDateTime.now();
	}
}
