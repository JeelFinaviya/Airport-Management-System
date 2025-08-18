package Project;

public class PassengerLinkedList {
    private Node head;

    private static class Node {
        Passenger data;
        Node next;

        Node(Passenger data) {
            this.data = data;
            this.next = null;
        }
    }
    public void add(Passenger p) {
        Node newNode = new Node(p);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }
    public Passenger searchByAadhar(String aadhar) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.aadhar.equals(aadhar)) {
                return temp.data;
            }
            temp = temp.next;
        }
        return null; // not found
    }
    public void displayAll() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }
}
