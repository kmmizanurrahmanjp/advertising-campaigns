package com.adndiginet.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adndiginet.assignment.entity.Banner;

/**
 * @author    Md Mizanur Rahman<kmmizanurrahmanjp@gmail.com>
 * @version   0.0.1-SNAPSHOT
 * @since     0.0.1-SNAPSHOT
 */
@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer>, BannerRepositoryCustom{

}
