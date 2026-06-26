package kingdom.entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kingdom.core.KingdomEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuardPostTest {

    @Test
    void defaultConstructorShouldInitializeGuardPost() {
        GuardPost guardPost = new GuardPost();

        assertNotNull(guardPost.getIdentity());
        assertTrue(guardPost.getIdentity().startsWith("GUARDPOST-"));
        assertEquals("Guard Post", guardPost.getName());
        assertNotNull(guardPost.getDescription());
        assertNotNull(guardPost.getFoundingDate());
        assertEquals(KingdomEntity.Status.UNDER_CONSTRUCTION, guardPost.getStatus());
        assertEquals(0, guardPost.getIncidentCount());
    }

    @Test
    void parameterizedConstructorShouldSetValues() {
        GuardPost guardPost =
                new GuardPost(
                        "Royal Guard Post",
                        "Protects the capital city");

        assertEquals("Royal Guard Post", guardPost.getName());
        assertEquals("Protects the capital city", guardPost.getDescription());
        assertEquals(KingdomEntity.Status.OPERATIONAL, guardPost.getStatus());
    }

    @Test
    void identitiesShouldBeUnique() {
        GuardPost first = new GuardPost();
        GuardPost second = new GuardPost();

        assertNotEquals(first.getIdentity(), second.getIdentity());
    }

    @Test
    void patrolShouldIncreaseIncidentCount() {
        GuardPost guardPost = new GuardPost();

        String report = guardPost.patrol();

        assertEquals(1, guardPost.getIncidentCount());
        assertTrue(report.contains("1"));
    }

    @Test
    void multiplePatrolsShouldAccumulateIncidents() {
        GuardPost guardPost = new GuardPost();

        guardPost.patrol();
        guardPost.patrol();
        guardPost.patrol();

        assertEquals(3, guardPost.getIncidentCount());
    }

    @Test
    void reportToStationShouldReturnFalseWhenNoIncidentsExist() {
        GuardPost guardPost = new GuardPost();

        assertFalse(guardPost.reportToStation());
    }

    @Test
    void reportToStationShouldReturnTrueWhenIncidentsExist() {
        GuardPost guardPost = new GuardPost();

        guardPost.patrol();

        assertTrue(guardPost.reportToStation());
    }

    @Test
    void jacksonSerializationShouldPreserveState() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        GuardPost original =
                new GuardPost(
                        "Royal Guard Post",
                        "Protects the capital");

        original.patrol();
        original.patrol();

        String json = mapper.writeValueAsString(original);

        GuardPost restored =
                mapper.readValue(json, GuardPost.class);

        assertEquals(original.getIdentity(), restored.getIdentity());
        assertEquals(original.getName(), restored.getName());
        assertEquals(original.getDescription(), restored.getDescription());
        assertEquals(original.getIncidentCount(), restored.getIncidentCount());
        assertEquals(original.getStatus(), restored.getStatus());
    }
}