/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */
 
class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int peak = findPeakIndex(mountainArr);

        // Try searching in the strictly ascending (left) slope first to get the minimum index
        int firstTry = orderAgnosticBinarySearch(mountainArr, target, 0, peak, true);
        if (firstTry != -1) {
            return firstTry;
        }

        // If not found in the left half, search the strictly descending (right) slope
        return orderAgnosticBinarySearch(mountainArr, target, peak + 1, mountainArr.length() - 1, false);
    }

    private int findPeakIndex(MountainArray mountainArr) {
        int start = 0;
        int end = mountainArr.length() - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;
            
            // Compare mid with mid + 1 to check slope direction
            if (mountainArr.get(mid) > mountainArr.get(mid + 1)) {
                // Decreasing slope: mid could be the peak, look left
                end = mid;
            } else {
                // Ascending slope: peak is strictly to the right
                start = mid + 1;
            }
        }
        return start;
    }

    private int orderAgnosticBinarySearch(MountainArray mountainArr, int target, int start, int end, boolean isAsc) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int midVal = mountainArr.get(mid);

            if (midVal == target) {
                return mid;
            }

            if (isAsc) {
                if (target < midVal) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (target > midVal) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return -1;
    }
}
    
