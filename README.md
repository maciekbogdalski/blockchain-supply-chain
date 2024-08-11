# Blockchain Supply Chain - Plant Tracking System

## Overview

This project implements a plant tracking system using Hyperledger Fabric to ensure the end-to-end traceability of basil plants in the supply chain, from the greenhouse to the supermarket. The system allows users to track the ownership, state, and history of each plant, ensuring transparency and accountability throughout the supply chain.

## Features

- **Create Plant Tracking**: Start tracking a new plant with a unique ID (QR code).
- **Update Plant State**: Record changes in the plant's state during transportation, including GPS position and timestamp.
- **Transfer Ownership**: Transfer the ownership of a plant between organizations (e.g., from supplier to supermarket).
- **Query Plant State**: Retrieve the current state of a plant.
- **Query Plant History**: Retrieve the full history of a plant's state changes.

## Technology Stack

- **Hyperledger Fabric**: A permissioned blockchain framework for building distributed ledger solutions.
- **Java**: Used for developing smart contracts (chaincode) and client applications.
- **Gradle**: Build automation tool for managing project dependencies and building the Java application.

## Setup Instructions

### Prerequisites

- **Docker**: Required for running the Hyperledger Fabric network.
- **Java 8 or above**: Required for running the client application.
- **Gradle**: For building and managing the project (included via the Gradle wrapper).

### Step-by-Step Setup

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/maciekbogdalski/blockchain-supply-chain.git
   cd blockchain-supply-chain
   ```

2. **Set Up the Hyperledger Fabric Network**:
   
    Navigate to the fabric-network directory (if applicable) and start the network:
    ```bash
    ./startNetwork.sh
    ```
     Deploy the chaincode:
    ```bash
    ./deployChaincode.sh
   ```

3. **Build the Java Project**:

    Use the Gradle wrapper to build the project:
    ```bash
    ./gradlew build
    ```
4. **Run the Client Application**:

    Command-line interface (CLI):
    ```bash
    java -cp "build/classes/java/main:build/libs/*" org.example.planttrackingclient.FabricClientApp
    ```
    Graphical user interface (GUI):
    ```bash
    java -cp "build/classes/java/main:build/libs/*" org.example.planttrackingclient.SimpleGUIApp
    ```
## Usage
### Command-Line Interface (CLI)
After running the FabricClientApp, you will be prompted to select your organization and perform various operations such as creating a plant tracking record, updating plant state, transferring ownership, or querying plant data.

### Graphical User Interface (GUI)
The SimpleGUIApp provides a basic graphical interface with buttons for each operation. Simply click the desired button to perform the corresponding operation.
