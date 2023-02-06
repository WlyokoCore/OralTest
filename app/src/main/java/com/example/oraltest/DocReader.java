package com.example.oraltest;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

public class DocReader {


    public String[] readerXml(String FileName, String RootNodeName, String TargetNodeName,Context context
    ) {
        int count=0;
        int k= 0;

        String[] Contains=null;

        try {
            AssetManager assetManager = context.getAssets();
            //System.out.println("已创造对象");
            InputStream inputStream = null;
            try {
                inputStream = assetManager.open(FileName);
            } catch (IOException e) {
                Log.e("tag", e.getMessage());
            }
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();//实例化
            DocumentBuilder db = factory.newDocumentBuilder();//创造一个Document对象

            Document doc = db.parse(inputStream);

            NodeList bl = doc.getElementsByTagName(RootNodeName);
            //System.out.println("There are "+bl.getLength());
            //是否获得文件
            //System.out.println(doc!=null);
            Contains= new String[bl.getLength()];

            while(k==count) {
                // //母节点遍历
                if(count>=bl.getLength()) {
                    break;
                }
                //主节点解释
                org.w3c.dom.Node bk = bl.item(count);

                NodeList ChildNodes =bk.getChildNodes();
                for(int i = 0; i<ChildNodes.getLength();i++) {//子节点遍历
                    //用于去掉空格和换行符的判断
                    if (ChildNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        if(ChildNodes.item(i).getNodeName().equals(TargetNodeName)) {
                            Contains[count]=ChildNodes.item(i).getTextContent();
                            k++;//单一性校验
                            //System.out.println("已装包");
                            //System.out.println(" --- 节点值："+ChildNodes.item(i).getTextContent());
                            break;
                        }
                        // 获取element类型的节点和节点值
                        //获得节点名
                        //System.out.print("问题：" + );
                        //System.out.println(" " + ChildNodes.item(i).getFirstChild().getNodeValue());
                    }
                }
                count++;
            }
        }catch(Exception e){
            e.printStackTrace();

        }
        return Contains;
    }

}
