package com.hunseong.dmaker.service;

import com.hunseong.dmaker.domain.dto.CreateMemberRequest;
import com.hunseong.dmaker.domain.dto.CreateMemberResponse;
import com.hunseong.dmaker.domain.type.SkillLevel;
import com.hunseong.dmaker.domain.type.SkillType;
import com.hunseong.dmaker.exception.DeveloperException;
import org.assertj.core.api.Assertions;
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
        CreateMemberRequest request = new CreateMemberRequest(
                "name", 20, 0, SkillType.BACK_END, SkillLevel.NEW);

        // when
        CreateMemberResponse response = developerService.createDeveloper(request);

        // then
        assertThat(response.getId()).isNotNull();
        assertThat(response.getName()).isEqualTo(request.getName());
    }

    @Test
    void create_developer_duplicate_name() {

        // given
        CreateMemberRequest request = new CreateMemberRequest(
                "name", 20, 0, SkillType.BACK_END, SkillLevel.NEW);
        developerService.createDeveloper(request);

        CreateMemberRequest errorRequest = new CreateMemberRequest(
                "name", 30, 20, SkillType.BACK_END, SkillLevel.SENIOR);

        // when & then
        assertThrows(DeveloperException.class,
                () -> developerService.createDeveloper(errorRequest));

    }

    @Test
    void create_developer_bad_level_request() {

        // given
        CreateMemberRequest errorRequest = new CreateMemberRequest(
                "name", 20, 10, SkillType.BACK_END, SkillLevel.NEW);

        // when & then
        assertThrows(DeveloperException.class,
                () -> developerService.createDeveloper(errorRequest));
    }
}