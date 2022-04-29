package com.hunseong.dmaker.domain.dto;

import com.hunseong.dmaker.domain.code.StatusCode;
import com.hunseong.dmaker.domain.entity.Developer;
import com.hunseong.dmaker.domain.type.SkillType;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * Created by Hunseong on 2022/04/29
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeveloperUpdateRequestDto {

    @Size(min = 3, max = 50, message = "이름은 3 ~ 50자까지 가능합니다.")
    private String name;

    @Min(20)
    private Integer age;

    @Positive
    private Integer workYear;

    private SkillType skillType;

    private StatusCode status;
}
