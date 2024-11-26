# Chatroom

This project is a simple **chatroom application** written in **Java**, where users can send and receive messages in real-time. It consists of a **client-server** architecture, with a **server** handling multiple client connections and a **client** connecting to the server to interact with other users.

## Features

- **Multi-client chatroom**: Multiple clients can connect to the server and communicate with each other in real-time.
- **Server-side management**: The server handles multiple clients using **threads**, and each client can send and receive messages.
- **Color-coded messages**: The application supports ANSI escape codes for color formatting in messages.
- **Graceful exit**: Clients can disconnect and exit the chatroom gracefully.

## Project Structure

### Client

The **client** is responsible for:
- Connecting to the server and sending messages.
- Receiving messages from the server.
- Sending requests to join, send messages, and exit.
- Handles user input and message output in a real-time chat interface.

Key Classes:
- `Driver.java`: The entry point to the client application. It manages the client connection, creates input/output streams, and starts the communication threads (`Sender` and `Receiver`).
- `Connector.java`: Handles the connection process to the server and manages the lifecycle of the connection.
- `Sender.java`: Responsible for sending messages from the client to the server.
- `Receiver.java`: Listens for incoming messages from the server and displays them to the client.

### Server

The **server** manages client connections and allows multiple clients to communicate with each other. It listens for incoming client connections and creates a new handler for each client.

Key Classes:
- `Server.java`: The entry point to the server application. It listens for incoming client connections on a specified port and assigns each client a handler (`Handler.java`).
- `Handler.java`: Manages individual client sessions, allowing them to send and receive messages to/from other connected clients.

### Common

The **common** package contains shared utilities and constants used by both the client and the server, such as the `API` class, which defines the server address, port, and various command constants (join, send-msg, exit).

## How to Run

### Prerequisites

- **Java 8 or higher** is required to run this project.
- Make sure both **client** and **server** are compiled and running in separate terminal windows or processes.

### Running the Server

1. Navigate to the `server` directory.
2. Compile the server classes:
   ```bash
   javac server/*.java
3. Run the server:
   ```bash
   java server.Server

### Running the Client

1. Navigate to the `client` directory.
2. Compile the client classes:
   ```bash
   javac client/*.java
3. Run the client:
   ```bash
   java client.Driver

### Exiting the Chat

- To exit the chatroom, type the exit command or simply close the client window.
- The server will handle client disconnections gracefully.

## License

This project is licensed under the MIT License - see the [MIT License](https://opensource.org/licenses/MIT) for details.
   

