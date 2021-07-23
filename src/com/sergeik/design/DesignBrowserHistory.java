package com.sergeik.design;

/**
 * You have a browser of one tab where you start on the homepage and you can visit another url, get back in the
 * history number of steps or move forward in the history number of steps.
 *
 * Implement the BrowserHistory class:
 *
 * BrowserHistory(string homepage) Initializes the object with the homepage of the browser.
 * void visit(string url) Visits url from the current page. It clears up all the forward history.
 * string back(int steps) Move steps back in history. If you can only return x steps in the history and steps > x,
 * you will return only x steps. Return the current url after moving back in history at most steps.
 * string forward(int steps) Move steps forward in history. If you can only forward x steps in the history and
 * steps > x, you will forward only x steps. Return the current url after forwarding in history at most steps.
 */
public class DesignBrowserHistory {

    public static void main(String[] args) {
        BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
        browserHistory.visit("google.com");       // You are in "leetcode.com". Visit "google.com"
        browserHistory.visit("facebook.com");     // You are in "google.com". Visit "facebook.com"
        browserHistory.visit("youtube.com");      // You are in "facebook.com". Visit "youtube.com"
        assert "facebook.com".equals(browserHistory.back(1));                   // You are in "youtube.com", move back to "facebook.com" return "facebook.com"
        assert "google.com".equals(browserHistory.back(1));                   // You are in "facebook.com", move back to "google.com" return "google.com"
        assert "facebook.com".equals(browserHistory.forward(1));                // You are in "google.com", move forward to "facebook.com" return "facebook.com"
        browserHistory.visit("linkedin.com");     // You are in "facebook.com". Visit "linkedin.com"
        assert "linkedin.com".equals(browserHistory.forward(2));                // You are in "linkedin.com", you cannot move forward any steps.
        assert "google.com".equals(browserHistory.back(2));                   // You are in "linkedin.com", move back two steps to "facebook.com" then to "google.com". return "google.com"
        assert "leetcode.com".equals(browserHistory.back(7));                   // You are in "google.com", you can move back only one step to "leetcode.com". return "leetcode.com"
    }

    static class BrowserHistory {

        Node head, tail, cur;

        public BrowserHistory(String homepage) {
            head = new Node("");
            tail = new Node("");
            cur = new Node(homepage);
            head.next = cur;
            cur.prev = head;
            cur.next = tail;
            tail.prev = cur;
        }

        public void visit(String url) {
            Node newCur = new Node(url);
            cur.next = newCur;
            newCur.prev = cur;
            cur = newCur;
            cur.next = tail;
            tail.prev = cur;
        }

        public String back(int steps) {
            while (steps-- > 0 && cur.prev != head)
                cur = cur.prev;
            return cur.val;
        }

        public String forward(int steps) {
            while (steps-- > 0 && cur.next != tail)
                cur = cur.next;
            return cur.val;
        }

        class Node {
            Node prev;
            Node next;
            String val;

            Node(String v) {
                val = v;
            }

        }

    }

}
