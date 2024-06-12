# Write a bubble sort for python code 
def bubble_sort(arr):
    n = len(arr)
    # Traverse through all elements in the array
    for i in range(n):
        # Last i elements are already in place
        for j in range(0, n-i-1):
            # Traverse the array from 0 to n-i-1
            # Swap if the element found is greater than the next element
            if arr[j] > arr[j+1]:
                arr[j], arr[j+1] = arr[j+1], arr[j]

def bubble_sort(arr):
    n = len(arr)
    # Traverse through all elements in the array
    for i in range(n):
        # Last i elements are already in place
        for j in range(0, n-i-1):
            # Traverse the array from 0 to n-i-1
            # Swap if the element found is greater than the next element
            if arr[j] < arr[j+1]:
                arr[j], arr[j+1] = arr[j+1], arr[j]

# Example usage:
example_list = [64, 34, 25, 12, 22, 11, 90]

print("Original list:")
print(example_list)

bubble_sort(example_list)

print("Sorted list:")
print(example_list)


# output
# Original list:
# [64, 34, 25, 12, 22, 11, 90]
# Sorted list:
# [11, 12, 22, 25, 34, 64, 90]