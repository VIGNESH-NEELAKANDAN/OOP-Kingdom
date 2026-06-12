package kingdom.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.UUID;
import kingdom.contracts.AbstractBlacksmith;
import kingdom.core.KingdomRegistry;

public class Blacksmith extends AbstractBlacksmith {

    static {
        KingdomRegistry.register(Blacksmith.class);
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
    private int weaponCount;

    @JsonProperty
    private int anvilDurability;

    private static final int MAX_DURABILITY = 3;

    // Default constructor for Jackson serialization
    public Blacksmith() {
        this.id = "BLACKSMITH-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.name = "Royal Forge";
        this.description = "A standard kingdom forge for weapons and ironworks.";
        this.foundingDate = LocalDate.now();
        this.status = Status.UNDER_CONSTRUCTION; 
        this.weaponCount = 0;
        this.anvilDurability = MAX_DURABILITY;
    }

    // Parameterized constructor for explicit creation
    public Blacksmith(String name, String description) {
        this.id = "BLACKSMITH-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.name = name;
        this.description = description;
        this.foundingDate = LocalDate.now();
        this.status = Status.OPERATIONAL; 
        this.weaponCount = 0;
        this.anvilDurability = MAX_DURABILITY;
    }

    @Override
    public void forgeWeapon() {
        if (this.status == Status.DAMAGED) {
            System.out.println(name + " cannot forge weapons! The anvil is broken. Call repairAnvil().");
            return;
        }

        this.weaponCount++;
        this.anvilDurability--;

        if (this.anvilDurability <= 0) {
            this.status = Status.DAMAGED;
            System.out.println("💥 Clang! The anvil has cracked from heavy use at " + name + ". Status set to DAMAGED.");
        } else {
            System.out.println("⚔️ A fine weapon was forged at " + name + ". Current stock: " + weaponCount);
        }
    }

    @Override
    public int getWeaponCount() {
        return this.weaponCount;
    }

    @Override
    public void repairAnvil() {
        this.anvilDurability = MAX_DURABILITY;
        this.status = Status.OPERATIONAL;
        System.out.println("🔨 The anvil at " + name + " has been thoroughly repaired and is fully OPERATIONAL.");
    }

    // Getters for KingdomEntity methods
    @Override public String getIdentity() { return id; }
    @Override public String getName() { return name; }
    @Override public String getDescription() { return description; }
    @Override public LocalDate getFoundingDate() { return foundingDate; }
    @Override public Status getStatus() { return status; }
    
    // Additional getter for testing internal state
    public int getAnvilDurability() { return anvilDurability; }
}