package com.smartfood.search;

public class AVLNode {
    private FoodItem item;
    private AVLNode left, right;
    private int height;

    public AVLNode(FoodItem item) {
        this.item = item;
        this.left = null;
        this.right = null;
        this.height = 0;
    }

    public FoodItem getItem() {
        return item;
    }

    public AVLNode getLeft() {
        return left;
    }

    public AVLNode getRight() {
        return right;
    }

    public int getHeight() {
        return height;
    }

    public void setItem(FoodItem item) {
        this.item = item;
    }

    public void setLeft(AVLNode left) {
        this.left = left;
    }

    public void setRight(AVLNode right) {
        this.right = right;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
