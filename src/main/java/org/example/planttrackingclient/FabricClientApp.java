package org.example.planttrackingclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.Gateway;
import org.hyperledger.fabric.client.identity.Identities;
import org.hyperledger.fabric.client.identity.Signers;
import org.hyperledger.fabric.client.identity.X509Identity;

import io.grpc.ChannelCredentials;
import io.grpc.Grpc;
import io.grpc.ManagedChannel;
import io.grpc.TlsChannelCredentials;

public class FabricClientApp {

    private static final Path NETWORK_PATH = Paths.get("/home/maciek/fabric-samples/test-network");

private static void displayMenu(BufferedReader reader, Contract contract, String orgChoice) throws Exception {
    while (true) {
        System.out.println("\n*** HLF Basil Tracking System Menu ***");
        if ("1".equals(orgChoice)) {
            System.out.println("1. Create a new plant tracking");
            System.out.println("2. Stop tracking a plant");
            System.out.println("3. Update the actual state of a plant");
            System.out.println("4. Get the actual state of a plant");
            System.out.println("5. Get the history of a plant");
            System.out.println("6. Transfer the ownership of a plant");
        } else if ("2".equals(orgChoice)) {
            System.out.println("1. Stop tracking a plant");
            System.out.println("2. Get the history of a plant");
            // Notice: Options are limited for Org2 based on your assignment description.
        }
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");

        String choice = reader.readLine();
        switch (choice) {
            case "1":
                if ("1".equals(orgChoice)) {
                    createTracking(reader, contract);
                } else {
                    System.out.println("This option is not available for your organization.");
                }
                break;
            case "2":
                if ("1".equals(orgChoice)) {
                    stopTracking(reader, contract);
                } else {
                    // Assuming this is the "Get the history of a plant" for Org2
                    getHistory(reader, contract);
                }
                break;
            case "3":
                if ("1".equals(orgChoice)) {
                    updateTracking(reader, contract);
                } else {
                    System.out.println("This option is not available for your organization.");
                }
                break;
            case "4":
                if ("1".equals(orgChoice)) {
                    getActualTracking(reader, contract);
                } else {
                    System.out.println("This option is not available for your organization.");
                }
                break;
            case "5":
                if ("1".equals(orgChoice)) {
                    getHistory(reader, contract);
                } else {
                    System.out.println("This option is not available for your organization.");
                }
                break;
            case "6":
                if ("1".equals(orgChoice)) {
                    transferTracking(reader, contract);
                } else {
                    System.out.println("This option is not available for your organization.");
                }
                break;
            case "7":
                System.out.println("Exiting...");
                return;
            default:
                System.out.println("Invalid choice, please try again.");
                break;
        }
    }
}




    public static void main(String[] args) {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
        System.out.println("Select your organization:");
        System.out.println("1. Pittaluga & fratelli (Org1)");
        System.out.println("2. Supermarket (Org2)");
        System.out.print("Enter your choice (1 or 2): ");
        String orgChoice = reader.readLine().trim();

        String mspId, peerEndpoint, overrideAuthority, certPathStr, privateKeyPathStr, tlsCaCertPathStr;
        if ("1".equals(orgChoice)) {
            mspId = "Org1MSP";
            peerEndpoint = "localhost:7051";
            overrideAuthority = "peer0.org1.example.com";
            certPathStr = "organizations/peerOrganizations/org1.example.com/users/User1@org1.example.com/msp/signcerts/cert.pem";
            privateKeyPathStr = "organizations/peerOrganizations/org1.example.com/users/User1@org1.example.com/msp/keystore";
            tlsCaCertPathStr = "organizations/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt";
        } else {
            mspId = "Org2MSP";
            peerEndpoint = "localhost:9051";
            overrideAuthority = "peer0.org2.example.com";
            certPathStr = "organizations/peerOrganizations/org2.example.com/users/User1@org2.example.com/msp/signcerts/cert.pem";
            privateKeyPathStr = "organizations/peerOrganizations/org2.example.com/users/User1@org2.example.com/msp/keystore";
            tlsCaCertPathStr = "organizations/peerOrganizations/org2.example.com/peers/peer0.org2.example.com/tls/ca.crt";
        }

        Path certPath = NETWORK_PATH.resolve(certPathStr);
        Path privateKeyPath = Files.list(NETWORK_PATH.resolve(privateKeyPathStr)).findFirst().orElseThrow();
        Path tlsCaCertPath = NETWORK_PATH.resolve(tlsCaCertPathStr); // Added line

        ChannelCredentials credentials = TlsChannelCredentials.newBuilder()
                .trustManager(tlsCaCertPath.toFile()) // Updated line
                .build();
        ManagedChannel channel = Grpc.newChannelBuilder(peerEndpoint, credentials)
                .overrideAuthority(overrideAuthority)
                .build();

        Gateway.Builder builder = Gateway.newInstance()
                .identity(new X509Identity(mspId, Identities.readX509Certificate(Files.newBufferedReader(certPath, StandardCharsets.UTF_8))))
                .signer(Signers.newPrivateKeySigner(Identities.readPrivateKey(Files.newBufferedReader(privateKeyPath, StandardCharsets.UTF_8))))
                .connection(channel);

        try (Gateway gateway = builder.connect()) {
            Contract contract = gateway.getNetwork("mychannel").getContract("planttracking");

            displayMenu(reader, contract, orgChoice);
        } finally {
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}


    private static void createTracking(BufferedReader reader, Contract contract) {
    try {
        System.out.print("Enter QR Code: ");
        String qr = reader.readLine();
        System.out.print("Enter Extra Info: ");
        String extraInfo = reader.readLine();
        System.out.print("Enter Org: ");
        String org = reader.readLine();
        System.out.print("Enter User: ");
        String user = reader.readLine();

        byte[] result = contract.submitTransaction("CreateTracking", qr, extraInfo, org, user);
        System.out.println("CreateTracking result: " + new String(result, StandardCharsets.UTF_8));
    } catch (Exception e) {
        // Handle specific chaincode exceptions here
        if (e.getMessage().contains("ChaincodeException")) {
            // This is a placeholder check, you need to replace it with actual error handling based on your chaincode's error messages
            System.out.println("A plant with this QR Code already exists. Please use a unique QR Code.");
        } else {
            System.out.println("An error occurred during 'CreateTracking': " + e.getMessage());
        }
    }
}


    private static void stopTracking(BufferedReader reader, Contract contract) {
    try {
        System.out.print("Enter QR Code: ");
        String qr = reader.readLine();
        System.out.print("Enter Requesting Org: ");
        String requestingOrg = reader.readLine();
        byte[] result = contract.submitTransaction("StopTracking", qr, requestingOrg);
        System.out.println("StopTracking result: " + new String(result, StandardCharsets.UTF_8));
    } catch (Exception e) {
        System.out.println("An error occurred during 'StopTracking': " + e.getMessage());
    }
}

   private static void updateTracking(BufferedReader reader, Contract contract) {
    try {
        System.out.print("Enter QR Code: ");
        String qr = reader.readLine();
        System.out.print("Enter Timestamp (yyyy-MM-ddTHH:mm:ss): ");
        String dateTimeString = reader.readLine();
        System.out.print("Enter GPS Position: ");
        String gpsPosition = reader.readLine();
        System.out.print("Enter Requesting Org: ");
        String requestingOrg = reader.readLine();
        
         LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        long timestamp = dateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
        
        byte[] result = contract.submitTransaction("UpdateTracking", qr, String.valueOf(timestamp), gpsPosition, requestingOrg);
        System.out.println("UpdateTracking result: " + new String(result, StandardCharsets.UTF_8));
    } catch (DateTimeParseException e) {
        System.out.println("Invalid date-time format. Please use 'yyyy-MM-ddTHH:mm:ss', for example, 2024-12-01T11:04:00");
    } catch (Exception e) {
        // Attempt to extract a more specific cause from the exception
        String message = e.getMessage();
        if (message.contains("ABORTED")) {
            System.out.println("Transaction aborted. This may be due to a non-existing QR code or other transaction issues.");
        } else {
            System.out.println("An error occurred: " + message);
        }
    }
}


    private static void getActualTracking(BufferedReader reader, Contract contract) throws Exception {
        try {
        System.out.print("Enter QR Code: ");
        String qr = reader.readLine();
        byte[] result = contract.evaluateTransaction("GetActualTracking", qr);
        System.out.println("GetActualTracking result: " + new String(result, StandardCharsets.UTF_8));
    } catch (Exception e) {
        // Handle specific exceptions if possible or use a generic Exception handler
        // to catch any runtime exceptions thrown by the chaincode invocation.
        System.out.println("An error occurred: " + e.getMessage());
        System.out.println("Please ensure the QR code exists and try again.");
    }
    }

    private static void getHistory(BufferedReader reader, Contract contract) {
    try {
        System.out.print("Enter QR Code: ");
        String qr = reader.readLine();
        byte[] result = contract.evaluateTransaction("GetHistory", qr);
        System.out.println("GetHistory result: " + new String(result, StandardCharsets.UTF_8));
    } catch (Exception e) {
        System.out.println("An error occurred during 'GetHistory': " + e.getMessage());
    }
}


    private static void transferTracking(BufferedReader reader, Contract contract) {
    try {
        System.out.print("Enter QR Code: ");
        String qr = reader.readLine();
        System.out.print("Enter New Org: ");
        String newOrg = reader.readLine();
        System.out.print("Enter New User: ");
        String newUser = reader.readLine();
        System.out.print("Enter Requesting Org: ");
        String requestingOrg = reader.readLine();
        byte[] result = contract.submitTransaction("TransferTracking", qr, newOrg, newUser, requestingOrg);
        System.out.println("TransferTracking result: " + new String(result, StandardCharsets.UTF_8));
    } catch (Exception e) {
        System.out.println("An error occurred during 'TransferTracking': " + e.getMessage());
    }
}

}

