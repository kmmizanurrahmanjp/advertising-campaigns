package com.adndiginet.assignment.repository;

import java.util.List;

import com.adndiginet.assignment.entity.Banner;

/**
 * @author    Md Mizanur Rahman<kmmizanurrahmanjp@gmail.com>
 * @version   0.0.1-SNAPSHOT
 * @since     0.0.1-SNAPSHOT
 */
public interface BannerRepositoryCustom {

	public List<Banner> selectByCampaignId(int id);
	
}
