import java.util.Scanner;
import java.util.*;
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n=in.nextInt();
        int m=in.nextInt();
        int[] events=new int[n];
        for(int i=0;i<n;i++){
            events[i]=Integer.valueOf(in.next().split(",")[0]);
        }
        int[] bandwitch=new int[m];
        for(int i=0;i<m;i++){
            bandwitch[i]=Integer.valueOf(in.next().split(",")[0]);
        }
        int[] latency=new int[m];
        for(int i=0;i<m;i++){
            latency[i]=Integer.valueOf(in.next().split(",")[0]);
        }
        int totalTime=minToTime(n,m,events,bandwitch,latency);
        System.out.println(totalTime);
    }
    public static int minToTime(int n,int m,int[] events,int[] bandwitch,int[] latency){
        int insertCount=0,deleteCount=0;
        for(int event:events){
            if(event==0){
                insertCount++;

            }
            else{
                deleteCount++;
            }
        }
        PriorityQueue<int[]> queue=new PriorityQueue<>((a,b)->b[1]-a[1]);
        for(int i=0;i<m;i++){
            queue.offer(new int[]{i,bandwitch[i]});
        }
        int totalTime=0;
        if(insertCount>0){
            totalTime+=Math.max(1,(insertCount-1)/m)*latency[queue.peek()[0]];
        }
        if(deleteCount>0){
            totalTime+=Math.max(1,(insertCount-1)/m)*latency[queue.poll()[0]];
        }
        return totalTime;
    }
}

