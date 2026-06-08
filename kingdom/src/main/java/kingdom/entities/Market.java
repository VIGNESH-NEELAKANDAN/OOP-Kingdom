package kingdom.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import kingdom.contracts.AbstractMarket;
import kingdom.core.KingdomRegistry;

import java.time.LocalDate;
import java.util.UUID;

public class Market extends AbstractMarket {

    static {
        KingdomRegistry.register(Market.class);
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
    private double goldBalance;

    // No-arg constructor - UNDER_CONSTRUCTION
    public Market() {
        this.id = "MARKET-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.name = "Market";
        this.description = "A bustling center of commerce and trade.";
        this.foundingDate = LocalDate.now();
        this.status = Status.UNDER_CONSTRUCTION;
        this.goldBalance = 0.0;
    }

    // Parameterized constructor - OPERATIONAL
    public Market(String name, String description) {
        this();
        this.name = name;
        this.description = description;
        this.status = Status.OPERATIONAL;
    }

    @Override
    public void conductTrade(double tradeValue) {
        this.goldBalance += tradeValue;
    }

    @Override
    public double getGoldBalance() {
        return this.goldBalance;
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
}
