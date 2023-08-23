/**
 * This class is written as an implementation of the different popular sort methods that can be used on arrays. As a test,
 * I also intend to run simulations on their actual runtime and their expected big o runtime.
 */
public class Sorts {
    /**
     * Repeatedly moves the smallest element to the already sorted portion of the array
     * Best Case - O(n) : Worst Case - O(n^2)
     * @param arr - int array
     * @return the sorted array in ascending order as a string
     */
    public static String selectionSort(int[] arr){
        int n = arr.length;
        for(int i=0; i<n; i++){
            for(int j=i; j<n; j++){
                if(arr[j] < arr[i]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        return arrToString(arr);
    }

    /**
     * Sorts an array in ascending order by repeatedly swapping two adjacent elements if they are in the wrong order
     * Best Case - O(n) : Worst Case - O(n^2)
     * @param arr - to be sorted
     * @return String representation of sorted array
     */
    public static String bubbleSort(int[] arr){
        int n = arr.length;
        for(int i=0; i<n-1; i++){
            int swapped = 0;
            for(int j=0; j<n-i-1; j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    swapped = 1;
                }
            }

            if(swapped == 0){
                break;
            }
        }

        return arrToString(arr);
    }

    /**
     * splits an array into a sorted and an unsorted part.
     * repeatedly moves the next observed element by finding its place and pushing other elements up one spot.
     * Best case - O(n) : Worst Case - O(n^2)
     * @param arr - array to be sorted
     * @return string representation of the sorted array
     */
    public static String insertionSort(int[] arr){
        int n= arr.length;
        for(int i=0; i<n-1; i++) {
            if (arr[i] > arr[i + 1]) {
                int j = i;
                while (j >= 0 && arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    j -= 1;
                }
            }
        }
        return arrToString(arr);
    }

    private static void merge(int[] arr, int start, int mid, int end){
        int range1 = mid-start+1;
        int range2 = end-mid;// mid is inclusive in range 1
        int[] sub1 = new int[range1];
        int[] sub2 = new int[range2];

        for(int i=0; i<range1; i++){
            sub1[i] = arr[start+i];
        }
        for(int j=0; j<range2; j++){
            sub2[j] = arr[mid+1+j];
        }

        int i=0;int j=0;int k=start;

        while(i<range1 && j<range2){
            if(sub1[i]<=sub2[j]){
                arr[k] = sub1[i];
                i++;
            }
            else{
                arr[k] = sub2[j];
                j++;
            }
            k++;
        }

        while(i<range1){
            arr[k] = sub1[i];
            i++;
            k++;
        }
        while(j<range2){
            arr[k] = sub2[j];
            j++;
            k++;
        }

    }

    /**
     * Sorts an array by dividing it into multiple constituent components and then sorting and merging the base cases
     * use pos1 = 0, pos2 = arr.length-1 to sort the whole array
     * Best Case, Worst Case : O(nlog(n))
     * @param arr - array to be sorted
     * @param position1 - starting index of sorting
     * @param position2 - ending index of sorting
     * @return String version of sorted arr
     */
    public static String mergeSort(int[] arr, int position1, int position2){
        if(position1<position2){
            int middle = (position1+position2)/2;
            mergeSort(arr, position1, middle);
            mergeSort(arr, middle+1, position2);

            merge(arr, position1, middle, position2);
        }
        return arrToString(arr);
    }

    public static int partition(int[] arr, int position1, int position2){
        //Chose a pivot I am always choosing the last element in arr
        int pivot = arr[position2];
        int tracker = position1-1;
        for(int i=position1; i<position2; i++){
            if(arr[i]<pivot){
                tracker++;
                int temp = arr[i];
                arr[i] = arr[tracker];
                arr[tracker] = temp;
            }
        }
        int temp = arr[position2-1];
        arr[position2-1] = arr[tracker];
        arr[tracker] = temp;
        return tracker+1;
    }

    /**
     * re implements the standard quicksort algorithm, which works by dividing the array repeatedly into parts with
     * elements that are smaller and larger than the pivot element.
     * Worst Case - O(n^2) : Best Case - O(nlog(n))
     * @param arr - array to be sorted
     * @param start - starting index from where to sort
     * @param end - ending index for sorting
     * @return String representation of the sorted array
     */
    public static String quickSort(int[] arr, int start, int end){
        if (start < end) {
            int partitionIndex = partition(arr, start, end);
            quickSort(arr, start, partitionIndex-1);
            quickSort(arr, partitionIndex+1, end);
        }
        return arrToString(arr);
    }

    /**
     * Converts an array to a string representation
     * Effectively overrides Arrays.toString(arr);
     * @param arr - array to convert into string
     * @return String representation of Array
     */
    private static String arrToString(int[] arr) {
        StringBuilder arrAsString = new StringBuilder();
        for(int i=0; i<arr.length; i++){
            arrAsString.append(arr[i]);
            if(i!=arr.length-1){
                arrAsString.append(", ");
            }

        }
        return arrAsString.toString();
    }

}
