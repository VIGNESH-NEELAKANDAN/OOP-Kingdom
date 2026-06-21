package kingdom.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.UUID;
import kingdom.contracts.AbstractTradingPost;
import kingdom.core.KingdomRegistry;
import kingdom.core.KingdomEntity.Status;

public class TradingPost extends AbstractTradingPost {

    static {
        KingdomRegistry.register(TradingPost.class);
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
    private int resourceStockpile;

    @JsonProperty
    private int tradesCompleted;

    // Default constructor for serialization
    public TradingPost() {
        this.id = "TRADINGPOST-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.name = "Trading Post";
        this.description = "Barters processed Velfire stones for essential resources.";
        this.foundingDate = LocalDate.now();
        this.status = Status.UNDER_CONSTRUCTION;
        this.resourceStockpile = 0;
        this.tradesCompleted = 0;
    }

    // Parameterized constructor for explicit creation
    public TradingPost(String name, String description) {
        this.id = "TRADINGPOST-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.name = name;
        this.description = description;
        this.foundingDate = LocalDate.now();
        this.status = Status.OPERATIONAL;
        this.resourceStockpile = 0;
        this.tradesCompleted = 0;
    }

    @Override
    public boolean barter(int stoneCount) {
        if (stoneCount <= 0) {
            return false;
        }
        // Core mechanic: Each Velfire stone yields 5 essential resources
        this.resourceStockpile += stoneCount * 5;
        this.tradesCompleted++;
        return true;
    }

    @Override
    public int getResourceStockpile() {
        return this.resourceStockpile;
    }

    @Override
    public int getTradesCompleted() {
        return this.tradesCompleted;
    }

    // Implement KingdomEntity interface methods
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
}