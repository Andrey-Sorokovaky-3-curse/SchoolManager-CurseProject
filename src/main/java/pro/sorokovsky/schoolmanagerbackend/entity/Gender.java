package pro.sorokovsky.schoolmanagerbackend.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NonNull;

public enum Gender {
    MALE(false),
    FEMALE(true);

    private final boolean value;

    Gender(boolean value) {
        this.value = value;
    }

    @JsonValue
    public boolean getValue() {
        return value;
    }

    @JsonCreator
    public static @NonNull Gender fromValue(boolean value) {
        for (Gender gender : Gender.values()) {
            if (gender.value == value) {
                return gender;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in inventory");
    }
}
