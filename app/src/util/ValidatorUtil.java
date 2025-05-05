package util;

public final class ValidatorUtil {

    private ValidatorUtil() {
        throw new UnsupportedOperationException("Cannot instantiate ValidatorUtil");
    }

    public static boolean isNotEmptyOrNull(String value) {
        return value != null && !value.trim().isEmpty();
    }

    public static boolean isValidEmail(String email) {
        return ValidatorUtil.isNotEmptyOrNull(email) && email.matches("^[\\w.-]+@[\\w-]+\\.[a-zA-Z]{2,}$");
    }

    public static boolean isValidName(String name) {
        return ValidatorUtil.isNotEmptyOrNull(name) && !name.matches(".*\\d.*") && name.matches("^[A-Za-záéíóúÁÉÍÓÚñÑ ]+$");
    }
}