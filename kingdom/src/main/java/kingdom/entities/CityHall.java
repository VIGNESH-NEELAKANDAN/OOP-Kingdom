package kingdom.entities;

import java.time.LocalDate;
import kingdom.core.KingdomEntity;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import kingdom.contracts.CityHallContract;
import kingdom.core.KingdomRegistry;

public class CityHall implements CityHallContract {

    static {
        KingdomRegistry.register(CityHall.class);
    }

    @JsonProperty("identity")
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private LocalDate foundingDate;

    @JsonProperty
    private String description;

    @JsonProperty
    private Status status;

    @JsonProperty
    private List<KingdomEntity> cityEntities;

    // Default constructor for serialization
    public CityHall() {
        this.cityEntities = new ArrayList<>();
    }

    public CityHall(String id, String name) {
        this.id = id;
        this.name = name;
        this.description = "";
        this.foundingDate = LocalDate.now();
        this.status = Status.UNDER_CONSTRUCTION;
        this.cityEntities = new ArrayList<>();
    }

    public String getIdentity() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDate getFoundingDate() {
        return this.foundingDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public List<KingdomEntity> getCityEntities() {
        return cityEntities;
    }

    @Override
    public String registerEntity(KingdomEntity entity) {
        this.cityEntities.add(entity);
        return "Registered Successfully!!";
    }
}
