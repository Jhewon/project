package com.interyard.review.vo;

import lombok.Data;

@Data
public class ReviewVO {

	private Long revNo;
	private String title;
	private String content;
	private String imgFile;
	private float grade;
	private float totalgrade;
	private String writeDate;
	private String id;
	private String blindId;
	private String name;
	private String blindName;
	private Long likeCnt;
	private Long ordNo;
	private Long levNo;
	private Long parentNo;
	private Long totalReview;
	private float avgReview;
	
	private Long goodsNo;
	private String goodsTitle;
	private String goodsSubTitle;
	private String goodsOpt;
	private String goodsImage;
	
	private Long orderNo;
	private String orderStatus;
	private Long optNo;
	private String optName;
	private Long amount;
	
	
}
