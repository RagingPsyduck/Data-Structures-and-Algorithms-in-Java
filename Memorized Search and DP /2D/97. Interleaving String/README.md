## DP
1. Last step
	* The last character is either from A[m-1] or B[n-1]
	* If it's from A[m-1], then X[0,m+n-2] is built by
		A[0,m-2] and B[0,n-1]
	* If it' sfrom B[n-1], then X[0,m+n-2] is built by
		A[0,m-1] and B[0,n-2]

2. Transfer function
	* State : dp[s][i][j]. The first s characters can be built by A[0,i-1] and B[0,j-1], s = i + j
	* dp[i][j] = (dp[i-1][j] && X[i+j-1] == A[i-1]) || (dp[i][j-1] && d[i+j-1] )

3. Initial and boundary conditions
	* dp[0][0] = true
	* if i = 0, we only consider B
	* if j = 0, we only consider A

4. Calculation order
	* dp[0][0].....dp[0][n]
	* dp[1][0].....dp[1][n]
	* dp[m][0].....dp[m][n]
	* return dp[m][n]

	
```java
class Solution {
    public boolean isInterleave(String a, String b, String x) {
        int m = a.length(), n = b.length();
        if (m + n != x.length()) return false;
        
        boolean[][] dp = new boolean[m+1][n+1];
        
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <=n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                    continue;
                }
                if (i > 0 && a.charAt(i-1) == x.charAt(i+j-1)) {
                    dp[i][j] |= dp[i-1][j];
                }
                if (j > 0 && b.charAt(j-1) == x.charAt(i+j-1)) {
                    dp[i][j] |= dp[i][j-1];
                }
            }
        }
        return dp[m][n];   
    }
}
```