package kingdom.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArcheryGroundTest {

    @Test
    public void testTrainArcherAndCount() {
        ArcheryGround ground = new ArcheryGround();
        assertEquals(0, ground.getArcherCount(), "Initial count should be 0");
        ground.trainArcher("Robin");
        ground.trainArcher("Legolas");
        assertEquals(2, ground.getArcherCount(), "Count should be 2 after training");
    }

    @Test
    public void testHoldPractice() {
        ArcheryGround ground = new ArcheryGround();
        assertEquals(0.0, ground.holdPractice(), "Accuracy should be 0 with no archers");
        ground.trainArcher("Artemis");
        double accuracy = ground.holdPractice();
        assertTrue(accuracy >= 0.0 && accuracy <= 100.0, "Accuracy must be between 0 and 100");
    }
}