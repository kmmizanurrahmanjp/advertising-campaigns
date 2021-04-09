 package com.adndiginet.assignment.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author    Md Mizanur Rahman<kmmizanurrahmanjp@gmail.com>
 * @version   0.0.1-SNAPSHOT
 * @since     0.0.1-SNAPSHOT
 */
@Entity
@Table(name="banner")
public class Banner implements Serializable{


	private static final long serialVersionUID = -2605292523776018688L;
	
	@Id
	@SequenceGenerator(name = "bannerSeqGen", sequenceName = "bannerSeq", initialValue = 2000, allocationSize = 1)
    @GeneratedValue(generator = "bannerSeqGen")
	//@JsonIgnore
	private int bannerId;
	
	@Column(nullable = true)
	@Lob
	private byte[] banner;
	
	@Column(nullable = false)
	private int campaignId;
	
	
	// setter and getter
	public int getBannerId() {
		return bannerId;
	}

	public void setBannerId(int bannerId) {
		this.bannerId = bannerId;
	}

	public byte[] getBanner() {
		return banner;
	}

	public void setBanner(byte[] banner) {
		this.banner = banner;
	}

	public int getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}

	
}
