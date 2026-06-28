package kingdom.entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GuardStationTest {

    @Test
    void testAssignPatrolAndCount() {
        GuardStation gs = new GuardStation();

        gs.assignPatrol("North");
        gs.assignPatrol("North");
        gs.assignPatrol("South");

        assertEquals(3, gs.getActivePatrolCount());
    }

    @Test
    void testCoordinateResponse() {
        GuardStation gs = new GuardStation();

        gs.assignPatrol("East");

        String response = gs.coordinateResponse();

        assertTrue(response.contains("districts"));
        assertTrue(response.contains("active patrols"));
    }

    @Test
    void testInvalidDistrictThrowsException() {
        GuardStation gs = new GuardStation();

        assertThrows(IllegalArgumentException.class,
                () -> gs.assignPatrol(""));
    }

    @Test
    void testUnmodifiableMap() {
        GuardStation gs = new GuardStation();

        Map<String, Integer> map = gs.getDistrictPatrols();

        assertThrows(UnsupportedOperationException.class,
                () -> map.put("West", 2));
    }

    @Test
    void testJacksonSerialization() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        GuardStation gs = new GuardStation();
        gs.assignPatrol("North");
        gs.assignPatrol("South");

        String json = mapper.writeValueAsString(gs);
        GuardStation copy = mapper.readValue(json, GuardStation.class);

        assertEquals(gs.getIdentity(), copy.getIdentity());
        assertEquals(gs.getName(), copy.getName());
        assertEquals(gs.getStatus(), copy.getStatus());
        assertEquals(gs.getActivePatrolCount(), copy.getActivePatrolCount());
    }
}