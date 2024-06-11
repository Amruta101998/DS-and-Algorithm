def selection_sort(arr):
    """
    Sorts an array using the Selection Sort algorithm.

    Parameters:
    - arr (list): The input list to be sorted in-place.

    Returns:
    None (The input list is sorted in-place).
    """

    # Get the length of the array
    n = len(arr)

    # Traverse the entire array
    for i in range(n):
        # Assume the current index is the minimum
        min_index = i

        # Iterate through the unsorted part of the array to find the minimum element
        for j in range(i + 1, n):
            # Compare the current element with the assumed minimum
            if arr[j] < arr[min_index]:
                # If the current element is smaller, update the minimum index
                min_index = j

        # Swap the found minimum element with the first element in the unsorted part
        arr[i], arr[min_index] = arr[min_index], arr[i]

# Example usage
if __name__ == "__main__":
    # Example array to be sorted
    unsorted_array = [64, 34, 25, 12, 22, 11, 90]

    print("Unsorted Array:", unsorted_array)

    # Apply selection sort to the array
    selection_sort(unsorted_array)

    print("Sorted Array (Selection Sort):", unsorted_array)
