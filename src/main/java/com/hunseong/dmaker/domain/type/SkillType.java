package com.hunseong.dmaker.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Hunseong on 2022/04/28
 */
@Getter
@AllArgsConstructor
public enum SkillType {
    BACK_END("백엔드 개발자"),
    FRONT_END("프론트엔드 개발자"),
    FULL_STACK("풀스택 개발자");

    private final String description;
}
