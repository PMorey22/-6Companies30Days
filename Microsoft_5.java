/*Given a set of distinct positive integers nums, return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:

answer[i] % answer[j] == 0, or
answer[j] % answer[i] == 0
If there are multiple solutions, return any of them.*/


class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] count = new int[n], pre = new int[n];
        int max = 0, index = -1;
        for (int i = 0; i < n; i++) {
            count[i] = 1;
            pre[i] = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0 && 1 + count[j] > count[i]) {
                    count[i] = count[j] + 1;
                    pre[i] = j;
                }
            }
            if (count[i] > max) {
                max = count[i];
                index = i;
            }
        }
        LinkedList<Integer> list = new LinkedList<>();
        while (index != -1) {
            list.addFirst(nums[index]);
            index = pre[index];
        }
        return list;
    }
}
