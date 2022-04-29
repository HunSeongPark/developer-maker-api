package com.hunseong.dmaker.domain.dto;

import com.hunseong.dmaker.domain.entity.Developer;
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
public class CreateMemberResponse {

    private Long id;
    private String name;
}
