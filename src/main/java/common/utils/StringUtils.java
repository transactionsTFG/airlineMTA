package common.utils;

public class StringUtils {
	private StringUtils() {}
	
	public static boolean isEmpty(final String data) {
		return data == null || data.isEmpty();
	}
	
	public static boolean isNotEmpty(final String data) {
		return !StringUtils.isEmpty(data);
	}
}
