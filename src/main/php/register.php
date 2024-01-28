<?php

// Retrieve the data from the request
$username = $_POST['username'];
$password = $_POST['password'];

// Validate input
if (empty($username) || empty($password)) {
    $response['success'] = false;
    $response['message'] = 'Please enter all fields';
    echo json_encode($response);
    return;
}

// Hash the password
$hashedPassword = password_hash($password, PASSWORD_DEFAULT);

// Connect to MySQL database
$servername = 'localhost';
$dbUsername = 'root';
$dbPassword = '';
$dbName = 'kidsapp';

$conn = new mysqli($servername, $dbUsername, $dbPassword, $dbName);

if ($conn->connect_error) {
    $response['success'] = false;
    $response['message'] = 'Connection failed: ' . $conn->connect_error;
    echo json_encode($response);
    return;
}

// Check if the username already exists
$stmt = $conn->prepare('SELECT * FROM users WHERE username = ?');
$stmt->bind_param('s', $username);
$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows > 0) {
    $response['success'] = false;
    $response['message'] = 'Username already exists';
    echo json_encode($response);
    return;
}

// Insert the user into the database
$stmt = $conn->prepare('INSERT INTO users (username, password) VALUES (?, ?)');
$stmt->bind_param('ss', $username, $hashedPassword);
$stmt->execute();

$response['success'] = true;
$response['message'] = 'Registration successful';
echo json_encode($response);

$stmt->close();
$conn->close();

?>
