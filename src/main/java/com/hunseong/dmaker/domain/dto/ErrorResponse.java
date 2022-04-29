package com.hunseong.dmaker.domain.dto;

import com.hunseong.dmaker.exception.DeveloperErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Hunseong on 2022/04/29
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class ErrorResponse {

    private DeveloperErrorCode code;
    private String message;
}
