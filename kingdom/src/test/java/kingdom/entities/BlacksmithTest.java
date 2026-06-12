package kingdom.entities;

import static org.junit.jupiter.api.Assertions.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;
import kingdom.core.KingdomEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BlacksmithTest {

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void testConstructors() {
        // Test Default Constructor
        Blacksmith defaultForge = new Blacksmith();
        assertNotNull(defaultForge.getIdentity());
        assertTrue(defaultForge.getIdentity().startsWith("BLACKSMITH-"));
        assertEquals(KingdomEntity.Status.UNDER_CONSTRUCTION, defaultForge.getStatus());
        assertEquals(0, defaultForge.getWeaponCount());

        // Test Parameterized Constructor
        Blacksmith parameterizedForge = new Blacksmith("Dragon Forge", "Forges fiery swords");
        assertEquals("Dragon Forge", parameterizedForge.getName());
        assertEquals("Forges fiery swords", parameterizedForge.getDescription());
        assertEquals(LocalDate.now(), parameterizedForge.getFoundingDate());
        assertEquals(KingdomEntity.Status.OPERATIONAL, parameterizedForge.getStatus());
    }

    @Test
    public void testForgingAndAnvilDamageLoop() {
        Blacksmith blacksmith = new Blacksmith("Iron Heart", "Heavy duty smithing");
        
        // Initial state
        assertEquals(0, blacksmith.getWeaponCount());
        assertEquals(KingdomEntity.Status.OPERATIONAL, blacksmith.getStatus());

        // Forge until it breaks (3 strikes based on MAX_DURABILITY)
        blacksmith.forgeWeapon();
        blacksmith.forgeWeapon();
        assertEquals(2, blacksmith.getWeaponCount());
        assertEquals(KingdomEntity.Status.OPERATIONAL, blacksmith.getStatus());

        // Third strike should break it
        blacksmith.forgeWeapon();
        assertEquals(3, blacksmith.getWeaponCount());
        assertEquals(KingdomEntity.Status.DAMAGED, blacksmith.getStatus());

        // Forging while DAMAGED should not increase count
        blacksmith.forgeWeapon();
        assertEquals(3, blacksmith.getWeaponCount());

        // Repair should bring it back online
        blacksmith.repairAnvil();
        assertEquals(KingdomEntity.Status.OPERATIONAL, blacksmith.getStatus());
        
        // Forging works again post-repair
        blacksmith.forgeWeapon();
        assertEquals(4, blacksmith.getWeaponCount());
    }

    @Test
    public void testJacksonSerialization() throws Exception {
        Blacksmith original = new Blacksmith("Volcano Forge", "Uses geothermal heat");
        original.forgeWeapon(); // weaponCount = 1, durability = 2

        // Serialize to JSON String
        String json = objectMapper.writeValueAsString(original);
        assertNotNull(json);
        assertTrue(json.contains("Volcano Forge"));

        // Deserialize back to Object
        Blacksmith deserialized = objectMapper.readValue(json, Blacksmith.class);
        
        assertEquals(original.getIdentity(), deserialized.getIdentity());
        assertEquals(original.getName(), deserialized.getName());
        assertEquals(original.getDescription(), deserialized.getDescription());
        assertEquals(original.getFoundingDate(), deserialized.getFoundingDate());
        assertEquals(original.getStatus(), deserialized.getStatus());
        assertEquals(original.getWeaponCount(), deserialized.getWeaponCount());
        assertEquals(original.getAnvilDurability(), deserialized.getAnvilDurability());
    }
}
