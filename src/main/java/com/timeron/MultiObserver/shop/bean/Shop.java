package com.timeron.MultiObserver.shop.bean;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.timeron.MultiObserver.dao.ObservedLinksPackageDAO;
import com.timeron.MultiObserver.dao.entity.ObservedLinksPackage;
import com.timeron.MultiObserver.dao.entity.Site;

public class Shop implements ShopInterface{
	
	private static final Logger LOG = LoggerFactory
			.getLogger(Shop.class);
	
	private Site site;
	
	private String shopName = "";
	private String url = "";
	
	private String articlesDivXPath = "";
	private String nextXPath = "";
	private int shopId;
	
	private String productNameXPath = "";
	private String productURLXPath = "";
	private String productPriceXPath = "";
	private String productOldPriceXPath = "";
	
	//private Integer maxArticleOnSite = 99999;
	
	protected int articleCounter = 0;
	protected int newArticleCounter = 0;
	protected int newPriceCounter = 0;
	protected int siteCounter = 0;
	protected int linkCounter = 0;
	protected int movementCounter = 0;
	
	/**
	 * Getters & Setters
	 */
	

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public String getNextXPath() {
		return nextXPath;
	}

	public void setNextXPath(String nextXPath) {
		this.nextXPath = nextXPath;
	}

	public String getArticlesDivXPath() {
		return articlesDivXPath;
	}

	public void setArticlesDivXPath(String articlesDivXPath) {
		this.articlesDivXPath = articlesDivXPath;
	}
	
	public String getProductNameXPath() {
		return productNameXPath;
	}

	public void setProductNameXPath(String productNameXPath) {
		this.productNameXPath = productNameXPath;
	}

	public String getProductPriceXPath() {
		return productPriceXPath;
	}

	public void setProductPriceXPath(String productPriceXPath) {
		this.productPriceXPath = productPriceXPath;
	}

	public String getProductOldPriceXPath() {
		return productOldPriceXPath;
	}

	public void setProductOldPriceXPath(String productOldPriceXPath) {
		this.productOldPriceXPath = productOldPriceXPath;
	}

//	public Integer getMaxArticleOnSite() {
//		return maxArticleOnSite;
//	}
//
//	public void setMaxArticleOnSite(Integer maxArticleOnSite) {
//		this.maxArticleOnSite = maxArticleOnSite;
//	}

	public String getProductURLXPath() {
		return productURLXPath;
	}

	public void setProductURLXPath(String productURLXPath) {
		this.productURLXPath = productURLXPath;
	}
	
	public int getArticleCounter() {
		return articleCounter;
	}

	public void setArticleCounter(int articleCounter) {
		this.articleCounter = articleCounter;
	}

	public int getNewArticleCounter() {
		return newArticleCounter;
	}

	public void setNewArticleCounter(int newArticleCounter) {
		this.newArticleCounter = newArticleCounter;
	}

	public int getNewPriceCounter() {
		return newPriceCounter;
	}

	public void setNewPriceCounter(int newPriceCounter) {
		this.newPriceCounter = newPriceCounter;
	}

	public int getSiteCounter() {
		return siteCounter;
	}

	public void setSiteCounter(int siteCounter) {
		this.siteCounter = siteCounter;
	}

	public int getLinkCounter() {
		return linkCounter;
	}

	public void setLinkCounter(int linkCounter) {
		this.linkCounter = linkCounter;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	/**
	 * Counters
	 */

	public void addArticleCounter() {
		this.articleCounter++;
	}

	public void addNewArticleCounter() {
		this.newArticleCounter++;
	}

	public void addNewPriceCounter() {
		this.newPriceCounter++;
	}

	public void addSiteCounter() {
		this.siteCounter++;
	}

	public void addLinkCounter() {
		this.linkCounter++;
	}
	
	public void addMovementCounter() {
		this.movementCounter++;
	}
	
	/**
	 * Methods
	 */
	
	public List<ObservedLinksPackage> getShopLinksPackage() {
		ObservedLinksPackageDAO observedLinksPackageDAO = new ObservedLinksPackageDAO();
		List<ObservedLinksPackage> ObservedLinksPackage;
		
		ObservedLinksPackage = observedLinksPackageDAO.getAll();
		
		return ObservedLinksPackage;
	}


	public void report(){
		String spaces = "";
		for (int i=0; i<38-getShopName().length(); i++){spaces += " ";}
		
		LOG.info("");
		LOG.info("#################################################");
		LOG.info("#                                               #");
		LOG.info("#   Nexus Multi Observer: Report                #");
		LOG.info("#   Shop: "+getShopName()+spaces+"#");
		LOG.info("#                                               #");
		LOG.info("#################################################");
		LOG.info("#");
		LOG.info("#   Articles:     "+articleCounter);
		LOG.info("#   New Articles: "+newArticleCounter);
		LOG.info("#   Links:        "+linkCounter);
		LOG.info("#   Sites:        "+siteCounter);
		LOG.info("#   Movement:     "+movementCounter);
		LOG.info("#");
		LOG.info("#");
		LOG.info("#");
		LOG.info("#################################################");
		LOG.info("");
	
	}




}
