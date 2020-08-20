package phonebook;

import java.util.List;

public class BinarySearch implements SearchAlgorithm {
    //binary search with quick sort, a lot quicker than jump search
    private String[] list;

    private int binarySearch(String[] array, String target) {
        int left = 0;
        int right = array.length - 1;
        int mid;

        while (left <= right) {
            mid = (left + right) / 2;

            if (array[mid].compareTo(target) < 0) {
                left = mid + 1;
            } else if (array[mid].compareTo(target) > 0) {
                right = mid - 1;
            } else return mid;
        }

        return -1;
    }

    private int partition(String[] names, int left, int right) {
        String pivot = names[right];
        int partitionIndex = left;

        for (int i = left; i < right; i++) {
            if (names[i].compareTo(pivot) <= 0) {
                swap(names, i, partitionIndex);
                partitionIndex++;
            }
        }

        swap(names, partitionIndex, right);

        return partitionIndex;
    }

    private static void swap(String[] array, int i, int j) {
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void quickSort(String[] names, int left, int right) {
        if (left >= right) return;

        int pivotIndex = partition(names, left, right);
        quickSort(names, left, pivotIndex - 1);
        quickSort(names, pivotIndex + 1, right);
    }

    @Override
    public void sortArray(List<String> list) {
        String[] arr = list.toArray(new String[0]);
        quickSort(arr, 0, arr.length - 1);
        this.list = arr;
    }

    @Override
    public int findInArray(List<String> requests) {
        int found = 0;
        for (String req : requests) {
            if (binarySearch(list, req) > -1) {
                found++;
            }
        }

        return found;
    }
}
