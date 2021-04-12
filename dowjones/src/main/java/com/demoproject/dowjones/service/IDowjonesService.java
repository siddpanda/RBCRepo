package com.demoproject.dowjones.service;

import java.util.List;

import com.demoproject.dowjones.entity.Stock;

public interface IDowjonesService {
public List<Stock> getStock (String ticker);
public boolean addStock(List<Stock> stocks);
}
