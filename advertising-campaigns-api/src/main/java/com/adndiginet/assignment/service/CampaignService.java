package com.adndiginet.assignment.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.adndiginet.assignment.entity.Campaign;

/**
 * @author    Md Mizanur Rahman<kmmizanurrahmanjp@gmail.com>
 * @version   0.0.1-SNAPSHOT
 * @since     0.0.1-SNAPSHOT
 */
public interface CampaignService {

	public Campaign add(Campaign c, MultipartFile[] files);
	public Campaign update(Campaign c, MultipartFile[] files);
	public List<Campaign> selectAll();
	public Campaign selectById(int id);
}
