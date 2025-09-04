class NumberOfPairs {
    public int numberOfPairs(int[][] points) {
        Arrays.sort(points, (a, b)->{
            if(a[0] == b[0]){
                return b[1] - a[1]; 
            }
            return a[0] - b[0];
        });
        int n = points.length;
        int count = 0;
        for(int i = 0; i < n; i++){
            int yA = points[i][1];
            int maxValidY = Integer.MIN_VALUE;
            for(int j = i + 1; j < n; j++){
                int yB = points[j][1];
                if(yA >= yB && yB > maxValidY){
                    count++;
                    maxValidY = yB;
                }
            }
        }
        return count;
    }
}
