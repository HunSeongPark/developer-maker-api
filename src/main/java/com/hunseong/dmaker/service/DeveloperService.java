package com.hunseong.dmaker.service;

import com.hunseong.dmaker.domain.dto.CreateMemberRequest;
import com.hunseong.dmaker.domain.dto.CreateMemberResponse;
import com.hunseong.dmaker.domain.entity.Developer;
import com.hunseong.dmaker.domain.type.SkillLevel;
import com.hunseong.dmaker.exception.DeveloperException;
import com.hunseong.dmaker.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.hunseong.dmaker.exception.DeveloperErrorCode.DUPLICATED_NAME;
import static com.hunseong.dmaker.exception.DeveloperErrorCode.LEVEL_EXPERIENCE_YEAR_NOT_MATCHED;

/**
 * Created by Hunseong on 2022/04/29
 */
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    @Transactional
    public CreateMemberResponse createDeveloper(CreateMemberRequest request) {
        validateDeveloperRequest(request);

        Developer developer = Developer.builder()
                .name(request.getName())
                .age(request.getAge())
                .workYear(request.getWorkYear())
                .skillLevel(request.getSkillLevel())
                .skillType(request.getSkillType())
                .build();

        Developer savedDeveloper = developerRepository.save(developer);
        return new CreateMemberResponse(savedDeveloper.getId(), savedDeveloper.getName());
    }

    private void validateDeveloperRequest(CreateMemberRequest request) {
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
}
