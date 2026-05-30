package com.smartfood.search;

public class AVLTree {
    private AVLNode root;

    private int getHeight(AVLNode node) {
        return node == null ? -1 : node.getHeight();
    }

    private void updateHeight(AVLNode node) {
        node.setHeight(1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight())));
    }

    private int getBalanceFactor(AVLNode node) {
        return node == null ? 0 : getHeight(node.getLeft()) - getHeight(node.getRight());
    }

    private AVLNode rotateLeft(AVLNode node) {
        AVLNode a = node.getRight();
        AVLNode b = a.getLeft();

        a.setLeft(node);
        node.setRight(b);

        updateHeight(node);
        updateHeight(a);

        return a;
    }

    private AVLNode rotateRight(AVLNode node) {
        AVLNode a = node.getLeft();
        AVLNode b = a.getRight();

        a.setRight(node);
        node.setLeft(b);

        updateHeight(node);
        updateHeight(a);
        
        return a;
    }

    private AVLNode rebalance(AVLNode node) {
        updateHeight(node);
        int bf = getBalanceFactor(node);

        if (bf > 1) {
            if (getBalanceFactor(node.getLeft()) >= 0) {
                return rotateRight(node);
            }
            else {
                node.setLeft(rotateLeft(node.getLeft()));
                return rotateRight(node);
            }
        }

        if (bf < -1) {
            if (getBalanceFactor(node.getRight()) < 0) {
                return rotateLeft(node);
            }
            else {
                node.setRight(rotateRight(node.getRight()));
                return rotateLeft(node);
            }
        }

        return node;
    }

    private AVLNode getMinNode(AVLNode node) {
        AVLNode currentNode = node; 

        while (currentNode.getLeft() != null) {
            currentNode = currentNode.getLeft();
        }

        return currentNode;
    }

    public void insert(FoodItem item) {
        root = insertHelper(root, item);
    }

    private AVLNode insertHelper(AVLNode node, FoodItem item) {
        if (node == null) return new AVLNode(item);
        
        int compare = item.getName().compareTo(node.getItem().getName());
        if (compare < 0) {
            node.setLeft(insertHelper(node.getLeft(), item));
        }
        else if (compare > 0) {
            node.setRight(insertHelper(node.getRight(), item));
        }
        else {
            System.out.println("Duplicate detected.");
        }
        
        return rebalance(node);
    }

    public void remove(String name) {
        root = removeHelper(root, name);
    }

    private AVLNode removeHelper(AVLNode node, String name) {
        if (node == null) return node;

        int compare = name.compareTo(node.getItem().getName());
        if (compare < 0) {
            node.setLeft(removeHelper(node.getLeft(), name));
        }
        else if (compare > 0) {
            node.setRight(removeHelper(node.getRight(), name));
        }
        else {
            if (node.getLeft() == null) return node.getRight();
            if (node.getRight() == null) return node.getLeft();

            AVLNode successor = getMinNode(node.getRight());
            node.setItem(successor.getItem());
            node.setRight(removeHelper(node.getRight(), successor.getItem().getName()));
        }

        return rebalance(node);
    }

    public FoodItem search(String name) {
        AVLNode currentNode = root;

        while (currentNode != null) {
            int compare = name.compareTo(currentNode.getItem().getName());
            if (compare == 0) {
                break;
            }
            currentNode = compare < 0 ? currentNode.getLeft() : currentNode.getRight();
        }
        
        return currentNode != null ? currentNode.getItem() : null;
    }

    public void inOrder() {
        inOrderHelper(root);
    }

    private void inOrderHelper(AVLNode node) {
        if (node == null) return;
        inOrderHelper(node.getLeft());
        System.out.println(node.getItem());
        inOrderHelper(node.getRight());
    }
}
