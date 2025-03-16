package com.taskmaster.domain;

import lombok.Getter;

@Getter
public enum TaskStatus {

    NOT_STARTED("Not started"),
    STARTED("Started"),
    COMPLETED("Completed");

    private final String displayName;

    TaskStatus(String displayName) {
        this.displayName = displayName;
    }

}
