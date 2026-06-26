package kingdom.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import kingdom.contracts.AbstractGuardPost;
import kingdom.core.KingdomRegistry;

import java.time.LocalDate;
import java.util.UUID;

public class GuardPost extends AbstractGuardPost {

    static {
        KingdomRegistry.register(GuardPost.class);
    }

    @JsonProperty("identity")
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String description;

    @JsonProperty
    private LocalDate foundingDate;

    @JsonProperty
    private Status status;

    @JsonProperty
    private int incidentCount;

    public GuardPost() {
        this.id = "GUARDPOST-" + UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 8)
                .toUpperCase();
        this.name = "Guard Post";
        this.description = "A military outpost responsible for patrolling the kingdom and reporting incidents.";
        this.foundingDate = LocalDate.now();
        this.status = Status.UNDER_CONSTRUCTION;
        this.incidentCount = 0;
    }

    public GuardPost(String name, String description) {
        this();
        this.name = name;
        this.description = description;
        this.status = Status.OPERATIONAL;
    }

    @Override
    public String patrol() {
        incidentCount++;
        return "Patrol completed successfully. Total incidents recorded: " + incidentCount;
    }

    @Override
    public int getIncidentCount() {
        return incidentCount;
    }

    @Override
    public boolean reportToStation() {
        return incidentCount > 0;
    }

    @Override
    public String getIdentity() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public LocalDate getFoundingDate() {
        return foundingDate;
    }

    @Override
    public Status getStatus() {
        return status;
    }

}