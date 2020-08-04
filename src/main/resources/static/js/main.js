'use strict'

var usernamePage = document.querySelector("#username-page");
var chatPage = document.querySelector("#chat-page");
var usernameForm = document.querySelector("#usernameForm");
var messageForm = document.querySelector("#messageForm");
var messageInput = document.querySelector("#message");
var messageArea = document.querySelector("#messageArea");
var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var username = null;
var url = '/websocket';

var colors = ['#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'];



function connect(event) {
    username = document.querySelector("#name").value.trim();
    if (username) {
        usernamePage.classList.add('hidden');
        chatPage.classList.remove('hidden');

        //https://stomp-js.github.io/stomp-websocket/codo/class/Client.html
        stompClient = Stomp.over(function(){
            return new SockJS(url);
        });
        stompClient.connect({}, onConnected, onError);
    }
    event.preventDefault();
}

function onConnected() {

}

function onError() {

}