package com.musinsa.api.search.model;

public enum Message {
    READ_STUDENTS("학생 조회 성공"),
    NOT_FOUND_STUDENTS("학생 조회 실패");

    private final String label;

    Message(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}
