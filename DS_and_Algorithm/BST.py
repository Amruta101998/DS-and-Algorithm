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

        # Visit the root node
        result.append(root.val)

        # Traverse the right subtree
        result += inorder_traversal(root.right)

    return result

# Example usage
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
