package common.validators;

import common.utils.StringUtils;

public class Validator {
	private Validator() {}
	
	public static boolean isEmail(String email) {
        return StringUtils.isNotEmpty(email) && email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    public static boolean isDni(String dni) {
        return StringUtils.isNotEmpty(dni) && dni.matches("^\\d{8}[A-Z]$");
    }

    public static boolean isPhone(String phone) {
        return StringUtils.isNotEmpty(phone) && phone.matches("^\\+?\\d{9,15}$");
    }
    
}
