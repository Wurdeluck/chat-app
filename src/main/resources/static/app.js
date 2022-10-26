var stompClient = null;
var users = {};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    const socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        registerUser()
        stompClient.subscribe('/topic/users', function (message) {
            showUserJoined(JSON.parse(message.body));
            saveUserIfNeeded(JSON.parse(message.body));
        });
        stompClient.subscribe('/topic/messages', function (message) {
            showMessages(JSON.parse(message.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

// function sendName() {
//     stompClient.send("/app/message", {}, JSON.stringify({'text': $("#name").val()}));
// }

function registerUser() {
    stompClient.send("/app/user", {}, JSON.stringify({'name': $("#name").val()}));
}

function sendTextMessage() {
    stompClient.send("/app/message", {}, JSON.stringify({'text': $("#textMessage").val(), 'userId': getUserIdByName($("#name").val())}));
}

function getUserIdByName(name) {
    for (const [key, value] of Object.entries(users)) {
        if (value === name) {
            return key
        }
    }
}

function saveUserIfNeeded(user) {
    if (user.name in Object.values(users)) {
        alert("Choose another name!")
        return
    }
    if (!(user.id in users)) {
        users[user.id] = user.name
    }
}

// function showGreeting(message) {
//     $("#greetings").append("<tr><td>" + message + "</td></tr>");
// }

function showMessages(message) {
    $("#messages").append("<tr><td>" + new Date(message.sentAt).toLocaleTimeString() + "[" + users[message.userId] + "]: " + message.text + "</td></tr>");
}

function showUserJoined(user) {
    $("#messages").append("<tr><td>" + user.name + " joined chat! </td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
    $( "#textMessageSend" ).click(function() { sendTextMessage(); });
});