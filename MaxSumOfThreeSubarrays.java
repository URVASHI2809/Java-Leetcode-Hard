class MaxSumOfThreeSubarrays {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] windowSum = new int[n - k + 1];
        int[] leftMaxIndex = new int[n - k + 1];
        int[] rightMaxIndex = new int[n - k + 1];

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (i >= k) {
                sum -= nums[i - k];
            }
            if (i >= k - 1) {
                windowSum[i - k + 1] = sum;
            }
        }

        int maxIndex = 0;
        for (int i = 0; i < windowSum.length; i++) {
            if (windowSum[i] > windowSum[maxIndex]) {
                maxIndex = i;
            }
            leftMaxIndex[i] = maxIndex;
        }

        maxIndex = windowSum.length - 1;
        for (int i = windowSum.length - 1; i >= 0; i--) {
            if (windowSum[i] >= windowSum[maxIndex]) {
                maxIndex = i;
            }
            rightMaxIndex[i] = maxIndex;
        }

        int[] result = new int[3];
        int maxSum = 0;

        for (int mid = k; mid < windowSum.length - k; mid++) {
            int left = leftMaxIndex[mid - k];
            int right = rightMaxIndex[mid + k];
            int totalSum = windowSum[left] + windowSum[mid] + windowSum[right];

            if (totalSum > maxSum) {
                maxSum = totalSum;
                result[0] = left;
                result[1] = mid;
                result[2] = right;
            }
        }
        return result;
    }
}

 
