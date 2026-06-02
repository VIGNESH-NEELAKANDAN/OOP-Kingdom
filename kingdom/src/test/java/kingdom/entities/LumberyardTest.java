package kingdom.entities;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import kingdom.core.KingdomRegistry;

/**
 * Comprehensive test suite for the Lumberyard entity.
 *
 * This test class demonstrates the testing standards for OOP Kingdom entities:
 * - Default constructor initialization
 * - Abstract method implementations
 * - Jackson serialization/deserialization
 * - Business logic validation
 * - Status management
 * - Edge cases and boundary conditions
 *
 * Contributors should use this as a template for testing their implementations.
 */
class LumberyardTest {

    private Lumberyard lumberyard;

    @BeforeEach
    void setUp() {
        lumberyard = new Lumberyard();
    }

    // ==================== DEFAULT CONSTRUCTOR TESTS ====================

    @Test
    void testDefaultConstructorInitializesSafeDefaults() {
        assertNotNull(lumberyard.getIdentity());
        assertTrue(lumberyard.getIdentity().startsWith("LUMBERYARD-"));
        assertEquals("Lumberyard", lumberyard.getName());
        assertNotNull(lumberyard.getDescription());
        assertEquals(LocalDate.now(), lumberyard.getFoundingDate());
        assertEquals(Lumberyard.Status.UNDER_CONSTRUCTION, lumberyard.getStatus());
    }

    @Test
    void testDefaultConstructorInitializesWoodStockpile() {
        assertEquals(50, lumberyard.getWoodStockpile());
        assertEquals(25, lumberyard.getHarvestRate());
        assertEquals(500, lumberyard.getMaxCapacity());
    }

    @Test
    void testUUIDUniqueness() {
        Lumberyard lumberyard1 = new Lumberyard();
        Lumberyard lumberyard2 = new Lumberyard();
        assertNotEquals(lumberyard1.getIdentity(), lumberyard2.getIdentity());
    }

    // ==================== KINGDOM ENTITY INTERFACE TESTS ====================

    @Test
    void testImplementsKingdomEntity() {
        assertNotNull(lumberyard.getIdentity());
        assertNotNull(lumberyard.getName());
        assertNotNull(lumberyard.getDescription());
        assertNotNull(lumberyard.getFoundingDate());
        assertNotNull(lumberyard.getStatus());
    }

    @Test
    void testStatusTransitions() {
        assertEquals(Lumberyard.Status.UNDER_CONSTRUCTION, lumberyard.getStatus());
        
        lumberyard.setStatus(Lumberyard.Status.OPERATIONAL);
        assertEquals(Lumberyard.Status.OPERATIONAL, lumberyard.getStatus());
        
        lumberyard.setStatus(Lumberyard.Status.DAMAGED);
        assertEquals(Lumberyard.Status.DAMAGED, lumberyard.getStatus());
    }

    // ==================== ABSTRACT METHOD IMPLEMENTATION TESTS ====================

    @Test
    void testHarvestWoodIncreasesStockpile() {
        int initialStockpile = lumberyard.getWoodStockpile();
        lumberyard.harvestWood();
        assertEquals(initialStockpile + 25, lumberyard.getWoodStockpile());
    }

    @Test
    void testHarvestWoodRespectsMaxCapacity() {
        lumberyard.setWoodStockpile(490); // Set to near max
        lumberyard.harvestWood(); // Try to harvest 25 more (would exceed 500)
        assertEquals(500, lumberyard.getWoodStockpile()); // Capped at max
    }

    @Test
    void testMultipleHarvests() {
        int initialStockpile = lumberyard.getWoodStockpile();
        for (int i = 0; i < 5; i++) {
            lumberyard.harvestWood();
        }
        assertEquals(initialStockpile + (25 * 5), lumberyard.getWoodStockpile());
    }

    @Test
    void testHarvestWoodWhenDamaged() {
        lumberyard.setStatus(Lumberyard.Status.DAMAGED);
        int initialStockpile = lumberyard.getWoodStockpile();
        lumberyard.harvestWood(); // Should harvest at 50% efficiency (12 instead of 25)
        assertEquals(initialStockpile + 12, lumberyard.getWoodStockpile());
    }

    @Test
    void testGetWoodStockpile() {
        assertEquals(50, lumberyard.getWoodStockpile());
        lumberyard.harvestWood();
        assertEquals(75, lumberyard.getWoodStockpile());
    }

    // ==================== BUSINESS LOGIC TESTS ====================

    @Test
    void testConsumeWoodSuccessfulWithSufficientAmount() {
        lumberyard.setWoodStockpile(100);
        assertTrue(lumberyard.consumeWood(50));
        assertEquals(50, lumberyard.getWoodStockpile());
    }

    @Test
    void testConsumeWoodFailsWithInsufficientAmount() {
        lumberyard.setWoodStockpile(30);
        assertFalse(lumberyard.consumeWood(50));
        assertEquals(30, lumberyard.getWoodStockpile()); // Unchanged
    }

    @Test
    void testConsumeWoodExactAmount() {
        lumberyard.setWoodStockpile(100);
        assertTrue(lumberyard.consumeWood(100));
        assertEquals(0, lumberyard.getWoodStockpile());
    }

    @Test
    void testRepairWhenDamaged() {
        lumberyard.setStatus(Lumberyard.Status.DAMAGED);
        lumberyard.setWoodStockpile(150);
        
        assertTrue(lumberyard.repair());
        assertEquals(Lumberyard.Status.OPERATIONAL, lumberyard.getStatus());
        assertEquals(50, lumberyard.getWoodStockpile()); // 150 - 100 repair cost
    }

    @Test
    void testRepairFailsWithInsufficientWood() {
        lumberyard.setStatus(Lumberyard.Status.DAMAGED);
        lumberyard.setWoodStockpile(50);
        
        assertFalse(lumberyard.repair());
        assertEquals(Lumberyard.Status.DAMAGED, lumberyard.getStatus());
        assertEquals(50, lumberyard.getWoodStockpile()); // Unchanged
    }

    @Test
    void testRepairFailsWhenOperational() {
        lumberyard.setStatus(Lumberyard.Status.OPERATIONAL);
        lumberyard.setWoodStockpile(200);
        
        assertFalse(lumberyard.repair());
        assertEquals(Lumberyard.Status.OPERATIONAL, lumberyard.getStatus());
        assertEquals(200, lumberyard.getWoodStockpile());
    }

    @Test
    void testParameterizedConstructor() {
        Lumberyard custom = new Lumberyard("Grand Mill", 50, 1000);
        assertEquals("Grand Mill", custom.getName());
        assertEquals(50, custom.getHarvestRate());
        assertEquals(1000, custom.getMaxCapacity());
    }

    // ==================== SETTER TESTS ====================

    @Test
    void testSetHarvestRate() {
        lumberyard.setHarvestRate(40);
        assertEquals(40, lumberyard.getHarvestRate());
        lumberyard.harvestWood();
        assertEquals(50 + 40, lumberyard.getWoodStockpile());
    }

    @Test
    void testSetWoodStockpile() {
        lumberyard.setWoodStockpile(200);
        assertEquals(200, lumberyard.getWoodStockpile());
    }

    @Test
    void testSetWoodStockpileRespectMaxCapacity() {
        lumberyard.setWoodStockpile(1000); // Try to set above max
        assertEquals(500, lumberyard.getWoodStockpile()); // Capped at max
    }

    // ==================== JACKSON SERIALIZATION TESTS ====================

    @Test
    void testJsonPropertyAnnotationsPresent() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
        mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE);
        
        String json = mapper.writeValueAsString(lumberyard);
        
        assertTrue(json.contains("\"identity\""));
        assertTrue(json.contains("\"name\""));
        assertTrue(json.contains("\"description\""));
        assertTrue(json.contains("\"foundingDate\""));
        assertTrue(json.contains("\"status\""));
        assertTrue(json.contains("\"woodStockpile\""));
        assertTrue(json.contains("\"harvestRate\""));
        assertTrue(json.contains("\"maxCapacity\""));
    }

    @Test
    void testSerializationDeserializationRoundtrip() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
        mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE);
        
        Lumberyard original = new Lumberyard("Test Mill", 30, 600);
        original.setWoodStockpile(250);
        original.setStatus(Lumberyard.Status.OPERATIONAL);
        
        String json = mapper.writeValueAsString(original);
        Lumberyard deserialized = mapper.readValue(json, Lumberyard.class);
        
        assertEquals(original.getIdentity(), deserialized.getIdentity());
        assertEquals(original.getName(), deserialized.getName());
        assertEquals(original.getWoodStockpile(), deserialized.getWoodStockpile());
        assertEquals(original.getHarvestRate(), deserialized.getHarvestRate());
        assertEquals(original.getStatus(), deserialized.getStatus());
    }

    // ==================== REGISTRY TESTS ====================

    @Test
    void testStaticRegistrationOccurs() {
        // KingdomRegistry.register() is called in the static {} block
        // Verify that Lumberyard is in the registered blueprints
        assertTrue(KingdomRegistry.getRegisteredEntities().contains(Lumberyard.class));
    }

    // ==================== EDGE CASE TESTS ====================

    @Test
    void testZeroHarvestRate() {
        lumberyard.setHarvestRate(0);
        int initialStockpile = lumberyard.getWoodStockpile();
        lumberyard.harvestWood();
        assertEquals(initialStockpile, lumberyard.getWoodStockpile()); // No change
    }

    @Test
    void testNegativeConsumeIsRejected() {
        lumberyard.setWoodStockpile(100);
        assertFalse(lumberyard.consumeWood(-50));
        assertEquals(100, lumberyard.getWoodStockpile());
    }

    @Test
    void testHarvestAtMaxCapacity() {
        lumberyard.setWoodStockpile(500);
        lumberyard.harvestWood();
        assertEquals(500, lumberyard.getWoodStockpile()); // Still at max
    }
}
