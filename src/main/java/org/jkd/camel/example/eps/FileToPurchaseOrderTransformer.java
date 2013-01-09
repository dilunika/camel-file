package org.jkd.camel.example.eps;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.camel.Body;
import org.apache.camel.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileToPurchaseOrderTransformer {
	
	private static final String COMMA = ",";

	private static final String NEW_LINE = "\n";

	private static final Logger LOG = LoggerFactory.getLogger(FileToPurchaseOrderTransformer.class);
	
	final DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss");

	
	@Handler
	public PurchaseOrder transform(@Body final String orderAsCsv){
		
		LOG.info("Purchase order csv {}",orderAsCsv);
		
		
		return buildPurchaseOrder(orderAsCsv.split(NEW_LINE));
	}

	private PurchaseOrder buildPurchaseOrder(final String[] orderInfo) {
		
		final PurchaseOrder order = new PurchaseOrder();
		order.setTime(convertToDate(orderInfo[0]));
		order.setTotalAmount(parseDouble(orderInfo[1]));
		order.setItems(buildItemList(orderInfo));
		
		LOG.info("Transformed {}",order);
		
		return order;
	}


	private Date convertToDate(final String time) {
		
		try {
			return dateFormat.parse(time);
		} catch (ParseException e) {
			LOG.error("Failed to parse the purchase time", e);
			return new Date();
		}
	}
	
	private List<PurchaseItem> buildItemList(final String[] orderInfo) {
		
		final List<PurchaseItem> items = new ArrayList<PurchaseItem>();
		
		for (int i = 2; i < orderInfo.length; i++) {
			items.add(newPurchaseItem(orderInfo[i]));
		}
		
		return items;
	}

	private PurchaseItem newPurchaseItem(final String item) {
		
		final String[] content = item.split(COMMA);
		final PurchaseItem pi = new PurchaseItem();
		pi.setItemCode(content[0]);
		pi.setQuantity(parseInt(content[1]));
		pi.setAmount(parseDouble(content[2]));
		
		return pi;
	}
}
