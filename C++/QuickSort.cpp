// Introduction to Quick Sort

// QuickSort is a Divide and Conquer algorithm widely used in sorting applications.
// The algorithm works by selecting a 'pivot' element from the array and partitioning 
// the other elements into two sub-arrays: elements less than the pivot and elements 
// greater than the pivot. This process is then recursively applied to the sub-arrays.

// Ways to select a pivot element:
// 1) Pick the first element as pivot.
// 2) Pick the last element as pivot.
// 3) Pick a random element as pivot.
// 4) Pick the median as pivot (median of first, middle, and last element).

// In the code below, we use the last element as the pivot for partitioning.

// Time Complexity of Quick Sort
// 1) Worst Case: O(n^2) 
//    - This occurs when the pivot is the smallest or largest element in each recursive call,
//      leading to unbalanced partitioning.
// 2) Average Case: O(nLogn)
//    - On average, the pivot divides the array into reasonably balanced partitions.
// 3) Best Case: O(nLogn)
//    - This occurs when the pivot consistently divides the array into two equal halves.

// Despite its worst-case scenario, QuickSort is one of the most efficient sorting algorithms 
// due to its cache-friendly properties and average-case performance, which is often much better 
// than O(n^2) sorting algorithms like Bubble Sort or Insertion Sort.

// C++ code for Quick Sort
#include<iostream>
using namespace std;

// Partition() function to rearrange elements relative to the pivot
// The function moves elements smaller than the pivot to the left of the pivot
// and elements larger than the pivot to the right.
int partition(int arr[], int s, int e) {

    // Last element chosen as pivot
    int pivot = arr[e]; 
    
    // Index of the smaller element (starts from -1, meaning no smaller elements initially)
    int i = s - 1; 
    
    // Loop over elements from start to one before the pivot (arr[e])
    for (int j = s; j < e; j++) {
        // If current element is smaller than the pivot
        if (arr[j] < pivot) {
            i++; // Increment index of smaller element
            swap(arr[i], arr[j]); // Swap current element with the element at index i
        }
    }
    // Place the pivot element at its correct position (i+1)
    swap(arr[i + 1], arr[e]);
    
    // Return the partition index (new position of the pivot)
    return i + 1;
}

// QuickSort() function that recursively sorts the array
// It partitions the array around a pivot and recursively sorts the left and right subarrays.
void quicksort(int arr[], int s, int e) {
    if (s < e) {
        // Partition the array and get the pivot index
        int pi = partition(arr, s, e);
        
        // Recursively sort elements before and after the partition
        quicksort(arr, s, pi - 1);  // Left subarray (elements smaller than the pivot)
        quicksort(arr, pi + 1, e);  // Right subarray (elements larger than the pivot)
    }
}

// Main function
int main() {
    // Initialize an array of integers
    int arr[] = {5, 2, 7, 10, 4, 8, 23, 14, 21, 1};
    
    // Number of elements in the array
    int n = sizeof(arr) / sizeof(arr[0]);

    // Call quicksort() to sort the array
    quicksort(arr, 0, n - 1);

    // Print the sorted array
    cout << "Sorted array: ";
    for (int i = 0; i < n; i++) {
        cout << arr[i] << " ";
    }
    cout << endl;

    return 0;
}
