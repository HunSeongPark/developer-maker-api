package com.hunseong.dmaker.repository;

import com.hunseong.dmaker.domain.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Hunseong on 2022/04/29
 */
public interface DeveloperRepository extends JpaRepository<Developer, Long> {

    Optional<Developer> findByName(String name);
}
