package kingdom.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.UUID;
import kingdom.contracts.AbstractLumberyard;
import kingdom.core.KingdomRegistry;

/**
 * The Lumberyard is a kingdom entity responsible for harvesting wood from forests.
 * It maintains a stockpile of wood and can harvest more when needed.
 *
 * This is an example implementation of the AbstractLumberyard quest.
 * Contributors should follow this pattern when implementing other entities.
 *
 * Key Features:
 * - Harvests wood at a configurable rate per harvest
 * - Maintains a stockpile with storage limits
 * - Tracks status (OPERATIONAL, UNDER_CONSTRUCTION, DAMAGED)
 * - Integrates with CityHall as a managed entity
 */
public class Lumberyard extends AbstractLumberyard {

    static {
        KingdomRegistry.register(Lumberyard.class);
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
    private int woodStockpile;

    @JsonProperty
    private int harvestRate;

    @JsonProperty
    private int maxCapacity;

    /**
     * Default constructor for Jackson serialization and reflection-based instantiation.
     * Initializes safe defaults.
     */
    public Lumberyard() {
        this.id = "LUMBERYARD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.name = "Lumberyard";
        this.description = "A timber processing facility harvesting wood from nearby forests.";
        this.foundingDate = LocalDate.now();
        this.status = Status.UNDER_CONSTRUCTION;
        this.woodStockpile = 50; // Start with some initial wood
        this.harvestRate = 25; // Each harvest yields 25 wood
        this.maxCapacity = 500; // Maximum storable wood
    }

    /**
     * Parameterized constructor for explicit instantiation.
     */
    public Lumberyard(String name, int harvestRate, int maxCapacity) {
        this();
        this.name = name;
        this.harvestRate = harvestRate;
        this.maxCapacity = maxCapacity;
    }

    /**
     * Harvests wood from the nearby forest and adds it to the stockpile.
     * If stockpile would exceed capacity, harvest is capped at max capacity.
     * If in DAMAGED status, harvest is inefficient (reduced by 50%).
     */
    @Override
    public void harvestWood() {
        if (this.status == Status.DAMAGED) {
            // Damaged lumberyard is less efficient
            int reducedHarvest = this.harvestRate / 2;
            this.woodStockpile = Math.min(this.woodStockpile + reducedHarvest, this.maxCapacity);
        } else {
            // Normal harvest
            this.woodStockpile = Math.min(this.woodStockpile + this.harvestRate, this.maxCapacity);
        }
    }

    /**
     * Retrieves the current amount of wood stored.
     */
    @Override
    public int getWoodStockpile() {
        return this.woodStockpile;
    }

    /**
     * Retrieves the harvest rate (wood per harvest operation).
     */
    public int getHarvestRate() {
        return this.harvestRate;
    }

    /**
     * Sets the harvest rate.
     */
    public void setHarvestRate(int harvestRate) {
        this.harvestRate = harvestRate;
    }

    /**
     * Retrieves the maximum storage capacity.
     */
    public int getMaxCapacity() {
        return this.maxCapacity;
    }

    /**
     * Consumes wood from the stockpile for construction or crafting.
     * Returns true if successful, false if insufficient wood.
     */
    public boolean consumeWood(int amount) {
        if (amount < 0) {
            return false;
        }
        if (this.woodStockpile >= amount) {
            this.woodStockpile -= amount;
            return true;
        }
        return false;
    }

    /**
     * Repairs the lumberyard, changing status from DAMAGED to OPERATIONAL.
     * Requires sufficient wood.
     */
    public boolean repair() {
        if (this.status == Status.DAMAGED && this.consumeWood(100)) {
            this.status = Status.OPERATIONAL;
            return true;
        }
        return false;
    }

    @Override
    public String getIdentity() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public LocalDate getFoundingDate() {
        return this.foundingDate;
    }

    @Override
    public Status getStatus() {
        return this.status;
    }

    /**
     * Sets the status of the lumberyard.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Sets the stockpile directly (useful for testing and initialization).
     */
    public void setWoodStockpile(int amount) {
        this.woodStockpile = Math.min(amount, this.maxCapacity);
    }
}
