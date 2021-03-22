package thesis.stocker.service;

import org.springframework.stereotype.Component;
import thesis.stocker.DTO.StockDTO;
import thesis.stocker.DTO.StockPriceDTO;

import java.util.List;

@Component
public class ApiService implements IApiService{
    @Override
    public List<StockDTO> listAllStocks() {
        return null;
    }

    @Override
    public StockPriceDTO getPrice() {
        return null;
    }
}
