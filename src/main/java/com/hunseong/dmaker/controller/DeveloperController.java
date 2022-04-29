package com.hunseong.dmaker.controller;

import com.hunseong.dmaker.domain.dto.CreateMemberRequest;
import com.hunseong.dmaker.domain.dto.CreateMemberResponse;
import com.hunseong.dmaker.service.DeveloperService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by Hunseong on 2022/04/28
 */
@RequiredArgsConstructor
@RestController
public class DeveloperController {

    private final DeveloperService developerService;

    @PostMapping("/create-developer")
    public CreateMemberResponse createDeveloper(@Valid @RequestBody CreateMemberRequest request) {
        return developerService.createDeveloper(request);
    }
}
