package com.thptcualo.main.messages;

public class MessagesList {
    private String name,email,lastMessage;

    private  int unseenMessages;

    public MessagesList(String name, String email, String lastMessage, int unseenMessages) {
        this.name = name;
        this.email = email;
        this.lastMessage = lastMessage;
        this.unseenMessages = unseenMessages;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public int getUnseenMessages() {
        return unseenMessages;
    }
}

