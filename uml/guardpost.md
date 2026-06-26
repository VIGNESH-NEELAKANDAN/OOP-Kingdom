# GuardPost UML Diagram

```mermaid
classDiagram

class KingdomEntity {
    <<interface>>
    +String getIdentity()
    +String getName()
    +String getDescription()
    +LocalDate getFoundingDate()
    +Status getStatus()
}

class AbstractGuardPost {
    <<abstract>>
    +patrol() String
    +getIncidentCount() int
    +reportToStation() boolean
}

class GuardPost {
    -String id
    -String name
    -String description
    -LocalDate foundingDate
    -Status status
    -int incidentCount

    +GuardPost()
    +GuardPost(String name, String description)

    +patrol() String
    +getIncidentCount() int
    +reportToStation() boolean

    +getIdentity() String
    +getName() String
    +getDescription() String
    +getFoundingDate() LocalDate
    +getStatus() Status
}

KingdomEntity <|.. AbstractGuardPost
AbstractGuardPost <|-- GuardPost
```

## Design Notes

- `GuardPost` extends `AbstractGuardPost` and fulfills all required contract methods.
- The entity maintains a single piece of operational state: `incidentCount`.
- Calling `patrol()` simulates a patrol by incrementing the incident count and returning a patrol report.
- `reportToStation()` returns whether there are incidents available to report without modifying the stored incident history.
- The implementation intentionally keeps the design simple and focused on the contract while following the project's OOP conventions.