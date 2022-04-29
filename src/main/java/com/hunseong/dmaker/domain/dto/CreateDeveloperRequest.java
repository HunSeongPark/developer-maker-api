package com.hunseong.dmaker.domain.dto;

import com.hunseong.dmaker.domain.type.SkillLevel;
import com.hunseong.dmaker.domain.type.SkillType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * Created by Hunseong on 2022/04/28
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateDeveloperRequest {

    @NotNull
    @Size(min = 3, max = 50, message = "이름은 3 ~ 50자까지 가능합니다.")
    private String name;

    @NotNull
    @Min(20)
    private Integer age;

    @NotNull
    @Positive
    private Integer workYear;

    @NotNull
    private SkillType skillType;

    @NotNull
    private SkillLevel skillLevel;

}
