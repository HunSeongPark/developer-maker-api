package com.hunseong.dmaker.domain.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by Hunseong on 2022/04/28
 */
@Getter
@RequiredArgsConstructor
public enum StatusCode {
    EMPLOYED("고용"),
    LEAVE("휴직"),
    RETIRED("퇴직");

    private final String description;
}
