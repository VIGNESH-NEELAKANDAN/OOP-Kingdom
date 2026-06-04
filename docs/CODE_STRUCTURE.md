# 📁 How the Kingdom Is Organised

> *Before you can build, you need to know where things go.*
> This document explains every folder and file in the repository.

---

## Full Repository Layout

```
oop-kingdom/
│
├── kingdom/                    # 🏗️  The Java codebase (Maven project)
│   ├── pom.xml                 #     Maven build configuration
│   └── src/
│       ├── main/java/kingdom/
│       │   ├── core/           #     Foundational interfaces & enums
│       │   ├── contracts/      #     Quest abstract contracts
│       │   ├── entities/       #     Merged kingdom buildings live here
│       │   ├── Kingdom.java    #     🔒 Maintainer-only
│       │   └── Main.java       #     Application entry point
│       └── test/java/kingdom/  #     Unit and integration tests
│
├── quests/                     # 📜  Weekly quest drops
│   └── week-01/
│       └── quest.md            #     This week's available buildings
│
├── chronicles/                 # 📖  The kingdom's lore and history
│   ├── chapter-00.md
│   ├── chapter-01.md
│   └── mentions.md             #     Hall of Honourable Mentions
│
├── state/                      # 💾  Auto-generated — do not edit manually
│   └── kingdom.json
│
├── docs/                       # 📚  All guides and documentation
│   ├── SETUP.md                #     ← New contributor? Start here
│   ├── BUILD.md
│   ├── CODE_STANDARDS.md
│   ├── CODE_STRUCTURE.md       #     This file
│   ├── REVIEW_RUBRIC.md
│   ├── RANKS.md
│   └── OOP_GUIDE.md
│
├── .github/
│   ├── CONTRIBUTING.md         #     Contribution rules and workflow
│   └── PULL_REQUEST_TEMPLATE.md
│
├── contributors.json           # 🏆  Registry of who built what
├── CODE_OF_CONDUCT.md
├── LICENSE
└── README.md
```

---

## Core Packages Explained

### `core/` — The Kingdom's Foundation

This is where the bedrock of the kingdom lives. **Do not modify anything here** — these files are maintainer-only.

| File | What it does |
|------|-------------|
| `KingdomEntity.java` | The master interface every building must implement |
| `EntityStatus.java` | Enum representing building states: `OPERATIONAL`, `DAMAGED`, `DESTROYED` |
| `KingdomRegistry.java` | Tracks all registered entity types in the kingdom |

Every entity you build will implement `KingdomEntity` (through its abstract contract). Think of it as the building permit every structure in the kingdom must carry.

---

### `contracts/` — The Quest Blueprints

Each weekly quest introduces new abstract contracts here. These define the **minimum requirements** your building must meet — the methods you're required to implement.

```
contracts/
├── AbstractBarracks.java     # "Build something that can train troops"
├── AbstractBlacksmith.java   # "Build something that can forge weapons"
├── AbstractMarket.java       # "Build something that manages trade"
└── AbstractLumberyard.java   # Already implemented — see entities/
```

Contracts are intentionally sparse. They tell you *what* to implement, not *how*. Your design choices determine your score.

---

### `entities/` — Where Your Work Lives

This is where merged implementations go. Every file here was written by a contributor and won the quest for their week.

```
entities/
├── Lumberyard.java     # Reference implementation — read this first!
└── Farm.java           # Week 01 merged implementation
```

**Your goal:** add your class here by winning the weekly quest.

---

### `test/` — Proving Your Code Works

Every entity in `entities/` has a corresponding test file here. Tests must pass for a PR to be considered.

```
test/
├── LumberyardTest.java     # Reference test — shows you exactly what's expected
└── FarmTest.java
```

Study `LumberyardTest.java` before writing your own tests — it covers every pattern you'll need.

---

## The Layering Rule

The packages are designed to follow a strict dependency direction:

```
core/  ←  contracts/  ←  entities/
```

- `core` knows nothing about `contracts` or `entities`
- `contracts` may depend on `core` only
- `entities` may depend on `core` and `contracts`

**Never import an `entities/` class from inside a `contracts/` file.** This breaks the layering and teaches backwards dependency — a real anti-pattern in professional Java.
