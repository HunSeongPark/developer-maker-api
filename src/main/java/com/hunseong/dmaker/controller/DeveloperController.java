package com.hunseong.dmaker.controller;

import com.hunseong.dmaker.domain.code.StatusCode;
import com.hunseong.dmaker.domain.dto.*;
import com.hunseong.dmaker.domain.entity.Developer;
import com.hunseong.dmaker.service.DeveloperService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/developers")
    public DeveloperListDto getDevelopers(@Nullable @RequestParam StatusCode status) {
        return developerService.getAllDevelopers(status);
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

    @DeleteMapping("/developer/{id}")
    public String deleteDeveloper(@PathVariable Long id) {
        developerService.deleteDeveloper(id);
        return "SUCCESS";
    }
}
