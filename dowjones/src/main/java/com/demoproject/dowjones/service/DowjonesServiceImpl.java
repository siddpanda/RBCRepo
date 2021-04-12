package com.demoproject.dowjones.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.demoproject.dowjones.entity.Stock;
import com.demoproject.dowjones.util.StockToCSVToStock;
@Service
public class DowjonesServiceImpl implements IDowjonesService{

	private static final Logger LOGGER = LoggerFactory.getLogger(DowjonesServiceImpl.class);
	public List<Stock> getStock(String ticker) {
		// TODO Auto-generated method stub
		List<Stock> retVal= StockToCSVToStock.readStocks().stream().filter(p->p.getStock().equalsIgnoreCase(ticker)).collect(Collectors.toList());	
		return retVal;
	}
	

	@Override
	public boolean addStock(List<Stock> stocks) {
		// TODO Auto-generated method stub
		try {
			StockToCSVToStock.writeStocks(stocks);
			return true;
		}catch(Exception ex) {
			LOGGER.error("addStock exception", ex);
			return false;
		}
		
	}



	
}
