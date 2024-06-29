package io.demo.purchase.core.support;

import java.util.regex.Pattern;

public enum Constants {
    EMAIL_REGEX("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"),
    PASSWORD_REGEX("^[A-Za-z\\d!@#$%^&*]{8,}$");

    private String pattern;

    Constants(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    public boolean isMatched(String input) {
        return !Pattern.matches(this.pattern, input);
    }
}
