package com.sergeik.design;

import java.util.Stack;

/**
 * Given the postfix tokens of an arithmetic expression, build and return the binary expression tree that
 * represents this expression.
 *
 * Postfix notation is a notation for writing arithmetic expressions in which the operands (numbers) appear before
 * their operators. For example, the postfix tokens of the expression 4*(5-(7+2)) are represented in the
 * array postfix = ["4","5","7","2","+","-","*"].
 *
 * The class Node is an interface you should use to implement the binary expression tree. The returned tree will
 * be tested using the evaluate function, which is supposed to evaluate the tree's value. You should not remove
 * the Node class; however, you can modify it as you wish, and you can define other classes to implement it if needed.
 *
 * A binary expression tree is a kind of binary tree used to represent arithmetic expressions. Each node of a binary
 * expression tree has either zero or two children. Leaf nodes (nodes with 0 children) correspond to operands
 * (numbers), and internal nodes (nodes with two children) correspond to the operators '+' (addition), '-'
 * (subtraction), '*' (multiplication), and '/' (division).
 *
 * It's guaranteed that no subtree will yield a value that exceeds 109 in absolute value, and all the operations
 * are valid (i.e., no division by zero).
 *
 * Follow up: Could you design the expression tree such that it is more modular? For example, is your design able
 * to support additional operators without making changes to your existing evaluate implementation?
 */
public class DesignAnExpressionTreeWithEvaluateFunction {

    public static void main(String[] args) {
        TreeBuilder treeBuilder = new TreeBuilder();
        assert 2 == treeBuilder.buildTree(new String[] {"3","4","+","2","*","7","/"}).evaluate();
        assert -16 == treeBuilder.buildTree(new String[] {"4","5","7","2","+","-","*"}).evaluate();
        assert 18 == treeBuilder.buildTree(new String[] {"4","2","+","3","5","1","-","*","+"}).evaluate();
        assert 757 == treeBuilder.buildTree(new String[] {"100","200","+","2","/","5","*","7","+"}).evaluate();
    }

    abstract static class Node {
        public abstract int evaluate();
        // define your fields here
    };

    static class NodeImpl extends Node {

        String val;
        Node left, right;

        @Override
        public int evaluate() {
            int res;
            switch (val) {
                case "+":
                    res = left.evaluate() + right.evaluate();
                    break;
                case "-":
                    res = left.evaluate() - right.evaluate();
                    break;
                case "/":
                    res = left.evaluate() / right.evaluate();
                    break;
                case "*":
                    res = left.evaluate() * right.evaluate();
                    break;
                default:
                    res = Integer.parseInt(val);
            }
            return res;
        }

        NodeImpl(String v) {
            val = v;
        }
    }


    /**
     * This is the TreeBuilder class.
     * You can treat it as the driver code that takes the postinfix input
     * and returns the expression tree represnting it as a Node.
     */

    static class TreeBuilder {
        Node buildTree(String[] postfix) {
            Stack<Node> stack = new Stack<>();
            for (int i = 0; i < postfix.length; i++) {
                switch (postfix[i]) {
                    case "+":
                    case "-":
                    case "/":
                    case "*":
                        Node right = stack.pop();
                        Node left = stack.pop();
                        NodeImpl newNode = new NodeImpl(postfix[i]);
                        newNode.left = left;
                        newNode.right = right;
                        stack.push(newNode);
                        break;
                    default:
                        stack.push(new NodeImpl(postfix[i]));
                }
            }
            return stack.pop();
        }
    };


}
