package com.demoproject.dowjones.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demoproject.dowjones.entity.Stock;
import com.demoproject.dowjones.exception.StockNotFoundException;
import com.demoproject.dowjones.service.IDowjonesService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
public class DowjonesController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DowjonesController.class);
	
	@Autowired
	private IDowjonesService dowjonesService; 
	
	@ApiOperation(value = "Returns Stock with given ticker")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "The request has succeeded and returns a list of ticker"),
	        @ApiResponse(code = 404, message = "The server has not found anything matching the Request ticker")
	})
	
	@RequestMapping(value="/stock/{ticker}"  ,method =RequestMethod.GET)
	 public List<Stock> getStock(@PathVariable(name="ticker") String name) {
		LOGGER.debug("getStock",name);
		List<Stock> retVal= dowjonesService.getStock(name);
		if (retVal==null || retVal.isEmpty()) throw new StockNotFoundException("No stock found with ticker :"+ name);
		return retVal;
	  }
	
	@ApiOperation(value = "Add bulk stock data")
	@ApiResponses(value = {
	        @ApiResponse(code = 201, message = "The request has succeeded and stock data has been added successfully"),
	        @ApiResponse(code = 500, message = "The request failed to add stock")
	})
	@RequestMapping(value="/stock" , method =RequestMethod.POST)
	 public ResponseEntity addStock(@RequestBody List<Stock> stocks) {
		LOGGER.debug("addStock",stocks);
		dowjonesService.addStock(stocks);
	    return ResponseEntity.status(HttpStatus.CREATED).build();
	  }
	
	
	@RequestMapping(value="/",method =RequestMethod.GET)
	 public void index(HttpServletResponse response) throws IOException {
		
	    response.sendRedirect("/DowjonesService/swagger-ui/#/");
	  }
	
}



