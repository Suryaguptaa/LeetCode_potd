class Solution {
    public int totalFruit(int[] fruits) {
        
        HashMap<Integer,Integer> map = new HashMap<>();
        int n = fruits.length;

        int left =0;
        int maxFruits =0;

        for(int i=0;i<n;i++){
            int x = fruits[i];
            map.put(x,map.getOrDefault(x, 0) + 1);

            while(map.size()>2){
                map.put(fruits[left],map.get(fruits[left]) - 1);
                if (map.get(fruits[left]) == 0) {
                    map.remove(fruits[left]);
                }
                left++;
            }

            maxFruits= Math.max(maxFruits,i-left+1);
        }

        return maxFruits;
    }
}