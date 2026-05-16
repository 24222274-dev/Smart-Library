/*
 * Task 2: Borrowing History  —  LIFO stack of borrowed books.
 * Linked-list based (not java.util.Stack) so the DS is built from scratch.
 * push / pop / peek = O(1),  show = O(n).
 *
 * ------------------------------------------------------------------
 * INTEGRATION NOTES (for the rest of the group)
 * ------------------------------------------------------------------
 * Depends on : the team's `Book` class (fields: int isbn, String title,
 *              String author). Do NOT use the stub Book.java in this
 *              folder once we merge — delete it.
 *
 * Public API : push(Book)  -> add a newly borrowed book on top
 *              pop()       -> remove + return most recent (for "return book")
 *              peek()      -> view most recent without removing
 *              isEmpty()   -> true if no borrows yet
 *              size()      -> number of books in history
 *              show()      -> print full history, newest first (LIFO)
 *
 * Usage in SmartLibrary.borrowBook(isbn):
 *     Book b = catalogue.search(isbn);
 *     if (b != null) history.push(b);          // <-- only line you need
 *
 * Usage in viewLatestHistory():
 *     history.show();
 *
 * Do NOT touch the private Node class or the `top` field — that's the
 * information-hiding boundary. Go through the methods only.
 * ------------------------------------------------------------------
 */
public class BorrowStack {

    private static class Node {           // private => information hiding
        Book book; Node next;
        Node(Book b, Node n) { book = b; next = n; }
    }

    private Node top;
    private int size;

    public void push(Book b) {            // add newest borrow on top
        if (b != null) { top = new Node(b, top); size++; }
    }

    public Book pop() {                   // remove + return most recent
        if (top == null) return null;
        Book b = top.book; top = top.next; size--; return b;
    }

    public Book peek()       { return top == null ? null : top.book; }
    public boolean isEmpty() { return top == null; }
    public int size()        { return size; }

    public void show() {                  // LIFO: newest -> oldest
        if (top == null) { System.out.println("History is empty."); return; }
        System.out.println("--- Borrowing History (most recent first) ---");
        int i = 1;
        for (Node c = top; c != null; c = c.next, i++)
            System.out.println(i + ". [ISBN: " + c.book.isbn + "] "
                                 + c.book.title + " — " + c.book.author);
    }

    // STANDALONE DEMO — REMOVE LINES 63-75 WHEN MERGING WITH YOUR CODE!!!
    public static void main(String[] args) {
        BorrowStack h = new BorrowStack();
        h.show();
        h.push(new Book(101, "Intro to Algorithms",       "Cormen"));
        h.push(new Book(202, "Clean Code",                "Martin"));
        h.push(new Book(303, "The Pragmatic Programmer",  "Hunt"));
        h.show();
        System.out.println("peek: " + h.peek().title + " | size: " + h.size());
        System.out.println("popped: " + h.pop().title);
        h.show();
    }
}
