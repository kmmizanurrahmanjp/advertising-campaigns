package com.adndiginet.assignment.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.adndiginet.assignment.entity.Banner;
import com.adndiginet.assignment.entity.Campaign;
import com.adndiginet.assignment.repository.BannerRepository;
import com.adndiginet.assignment.repository.CampaignRepository;
import com.adndiginet.assignment.utility.FloatInPlace;

/**
 * @author Md Mizanur Rahman<kmmizanurrahmanjp@gmail.com>
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
@Service
public class CampaignServiceImpl implements CampaignService {

	@Autowired
	CampaignRepository campaignRepository;

	@Autowired
	BannerRepository bannerRepository;

	@Override
	@Transactional
	public Campaign add(Campaign campaign, MultipartFile[] files) {
		// save campaign
		int id = campaignRepository.save(campaign).getId();

		// set banner list to campaign
		campaign.setBanners(convertMultipartToBannerList(files));

		// save banner
		saveBanner(id, campaign.getBanners());

		return selectById(id);
	}

	@Override
	@Transactional
	public Campaign update(Campaign campaign, MultipartFile[] files) {
		// save campaign
		int id = campaignRepository.save(campaign).getId();

		// set banner list to campaign
		campaign.setBanners(convertMultipartToBannerList(files));
		
		List<Banner> requestedBanner = campaign.getBanners();
		if(!CollectionUtils.isEmpty(requestedBanner)) {
			// delete existing banner
			List<Banner> bannerList = bannerRepository.selectByCampaignId(id);
			deleteExistingBanner(bannerList);
			
			// save updated banner
			saveBanner(id, campaign.getBanners());
		}
		return selectById(id);
	}

	@Override
	public List<Campaign> selectAll() {
		List<Campaign> responses = new ArrayList<>();
		List<Campaign> campaigns = campaignRepository.findAll();
		for (Campaign campaign : campaigns) {
			campaign.setDailyBudget(FloatInPlace.convertIn2DecimalPlace(campaign.getDailyBudget()));
			campaign.setTotalBudget(FloatInPlace.convertIn2DecimalPlace(campaign.getTotalBudget()));
			campaign.setBanners(bannerRepository.selectByCampaignId(campaign.getId()));
			responses.add(campaign);
		}
		return responses;
	}

	@Override
	public Campaign selectById(int id) {
		Optional<Campaign> campaigns = campaignRepository.findById(id);
		if (!campaigns.isPresent()) {
			return null;
		}
		Campaign campaign = campaigns.get();
		campaign.setDailyBudget(FloatInPlace.convertIn2DecimalPlace(campaign.getDailyBudget()));
		campaign.setTotalBudget(FloatInPlace.convertIn2DecimalPlace(campaign.getTotalBudget()));
		campaign.setBanners(bannerRepository.selectByCampaignId(campaign.getId()));

		return campaign;
	}

	//save banner
	public void saveBanner(int campaignId, List<Banner> bannerRequest) {
		if (!CollectionUtils.isEmpty(bannerRequest)) {
			for (Banner banner : bannerRequest) {
				banner.setCampaignId(campaignId);
				bannerRepository.save(banner);
			}
		}
	}

	//delete banner
	public void deleteExistingBanner(List<Banner> bannerList) {
		if (!CollectionUtils.isEmpty(bannerList)) {
			for (Banner banner : bannerList) {
				bannerRepository.delete(banner);
			}
		}
	}

	//convert files to banner list
	public List<Banner> convertMultipartToBannerList(MultipartFile[] files) {
		List<Banner> bannerList = new ArrayList<>();
		for (MultipartFile file : files) {
			Banner banner = new Banner();
			try {
				banner.setBanner(file.getBytes());
				bannerList.add(banner);
			} catch (IOException e) {
				// LOG
			}
		}
		return bannerList;
	}

}
