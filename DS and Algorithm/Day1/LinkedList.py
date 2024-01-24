class Node:
    def __init__(self, data):
        self.data = data
        self.next = None

def reverse_linked_list(head):
    prev = None
    current = head

    while current is not None:
        next_node = current.next
        current.next = prev
        prev = current
        current = next_node

    head = prev
    return head

# Example usage
if __name__ == "__main__":
    # Creating a sample linked list: 1 -> 2 -> 3 -> 4 -> 5
    head = Node(1)
    head.next = Node(2)
    head.next.next = Node(3)
    head.next.next.next = Node(4)
    head.next.next.next.next = Node(5)

    print("Original Linked List:")
    current = head
    while current:
        print(current.data, end=" -> ")
        current = current.next
    print("None")

    # Reverse the linked list
    head = reverse_linked_list(head)

    print("\nReversed Linked List:")
    current = head
    while current:
        print(current.data, end=" -> ")
        current = current.next
    print("None")
