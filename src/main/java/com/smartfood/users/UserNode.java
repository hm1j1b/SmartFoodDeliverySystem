package com.smartfood.users;

public class UserNode {
    private User user;
    private UserNode next;

    // Constructors
    public UserNode(User user) {
        this.user = user;
        this.next = null;
    }

    // Setter and Getter
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public UserNode getNext() {
        return next;
    }
    public void setNext(UserNode next) {
        this.next = next;
    }

    
}
