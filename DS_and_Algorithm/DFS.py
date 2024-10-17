
class Stack:
    def __init__(self):
        self.items = []

    def is_empty(self):
        return not self.items

    def push(self, item):
        self.items.append(item)

    def pop(self):
        if not self.is_empty():
            return self.items.pop()

    def peek(self):
        if not self.is_empty():
            return self.items[-1]

    def size(self):
        return len(self.items)

def dfs(graph, start):
    visited = set()
    stack = Stack()
    stack.push(start)
    
    while not stack.is_empty():
        vertex = stack.pop()
        
        if vertex not in visited:
            visited.add(vertex)
            print(vertex, end=" ")  # Print visited vertex
            
            # Add all neighbors to the stack
            for neighbor in graph[vertex]:
                if neighbor not in visited:
                    stack.push(neighbor)

# Example usage:
graph = {
    'A': ['B', 'C'],
    'B': ['A', 'D', 'E'],
    'C': ['A', 'F'],
    'D': ['B'],
    'E': ['B', 'F'],
    'F': ['C', 'E']
}

print("Depth-First Search starting from vertex 'A':")
dfs(graph, 'A')