package com.rickyin.coding.笔试.美团;

import java.util.LinkedList;
import java.util.Scanner;

public class coding_Main_05_AC100 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        String s[]=new String[k+1];
        boolean de[]=new boolean[k+1];
        for(int i=1;i<=k;i++){
            s[i]=sc.next();
        }
        String str="";
        LinkedList<Integer> sum = new LinkedList<>();
        for(int kkk=0;kkk<n;kkk++){
            str=sc.next();

            if(str.charAt(0)=='-'){
                de[Integer.parseInt(str.substring(1,str.length()))]=true;
            }
            else if(str.charAt(0)=='+'){
                de[Integer.parseInt(str.substring(1,str.length()))]=false;
            }
            else{
                int ans=0;
                String mo = str.substring(1, str.length());
                for(int i=1;i<=k;i++){
                    if(de[i])continue;
                    int index=0;
                    while(true){
                        int tmp=mo.indexOf(s[i],index);
                        //System.out.println(tmp);
                        if(tmp==-1)break;
                        index=tmp+1;
                        ans++;
                    }
                }
                sum.add(ans);
            }
        }
        for (Integer integer : sum) {
            System.out.println(integer);
        }
    }
}
