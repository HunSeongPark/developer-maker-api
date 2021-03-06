package com.hunseong.dmaker.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by Hunseong on 2022/04/28
 */
@Getter
@RequiredArgsConstructor
public enum SkillLevel {

    NEW("신입"),
    JUNIOR("주니어"),
    JUNGNIOR("중니어"),
    SENIOR("시니어");

    private final String description;
}
