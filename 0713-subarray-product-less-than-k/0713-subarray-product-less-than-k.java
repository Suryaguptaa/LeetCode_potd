class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int n = nums.length;
        int right=0;
        int left =0;
        int count =0;
        int product=1;

        if(k<=1) return 0;
        for(int i =0;i<n;i++){
            product *=nums[i];

            while(product>=k && left<=i){
                product /= nums[left];
                left++;
            }
            count+=(i-left+1);
        }

     return count;
    }
}