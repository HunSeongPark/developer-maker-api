package com.hunseong.dmaker.domain.dto;

import com.hunseong.dmaker.domain.code.StatusCode;
import com.hunseong.dmaker.domain.entity.Developer;
import com.hunseong.dmaker.domain.type.SkillLevel;
import com.hunseong.dmaker.domain.type.SkillType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Hunseong on 2022/04/29
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeveloperDetailDto {

    private String name;
    private int age;
    private SkillType skillType;
    private SkillLevel skillLevel;
    private StatusCode status;

    public DeveloperDetailDto(Developer developer) {
        this.name = developer.getName();
        this.age = developer.getAge();
        this.skillType = developer.getSkillType();
        this.skillLevel = developer.getSkillLevel();
        this.status = developer.getStatus();
    }
}
