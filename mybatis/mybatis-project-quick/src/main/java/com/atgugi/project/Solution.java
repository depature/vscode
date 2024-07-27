package com.atgugi.project;

import org.junit.Test;

import java.security.CodeSigner;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int [] nums={1,3,-1,-3,5,3,6,7};
        int  target=3;
        System.out.println(maxSlidingWindow(nums, target));
    }
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int [] ans=new int[nums.length-k+1];
        for(int i=0;i<=nums.length-k;i++){
            int max=nums[i];
            for(int j=1;j<k;j++){
                max=Math.max(max, nums[i+j]);
            }
            ans[i]=max;
        }
        return ans;
    }
    public static int subarraySum(int[] nums, int k) {
        int count=0;
        for(int start=0;start<nums.length;start++){
            int sum=0;
            for(int end=start;end>=0;end--){
                sum+=nums[end];
                if(sum==k)count++;
            }
        }
        return count;
    }
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> list=new ArrayList<>();
        char[] P = p.toCharArray();
        Arrays.sort(P);
        for(int i=0;i<s.length()-p.length();i++){
            char[] S=s.substring(i, i+p.length()).toCharArray();
            Arrays.sort(S);
            if(new String(S).equals(new String(P)))
            list.add(i);
        }
        return list;
    }
    public static String getHint(String secret, String guess) {
        int Bulls=0,Cows=0;
        char[] Secret = secret.toCharArray();;
        char[] Guess = guess.toCharArray();
        Map<Character,Integer> map=new HashMap<>();
        for(int i=0;i<secret.length();i++){
            if(Secret[i]==Guess[i])Bulls++;
            if(map.containsKey(Secret[i]))
                map.replace(Secret[i],map.get(Secret[i])+1);
            else
                map.put(Secret[i], 1);
        }
        for(int i=0;i<secret.length();i++){
            if(map.containsKey(Guess[i])){
                if(map.get(Guess[i])!=0){
                map.replace(Guess[i],map.get(Guess[i])-1);
                Cows++;
                }
            }
        }
        return String.valueOf(Bulls)+"A"+String.valueOf(Cows-Bulls)+"B";

    }
    public static void moveZeroes(int[] nums) {
        int i=0,j=0,temp=0;
        while (i<nums.length&&j<nums.length){
            if(nums[j]!=0)j++;
            else if(i>=j){
                temp=nums[i];
                nums[i]=nums[j];
                nums[j]=temp;
            }
            else{
                break;
            }
            if(nums[i]==0)i++;
        }
    }
//    给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
//    请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
    public static int longestConsecutive(int[] nums) {
        Map<Integer,Integer> map=new HashMap<>();
        if(nums.length==0)return 0;
        Arrays.sort(nums);
        int [] len=new int[nums.length];
        Arrays.fill(len,1);
        len[0]=1;
        int min=1;
        for(int i=1;i<len.length;i++){
            if(nums[i]==nums[i-1]+1)
                len[i]=len[i-1]+1;
            if(nums[i]==nums[i-1])
                len[i]=len[i-1];
            if(len[i]>min)min=len[i];
        }
        return min;
    }
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map=new HashMap<>();
        for(String str:strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String key=new String(charArray);
            List<String> list=map.getOrDefault(key,new ArrayList<String>());
            list.add(str);
            map.put(key,list);
        }
        return new ArrayList<List<String>>(map.values());
    }
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map=new HashMap<>();
        for(int i=0;i<map.size();i++){
            if(map.containsKey(target-nums[i]))
                return new int[] {map.get(target-nums[i]),i};
            map.put(nums[i],i);
        }
        return new int[] {};
    }
}

