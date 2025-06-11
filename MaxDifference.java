class MaxDifference {
	private static final int BAD_DIFF = Integer.MAX_VALUE / 2;

	public static int maxDifference(String s, int k) {
		char[] chars = s.toCharArray();
		int n = chars.length;
		int[] mpdAB = new int[4];
		int[] mpdBA = new int[4];
		boolean[] isAbsent = new boolean[5];
		int result = Integer.MIN_VALUE;
		aCharLoop:
		for (char a = '1'; a <= '4'; a++)
			bCharLoop:
			for (char b = '0'; b < a; b++) {
				if (isAbsent[b - '0'])
					continue;
				int right = 0;
				int arf = 0;
				int brf = 0;
				
				while (right < k || arf + brf < 3 || arf == 0 || brf == 0) {
					if (right == n) {
						if (brf == 0)
							isAbsent[b - '0'] = true;
						if (arf == 0) {
							isAbsent[a - '0'] = true;
							continue aCharLoop;
						} else
							continue bCharLoop;
					}
					char c = chars[right++];
					if (c == a)
						arf++;
					else if (c == b)
						brf++;
				}
				int left = 0;
				int alf = 0;
				int blf = 0;
				Arrays.fill(mpdAB, BAD_DIFF);
				Arrays.fill(mpdBA, BAD_DIFF);
				mpdAB[0] = mpdBA[0] = 0;
				while (true) {
					int parityState, freqDiff;
					
					while (left < right - k) {
						char c = chars[left++];
						if (c == a) {
							if (arf == alf + 1) {
								left--;
								break;
							}
							alf++;
						} else if (c == b) {
							if (brf == blf + 1) {
								left--;
								break;
							}
							blf++;
						} else
							continue;
						parityState = ((alf & 1) << 1) + (blf & 1);
						freqDiff = alf - blf;
						mpdAB[parityState] = Math.min(mpdAB[parityState], freqDiff);
						mpdBA[parityState] = Math.min(mpdBA[parityState], -freqDiff);
					}
					
					parityState = ((arf & 1) << 1) + (brf & 1);
					freqDiff = arf - brf;
					result = Math.max(result, freqDiff - mpdAB[parityState ^ 2]);
					result = Math.max(result, -freqDiff - mpdBA[parityState ^ 1]);
					if (right == n)
						break;

					char c = chars[right++];
					if (c == a)
						arf++;
					else if (c == b)
						brf++;
					else
						while (right < n && (c = chars[right]) != a && c != b)
							right++;
				}
			}
		return result;
	}
}
