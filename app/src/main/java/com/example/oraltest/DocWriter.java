package com.example.oraltest;
import android.content.Context;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.Exception;
import java.lang.String;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.LinkedHashSet;


public class DocWriter {

    public void writeFileData(String fileName, ArrayList<Integer> E, Context context){
        if(E==null){
            //System.out.println("entering null!");
            return;
        }
        FileOutputStream fileOutputStream;
        BufferedWriter bufferedWriter = null;
        String s="";
        //将队列里的所有数组转化为String
            System.out.println("long="+E.size());
            for (int i = 0; i < E.size(); i++) {
                System.out.println(E.get(i));
                if(s==null){
                    s=E.get(i)+"\n";
                }else if(s!=null){
                    s += E.get(i)+"\n";
                }

            }



        try {
            fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            bufferedWriter.write(s);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }




    public ArrayList<Integer> readFileData(String fileName,Context context){
        String result="";
        ArrayList<Integer> Y = new ArrayList<Integer>();
        try {
            FileInputStream fin = context.openFileInput(fileName);

            //获取文件长度
            int lenght = fin.available();
            System.out.println("len="+lenght);
            if(lenght==0){
                System.out.println("Len is 0！");
                return null;
            }
            byte[] buffer = new byte[lenght];
            fin.read(buffer);
            //将byte数组转换成指定格式的字符串
            result = new String(buffer);

            try (Scanner sc = new Scanner(result)) {
                while (sc.hasNextLine()) {  //按行读取字符串
                    Y.add(Integer.parseInt((String)sc.nextLine())) ;

                }

            }
        } catch (Exception e) {
            System.out.println("reading Fail!");
            e.printStackTrace();
        }
        if(Clean(Y)==null){
            System.out.println("Clean nullz!");
        }

        return Clean(Y);
    }
    //清除重复元素
    public ArrayList<Integer> Clean(ArrayList<Integer> C){

        LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(C);

        ArrayList<Integer> listWithoutDuplicates = new ArrayList<>(hashSet);

        System.out.println("read="+listWithoutDuplicates);

        return listWithoutDuplicates;
    }


}
