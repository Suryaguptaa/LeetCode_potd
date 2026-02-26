class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;

        int left =0;
        int res =0;
        int zeroCounter=0;

        for(int i=0;i<n;i++){

            int x = nums[i];
            // int l = nums[left];

            if(x==0){
                zeroCounter++;
            }

            while(zeroCounter>1){
                if(nums[left]==0){
                    zeroCounter--;
                }
                left++;
            }

            int curr = (i-left);

            res = Math.max(res,curr);
        }

        return res;
    }
}