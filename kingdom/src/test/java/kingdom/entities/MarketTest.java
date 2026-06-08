package kingdom.entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MarketTest {

    private Market market;

    @BeforeEach
    void setup() {
        market = new Market();
    }

    @Test
    @DisplayName("No-arg constructor should have UNDER_CONSTRUCTION status")
    void noArgConstructor_shouldHaveUnderConstructionStatus() {
      assertEquals(Market.Status.UNDER_CONSTRUCTION, market.getStatus());
    }

    @Test
    @DisplayName("No-arg constructor should have non-null identity")
    void noArgConstructor_shouldHaveNonNullIdentity() {
        assertNotNull(market.getIdentity());
        assertTrue(market.getIdentity().startsWith("MARKET-"));
    }

    @Test
    @DisplayName("No-arg constructor should start with zero gold balance")
    void noArgConstructor_shouldStartWithZeroGoldBalance() {
        assertEquals(0.0, market.getGoldBalance());
    }

    @Test
    @DisplayName("Parameterized constructor should have OPERATIONAL status")
    void parameterizedConstructor_shouldHaveOperationalStatus() {
        Market operational = new Market("Grand Market", "The main trading hub..");
        assertEquals(Market.Status.OPERATIONAL, operational.getStatus());
    }

    @Test
    @DisplayName("conductTrade should increase gold balance")
    void conductTrade_shouldIncreaseGoldBalance() {
        market.conductTrade(100.0);
        assertEquals(100.0, market.getGoldBalance());
    }

    @Test
    @DisplayName("Multiple trades should accumulate gold balance")
    void conductTrade_multipleTrades_shouldAccumulate() {
        market.conductTrade(100.0);
        market.conductTrade(50.0);
        assertEquals(150.0, market.getGoldBalance());
    }

    @Test
    @DisplayName("Should serialize and deserialize correctly with Jackson")
    void serialization_shouldPreserveState() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.activateDefaultTyping(
                mapper.getPolymorphicTypeValidator(),
                ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE
        );

        market.conductTrade(200.0);
        String json = mapper.writeValueAsString(market);
        Market deserialized = mapper.readValue(json, Market.class);

        assertEquals(market.getGoldBalance(), deserialized.getGoldBalance());
        assertEquals(market.getIdentity(), deserialized.getIdentity());
    }
}
