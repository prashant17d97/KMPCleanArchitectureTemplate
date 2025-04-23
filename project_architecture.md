# KMP Clean Architecture Template - Architecture Diagram

## This document provides a visual representation of the project architecture using Mermaid diagrams.

## Module Structure and Dependencies

```mermaid
graph TD
    %% Main Application Module
    composeApp[composeApp]

    %% Feature Modules
    featureOnboarding[feature-onboarding]
    featureHome[feature-home]

    %% Core Modules
    core[core]
    coreData[core-data]
    coreLocalDatasource[core-local-datasource]
    network[network]

    %% UI Modules
    uiAlert[ui-alert]

    %% iOS Entry Point
    iosApp[iosApp]

    %% Dependencies
    composeApp --> featureOnboarding
    composeApp --> featureHome
    composeApp --> uiAlert
    composeApp --> core

    featureOnboarding --> core
    featureOnboarding --> coreData
    featureOnboarding --> uiAlert

    featureHome --> core
    featureHome --> coreData
    featureHome --> uiAlert

    coreData --> core
    coreData --> network
    coreData --> coreLocalDatasource

    coreLocalDatasource --> core

    network --> core

    iosApp --> composeApp

    %% Styling
    classDef appModule color:#000, fill:#f9d77e,stroke:#d9b73a,stroke-width:2px
    classDef featureModule color:#000, fill:#a1d2f7,stroke:#5da9e9,stroke-width:2px
    classDef coreModule color:#000, fill:#c3e6cb,stroke:#82c596,stroke-width:2px
    classDef uiModule color:#000, fill:#f5c6cb,stroke:#e4868e,stroke-width:2px
    classDef platformModule color:#000, fill:#d6c5f7,stroke:#d6c5f7,stroke-width:2px

    class composeApp appModule
    class featureOnboarding,featureHome featureModule
    class core,coreData,coreLocalDatasource,network coreModule
    class uiAlert uiModule
    class iosApp platformModule
```

## Clean Architecture Layers

```mermaid
graph TD
    %% Architecture Layers
    presentation[Presentation Layer]
    domain[Domain Layer]
    data[Data Layer]

    %% Layer Components
    ui[UI Components]
    viewModels[ViewModels]
    navigation[Navigation]

    useCases[Use Cases]
    entities[Entities]
    repositoryInterfaces[Repository Interfaces]

    repositoryImplementations[Repository Implementations]
    dataSources[Data Sources]
    dataModels[Data Models]

    %% Layer Relationships
    presentation --> domain
    domain --> data

    %% Component Relationships
    presentation --> ui
    presentation --> viewModels
    presentation --> navigation

    domain --> useCases
    domain --> entities
    domain --> repositoryInterfaces

    data --> repositoryImplementations
    data --> dataSources
    data --> dataModels

    repositoryImplementations --> repositoryInterfaces

    %% Styling
    classDef presentationLayer color:#000, fill:#f9d77e,stroke:#d9b73a,stroke-width:2px
    classDef domainLayer color:#000, fill:#a1d2f7,stroke:#5da9e9,stroke-width:2px
    classDef dataLayer color:#000, fill:#c3e6cb,stroke:#82c596,stroke-width:2px

    class presentation,ui,viewModels,navigation presentationLayer
    class domain,useCases,entities,repositoryInterfaces domainLayer
    class data,repositoryImplementations,dataSources,dataModels dataLayer
```

## Module Mapping to Clean Architecture

```mermaid
graph TD
    %% Architecture Layers
    presentation[Presentation Layer]
    domain[Domain Layer]
    data[Data Layer]

    %% Modules
    composeApp[composeApp]
    featureOnboarding[feature-onboarding]
    featureHome[feature-home]
    uiAlert[ui-alert]
    core[core]
    coreData[core-data]
    coreLocalDatasource[core-local-datasource]
    network[network]

    %% Mapping
    presentation --> composeApp
    presentation --> featureOnboarding
    presentation --> featureHome
    presentation --> uiAlert

    domain --> core

    data --> coreData
    data --> coreLocalDatasource
    data --> network

    %% Styling
    classDef presentationLayer color:#000, fill:#f9d77e,stroke:#d9b73a,stroke-width:2px
    classDef domainLayer color:#000, fill:#a1d2f7,stroke:#5da9e9,stroke-width:2px
    classDef dataLayer color:#000, fill:#c3e6cb,stroke:#82c596,stroke-width:2px

    class presentation presentationLayer
    class domain domainLayer
    class data dataLayer

    class composeApp,featureOnboarding,featureHome,uiAlert presentationLayer
    class core domainLayer
    class coreData,coreLocalDatasource,network dataLayer
```

## Data Flow

```mermaid
sequenceDiagram
    participant UI as UI (Compose)
    participant VM as ViewModel
    participant UC as Use Case
    participant RI as Repository Interface
    participant R as Repository Implementation
    participant DS as Data Source
    participant API as Network API

    UI->>VM: User Action
    VM->>UC: Execute Use Case
    UC->>RI: Call Repository Method
    RI->>R: Implementation Handles Request

    alt Local Data
        R->>DS: Query Local Data
        DS-->>R: Return Local Data
    else Remote Data
        R->>API: Make API Request
        API-->>R: Return API Response
        R->>DS: Cache Data (if needed)
    end

    R-->>RI: Return Result
    RI-->>UC: Process Result
    UC-->>VM: Return Processed Data
    VM-->>UI: Update UI State
```

## This architecture follows the principles of Clean Architecture, ensuring separation of concerns and maintainability while enabling code sharing across platforms.
