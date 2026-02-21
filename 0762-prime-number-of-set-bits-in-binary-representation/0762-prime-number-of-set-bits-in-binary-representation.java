class Solution {
    public int countPrimeSetBits(int left, int right) {
        int count =0;
        
        for(int i =left;i<=right;i++){
            int x = Integer.bitCount(i);
            if(checkPrime(x)){
                count++;
            }
        }
        return count;
    }

    public static boolean checkPrime(int n){
        
        if(n<=1) return false;
        if(n==2) return true;
        if(n%2==0) return false;

        for(int i =3;i*i<=n;i++){
            if(n%i==0){
                return false;
            }
        }
        return true;
    }
}