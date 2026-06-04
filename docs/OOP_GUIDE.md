# 📘 Object-Oriented Programming in the Kingdom

> *The OOP Kingdom isn't just a fun theme — every design decision is rooted in a real OOP principle.*
> This guide explains the four pillars of OOP using kingdom examples so you can apply them in your own implementation.

---

## The Four Pillars

| Pillar | One-line summary |
|--------|-----------------|
| **Encapsulation** | Each building manages its own data — no outsider can reach inside |
| **Abstraction** | Contributors build to a contract, not an implementation |
| **Inheritance** | Every building shares common DNA through `KingdomEntity` |
| **Polymorphism** | The kingdom can treat a `Blacksmith` and a `Market` the same way — as a `KingdomEntity` |

---

## 1. Encapsulation — The Blacksmith's Workshop

> *No citizen has the right to walk into the Blacksmith's workshop and change the weapon count by hand.*

**What it means:** An object owns its data. Other objects must ask politely (via methods) — they can't reach in and change fields directly.

**In Java:**

```java
public class Blacksmith extends AbstractBlacksmith {

    private int weaponCount;    // ✅ Private — only Blacksmith controls this

    // Others must ask through a method
    public int getWeaponCount() {
        return weaponCount;
    }

    // Only Blacksmith can forge — external code can't just say blacksmith.weaponCount++
    public void forgeWeapon(String weaponName) {
        if (this.status == EntityStatus.OPERATIONAL) {
            this.weaponCount++;
        }
    }
}
```

**Why it matters:** If anyone could directly modify `weaponCount`, you'd have no guarantee it was done correctly — no status check, no logging, no validation. Encapsulation ensures invariants hold.

---

## 2. Abstraction — The Quest Contract

> *The kingdom doesn't care how you build the Barracks — it just needs to know that it can train troops.*

**What it means:** You define *what* something can do, without saying *how* it does it. The contract describes capabilities; the implementation decides the details.

**In OOP Kingdom, this is the abstract contract:**

```java
public abstract class AbstractBarracks implements KingdomEntity {

    // The kingdom knows every Barracks can do this
    public abstract void trainTroop(String troopName);
    public abstract List<String> getTroops();
}
```

**Your implementation decides the how:**

```java
public class Barracks extends AbstractBarracks {

    private List<String> troops = new ArrayList<>();
    private static final int MAX_CAPACITY = 20;

    @Override
    public void trainTroop(String troopName) {
        if (troops.size() < MAX_CAPACITY) {
            troops.add(troopName);
        }
        // Your design choice: silently ignore overflow? Throw an exception? Log a warning?
    }
}
```

**Why it matters:** Abstraction allows the kingdom to grow without anyone needing to rewrite the core. A future `EliteBarracks` can extend `AbstractBarracks` with its own implementation, and the rest of the codebase doesn't need to change.

---

## 3. Inheritance — The Kingdom's DNA

> *Every building in the kingdom, no matter how different, carries the same founding charter.*

**What it means:** Classes can inherit behaviour and structure from parent classes. In OOP Kingdom, every entity inherits from `KingdomEntity` through its abstract contract.

**The inheritance chain:**

```
KingdomEntity (interface)
    └── AbstractBarracks (abstract class — your quest contract)
            └── Barracks (your implementation)
```

**What you get for free by implementing `KingdomEntity`:**
- A unique `id` (UUID) for every instance
- An `EntityStatus` (OPERATIONAL / DAMAGED / DESTROYED)
- Jackson serialization annotations

**In practice:**

```java
// Because Barracks IS a KingdomEntity through inheritance,
// the kingdom can store it alongside every other building:
List<KingdomEntity> buildings = new ArrayList<>();
buildings.add(new Barracks());
buildings.add(new Blacksmith());
buildings.add(new Market());
```

---

## 4. Polymorphism — One Kingdom, Many Buildings

> *The kingdom's census officer doesn't care whether the building is a Blacksmith or a Market — they just check the status of each building on their list.*

**What it means:** Different classes can be treated as the same type (their shared interface/parent), and each responds to the same method call in its own way.

```java
List<KingdomEntity> allBuildings = kingdom.getBuildings();

for (KingdomEntity building : allBuildings) {
    // The same method call works on every building type
    System.out.println(building.getId() + " → " + building.getStatus());
}
```

Even though `building` could be a `Blacksmith`, `Barracks`, or `Market`, calling `.getStatus()` works on all of them — each class provides its own implementation.

**Why it matters:** Polymorphism is what allows the `Kingdom.java` core to work with *any* entity you add, without ever being modified. New buildings plug in; the kingdom grows.

---

## Putting It All Together

When you implement a quest entity, you're using all four pillars simultaneously:

| Pillar | Where it shows up in your code |
|--------|-------------------------------|
| Encapsulation | `private` fields, getters/setters, validation in methods |
| Abstraction | You implement the abstract contract's required methods |
| Inheritance | Your class `extends AbstractBarracks` |
| Polymorphism | Your class can be stored in a `List<KingdomEntity>` |

---

## Further Reading

- [CODE_STANDARDS.md](CODE_STANDARDS.md) — The rules that enforce these principles in your PR
- [Lumberyard.java](../kingdom/src/main/java/kingdom/entities/Lumberyard.java) — A fully implemented example showing all four pillars in action
