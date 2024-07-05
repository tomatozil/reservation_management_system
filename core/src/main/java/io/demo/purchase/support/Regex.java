package io.demo.purchase.support;

import java.util.regex.Pattern;

public enum Regex {
    EMAIL_REGEX("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"),
    PASSWORD_REGEX("^[A-Za-z\\d!@#$%^&*]{8,}$");

    private String pattern;

    Regex(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    public boolean isMatched(String input) {
        return !Pattern.matches(this.pattern, input);
    }
}
