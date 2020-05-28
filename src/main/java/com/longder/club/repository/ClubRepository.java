package com.longder.club.repository;

import com.longder.club.entity.po.Club;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 社团表数据库操作
 */
public interface ClubRepository extends JpaRepository<Club,Long> {
}
