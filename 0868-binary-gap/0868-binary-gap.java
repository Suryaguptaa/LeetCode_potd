class Solution {
    public int binaryGap(int n) {
        if(Integer.bitCount(n)==0 || Integer.bitCount(n)==1 ){
            return 0;
        }

        String bin = Integer.toBinaryString(n);
        char [] crr = bin.toCharArray();
        int l = crr.length;
        int f =0;
        
        int res =0;

        for(int i =0;i<l;i++){

            while(crr[f]!='1' && f<l){
                f++;
            }

            int x = crr[i];
            int y = crr[f];

            if((x=='1' && y=='1') && i!=f){
                res = Math.max(res,Math.abs(i-f));
                f++;
            }
        }

    return res;

    }
}