package com.hunseong.dmaker.controller;

import com.hunseong.dmaker.domain.dto.CreateDeveloperRequest;
import com.hunseong.dmaker.domain.dto.CreateDeveloperResponse;
import com.hunseong.dmaker.domain.dto.DeveloperDetailDto;
import com.hunseong.dmaker.domain.dto.DeveloperUpdateRequestDto;
import com.hunseong.dmaker.service.DeveloperService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Hunseong on 2022/04/28
 */
@RequiredArgsConstructor
@RestController
public class DeveloperController {

    private final DeveloperService developerService;

    @PostMapping("/create-developer")
    public CreateDeveloperResponse createDeveloper(@Valid @RequestBody CreateDeveloperRequest request) {
        return developerService.createDeveloper(request);
    }

    @GetMapping("/developer/{id}")
    public DeveloperDetailDto developerDetail(@PathVariable Long id) {
        return developerService.findDeveloperDetail(id);
    }

    @PutMapping("/developer/{id}")
    public DeveloperDetailDto updateDeveloper(@PathVariable Long id,
                                              @Valid @RequestBody DeveloperUpdateRequestDto request) {
        return developerService.updateDeveloper(id, request);
    }
}
