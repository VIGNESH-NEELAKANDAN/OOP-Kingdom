package kingdom.entities;
import kingdom.contracts.AbstractArcheryGround;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class ArcheryGround extends AbstractArcheryGround {

    @JsonProperty("archers")
    private List<String> archers;
    private final String identity;
    private final LocalDate foundingDate;

    public ArcheryGround() {
        this.archers = new ArrayList<>();
        this.identity = UUID.randomUUID().toString();
        this.foundingDate = LocalDate.now();
    }

    @Override
    public String getIdentity() {
        return this.identity;
    }

    @Override
    public String getName() {
        return "Archery Ground";
    }

    @Override
    public String getDescription() {
        return "Training facility for ranged combatants.";
    }

    @Override
    public LocalDate getFoundingDate() {
        return this.foundingDate;
    }

    @Override
    public Status getStatus() {
        return Status.OPERATIONAL;
    }

    @Override
    public void trainArcher(String archerName) {
        if (archerName != null && !archerName.trim().isEmpty()) {
            archers.add(archerName);
        }
    }

    @Override
    public int getArcherCount() {
        return archers.size();
    }

    @Override
    public double holdPractice() {
        if (archers.isEmpty()) {
            return 0.0;
        }

        Random random = new Random();
        return 50.0 + (50.0 * random.nextDouble());
    }
}