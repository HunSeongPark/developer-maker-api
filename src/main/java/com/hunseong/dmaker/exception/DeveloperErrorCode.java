package com.hunseong.dmaker.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by Hunseong on 2022/04/29
 */
@Getter
@RequiredArgsConstructor
public enum DeveloperErrorCode {
    NO_DEVELOPER("해당되는 개발자가 없습니다."),
    DUPLICATED_NAME("이름이 중복되는 개발자가 있습니다."),
    LEVEL_EXPERIENCE_YEAR_NOT_MATCHED("개발자 레벨과 연차가 맞지 않습니다."),


    INTERNAL_SERVER_ERROR("서버에 오류가 발생했습니다."),
    INVALID_REQUEST("잘못된 요청입니다.")
    ;

    private final String message;
}
