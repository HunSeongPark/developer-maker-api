package com.hunseong.dmaker.service;

import com.hunseong.dmaker.domain.dto.CreateDeveloperRequest;
import com.hunseong.dmaker.domain.dto.CreateDeveloperResponse;
import com.hunseong.dmaker.domain.dto.DeveloperDetailDto;
import com.hunseong.dmaker.domain.dto.DeveloperUpdateRequestDto;
import com.hunseong.dmaker.domain.type.SkillLevel;
import com.hunseong.dmaker.domain.type.SkillType;
import com.hunseong.dmaker.exception.DeveloperException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Hunseong on 2022/04/29
 */
@SpringBootTest
@Transactional
class DeveloperServiceTest {

    @Autowired
    DeveloperService developerService;

    @Test
    void create_developer_success() {

        // given
        CreateDeveloperRequest request = new CreateDeveloperRequest(
                "name", 20, 0, SkillType.BACK_END, SkillLevel.NEW);

        // when
        CreateDeveloperResponse response = developerService.createDeveloper(request);

        // then
        assertThat(response.getId()).isNotNull();
        assertThat(response.getName()).isEqualTo(request.getName());
    }

    @Test
    void create_developer_duplicate_name() {

        // given
        CreateDeveloperRequest request = new CreateDeveloperRequest(
                "name", 20, 0, SkillType.BACK_END, SkillLevel.NEW);
        developerService.createDeveloper(request);

        CreateDeveloperRequest errorRequest = new CreateDeveloperRequest(
                "name", 30, 20, SkillType.BACK_END, SkillLevel.SENIOR);

        // when & then
        assertThrows(DeveloperException.class,
                () -> developerService.createDeveloper(errorRequest));

    }

    @Test
    void create_developer_bad_level_request() {

        // given
        CreateDeveloperRequest errorRequest = new CreateDeveloperRequest(
                "name", 20, 10, SkillType.BACK_END, SkillLevel.NEW);

        // when & then
        assertThrows(DeveloperException.class,
                () -> developerService.createDeveloper(errorRequest));
    }

    @Test
    void developer_detail_success() {

        // given
        CreateDeveloperRequest request = new CreateDeveloperRequest(
                "name", 20, 0, SkillType.BACK_END, SkillLevel.NEW);

        CreateDeveloperResponse response = developerService.createDeveloper(request);

        // when
        DeveloperDetailDto detailDto = developerService.findDeveloperDetail(response.getId());

        // then
        assertThat(detailDto.getName()).isEqualTo(request.getName());
        assertThat(detailDto.getAge()).isEqualTo(request.getAge());
        assertThat(detailDto.getSkillType()).isEqualTo(request.getSkillType());
        assertThat(detailDto.getSkillLevel()).isEqualTo(request.getSkillLevel());
    }

    @Test
    void developer_detail_no_developer() {

        // given & when & then
        assertThrows(DeveloperException.class,
                () -> developerService.findDeveloperDetail(1L));

    }

    @Test
    void developer_edit_success() {

        // given
        CreateDeveloperRequest request = new CreateDeveloperRequest(
                "name", 20, 0, SkillType.BACK_END, SkillLevel.NEW);

        CreateDeveloperResponse response = developerService.createDeveloper(request);

        String changeName = "name2";
        Integer changeWorkYear = 0;

        DeveloperUpdateRequestDto updateDto = DeveloperUpdateRequestDto.builder()
                .name(changeName)
                .workYear(changeWorkYear)
                .build();

        // when
        DeveloperDetailDto updateDeveloper = developerService.updateDeveloper(response.getId(), updateDto);

        // then
        assertThat(updateDeveloper.getName()).isEqualTo(changeName);
        assertThat(updateDeveloper.getSkillLevel()).isEqualTo(SkillLevel.NEW);
    }
}