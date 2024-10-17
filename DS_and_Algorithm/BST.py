class TreeNode:
    def __init__(self, key):
        self.left = None
        self.right = None
        self.val = key

def inorder_traversal(root):
    result = []
    if root:
        # Traverse the left subtree
        result += inorder_traversal(root.left)

        result.append(root.val)
        result.append(root.val)  # Add the value to the list twice to get the node

        # Traverse the right subtree
        result += inorder_traversal(root.right)

    return result


if __name__ == "__main__":
    # Creating a sample Binary Search Tree
    root = TreeNode(5)
    root.left = TreeNode(3)
    root.right = TreeNode(7)
    root.left.left = TreeNode(2)
    root.left.right = TreeNode(4)
    root.right.left = TreeNode(6)
    root.right.right = TreeNode(8)

    print("Inorder Traversal of BST:")
    result = inorder_traversal(root)
    print(result)
