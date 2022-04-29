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

import java.util.List;

import static com.hunseong.dmaker.domain.code.StatusCode.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void developer_delete_success() {

        // given
        CreateDeveloperRequest request = new CreateDeveloperRequest(
                "name", 20, 0, SkillType.BACK_END, SkillLevel.NEW);

        CreateDeveloperResponse response = developerService.createDeveloper(request);

        // when
        developerService.deleteDeveloper(response.getId());

        // then
        assertThrows(DeveloperException.class,
                () -> developerService.findDeveloperDetail(response.getId()));
    }

    @Test
    void developer_delete_no_developer() {

        // given & when & then
        assertThrows(DeveloperException.class,
                () -> developerService.findDeveloperDetail(1L));
    }

    @Test
    void find_all_success() {

        // given
        CreateDeveloperRequest request1 = new CreateDeveloperRequest(
                "name1", 20, 0, SkillType.BACK_END, SkillLevel.NEW);
        CreateDeveloperRequest request2 = new CreateDeveloperRequest(
                "name2", 20, 0, SkillType.BACK_END, SkillLevel.NEW);
        CreateDeveloperRequest request3 = new CreateDeveloperRequest(
                "name3", 20, 0, SkillType.BACK_END, SkillLevel.NEW);
        CreateDeveloperRequest request4 = new CreateDeveloperRequest(
                "name4", 20, 0, SkillType.BACK_END, SkillLevel.NEW);
        CreateDeveloperRequest request5 = new CreateDeveloperRequest(
                "name5", 20, 0, SkillType.BACK_END, SkillLevel.NEW);

        CreateDeveloperResponse response1 = developerService.createDeveloper(request1);
        CreateDeveloperResponse response2 = developerService.createDeveloper(request2);
        CreateDeveloperResponse response3 = developerService.createDeveloper(request3);
        CreateDeveloperResponse response4 = developerService.createDeveloper(request4);
        CreateDeveloperResponse response5 = developerService.createDeveloper(request5);

        DeveloperUpdateRequestDto leaveUpdate = DeveloperUpdateRequestDto.builder()
                .status(LEAVE)
                .build();

        DeveloperUpdateRequestDto retiredUpdate = DeveloperUpdateRequestDto.builder()
                .status(RETIRED)
                .build();

        developerService.updateDeveloper(response2.getId(), leaveUpdate);
        developerService.updateDeveloper(response3.getId(), leaveUpdate);
        developerService.updateDeveloper(response4.getId(), retiredUpdate);
        developerService.updateDeveloper(response5.getId(), retiredUpdate);

        // when
        List<CreateDeveloperResponse> all = developerService.getAllDevelopers(null).getDevelopers();
        List<CreateDeveloperResponse> employed = developerService.getAllDevelopers(EMPLOYED).getDevelopers();
        List<CreateDeveloperResponse> leave = developerService.getAllDevelopers(LEAVE).getDevelopers();
        List<CreateDeveloperResponse> retired = developerService.getAllDevelopers(RETIRED).getDevelopers();

        // then
        assertThat(all.size()).isEqualTo(5);
        assertThat(employed.size()).isEqualTo(1);
        assertThat(leave.size()).isEqualTo(2);
        assertThat(retired.size()).isEqualTo(2);
    }
}