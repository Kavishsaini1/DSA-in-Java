class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums){
        int i = 0;
        while(i<nums.length){
            int correct = nums[i]-1;
            if(nums[i]!=nums[correct]){
                swap(nums,i,correct);
            }else{
                i++;
            }
        }
        // find the missing numbers
        // here we use dynamic array for making a list
           List<Integer> ans = new ArrayList<>();
           for(int index = 0;index<nums.length;index++){
            if(nums[index]!=index+1){
                ans.add(index+1);
            }
           }
           return ans;
    }
    static void swap(int[]nums,int first,int second){
        int temp = nums[first];
        nums[first]=nums[second];
        nums[second]=temp;
    }
}
