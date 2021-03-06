package com.timeron.MultiObserver.shop.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.timeron.NexusDatabaseLibrary.Entity.ObservedLinksPackage;
import com.timeron.NexusDatabaseLibrary.Entity.Site;
import com.timeron.NexusDatabaseLibrary.dao.ObservedLinksPackageDAO;
import com.timeron.NexusDatabaseLibrary.dao.SiteDAO;

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
	private String productKayXPath = "";
	
	//private Integer maxArticleOnSite = 99999;

	protected int articleCounter = 0; //wszystkie zdobyte atrykuły
	protected int newArticleCounter = 0; //tylko nowe artykuły
	protected int updatedPricesCounter = 0; //tylko artykuły które zmieniły cenę
	protected int siteCounter = 0; //rezygnujemy z tego
	protected int linkCounter = 0; //linki zbadane
	protected int movementCounter = 0; //powtarzające się artukuły przez błedne listowanie na stronie
	
	//błędy
	protected List<String> errors = new ArrayList<String>();
	
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
	
	public String getProductKayXPath() {
		return productKayXPath;
	}

	public void setProductKayXPath(String productKayXPath) {
		this.productKayXPath = productKayXPath;
	}

	public int getMovementCounter() {
		return movementCounter;
	}

	public void setMovementCounter(int movementCounter) {
		this.movementCounter = movementCounter;
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

	public int getUpdatedPricesCounter() {
		return updatedPricesCounter;
	}

	public void setUpdatedPricesCounter(int updatedPricesCounter) {
		this.updatedPricesCounter = updatedPricesCounter;
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

	public void addUpdatePriceCounter() {
		this.updatedPricesCounter++;
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
	
	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	/**
	 * Methods
	 */
	
	public List<ObservedLinksPackage> getShopLinksPackage() {
		ObservedLinksPackageDAO observedLinksPackageDAO = new ObservedLinksPackageDAO();
		List<ObservedLinksPackage> ObservedLinksPackage;
		
		SiteDAO siteDAO = new SiteDAO();
		this.site = siteDAO.getById(shopId);
		if(site != null){
			ObservedLinksPackage = observedLinksPackageDAO.getLinksByShopId(this.site);
			return ObservedLinksPackage;
		}else{
			return Collections.emptyList();
		}
		
		
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
		LOG.info("#   Articles:      "+articleCounter);
		LOG.info("#   New Articles:  "+newArticleCounter);
		LOG.info("#   Updated price: "+updatedPricesCounter);
		LOG.info("#   Links:         "+linkCounter);
		LOG.info("#   Movement:      "+movementCounter);
		LOG.info("#");
		if(errors.size()>0){
			LOG.info("#  Errors:");
			for(String error : errors){
				LOG.info("#  "+error);
			}
		}
		LOG.info("#");
		LOG.info("#################################################");
		LOG.info("");
	
	}

	public void addError(String error){
		if(!this.errors.contains(error)){
			this.errors.add(error);
		}
	}


}
