package org.acme.stocks.repository;

import org.acme.stocks.entity.StockEntity;
import org.acme.stocks.fixture.Fixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase
public class StockRepositoryTest {

    @Autowired
    private StockRepository repository;

    @Test
    public void testRepository() {

        final StockEntity stock = Fixture.make(StockEntity.class);
        stock.setId(null);

        repository.save(stock);

        assertNotNull(stock.getId());

    }

    @Test
    public void testFindAllByCodeContains() {

        final StockEntity stock1 = Fixture.make(StockEntity.class);
        stock1.setId(1l);

        repository.save(stock1);

        final StockEntity stock2 = Fixture.make(StockEntity.class);
        stock2.setId(2l);

        repository.save(stock2);

        final List<StockEntity> stocks = repository.findAllByCodeContains(stock1.getCode());
        assertEquals(1, stocks.size());
        final StockEntity persistedStock1 = stocks.get(0);
        assertEquals(stock1.getStock(), persistedStock1.getStock());
        assertEquals(stock1.getTheoricQuantity(), persistedStock1.getTheoricQuantity());
        assertEquals(stock1.getCode(), persistedStock1.getCode());


        final List<StockEntity> stocks2 = repository.findAllByCodeContains(stock2.getCode());
        assertEquals(1, stocks2.size());
        final StockEntity persistedStock2 = stocks2.get(0);
        assertEquals(stock2.getStock(), persistedStock2.getStock());
        assertEquals(stock2.getTheoricQuantity(), persistedStock2.getTheoricQuantity());
        assertEquals(stock2.getCode(), persistedStock2.getCode());

    }
}
