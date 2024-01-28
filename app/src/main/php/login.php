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

// Retrieve the user from the database
$stmt = $conn->prepare('SELECT * FROM users WHERE username = ?');
$stmt->bind_param('s', $username);
$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows === 1) {
    $row = $result->fetch_assoc();
    if (password_verify($password, $row['password'])) {
        $response['success'] = true;
        $response['message'] = 'Login successful';
        echo json_encode($response);
    } else {
        $response['success'] = false;
        $response['message'] = 'Invalid username or password';
        echo json_encode($response);
    }
} else {
    $response['success'] = false;
    $response['message'] = 'Invalid username or password';
    echo json_encode($response);
}

$stmt->close();
$conn->close();

?>
