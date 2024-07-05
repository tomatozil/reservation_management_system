package io.demo.purchase.support;

public enum WorkoutType {
    WEIGHT("웨이트"),
    CARDIO("유산소");

    private String title;

    WorkoutType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
