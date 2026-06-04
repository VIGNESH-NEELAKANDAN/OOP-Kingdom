# 📐 Laws of the Kingdom

> *The kingdom is only as strong as the code that holds it together.*
> Follow these standards to get your PR merged. Violate them and it gets rejected — no exceptions.

---

## 1. Object-Oriented Principles

These aren't just rules — they're the entire *point* of this project. Every standard here maps to a real OOP principle you'll use in every professional Java job.

---

### 🔒 Encapsulation — Keep Your Secrets

> *"The blacksmith shouldn't know the baker's recipe to do his work."*

- **All fields must be `private` or `protected`.** Never `public`.
- **Expose state only through getters and setters.** If a field doesn't need to be changed from outside, don't write a setter for it.

```java
// ✅ Correct
private int woodStockpile;

public int getWoodStockpile() {
    return woodStockpile;
}

// ❌ Wrong — never do this
public int woodStockpile;
```

---

### 🎯 Single Responsibility Principle (SRP) — One Job Per Class

> *A Blacksmith forges tools. It doesn't collect taxes or sell bread.*

- Every class should have **one, and only one, reason to change**.
- If you find your class doing too many unrelated things — break it into smaller classes.

**Ask yourself:** *"If I describe this class in one sentence, does it require the word 'and'?"*
If yes, you probably have two classes hiding inside one.

---

### 🧬 Inheritance & Polymorphism — Build on What Exists

- Extend the abstract contract provided for your quest entity (e.g., `AbstractBlacksmith`).
- Always use the `@Override` annotation when implementing inherited methods — it catches typos at compile time.
- Favour **composition over inheritance** where it makes sense, but use inheritance correctly when the quest requires it.

```java
// ✅ Always annotate overridden methods
@Override
public String getId() {
    return this.id;
}
```

---

### ➕ Extra Methods — Go Beyond the Contract

You are **encouraged** to add extra properties, getters, setters, and business logic methods beyond what the abstract contract requires. Extra methods make your implementation richer and earn more points on the [Review Rubric](REVIEW_RUBRIC.md).

**Rule:** Any extra method you add **must have a unit test.** Untested extra methods will result in PR rejection.

---

## 2. Naming Conventions

Consistent naming makes the codebase readable. These are non-negotiable.

| Thing | Convention | Example |
|-------|-----------|---------|
| **Classes** | `PascalCase` matching the contract | `Blacksmith.java`, `Market.java` |
| **Test Classes** | `PascalCase` + `Test` suffix | `BlacksmithTest.java`, `MarketTest.java` |
| **Methods** | `camelCase` | `harvestWood()`, `trainTroop()` |
| **Variables & Fields** | `camelCase` | `woodStockpile`, `troopCount` |
| **Constants** | `UPPER_SNAKE_CASE` | `MAX_CAPACITY`, `DEFAULT_STATUS` |

---

## 3. Testing Standards

> *Code without tests is just hope.*

Every submitted class **must** have a corresponding test class. Tests are not optional — they are part of the definition of "done."

### Required Test Coverage

| Test Type | What to Test |
|-----------|-------------|
| **Constructor Tests** | Default constructor sets safe, non-null default values |
| **Business Logic Tests** | Methods perform their operations and modify state correctly |
| **Edge Case Tests** | What happens with invalid input, zero values, boundary conditions? |
| **Serialization Tests** | The class serializes and deserializes via Jackson without losing state |
| **Extra Method Tests** | Every custom method you added must have its own test |

### Serialization Test Template

Every entity must survive a Jackson round-trip (serialize → deserialize → verify). Here's the pattern:

```java
@Test
void testSerialization() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    Blacksmith original = new Blacksmith();
    // ... set up state

    String json = mapper.writeValueAsString(original);
    Blacksmith restored = mapper.readValue(json, Blacksmith.class);

    assertEquals(original.getId(), restored.getId());
    // ... verify all fields match
}
```

> 🔧 New to Jackson? Read [JACKSON_GUIDE.md](JACKSON_GUIDE.md) before writing your tests.

---

## 4. Documentation (JavaDoc)

- Write JavaDoc for **all public classes and methods**.
- Explain *why* a design decision was made, not just *what* the code does.

```java
/**
 * Represents the kingdom's lumber production facility.
 *
 * The Lumberyard harvests wood from the surrounding forest and stores it
 * in a stockpile. Wood is consumed by construction projects elsewhere in
 * the kingdom — which is why stockpile access is read-only from outside.
 */
public class Lumberyard extends AbstractLumberyard {
```

---

## 5. The Golden Rule

> **If it breaks the kingdom, it doesn't get merged.**

Your code must pass CI. If your PR causes another contributor's previously merged building to fail, you must resolve the conflict — or your PR gets closed.

Run `mvn clean test` locally before every PR. Always.
