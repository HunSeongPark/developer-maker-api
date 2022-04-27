package com.hunseong.dmaker.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Hunseong on 2022/04/28
 */
@Getter
@AllArgsConstructor
public enum SkillLevel {

    NEW("신입"),
    JUNIOR("주니어"),
    JUNGNIOR("중니어"),
    SENIOR("시니어");

    private final String description;
}
