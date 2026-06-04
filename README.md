# ⚔️ OOP Kingdom

> *A collaborative open-source kingdom, built stone by stone — entirely through Java code.*

---

## 📖 The Story

For centuries, the **POP Kingdom** stood unchallenged.

Its laws were absolute — every citizen followed a single path, a long unbroken sequence of instructions handed down from the Royal Procedure. You did not think. You did not own. You simply executed, in order, as commanded.

But there were those who questioned.

> *"Why must the blacksmith know the baker's recipe to do his work?"*

They were called troublemakers. And so they were banished.

Thirty-seven souls crossed the eastern border with nothing but their skills, their names, and one shared belief — that every entity deserves its own purpose, its own data, its own way of interacting with the world.

They called it the **OOP Kingdom**.

**And they needed builders.**

---

## 🏰 What Is OOP Kingdom?

OOP Kingdom is a **collaborative open-source Java project** where contributors collectively build a virtual kingdom — one class at a time.

Every week, a new chapter of the kingdom's story is released describing what the kingdom needs next — a marketplace, a blacksmith, a postal service. You design and implement these as real, object-oriented Java code, raise a pull request, and the best implementation becomes the kingdom's official canon.

**This is the closest thing to contributing to a real company codebase — without needing to be hired first.**

Whether you're a complete beginner learning Java for the first time, or someone looking to sharpen your OOP skills on a living codebase, OOP Kingdom is built for you.

---

## 🚀 Quick Start — New Here?

**Never contributed to open source before? Start here. 👇**

| Step | What to do |
|------|------------|
| 1️⃣ | Read [SETUP.md](docs/SETUP.md) — fork, clone, install Java 17 & Maven |
| 2️⃣ | Read [Chapter 00](chronicles/chapter-00.md) to understand the world |
| 3️⃣ | Study [Lumberyard.java](kingdom/src/main/java/kingdom/entities/Lumberyard.java) — it's your reference implementation |
| 4️⃣ | Open [quests/week-01/quest.md](quests/week-01/quest.md) and pick a building to implement |
| 5️⃣ | Build it, test it, raise a PR! |

> 💡 Stuck? Open a [Discussion](../../discussions) and ask — no question is too basic.

---

## ⚙️ How It Works

```
📜 Quest Drops  →  🛠️ You Code  →  📬 Raise a PR  →  👑 Best PR Merged  →  🏙️ Kingdom Grows
```

1. Every week, a `quest.md` is released describing the new buildings the kingdom needs
2. You implement your building as a Java class and raise a PR
3. The community reviews all PRs using the [Review Rubric](docs/REVIEW_RUBRIC.md)
4. The best implementation gets merged and you're permanently credited as a **Kingdom Official**
5. All contributors whose PRs were valid receive recognition in the [Hall of Honourable Mentions](chronicles/mentions.md)

---

## 🗺️ Kingdom Structure

```
Kingdom
└── CityHall
      ├── Lumberyard      ✅ built
      ├── Farm            ✅ built
      ├── Blacksmith      ⚔️  this week's quest
      ├── Market          ⚔️  this week's quest
      ├── Barracks        ⚔️  this week's quest
      └── ...             🔒  coming in future quests
```

Every entity implements `KingdomEntity` — the core contract every building must follow.

---

## 📜 The Rules

> The kingdom runs on clear laws. Break them and your PR gets rejected. Follow them and you build something that lasts.

| Rule | Detail |
|------|--------|
| **Language** | Java only. No exceptions. |
| **PR Scope** | One class + its test + `contributors.json` per PR. No bundling. |
| **No Pre-booking** | Anyone can implement any quest entity. Best design wins. |
| **PR Limit** | Max 2 PRs per contributor per week. |
| **UML Required** | Every PR must include a Mermaid UML diagram of your class. |
| **Core Files Off-limits** | `KingdomEntity.java`, `Kingdom.java`, `CityHall.java` are maintainer-only. |
| **CI Must Pass** | If your PR breaks the build, it's rejected. |

---

## ⚖️ How PRs Are Scored

| Criteria | Points |
|----------|--------|
| Passes CI | **Mandatory** |
| Follows quest requirements | 10 |
| OOP quality (SRP, encapsulation, inheritance) | 20 |
| Extensibility (can future contributors build on it?) | 10 |
| UML accuracy | 10 |
| Naming & readability | 10 |
| **Total** | **60** |

The highest score wins the merge. Full rubric → [REVIEW_RUBRIC.md](docs/REVIEW_RUBRIC.md)

---

## 👑 Contributor Ranks

As your PRs get merged, you ascend the ranks of the kingdom.

| Rank | Title | Requirement |
|------|-------|-------------|
| 🌱 | **Settler** | 1st merged PR |
| ⚒️ | **Craftsman** | 3 merged PRs |
| 🏛️ | **Architect** | 7 merged PRs |
| 👑 | **Royal Council** | 15 merged PRs |

---

## 🏆 What Do You Get?

- A **permanent credit** on the kingdom website as a Kingdom Official
- A **real open-source contribution** on your GitHub profile
- **OOP + system design practice** on a living, growing codebase — not an isolated exercise
- Code that gets **peer reviewed** on actual design decisions
- The ability to point at a building in the kingdom and say *"I built that"*

---

## 📚 Read More

| Document | What it covers |
|----------|---------------|
| [🛠️ SETUP.md](docs/SETUP.md) | Fork → Clone → Install → Build (start here!) |
| [📖 Chronicles](chronicles/chapter-00.md) | The full kingdom story |
| [🤝 Contributing Guide](.github/CONTRIBUTING.md) | Step-by-step contribution rules |
| [🔨 Build Guide](docs/BUILD.md) | Maven commands to compile and test |
| [📐 Code Standards](docs/CODE_STANDARDS.md) | Java OOP rules we enforce |
| [📁 Code Structure](docs/CODE_STRUCTURE.md) | Repository layout explained |
| [⚖️ Review Rubric](docs/REVIEW_RUBRIC.md) | How PRs are judged |
| [👑 Contributor Ranks](docs/RANKS.md) | Rank requirements and rewards |
| [📘 OOP Guide](docs/OOP_GUIDE.md) | OOP concepts with kingdom-themed examples |

---

## 🌐 Website

The kingdom's live state, story, and contributor hall of fame:
**[oop-kingdom.github.io](https://oop-kingdom.github.io)** *(coming soon)*

---

*Every entity deserves its own purpose. Every contributor deserves their credit. Build well.*

⚔️ **The kingdom awaits.**
