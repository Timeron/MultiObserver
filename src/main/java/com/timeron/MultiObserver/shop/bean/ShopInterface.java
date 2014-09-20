package com.timeron.MultiObserver.shop.bean;

public interface ShopInterface {
	
	public String getNextXPath();
	
	public void setNextXPath(String nextXPath);
	
	public String getArticlesDivXPath();
	
	public void setArticlesDivXPath(String articlesDivXPath);
	
	public String getProductNameXPath();
	
	public void setProductNameXPath(String productNameXPath);
	
	public String getProductPriceXPath();
	
	public void setProductPriceXPath(String productPriceXPath);
	
	public String getProductOldPriceXPath();
	
	public void setProductOldPriceXPath(String productOldPriceXPath);
	
//	public Integer getMaxArticleOnSite();
//	
//	public void setMaxArticleOnSite(Integer maxArticleOnSite);
	
	public String getProductURLXPath();
	
	public void setProductURLXPath(String productURLXPath);
	
	public int getArticleCounter();
	
	public void setArticleCounter(int articleCounter);
	
	public int getNewArticleCounter();
	
	public void setNewArticleCounter(int newArticleCounter);
	
	public int getNewPriceCounter();
	
	public void setNewPriceCounter(int newPriceCounter);
	
	public int getSiteCounter();
	
	public void setSiteCounter(int siteCounter);
	
	public int getLinkCounter();

	public void setLinkCounter(int linkCounter);

	/**
	 * Counters
	 */

//	public void addArticleCounter();
//
//	public void addNewArticleCounter();
//
//	public void addNewPriceCounter();
//
//	public void addSiteCounter();
//
//	public void addLinkCounter();
//	
//	public void sevaArticle(Article article);
//	
//	public void report();
}
