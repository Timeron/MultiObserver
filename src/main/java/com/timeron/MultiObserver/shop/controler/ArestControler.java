package com.timeron.MultiObserver.shop.controler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.timeron.MultiObserver.downloader.Downloader;
import com.timeron.MultiObserver.shop.bean.Arest;
/**
 * Need to be rewritten!
 * @author Timeron
 *
 */
public class ArestControler extends ShopControler {
	
	private static final Logger LOG = LoggerFactory
			.getLogger(MoreleControler.class);

	private Arest arest;
	
	public ArestControler() {
		LOG.info("constructor: ArestControler");
		
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"arest.xml");
		this.arest = (Arest) context.getBean("arest");
		this.shop = (Arest) arest;
	}
	
	protected void downloadArticlesFromUrl(String url) {
		LOG.info("method: downloadArticlesFromUrl url: "+url);
		
		Downloader downloader = new Downloader();
		HtmlPage htmlPage = downloader.getSite(url);

		// get next site HtmlAnchor and links to the articles
		List<DomNode> articlesDiv = (List<DomNode>) htmlPage.getByXPath(shop.getArticlesDivXPath());
		List<HtmlAnchor> next = (List<HtmlAnchor>) htmlPage.getByXPath(shop.getNextXPath());

		this.shop.addSiteCounter();
		// create article object and add it to the moreleSites list
		for (DomNode articleDiv : articlesDiv) {
			this.shop.addArticleCounter();
			if (!articleDiv.getTextContent().isEmpty()) {
				
				List<HtmlAnchor> articleLink = (List<HtmlAnchor>) articleDiv.getByXPath(articleDiv.getCanonicalXPath()+ shop.getProductNameXPath());
				List<DomNode> price = (List<DomNode>) articleDiv.getByXPath(articleDiv.getCanonicalXPath()+ shop.getProductPriceXPath());
				//Article article = new Article();

				//article.setType(globalType, type);

				//ArticleSite articleSite = new ArticleSite(shop.getUrl()+articleLink.get(0).getHrefAttribute());
//				articleSite.setShop(shop);
//				articleSite.setSiteName(articleLink.get(0).asText());
//				articleSite.setAvailability(true);
//				articleSite.setExist(true);
//				articleSite.setPrice(new Price(price.get(0).getTextContent()));
//				articleSite.setHash(articleLink.get(0).getHrefAttribute().hashCode());
				
				//article.addShop(shop.getShopName(), articleSite);
				//shopSites.add(article);
				//LOG.info(article.toString());
			}
		}
		

		if (!next.isEmpty()) {
			if(next.get(0).getTextContent().equals("następna")){
				//LOG.info("next link: "+shop.getUrl()+next.get(0).getHrefAttribute()+arest.getNextXPathPostfix());
				//shopLinks.add(shop.getUrl()+next.get(0).getHrefAttribute()+arest.getNextXPathPostfix());
				//downloadArticlesFromUrl(shop.getUrl()+next.get(0).getHrefAttribute()+arest.getNextXPathPostfix());
			}else{
				//LOG.info("next link: "+shop.getUrl()+next.get(0).getHrefAttribute()+arest.getNextXPathPostfix());
			}
		} else {
			LOG.info("next is empty");
		}

	}
	

}
