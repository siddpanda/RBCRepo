package com.demoproject.dowjones.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demoproject.dowjones.constant.DowjonesConstant;
import com.demoproject.dowjones.entity.Stock;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;

public class StockToCSVToStock {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockToCSVToStock.class);

    public static void writeStocks(List<Stock> stocks) {

        try {
        	
        	FileWriter writer= new FileWriter(DowjonesConstant.datafile,true);
            ColumnPositionMappingStrategy<Stock> mapStrategy
                    = new ColumnPositionMappingStrategy<>();

            mapStrategy.setType(Stock.class);

            String[] columns = DowjonesConstant.columns;
            mapStrategy.setColumnMapping(columns);

            StatefulBeanToCsv<Stock> btcsv = new StatefulBeanToCsvBuilder<Stock>(writer)
            		.withMappingStrategy(mapStrategy)
            		.withApplyQuotesToAll(false)
                    .build();
            
            btcsv.write(stocks);
            writer.close();

        } catch (CsvException ex) {

            LOGGER.error("CSV exception", ex);
        } catch (FileNotFoundException ex) {
			// TODO Auto-generated catch block
        	LOGGER.error("FileNotFoundException", ex);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    
    public static List<Stock> readStocks() {
    	String[] columns = DowjonesConstant.columns;
		Reader fileReader = null;
		InputStream csvFileStream=null;
		CsvToBean<Stock> csvToStock = null;
	
		List<Stock> Stocks = new ArrayList<Stock>();
		
		try {
			 csvFileStream= new FileInputStream(DowjonesConstant.datafile);
			fileReader = new InputStreamReader(csvFileStream);
	
			ColumnPositionMappingStrategy<Stock> mappingStrategy = new ColumnPositionMappingStrategy<Stock>();
	
			mappingStrategy.setType(Stock.class);
			mappingStrategy.setColumnMapping(columns);
	
			csvToStock = new CsvToBeanBuilder<Stock>(fileReader).withMappingStrategy(mappingStrategy).withSkipLines(1)
					.withIgnoreLeadingWhiteSpace(true).build();
			
			Stocks = csvToStock.parse();
			
			return Stocks;
		} catch (Exception e) {
			LOGGER.error("Reading CSV Error!");
			
		} finally {
			try {
				 
				fileReader.close();
				csvFileStream.close();
				
			} catch (IOException e) {
				LOGGER.error("Closing fileReader/csvParser Error!");
			
			}
		}
		
		return Stocks;
	}
    
}