# ⚔️ Week 04 Quest: The Warning

The POP Kingdom has delivered an ultimatum — cease all trade or face war. General Phir has seen the city and dismissed it as a tiny settlement that will crumble. The people have chosen to stand their ground. Now the kingdom must build an army.

The Barracks exists, but it has never trained true soldiers. This week, the city recruits its first defenders — and builds the training grounds to forge them into fighters.

## Available Entities

| Entity | Contract | Status |
|--------|----------|--------|
| **Archery Ground** | [`AbstractArcheryGround`](../kingdom/src/main/java/kingdom/contracts/AbstractArcheryGround.java) | ⚔️ Quest open |
| **Explosive Arena** | [`AbstractExplosiveArena`](../kingdom/src/main/java/kingdom/contracts/AbstractExplosiveArena.java) | ⚔️ Quest open |
| **Siege Workshop** | [`AbstractSiegeWorkshop`](../kingdom/src/main/java/kingdom/contracts/AbstractSiegeWorkshop.java) | ⚔️ Quest open |

> **Note for all entities:** The `Barracks` entity already exists at `kingdom/entities/Barracks.java`. These training facilities should complement it — the Barracks trains soldiers in melee combat, while the Archery Ground, Explosive Arena, and Siege Workshop handle ranged, explosive, and siege specialization respectively.

## Instructions

1. **Pick an entity** from the table above
2. **Read the contract** — open the corresponding abstract class in `kingdom/contracts/`
3. **Implement the class** in `kingdom/entities/` — extend the contract, implement all methods, add `@JsonProperty`, register with `KingdomRegistry`
4. **Write tests** in `kingdom/src/test/java/kingdom/entities/` — constructor, contract methods, extra methods, Jackson serialization
5. **Test locally:**
   ```bash
   cd kingdom
   mvn clean test
   ```
6. **Boot check:**
   ```bash
   cd kingdom
   mvn exec:java -Dexec.mainClass="kingdom.Main"
   ```
7. **Update [`contributors.json`](../contributors.json):** `"YourClass": "YourGitHubUsername"`
8. **Create a UML diagram** (optional — save in `uml/yourclass.md`, include only directly related classes, earns bonus points during review)
9. **Submit a PR** using the [PR Template](../.github/PULL_REQUEST_TEMPLATE.md)

> **Note:** Issues are **not assigned.** Multiple contributors can work on the same entity. Everyone submits their best design, the community scores them, and the highest-scoring implementation gets merged.

---

## Reference

| Doc | Link |
|-----|------|
| Code Standards | [CODE_STANDARDS.md](../docs/CODE_STANDARDS.md) |
| Build & Test | [BUILD.md](../docs/BUILD.md) |
| Review Rubric | [REVIEW_RUBRIC.md](../docs/REVIEW_RUBRIC.md) |
| Quest Guide | [template.md](../quests/template.md) |
| Contributing Guide | [CONTRIBUTING.md](../.github/CONTRIBUTING.md) |

---

_May the best design win. ⚔️_