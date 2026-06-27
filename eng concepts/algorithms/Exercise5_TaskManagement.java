class Task {
    int taskId;
    String taskName;
    String status;
    Task next;

    Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    @Override
    public String toString() {
        return "[" + taskId + "] " + taskName + " (" + status + ")";
    }
}

class SinglyLinkedList {
    private Task head;

    // O(1) â€” prepend to front
    public void addFirst(Task t) {
        t.next = head;
        head = t;
    }

    // O(n) â€” append to end
    public void addLast(Task t) {
        if (head == null) { head = t; return; }
        Task cur = head;
        while (cur.next != null) cur = cur.next;
        cur.next = t;
    }

    // O(n) â€” linear scan by taskId
    public Task search(int taskId) {
        Task cur = head;
        while (cur != null) {
            if (cur.taskId == taskId) return cur;
            cur = cur.next;
        }
        return null;
    }

    // O(n)
    public void traverse() {
        Task cur = head;
        while (cur != null) {
            System.out.println("  " + cur);
            cur = cur.next;
        }
    }

    // O(n) â€” find predecessor, re-link
    public boolean delete(int taskId) {
        if (head == null) return false;
        if (head.taskId == taskId) { head = head.next; return true; }
        Task cur = head;
        while (cur.next != null) {
            if (cur.next.taskId == taskId) {
                cur.next = cur.next.next;
                return true;
            }
            cur = cur.next;
        }
        return false;
    }
}

public class Exercise5_TaskManagement {
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        list.addLast(new Task(1, "Design DB schema",  "Done"));
        list.addLast(new Task(2, "Implement API",     "In Progress"));
        list.addLast(new Task(3, "Write unit tests",  "Pending"));
        list.addFirst(new Task(0, "Kick-off meeting", "Done"));

        System.out.println("=== All Tasks ===");
        list.traverse();

        System.out.println("\n=== Search taskId=2 ===");
        System.out.println(list.search(2));

        list.delete(2);
        System.out.println("\n=== After Delete taskId=2 ===");
        list.traverse();

        /*
         * Time Complexity:
         *   addFirst -> O(1)
         *   addLast  -> O(n)  [use tail pointer for O(1)]
         *   search   -> O(n)
         *   traverse -> O(n)
         *   delete   -> O(n)
         *
         * Linked List vs Array:
         *   + No pre-allocated size; grows dynamically, O(1) head insert/delete.
         *   - No random access; extra memory per node (pointer).
         *   Use when frequent insertions/deletions at arbitrary positions.
         */
    }
}
