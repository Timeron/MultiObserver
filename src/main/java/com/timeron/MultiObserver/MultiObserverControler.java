package com.timeron.MultiObserver;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.timeron.MultiObserver.shop.controler.ArestControler;
import com.timeron.MultiObserver.shop.controler.KomputronikControler;
import com.timeron.MultiObserver.shop.controler.MediaMarktControler;
import com.timeron.MultiObserver.shop.controler.MoreleControler;
import com.timeron.MultiObserver.shop.controler.SaturnControler;
import com.timeron.MultiObserver.shop.controler.ShopControler;

public class MultiObserverControler {
	
	private static final Logger LOG = Logger.getLogger(MultiObserverControler.class);

	private List<ShopControler> shopControlersList;

	private ShopControler moreleControler;
	private ShopControler arestControler;
	private ShopControler komputronikControler;
	private ShopControler mediaMarktControler;
	private ShopControler saturnControler;
//	private ShopControler ikeaControler;

	public void runMultiObserver() {
		this.shopControlersList = getAllShops();
		
		for (ShopControler shopControler : shopControlersList) {
			shopControler.runObserver();
			shopControler.saveObservedSites();
		}
		for (ShopControler shopControler : shopControlersList) {
			shopControler.printReport();
		}

	}

	private List<ShopControler> getAllShops() {
		List<ShopControler> shopControlersList = new ArrayList<ShopControler>();

		moreleControler = new MoreleControler();
		arestControler = new ArestControler();
		komputronikControler = new KomputronikControler();
		mediaMarktControler = new MediaMarktControler();
		saturnControler = new SaturnControler();
//		ikeaControler = new IkeaControler();

		shopControlersList.add(moreleControler);
		shopControlersList.add(arestControler);
		shopControlersList.add(komputronikControler);
		shopControlersList.add(mediaMarktControler);
		shopControlersList.add(saturnControler);
//		shopControlersList.add(ikeaControler);

		return shopControlersList;

	}

}
