def selection_sort(arr):
    n = len(arr)

    for i in range(n):
        # Find the minimum element in the unsorted part
        min_index = i
        for j in range(i+1, n):
            if arr[j] < arr[min_index]:
                min_index = j
 
        # Swap the found minimum element with the first element
        arr[i], arr[min_index] = arr[min_index], arr[i]

# Example usage
if __name__ == "__main__":
    unsorted_array = [64, 34, 25, 12, 22, 11, 90]

    print("Unsorted Array:", unsorted_array)
    
    selection_sort(unsorted_array)
    
    print("Sorted Array (Selection Sort):", unsorted_array)
