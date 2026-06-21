package kingdom.entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kingdom.core.KingdomEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TradingPostTest {

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void testDefaultConstructor() {
        TradingPost post = new TradingPost();
        assertNotNull(post.getIdentity());
        assertTrue(post.getIdentity().startsWith("TRADINGPOST-"));
        assertEquals("Trading Post", post.getName());
        assertEquals(LocalDate.now(), post.getFoundingDate());
        assertEquals(KingdomEntity.Status.UNDER_CONSTRUCTION, post.getStatus());
        assertEquals(0, post.getResourceStockpile());
        assertEquals(0, post.getTradesCompleted());
    }

    @Test
    public void testParameterizedConstructor() {
        TradingPost post = new TradingPost("East Outpost", "Handles eastern border trades.");
        assertEquals("East Outpost", post.getName());
        assertEquals("Handles eastern border trades.", post.getDescription());
        assertEquals(KingdomEntity.Status.OPERATIONAL, post.getStatus());
    }

    @Test
    public void testBarterLogic() {
        TradingPost post = new TradingPost("Main Market", "Core exchange point.");
        
        // Invalid trade verifications
        assertFalse(post.barter(0));
        assertFalse(post.barter(-5));
        
        // Valid trade verification
        assertTrue(post.barter(10));
        assertEquals(50, post.getResourceStockpile());
        assertEquals(1, post.getTradesCompleted());
    }

    @Test
    public void testJacksonSerialization() throws Exception {
        TradingPost original = new TradingPost("Border Post", "Description");
        original.barter(4);

        String json = objectMapper.writeValueAsString(original);
        TradingPost deserialized = objectMapper.readValue(json, TradingPost.class);

        assertEquals(original.getIdentity(), deserialized.getIdentity());
        assertEquals(original.getName(), deserialized.getName());
        assertEquals(original.getResourceStockpile(), deserialized.getResourceStockpile());
        assertEquals(original.getTradesCompleted(), deserialized.getTradesCompleted());
    }
}