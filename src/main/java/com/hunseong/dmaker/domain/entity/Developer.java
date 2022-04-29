package com.hunseong.dmaker.domain.entity;

import com.hunseong.dmaker.domain.code.StatusCode;
import com.hunseong.dmaker.domain.dto.DeveloperUpdateRequestDto;
import com.hunseong.dmaker.domain.type.SkillLevel;
import com.hunseong.dmaker.domain.type.SkillType;
import com.hunseong.dmaker.exception.DeveloperException;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import static com.hunseong.dmaker.exception.DeveloperErrorCode.LEVEL_EXPERIENCE_YEAR_NOT_MATCHED;
import static org.springframework.util.StringUtils.hasText;

/**
 * Created by Hunseong on 2022/04/28
 */
@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Developer extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private Integer workYear;

    @Enumerated(EnumType.STRING)
    private SkillType skillType;

    @Enumerated(EnumType.STRING)
    private SkillLevel skillLevel;

    @Enumerated(EnumType.STRING)
    private StatusCode status;

    public void update(DeveloperUpdateRequestDto request) {
        if (hasText(request.getName())) {
            this.name = request.getName();
        }
        if (request.getAge() != null) {
            this.age = request.getAge();
        }
        if (request.getWorkYear() != null) {
            this.workYear = request.getWorkYear();

            if (workYear == 0) {
                this.skillLevel = SkillLevel.NEW;
            } else if (workYear < 4) {
                this.skillLevel = SkillLevel.JUNIOR;
            } else if (workYear > 4 && workYear < 10) {
                this.skillLevel = SkillLevel.JUNGNIOR;
            } else {
                this.skillLevel = SkillLevel.SENIOR;
            }
        }
        if (request.getSkillType() != null) {
            this.skillType = request.getSkillType();
        }
        if (request.getStatus() != null) {
            this.status = request.getStatus();
        }
    }
}
