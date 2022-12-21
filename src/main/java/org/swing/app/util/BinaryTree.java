package org.swing.app.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree<T> {

    private TreeNode<T> root;

    private final CustomComparator<T> comparator;

    public BinaryTree(CustomComparator<T> comparator) {
        this.comparator = comparator;
    }

    private TreeNode<T> addValueToTreeNode(TreeNode<T> node, T valueToAdd) {
        if (node == null) {
            return new TreeNode<>(valueToAdd);
        }

        final int compareResult = this.comparator.compareTo(valueToAdd, node.value);
        if (compareResult == 0) {
            return node;
        }
        if (compareResult < 0) {
            node.left = addValueToTreeNode(node.left, valueToAdd);
        }
        else {
            node.right = addValueToTreeNode(node.right, valueToAdd);
        }

        return node;
    }

    public void add(T value) {
        this.root = addValueToTreeNode(root, value);
    }

    private T getSmallestValueInTree(TreeNode<T> node) {
        return node.left == null ? node.value : getSmallestValueInTree(node.left);
    }

    private TreeNode<T> removeRootFromTreeNode(TreeNode<T> node) {
        if (node.left == null && node.right == null) {
            return null;
        }
        if (node.left == null) {
            return node.right;
        }
        if (node.right == null) {
            return node.left;
        }

        final T smallestValueThatGreaterThanCurrentNodeValue = getSmallestValueInTree(node.right);

        node.value = smallestValueThatGreaterThanCurrentNodeValue;
        node.right = removeValueFromTreeNode(node.right, smallestValueThatGreaterThanCurrentNodeValue);

        return node;
    }

    private TreeNode<T> removeValueFromTreeNode(TreeNode<T> node, T valueToRemove) {
        if (node == null) {
            return null;
        }

        final int compareResult = this.comparator.compareTo(valueToRemove, node.value);
        if (compareResult == 0) {
            return removeRootFromTreeNode(node);
        }
        if (compareResult < 0) {
            node.left = removeValueFromTreeNode(node.left, valueToRemove);
        } else {
            node.right = removeValueFromTreeNode(node.right, valueToRemove);
        }

        return node;
    }

    public void remove(T value) {
        this.root = removeValueFromTreeNode(this.root, value);
    }

    private boolean containsTreeNode(TreeNode<T> node, T valueToSearch) {
        if (node == null) {
            return false;
        }
        if (valueToSearch == node.value) {
            return true;
        }

        final int compareResult = this.comparator.compareTo(node.value, valueToSearch);
        if (compareResult < 0) {
            return containsTreeNode(node.left, valueToSearch);
        }
        return containsTreeNode(node.right, valueToSearch);
    }

    public boolean contains(T value) {
        return containsTreeNode(this.root, value);
    }

    private List<TreeNode<T>> traverseInOrder(TreeNode<T> node) {
        final List<TreeNode<T>> nodes = new ArrayList<>();

        if (node != null) {
            nodes.addAll(traverseInOrder(node.left));
            nodes.add(node);
            nodes.addAll(traverseInOrder(node.right));
        }

        return nodes;
    }

    public List<TreeNode<T>> getTraverseInOrderNodes() {
        return traverseInOrder(this.root);
    }

    public List<TreeNode<T>> traversePreOrder(TreeNode<T> node) {
        final List<TreeNode<T>> nodes = new ArrayList<>();

        if (node != null) {
            nodes.add(node);
            nodes.addAll(traverseInOrder(node.left));
            nodes.addAll(traverseInOrder(node.right));
        }

        return nodes;
    }

    public List<TreeNode<T>> getTraversePreOrderNodes() {
        return traversePreOrder(this.root);
    }

    private List<TreeNode<T>> traverseLevelOrder(TreeNode<T> node) {
        final List<TreeNode<T>> nodes = new ArrayList<>();

        if (node != null) {
            Queue<TreeNode<T>> nodeQueue = new LinkedList<>();
            nodeQueue.add(node);

            while (!nodeQueue.isEmpty()) {
                final TreeNode<T> removedNode = nodeQueue.remove();
                nodes.add(removedNode);

                if (removedNode.left != null) {
                    nodeQueue.add(removedNode.left);
                }
                if (removedNode.right != null) {
                    nodeQueue.add(removedNode.right);
                }
            }
        }

        return nodes;
    }

    public List<TreeNode<T>> getTraverseLevelOrderNodes() {
        return traverseLevelOrder(this.root);
    }
}
