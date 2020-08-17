class Solution {
	public int removeDuplicates(int[] nums) {
		int p = 1;
		int maxSearchP = -1;

		while (maxSearchP < nums.length && p < nums.length) {
			int preVal = nums[p - 1];
			for (maxSearchP = p; maxSearchP < nums.length; maxSearchP++) {
				if (nums[maxSearchP] > preVal) {
					if (p != maxSearchP) {
						nums[p] = nums[maxSearchP];					
					}
					p++;
					break;
				}
			}
		}

		return p;
	}

	public static void main(String[] args) {
		int[] nums = {1,2 };
		Solution s = new Solution();
		int n = s.removeDuplicates(nums);
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + ",");
		}
		System.out.println();
		System.out.println(n);
	}
}