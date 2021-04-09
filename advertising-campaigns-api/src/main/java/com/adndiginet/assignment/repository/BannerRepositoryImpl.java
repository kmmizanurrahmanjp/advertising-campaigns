package com.adndiginet.assignment.repository;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.adndiginet.assignment.entity.Banner;

/**
 * @author    Md Mizanur Rahman<kmmizanurrahmanjp@gmail.com>
 * @version   0.0.1-SNAPSHOT
 * @since     0.0.1-SNAPSHOT
 */
@Repository
@Transactional(readOnly= true)
public class BannerRepositoryImpl implements BannerRepositoryCustom{

	@PersistenceContext
    EntityManager entityManager;
	
	@Override
	public List<Banner> selectByCampaignId(int id) {
		Query query = entityManager.createNativeQuery("SELECT b.* FROM banner as b " +
                "WHERE b.campaign_id = ?", Banner.class);
		query.setParameter(1, id);
		return query.getResultList();
	}

}
