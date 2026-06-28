package kingdom.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import kingdom.contracts.AbstractPrison;
import kingdom.core.KingdomRegistry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Prison extends AbstractPrison {

    static {
        KingdomRegistry.register(Prison.class);
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
    private List<String> inmates;

    public Prison() {
        this.id = "PRISON-" + UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 8)
                .toUpperCase();
        this.name = "Prison";
        this.description = "A secure facility for holding convicted criminals within the kingdom.";
        this.foundingDate = LocalDate.now();
        this.status = Status.UNDER_CONSTRUCTION;
        this.inmates = new ArrayList<>();
    }

    public Prison(String name, String description) {
        this();
        this.name = name;
        this.description = description;
        this.status = Status.OPERATIONAL;
    }

    @Override
    public void imprison(String criminalName) {
        if (criminalName == null || criminalName.isBlank()) {
            return;
        }
        String trimmed = criminalName.trim();
        if (!inmates.contains(trimmed)) {
            inmates.add(trimmed);
        }
    }

    @Override
    public void release(String criminalName) {
        if (criminalName == null || criminalName.isBlank()) {
            return;
        }
        inmates.remove(criminalName.trim());
    }

    @Override
    public int getInmateCount() {
        return inmates.size();
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