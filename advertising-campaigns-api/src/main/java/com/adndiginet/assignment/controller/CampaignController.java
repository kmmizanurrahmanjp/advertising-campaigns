package com.adndiginet.assignment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.adndiginet.assignment.entity.Campaign;
import com.adndiginet.assignment.service.CampaignService;

/**
 * @author    Md Mizanur Rahman<kmmizanurrahmanjp@gmail.com>
 * @version   0.0.1-SNAPSHOT
 * @since     0.0.1-SNAPSHOT
 */
@RestController
@RequestMapping("/api/campaign")
public class CampaignController {
	
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(CampaignController.class);
	
	@Autowired
	CampaignService service;
	
	
	@PostMapping()
	public ResponseEntity<?> saveOrUpdate(@RequestPart(value="campaign", required=true) Campaign campaign, 
			@RequestPart(value="files", required = true) final MultipartFile[] files){
		
		if(ObjectUtils.isEmpty(campaign.getId())) {
			campaign = service.add(campaign, files);
		}else {
			campaign = service.update(campaign, files);
		}
		return new ResponseEntity<>(campaign, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> selectAll(){
			return new ResponseEntity<>(service.selectAll(), HttpStatus.OK);
    }
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> selectById(@PathVariable int id){
			return new ResponseEntity<>(service.selectById(id), HttpStatus.OK);
    }
}
