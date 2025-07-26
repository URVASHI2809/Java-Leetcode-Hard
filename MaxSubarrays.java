class MaxSubarrays {
    private static final int MAXN = 100_000;

    private static final long[] leftconf = new long[MAXN], inc = new long[MAXN];

    private static long ins(final int v, final long l) {
        final int a = (int)(l >> 17), b = (int)l & (1 << 17) - 1,
                  in = v > b ? -1 : 0, top = v > a ? -1 : 0, mid = in & ~top;
        return ((long)(v & top) + (a & ~top) << 17) + (a & top) + (v & mid) + (b & ~in);
    }

    public static long maxSubarrays(final int n, final int[][] conflictingPairs) {
        int minr = MAXN;
        for (final var p : conflictingPairs) {
            final int p0 = p[0], p1 = p[1], j = Math.max(p0, p1) - 1;
            leftconf[j] = ins(Math.min(p0, p1), leftconf[j]);
            minr = Math.min(j + 1, minr);
        }

        long res = minr * (minr - 1l) / 2, maxi = 0, l = 0;
        for (int r = minr; r <= n; r++) {
            l = ins((int)(leftconf[r-1] >> 17), l);
            l = ins((int)leftconf[r-1] & (1 << 17) - 1, l);
            final int lf = (int)(l >> 17), ls = (int)l & (1 << 17) - 1;
            res += r - lf;
            final var i = inc[lf] += lf - ls;
            maxi = Math.max(i, maxi);
            leftconf[r-1] = 0;
        }

        Arrays.fill(inc, 0, n, 0);
        return res + maxi;
    }
}
