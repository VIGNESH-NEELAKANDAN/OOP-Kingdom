package kingdom.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import kingdom.contracts.AbstractGuardStation;
import kingdom.core.KingdomRegistry;

import java.time.LocalDate;
import java.util.*;

public class GuardStation extends AbstractGuardStation {

    static {
        KingdomRegistry.register(GuardStation.class);
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
    private Map<String, Integer> districtPatrols;

    @JsonProperty
    private int activePatrolCount = 0;

    // ---------------- Constructors ----------------

    public GuardStation() {
        this.id = "GUARD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.name = "Guard Station";
        this.description = "Coordinates patrol units across kingdom districts.";
        this.foundingDate = LocalDate.now();
        this.status = Status.UNDER_CONSTRUCTION;
        this.districtPatrols = new HashMap<>();
    }

    public GuardStation(String name, String description) {
        this();
        this.name = name;
        this.description = description;
        this.status = Status.OPERATIONAL;
    }

    // ---------------- Required Methods ----------------

    @Override
    public void assignPatrol(String districtName) {
        if (districtName == null || districtName.isBlank()) {
            throw new IllegalArgumentException("District name cannot be empty");
        }

        districtPatrols.merge(districtName.trim(), 1, Integer::sum);
    }

    @Override
    public int getActivePatrolCount() {
        return districtPatrols.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public String coordinateResponse() {
        if (districtPatrols.isEmpty()) {
            return "No active patrols available.";
        }

        return "Coordinating response across "
                + districtPatrols.size()
                + " districts with "
                + getActivePatrolCount()
                + " active patrols.";
    }

    // ---------------- KingdomEntity Methods ----------------

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

    // ---------------- Getter ----------------

    public Map<String, Integer> getDistrictPatrols() {
        return Collections.unmodifiableMap(districtPatrols);
    }
}