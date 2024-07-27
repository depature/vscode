import java.io.IOException;
import java.lang.reflect.Array;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.*;

import javax.swing.text.html.HTMLDocument.Iterator;

public class HelloWorld {
    public static void main(String[] args) throws IOException{
//         给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。

 

// 示例 1：


// 输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
// 输出：[[1,0,1],[0,0,0],[1,0,1]]
// 示例 2：


// 输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
// 输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
        int [][] matrix=new int[][]{{1,0},{0,1}};
        System.out.println(maximumOddBinaryNumber("000110"));
        }
        public static String maximumBinaryString(String binary) {
            int n = binary.length();
            char[] s = binary.toCharArray();
            int j = 0;
            for (int i = 0; i < n; i++) {
                if (s[i] == '0') {
                    while (j <= i || (j < n && s[j] == '1')) {
                        j++;
                    }
                    if (j < n) {
                        s[j] = '1';
                        s[i] = '1';
                        s[i + 1] = '0';
                    }
                }
            }
            return new String(s);
        }
        public static boolean canFinish(int numCourses, int[][] prerequisites) {
            Queue<Integer> que=new LinkedList<>();
            int[] in =new int[numCourses];
            int[] out=new int[numCourses];
            for(int[] edge:prerequisites){
                in[edge[1]]++;
                out[edge[0]]++;
            }
            for(int i=0;i<numCourses;i++){
                if(in[i]==0)que.offer(i);
            }
            while(!que.isEmpty()){
                int cur=que.poll();
                for(int[] edge:prerequisites){
                    if(edge[0]==cur){
                        in[edge[1]]--;
                        out[edge[0]]--;
                        if(out[edge[0]]==0)
                            que.offer(edge[0]);
                    }
                }
            }
            for(int i=0;i<numCourses;i++){
                if(in[i]>0)return false;
            }
            return true;
        }
        public static int minOperations(int[] nums) {
            int n=nums.length;
            Set<Integer> set=new HashSet<Integer>();
            for(int num:nums){
                set.add(num);
            }
            List<Integer> sortedUniqueNums=new ArrayList<Integer>(set);
            Collections.sort(sortedUniqueNums);
            int res =n;
            int j=0;
            for(int i=0;i<sortedUniqueNums.size();i++){
                int left=sortedUniqueNums.get(i);
                int right=left+n-1;
                while(j<sortedUniqueNums.size()&&sortedUniqueNums.get(j)<=right){
                    res=Math.min(res,n-(j-i+1));
                    j++;
                }
            }
            return res;
        }
        public int orangesRotting(int[][] grid) {
            for(int i=0; i<grid.length; i++){
                for(int j=0;j<grid[0].length;j++){
                    if(grid[i][j]==2){
                        orangeRotting(grid,i,j);
                    }
                }
            }
        }
        public static orangeRotting(int[][] grid,int i,int j){
            if(i<0||i>=grid.length||j<0||j>=grid[0].length||grid[i][j]!=1)
                return 0;
            if(grid[i][j]==1)return orangeRotting(grid,i-1,j)+orangeRotting(grid,i,j-1)+orangeRotting(grid,i+1,j)+orangeRotting(grid,i,j+1);
        }
        public boolean searchMatrix(int[][] matrix, int target) {
            for(int i=0; i<matrix.length; i++) {
                for(int j=0;j<matrix[0].length; j++) {
                    if(matrix[i][j]==target)
                    return true;
                }
            }
            return false;
        }
        public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int m=nums1.length,n=nums2.length;
            if(m>n)return findMedianSortedArrays(nums2, nums1);
            int iMin=0,iMax=m-1;
            while (iMin<=iMax) {
                int i=(iMin+iMax)/2;
                int j=(iMin+iMax)/2;
                if(j!=0&&i!=m&&nums2[j-1]>nums1[i]){
                    iMin=i+1;
                }
                else if(i!=0&&j!=n&&nums2[j]<nums1[i-1]){
                    iMax=i-1;
                }
                else{
                    int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; } // 奇数的话不需要考虑右半部分

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0; //如果是偶数的话返回结果
                }
                return 0;
            }
        }
        public static int minimumSum(int[] nums) {
            int left=0,right=nums.length-1,mid=1;
            int min=Integer.MAX_VALUE;
            for(int i=0;i<nums.length-2;i++){
                for(int j=i+1;j<nums.length-1;j++){
                    for(int k=j+1;k<nums.length;k++){
                        if(nums[i]<nums[j]&&nums[k]<nums[j]){
                            if(nums[i]+nums[j]+nums[k]<min){
                                min=nums[i]+nums[j]+nums[k];
                                left=i;mid=j;right=k;
                            }
                        }
                        }
                    }
            }
            if(min==Integer.MAX_VALUE)return -1;
            return min;
        }
        public static int firstDayBeenInAllRooms(int[] nextVisit) {
            int visit=0,count=0,room=1;
            int [] Visited=new int[nextVisit.length];
            Visited[0]++;
            while (room<Visited.length) {   
                if(Visited[visit]%2==0){
                    visit=(visit+1)%nextVisit.length;
                    if(Visited[visit]==0)room++;   
                    Visited[visit]++;
                }
                else{
                    visit=nextVisit[visit];
                    Visited[visit]++;
                }
                count=(count+1)%1000000007;
            }
            return count;
        }
        public static boolean allVisted(int[] nextVisit){
            for(int i=0;i<nextVisit.length;i++){
                if(nextVisit[i]==0)return false;
            }
            return true;
        }
        public static void rotate(int[][] matrix) {
            int n=matrix.length,temp=0;
            for(int i=1;i<n;i++){
                for(int j=i;j<n;j++){
                    temp=matrix[i][j];
                    matrix[i][j]=matrix[j][i];
                    matrix[j][i]=temp;
                }
            }
            for(int i = 0; i < n; i++){
                for(int j=0; j < n/2; j++){
                    temp = matrix[i][j];
                    matrix[i][j] = matrix[i][n-j-1];
                    matrix[i][n-j-1] = temp;
                }
            }
        }
        public List<Integer> spiralOrder(int[][] matrix) {
            int rows=matrix.length,cols=matrix[0].length;
            List<Integer> result = new ArrayList<Integer>();
            boolean [][] matrixFlag = new boolean[rows][cols];
            Arrays.fill(matrixFlag,false);
            int toal=rows*cols;
            int row=0,col=0;
            int [][] direction = {{0,1},{1,0},{0,-1},{-1,0}};
            int index=0;
            for(int i=0;i<toal;i++){
                result.add(matrix[row],matrix[col]);
                matrixFlag[row][col]=0;
                int nextRow=row+direction[index][0],nextCol=col+direction[index][1];
                if(nextRow<0||nextRow>rows||nextCol<0||nextRow>cols||matrixFlag[nextRow][nextCol]){
                    index=(index+1)%4;
                }
                row=row+direction[index][0];
                col=col+direction[index][1];
            }
            return result;
        }
        public static void setZeroes(int[][] matrix) {
            for(int i=0;i<matrix.length;i++){
                for(int j=0;j<matrix[0].length;j++){
                    if(matrix[i][j]==0){
                        matrix[i][j] = Integer.MAX_VALUE;
                    }
                }
            }
            for(int i=0;i<matrix.length;i++){
                for(int j=0;j<matrix[0].length;j++){
                    if(matrix[i][j]==Integer.MAX_VALUE){
                        for(int i1=0;i1<matrix.length;i1++){
                            matrix[i][i1]=0;
                        }
                        for(int i2=0;i2<matrix.length;i2++){
                            matrix[i2][j]=0;
                        }
                    }
                }
            }
        }
        public static int countWays(int[][] ranges) {
            if(ranges.length==1)return 2;
            Arrays.sort(ranges,Comparator.comparingInt(arr->arr[0]));
            List<int[]> merged=new ArrayList<>();
            for(int [] interval: ranges){
                int L=interval[0],R=interval[1];
                if(merged.size()==0||merged.get(merged.size()-1)[1]<L){
                    merged.add(new int[]{L,R});
                    
                }
                else merged.get(merged.size()-1)[1]=Math.max(merged.get(merged.size()-1)[1], interval[1]);
            }
            int count=merged.size();
            int ans=1;
            for(int i=0;i<count;i++){
                ans*=2%1000000007;
            }
            //返回2的count次方
            return ans;
        }
        public static int[] productExceptSelf(int[] nums) {
            int [] ans=new int[nums.length];
            ans[0]=1;
            for(int i=1;i<nums.length;i++){
                ans[i]=ans[i-1]*nums[i-1];
            }
            int temp=1;
            for(int i=nums.length-2;i>=0;i--){
                temp*=nums[i+1];
                ans[i]*=temp;
            }
            return ans;
            
        }
        public static void rotate(int[] nums, int k) {
            Queue<Integer> q=new LinkedList<>();
            for(int i=nums.length-1; i>=0;i--)
                q.offer(nums[i]);
            for(int i=0; i<k; i++){
                int temp=q.poll();
                q.offer(temp);
            }
            for(int i=nums.length-1;!q.isEmpty();--i){
                nums[i]=q.poll();
            }

        }
        public static int[][] merge(int[][] intervals) {
            Arrays.sort(intervals,Comparator.comparingInt(arr->arr[0]));
            List<int[]> merged=new ArrayList<>();
            for(int [] interval: intervals){
                int L=interval[0],R=interval[1];
                if(merged.size()==0||merged.get(merged.size()-1)[1]<L){
                    merged.add(new int[]{L,R});
                    
                }
                else merged.get(merged.size()-1)[1]=Math.max(merged.get(merged.size()-1)[1], interval[1]);
            }
            return merged.toArray(new int[merged.size()][]);
        }

        public static int coinChange1(int[] coins, int amount) {
            int [] dp=new int[amount+1];
            Arrays.fill(dp,Integer.MAX_VALUE);
            dp[0] = 0;
            for(int i=1; i<amount+1; i++){
                for(int coin:coins){
                    if(i>=coin){
                        dp[i]=Math.min(dp[i], dp[i-coin]+1);
                    }           
                }
            }
            if(dp[amount]==Integer.MAX_VALUE)return -1;
            return dp[amount];
        }
        public static int coinChange(int[] coins, int amount) {
            if(amount < 1)return -1;
            return coinChange(coins, amount, new int [amount]);
        }
        private static int coinChange(int[] coins, int amount,int []count) {
            if(amount < 0)return -1;
            if(amount==0)return 0;
            if(count[amount-1]!=0)return count[amount-1];
            int min=Integer.MAX_VALUE;
            for(int coin:coins) {
            int res=coinChange(coins, amount-coin,count);
            if(res>0&&res<min)min=res+1;
            }
            count[amount-1]=min==Integer.MAX_VALUE?0:min;
            return count[amount-1];
        }
        
        public static int change(int amount, int[] coins) {
            int dp[]=new int[amount+1];
            dp[0]=1;
            for(int coin:coins){
                for(int i=coin;i<=amount;i++){
                    dp[i]+=dp[i-coin];
                }
            }
            return dp[amount];
        }
        public static void runable(){
            System.out.println("123");
        }
        public static int minimumVisitedCells(int[][] grid) {
            int col=grid.length,row=grid[0].length;
            int [][] dp=new int[col][row];
            for(int i=0;i<col;i++){
                Arrays.fill(dp[i], 100);
            }
            dp[0][0]=0;
            for(int i=0;i<col;i++){
                for(int j=0;j<row;j++){
                    for(int k=i+1;k-i<=grid[i][j]&&k<col;k++){
                        dp[k][j]=Math.min(dp[i][j]+1, dp[k][j]);
                    }
                    for(int k=j+1;k-j<=grid[i][j]&&k<row;k++){
                        dp[i][k]=Math.min(dp[i][j]+1, dp[i][k]);
                    }
                }
            }
            if(dp[col-1][row-1]!=100)
                return dp[col-1][row-1];
            else
                return -1;
        }
        public static int maximumScore(int[] nums, int k) {
            int left=k,right=k,ans=nums[k],min=nums[k];
            while (left>=0&&right<nums.length) {
                if(left>0){
                    if(nums[left-1]>=min){
                        left--;
                        ans=Math.max(ans, min*(right-left+1));
                    }
                }
                if(right<nums.length-1){
                    if(nums[right+1]>=min){
                        right++;
                        ans=Math.max(ans, min*(right-left+1));
                    }
                }
                if(left>0&&right<nums.length-1){
                    if(nums[right+1]>nums[left-1]){
                        right++;
                        min=Math.min(min, nums[right]);
                        ans=Math.max(ans, min*(right-left+1));
                    }
                    else {
                        left--;
                        min=Math.min(min, nums[left]);
                        ans=Math.max(ans, min*(right-left+1));
                    }
                }
                if(left==0&&right<nums.length-1){
                        right++;
                        min=Math.min(min, nums[right]);
                        ans=Math.max(ans, min*(right-left+1));
                }
                if(right==nums.length-1&&left>0){
                    left--;
                    min=Math.min(min, nums[left]);
                    ans=Math.max(ans, min*(right-left+1));
                }
                if(left==0&&right==nums.length-1)
                break;
            }
            return ans;
        }
        public static int maxMoves(int[][] grid) {
            int row=grid.length,col=grid[0].length;
            int [][] dp= new int[row][col];
            int max=1;
            for(int i=0;i<row;i++){
                dp[i][0]=1;
            }
            for(int j=1;j<col;++j){
                for(int i=0;i<row;i++){
                    if(i-1>=0&&grid[i][j]>grid[i-1][j-1]&&dp[i-1][j-1]==1){dp[i][j]=1;max=j;}
                    if(grid[i][j]>grid[i][j-1]&&dp[i][j-1]==1){dp[i][j]=1;max=j;}
                    if(i+1<row&&grid[i][j]>grid[i+1][j-1]&&dp[i+1][j-1]==1){dp[i][j]=1;max=j;}
                }
            }
            return max;
        }
        public static long sellingWood(int m, int n, int[][] prices) {
            long[][] dp=new long[m+1][n+1];
            for(int[] p:prices){
                dp[p[0]][p[1]]=p[2];
            }
            for(int i=1;i<=m;++i){
                for(int j=1;j<=n;j++){
                    for(int k=1;k<j;k++)dp[i][j]=Math.max(dp[i][j], dp[i][k]+dp[i][j-k]);
                    for(int k=1;k<i;k++)dp[i][j]=Math.max(dp[i][j], dp[k][j]+dp[i-k][j]);               
                }
            }
            return dp[m][n];
        }
        public static long maxArrayValue(int[] nums) {
            int left=nums.length-2;
            long max=nums[nums.length-1];
            while (left>=0) {
                if(max>nums[left]){
                    max+=nums[left];
                }
                else if(max<nums[left]){
                    max=nums[left];
                }
                left--;
            }
            return max;

        }
        public static String minWindow(String s, String t) {
            int left=0,right=-1,ansL=-1,ansR=-1,ansLen=Integer.MAX_VALUE;
            int tLen=t.length(),sLen=s.length();
            boolean flag=false;
            HashMap<Character,Integer> map = new HashMap<>();
            HashMap<Character,Integer> mapS= new HashMap<>();         
            for(Character ch:t.toCharArray()){
                if(map.containsKey(ch))map.compute(ch, (k, v) -> (v == null) ? 1 : v + 1);
                else map.put(ch, 1); 
            }
            while(right<sLen){
                right++;
                if(map.containsKey(s.charAt(right))&&right<sLen){
                    mapS.put(s.charAt(r), mapS.getOrDefault(s.charAt(right), 0)+1);
                }
                while (flag&&left<=right) {
                    if(ansR-ansL+1>ansLen){
                        ansLen=ansR-ansL+1;
                        ansL=left;
                        ansR=right;
                    }
                    if(map.containsKey(s.charAt(right))&&right<sLen){
                        mapS.put(s.charAt(r), mapS.getOrDefault(s.charAt(right), 0)-1);
                    }
                    for(Map.Entry<Character,Integer> entry:map.entrySet()){
                        Character key=entry.getKey();
                        Integer value=entry.getValue();
                        if(map2.getOrDefault(key, 0)<value){
                            flag=false;
                            break;
                        }
                        flag=true;
                    }

                }                           

            }
            if(ansR<0)return "";
            return s.substring(ansL,ansR);
        }
        public static String maximumOddBinaryNumber(String s) {
            String one="",zero="";
            for(int i=0;i<s.length();i++){
                if(s.charAt(i)=='0')zero+="0";
                else{
                    one+="1";
                }
            }
            return one.substring(0,one.length()-1)+zero+"1";
        }
        public static int lengthOfLongestSubstring(String s) {
            return 0;
        }
        public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] pair1, int[] pair2) {
                return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
            }
        });
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{nums[i], i});
        }
        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < n; ++i) {
            pq.offer(new int[]{nums[i], i});
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }
        public static String capitalizeTitle(String title) {
            String[] split = title.split(" ");
            String ans="";
            for(int i=0;i<split.length;i++){
                split[i]=split[i].toLowerCase();
                if(split[i].length()>=3){    
                    String a=split[i].substring(0,1);//取首部
                    split[i]=a.toUpperCase()+split[i].substring(1,split[i].length());
                    ans+=split[i]+" ";
                }                 
                else{
                    ans+=split[i]+" ";
                }
            }
            ans=ans.substring(0,ans.length()-1);
            return ans;
        }
        public static int minimumPossibleSum(int n, int target) {
            int m=target/2;
            
            return n>=m?n*(n+1)/2:(m*(m+1)/2+(target + target + (n - m) - 1) * (n - m) / 2);
        }
        public static int trap(int[] height) {
            int ans=0;
            int left=0;
            int[] max_right=new int[height.length];
            for(int i=height.length-2;i>=0;i--)
                max_right[i]=Math.max(max_right[i+1],height[i+1]);
            for(int i=1;i<height.length-1;i++){
                left=Math.max(left,height[i-1]);
                int min=Math.min(left,max_right[i]);
                if(height[i]<min)
                    ans+=min-height[i];
            }
            return ans;
        }
    }