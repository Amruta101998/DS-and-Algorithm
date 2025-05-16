class Node:
    def __init__(self, data):
        self.data = data
        self.next = None

def reverse_linked_list(head):
    # Initialize pointers to keep track of the current, previous, and next nodes
    prev = None            # To keep track of the previous node
    current = head         # To traverse the linked list starting from the head

    # Traverse the linked list
    while current is not None:
        # Store the next node before changing the 'next' reference
        next_node = current.next

        # Reverse the link by pointing the 'next' of the current node to the previous node
        current.next = prev

        # Move to the next pair of nodes in the list
        prev = current
        current = next_node

    # The 'prev' pointer now points to the new head of the reversed linked list
    head = prev

    # Return the new head of the reversed linked list
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
