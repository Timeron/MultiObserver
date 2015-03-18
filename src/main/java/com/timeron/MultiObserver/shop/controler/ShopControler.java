package com.timeron.MultiObserver.shop.controler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.timeron.MultiObserver.dao.ObservedSiteDAO;
import com.timeron.MultiObserver.dao.ObservedSiteHistoryDAO;
import com.timeron.MultiObserver.dao.entity.ObservedLinksPackage;
import com.timeron.MultiObserver.dao.entity.ObservedSite;
import com.timeron.MultiObserver.dao.entity.ObservedSiteHistory;
import com.timeron.MultiObserver.downloader.Downloader;
import com.timeron.MultiObserver.shop.bean.Shop;
import com.timeron.MultiObserver.shop.helper.Price;

/**
 * Klasa dziedziczona przez wszystkie kontrolery sklepów. Zarządza działaniami
 * sklepów.
 * 
 * 
 * @author Timeron
 *
 */
public class ShopControler implements ShopControlerInterface {
	
	protected static final String EMPTY_NAME = "No name on site";
	protected static final String EMPTY_URL = "No url on site";

	private static final Logger LOG = Logger.getLogger(ShopControler.class);
	protected Shop shop = null;
	
	protected List<ObservedSite> observedSiteList; 
	protected List<ObservedLinksPackage> observedLinksPackages;
	
	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public void runObserver() {
		// pobieramy wszystkie linki pakietów
		observedLinksPackages = this.shop.getShopLinksPackage();
		this.observedSiteList = new ArrayList<ObservedSite>();
		
		for (ObservedLinksPackage observedLinksPackage : observedLinksPackages) {
			prepareControler();
			downloadObservedSitesFromUrl(observedLinksPackage, observedLinksPackage.getUrl());
		}
	}

	@SuppressWarnings("unchecked")
	private void downloadObservedSitesFromUrl(ObservedLinksPackage observedLinksPackage, String next) {
		List<HtmlAnchor> nextHtmlAnchor = null;
		do{
			Downloader downloader = new Downloader();
			HtmlPage htmlPage = downloader.getSite(next);
			int objects = 0;
			// get next site HtmlAnchor and links to the articles
			List<DomNode> articlesDiv = (List<DomNode>) htmlPage.getByXPath(shop.getArticlesDivXPath());
			if (!shop.getNextXPath().isEmpty()) {
				nextHtmlAnchor = (List<HtmlAnchor>) htmlPage.getByXPath(shop.getNextXPath());
			}
	
			// jeśli artukuł jest dostępny na stronie
			if (!articlesDiv.isEmpty()) {
				// dla każdego artukułu budujemy obiekt
				objects = getArticlesFromDivs(observedLinksPackage, articlesDiv);
			}
			this.shop.addLinkCounter();
			//jeśli na stronie nie ma już obiektów a przycisk next jest dostępny to przewij
			next = (objects > 0) ? checkNextSite(nextHtmlAnchor) : null;
		}while(next!=null);
	}

	protected String checkNextSite(List<HtmlAnchor> next) {
		if (next != null && !next.isEmpty()) {
			String nextUrl = "";
			try {
				nextUrl = next.get(0).click().getUrl().toString();
			} catch (IOException e) {
				LOG.info("Not able to receive url from Click; Resoult: " + nextUrl);
				e.printStackTrace();
			}
			LOG.info("next link: " + nextUrl);
			return nextUrl;
		} else {
			return null;
		}

	}
	
	protected int getArticlesFromDivs(ObservedLinksPackage observedLinksPackage, List<DomNode> articlesDiv){
		int objects = 0;
		for (DomNode articleDiv : articlesDiv) {
			if (!articleDiv.getTextContent().isEmpty()) {
				ObservedSite observedSite = new ObservedSite();
				ObservedSiteHistory observedSiteHistory = new ObservedSiteHistory();
				List<ObservedSiteHistory> observedSiteHistorys = new ArrayList<ObservedSiteHistory>();
	
				observedSite = parseObservedSite(articleDiv);
				observedSite.setObservedLinksPackage(observedLinksPackage);
				observedSiteHistory = parseObservedSiteHistory(articleDiv);
				observedSiteHistory.setObservedSite(observedSite);
				observedSiteHistory.setTimestamp(new Date());
				observedSiteHistorys.add(observedSiteHistory);
				observedSite.setObservedSiteHistory(observedSiteHistorys);
				this.observedSiteList.add(observedSite);
				objects++;
				
				if( observedSite.getArticleName() == null || observedSite.getArticleName().isEmpty()){
					shop.addError(EMPTY_NAME);
				}
				if(observedSite.getUrl() == null || observedSite.getUrl().isEmpty() ){
					shop.addError(EMPTY_URL);
				}
				
				LOG.info("New Object: "+observedSite.getArticleName()+" with price: "+observedSiteHistory.getPrice());
				
			} else {
				LOG.info("Site is empty!");
			}
		}
		return objects;
	}

	@SuppressWarnings("unchecked")
	private ObservedSite parseObservedSite(DomNode articleDiv) {
		List<HtmlAnchor> articleLink = (List<HtmlAnchor>) articleDiv.getByXPath(articleDiv.getCanonicalXPath()+ shop.getProductURLXPath());
		List<HtmlAnchor> articleName = (List<HtmlAnchor>) articleDiv.getByXPath(articleDiv.getCanonicalXPath()+ shop.getProductNameXPath());

		ObservedSite observedSite = new ObservedSite();

		if (!articleLink.isEmpty()) {
			observedSite.setUrl(buildArticleUrl(articleLink.get(0)));
			observedSite.setHashUrl(buildArticleUrl(articleLink.get(0))
					.hashCode());
			observedSite.setArticleName(buildArticleName(articleName.get(0)));
		}
		return observedSite;
	}

	@SuppressWarnings("unchecked")
	private ObservedSiteHistory parseObservedSiteHistory(DomNode articleDiv) {
		List<DomNode> price = (List<DomNode>) articleDiv.getByXPath(articleDiv.getCanonicalXPath() + shop.getProductPriceXPath());
		

		ObservedSiteHistory observedSiteHistory = new ObservedSiteHistory();

		if (!price.isEmpty()) {
			observedSiteHistory.setPrice(buildPrice(price.get(0)).getPrice());
		}
		if (shop.getProductOldPriceXPath() != null && !shop.getProductOldPriceXPath().isEmpty()) {
			List<DomNode> oldPrice = (List<DomNode>) articleDiv.getByXPath(articleDiv.getCanonicalXPath() + shop.getProductOldPriceXPath());
			if (!oldPrice.isEmpty() && shop.getProductOldPriceXPath() != null) {
				observedSiteHistory.setOldPrice(buildPrice(oldPrice.get(0))
						.getPrice());
			}
		}

		return observedSiteHistory;
	}

	protected Price buildPrice(Object objectPrice) {
		DomNode domNodePrice = (DomNode) objectPrice;
		Price price = new Price(domNodePrice.getTextContent());
		return price;
	}

	protected String buildArticleUrl(Object objectUrl) {
		String url = "";
		HtmlAnchor articleLink = (HtmlAnchor) objectUrl;
		if(!articleLink.getHrefAttribute().contains(shop.getUrl())){
			url = shop.getUrl();
		}
		url += articleLink.getHrefAttribute();
		return url;
	}

	protected String buildArticleName(Object objectName) {
		String name = "";
		HtmlAnchor articleName = (HtmlAnchor) objectName;
		name = articleName.asText();
		return name;
	}

	public void saveObservedSites() {
		ObservedSiteDAO observedSiteDAO = new ObservedSiteDAO();
		ObservedSiteHistoryDAO observedSiteHistoryDAO = new ObservedSiteHistoryDAO();
		for(int index=0; index<this.observedSiteList.size(); index++){
			LOG.info("Dodano adres z hash = "+observedSiteList.get(index).getHashUrl()); //Błąd
			//Sprawdzamy czy to nowy produkt z tej strony
			if(!observedSiteDAO.checkIfHashExist(observedSiteList.get(index))){
				this.shop.addNewArticleCounter();
				this.shop.addArticleCounter();
				observedSiteList.get(index).setTimestamp(new Date());
				observedSiteDAO.save(observedSiteList.get(index));
			}else{
				//Produkty mogą być źle posortowane na stronie. Nie zapisujemy produktów które w wyniku przesunięcia na liście pojawiły się dwa razy.
				if(!observedSiteDAO.siteWasAddedDoday(observedSiteList.get(index))){
					this.shop.addArticleCounter();
					//observedSiteList.get(index).getObservedSiteHistory().get(0).setId(observedSiteDAO.getId(observedSiteList.get(index)));  //ustawiamy id strony dla hisorii
					observedSiteList.get(index).getObservedSiteHistory().get(0).getObservedSite().setId(observedSiteDAO.getId(observedSiteList.get(index)));
					if(observedSiteHistoryDAO.priceChanged(observedSiteList.get(index).getObservedSiteHistory().get(0))){
						observedSiteList.get(index).setId(observedSiteDAO.getId(observedSiteList.get(index)));
						observedSiteList.get(index).setTimestamp(new Date());
						observedSiteDAO.update(observedSiteList.get(index));
						this.shop.addUpdatePriceCounter();
						LOG.info("Nowa cena dodana dla produktu: "+observedSiteList.get(index).getArticleName());
					}else{
						LOG.info("Cena się nie zmieniła");
					}
				}else{
					this.shop.addMovementCounter();
					LOG.info("Wykryto przesunięcie na liście lub obiekt był dodany już dzisiaj");
				}
			}
		}
	}

	public void saveObservedSiteHistory() {
		ObservedSiteHistoryDAO observedSiteHistoryDAO = new ObservedSiteHistoryDAO();
		for(ObservedSite observedSite : observedSiteList){
			for(ObservedSiteHistory observedSiteHistory : observedSite.getObservedSiteHistory()){
				observedSiteHistory.setTimestamp(new Date());
				observedSiteHistoryDAO.save(observedSiteHistory);
			}
		}
		
	}
	
	protected void prepareControler(){
		
	}
	
	public void printReport(){
		this.shop.report();
	}

}
