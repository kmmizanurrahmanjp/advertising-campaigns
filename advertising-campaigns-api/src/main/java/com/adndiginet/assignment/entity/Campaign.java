package com.adndiginet.assignment.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * @author    Md Mizanur Rahman<kmmizanurrahmanjp@gmail.com>
 * @version   0.0.1-SNAPSHOT
 * @since     0.0.1-SNAPSHOT
 */
@Entity
@Table(name="campaign")
public class Campaign implements Serializable{

	private static final long serialVersionUID = 3540582086400336387L;
	
	@Id
	@SequenceGenerator(name = "campaignSeqGen", sequenceName = "campaignSeq", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(generator = "campaignSeqGen")
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dateFrom;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dateTo;
	
	@Column(nullable = false)
	private float dailyBudget;
	
	@Column(nullable = false)
	private float totalBudget;
	
	@Transient
    private List<Banner> banners;

	
	//getter and setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public float getDailyBudget() {
		return dailyBudget;
	}

	public void setDailyBudget(float dailyBudget) {
		this.dailyBudget = dailyBudget;
	}

	public float getTotalBudget() {
		return totalBudget;
	}

	public void setTotalBudget(float totalBudget) {
		this.totalBudget = totalBudget;
	}

	public List<Banner> getBanners() {
		return banners;
	}

	public void setBanners(List<Banner> banners) {
		this.banners = banners;
	}
}
