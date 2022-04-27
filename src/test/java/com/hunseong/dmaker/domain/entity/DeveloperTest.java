package com.hunseong.dmaker.domain.entity;

import com.hunseong.dmaker.domain.type.SkillLevel;
import com.hunseong.dmaker.domain.type.SkillType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Hunseong on 2022/04/28
 */
@SpringBootTest
@Transactional
class DeveloperTest {

    @Autowired
    EntityManager em;

    @Test
    void auditingTest() {

        em.persist(Developer.builder()
                .name("hun")
                .age(10)
                .workYear(1)
                .skillLevel(SkillLevel.JUNGNIOR)
                .skillType(SkillType.BACK_END)
                .build());

        Developer developer = em.createQuery("select d from Developer d", Developer.class).getSingleResult();

        System.out.println("developer.create = " + developer.getCreatedDate());
        System.out.println("developer.modi = " + developer.getLastModifiedDate());

        assertThat(developer.getCreatedDate()).isBefore(LocalDateTime.now());
        assertThat(developer.getLastModifiedDate()).isBefore(LocalDateTime.now());
    }
}