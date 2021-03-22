package thesis.stocker.service;

import thesis.stocker.DTO.StockDTO;
import thesis.stocker.DTO.StockPriceDTO;

import java.util.List;

public interface IApiService {

    List<StockDTO> listAllStocks();

    StockPriceDTO getPrice();

}
