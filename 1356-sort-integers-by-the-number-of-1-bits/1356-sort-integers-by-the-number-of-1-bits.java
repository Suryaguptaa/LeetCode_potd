class Solution {
    public int[] sortByBits(int[] arr) {

        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {

                int bit1 = Integer.bitCount(arr[j]);
                int bit2 = Integer.bitCount(arr[j + 1]);


                if (bit1 > bit2 || 
                   (bit1 == bit2 && arr[j] > arr[j + 1])) {

                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        return arr;
    }
}