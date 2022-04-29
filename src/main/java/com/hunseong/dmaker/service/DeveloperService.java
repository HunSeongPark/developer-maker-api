package com.hunseong.dmaker.service;

import com.hunseong.dmaker.domain.code.StatusCode;
import com.hunseong.dmaker.domain.dto.*;
import com.hunseong.dmaker.domain.entity.Developer;
import com.hunseong.dmaker.domain.type.SkillLevel;
import com.hunseong.dmaker.exception.DeveloperException;
import com.hunseong.dmaker.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.hunseong.dmaker.exception.DeveloperErrorCode.*;

/**
 * Created by Hunseong on 2022/04/29
 */
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    @Transactional
    public CreateDeveloperResponse createDeveloper(CreateDeveloperRequest request) {
        validateDeveloperRequest(request);

        Developer developer = Developer.builder()
                .name(request.getName())
                .age(request.getAge())
                .workYear(request.getWorkYear())
                .skillLevel(request.getSkillLevel())
                .skillType(request.getSkillType())
                .status(StatusCode.EMPLOYED)
                .build();

        Developer savedDeveloper = developerRepository.save(developer);
        return new CreateDeveloperResponse(savedDeveloper.getId(), savedDeveloper.getName());
    }

    private void validateDeveloperRequest(CreateDeveloperRequest request) {
        validateDeveloperLevel(request.getSkillLevel(), request.getWorkYear());

        developerRepository.findByName(request.getName())
                .ifPresent((developer -> {
                    throw new DeveloperException(DUPLICATED_NAME);
                }));
    }

    private void validateDeveloperLevel(SkillLevel skillLevel, int workYear) {
        if (skillLevel == SkillLevel.SENIOR && workYear < 10) {
            throw new DeveloperException(LEVEL_EXPERIENCE_YEAR_NOT_MATCHED);
        }
        if (skillLevel == SkillLevel.JUNGNIOR && (workYear < 4 || workYear > 10)) {
            throw new DeveloperException(LEVEL_EXPERIENCE_YEAR_NOT_MATCHED);
        }
        if (skillLevel == SkillLevel.JUNIOR && workYear > 4) {
            throw new DeveloperException(LEVEL_EXPERIENCE_YEAR_NOT_MATCHED);
        }
        if (skillLevel == SkillLevel.NEW && workYear > 0) {
            throw new DeveloperException(LEVEL_EXPERIENCE_YEAR_NOT_MATCHED);
        }
    }

    public DeveloperDetailDto findDeveloperDetail(Long id) {
        return developerRepository.findById(id)
                .map(DeveloperDetailDto::new)
                .orElseThrow(() -> new DeveloperException(NO_DEVELOPER));
    }

    @Transactional
    public DeveloperDetailDto updateDeveloper(Long id, DeveloperUpdateRequestDto request) {

        Developer developer = developerRepository.findById(id)
                .orElseThrow(() -> new DeveloperException(NO_DEVELOPER));

        developer.update(request);

        return new DeveloperDetailDto(developer);
    }

    @Transactional
    public void deleteDeveloper(Long id) {
        Developer developer = developerRepository.findById(id)
                .orElseThrow(() -> new DeveloperException(NO_DEVELOPER));

        developerRepository.delete(developer);
    }

    public DeveloperListDto getAllDevelopers(StatusCode status) {
        List<Developer> developers = developerRepository.findAll();

        if (status != null) {
            List<CreateDeveloperResponse> filterList =
                    developers.stream()
                            .filter(developer -> developer.getStatus() == status)
                            .map(CreateDeveloperResponse::new)
                            .collect(Collectors.toList());

            return new DeveloperListDto(filterList);
        } else {
            List<CreateDeveloperResponse> list =
                    developers.stream().map(CreateDeveloperResponse::new).collect(Collectors.toList());

            return new DeveloperListDto(list);
        }
    }
}
