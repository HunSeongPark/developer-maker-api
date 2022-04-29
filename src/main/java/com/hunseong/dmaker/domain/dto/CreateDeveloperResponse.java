package com.hunseong.dmaker.domain.dto;

import com.hunseong.dmaker.domain.entity.Developer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Hunseong on 2022/04/28
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateDeveloperResponse {

    private Long id;
    private String name;

    public CreateDeveloperResponse(Developer developer) {
        this.id = developer.getId();
        this.name = developer.getName();
    }
}
