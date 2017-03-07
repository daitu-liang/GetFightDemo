package com.example;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by leixiaoliang on 2017/3/4.
 */
public class ParserHtml {

    public static  final String childUrl="F:\\dataLoad\\";
    public static  final String sourcrUrl="F:/azone/mark3.xml";

    public  static  void parseHtml(String htmlData){
//        if(htmlData==null||htmlData.length()==0)return;
        //创建一个DocumentBuilderFactory的对象
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //创建一个DocumentBuilder的对象
        try {
            //创建DocumentBuilder对象
            DocumentBuilder db = dbf.newDocumentBuilder();
            //通过DocumentBuilder对象的parser方法加载books.xml文件到当前项目下
            Document document = db.parse(sourcrUrl);

            //获取所有book节点的集合
            NodeList bookList = document.getElementsByTagName("Jewel");
            //通过nodelist的getLength()方法可以获取bookList的长度
            System.out.println("一共有" + bookList.getLength() + "条数据");
            //遍历每一个book节点
            for (int i = 0; i < bookList.getLength(); i++) {
                System.out.println("=================下面开始遍历第" + (i + 1) + "本书的内容=================");
                //通过 item(i)方法 获取一个book节点，nodelist的索引值从0开始
                Node book = bookList.item(i);
                //获取book节点的所有属性集合
                NamedNodeMap attrs = book.getAttributes();

                //遍历book的属性

                for (int j = 0; j < attrs.getLength(); j++) {
                    //通过item(index)方法获取book节点的某一个属性
                    Node attr = attrs.item(j);
                    if("JewelID".equals(attr.getNodeName())){
//                        dirName=attr.getNodeValue();
//                        System.out.println("JewelID=" + attr.getNodeValue());
//                        savePicUrl = "F:\\azone\\"+attr.getNodeValue();
//                        System.out.println("saveUrl-id=" +savePicUrl);
                    }
                    if("Img".equals(attr.getNodeName())){
                        String url = "https://ion.r2net.com/" + attr.getNodeValue();
                        System.out.println("url-Img=" +url);
                        String getName = attr.getNodeValue();
                        String fileImgName = getFileName(getName);
                        String[] arr = getName.split("/");
                        int size=arr.length;
                        String dir = arr[size - 2];
                        System.out.print("fileImgName=" + fileImgName);
                        String savePicUrl = childUrl+dir;
                        try{
                            Download.download(url,fileImgName,savePicUrl);
                        }catch(Exception e){
                           continue;
                        }
                    }
                    if("Set".equals(attr.getNodeName())){
                        System.out.println("Set=" + attr.getNodeValue());
                        String txtUrl = "https://ion.r2net.com/" + attr.getNodeValue();
                        String getName = attr.getNodeValue();
                        String fileSetName = getFileName(getName);
                        String[] arr = getName.split("/");
                        int size=arr.length;
                        String dir = arr[size - 2];
                        System.out.print("fileSetName=" + fileSetName);
                        String saveTxtUrl = childUrl+dir;
                        try{
                            Download.download(txtUrl,fileSetName,saveTxtUrl);
                        }catch(Exception e){
                            continue;
                        }
                    }
                    //获取属性名
                    System.out.print("获取属性值" + attr.getNodeName()+"="+attr.getNodeValue());
                    //获取属性值
                    System.out.println("--属性值" + attr.getNodeValue());
                }
                //解析book节点的子节点
                NodeList childNodes = book.getChildNodes();
                //遍历childNodes获取每个节点的节点名和节点值
                System.out.println("第" + (i+1) + "本书共有" +
                        childNodes.getLength() + "个子节点");
                for (int k = 0; k < childNodes.getLength(); k++) {
                    //区分出text类型的node以及element类型的node
                    if (childNodes.item(k).getNodeType() == Node.ELEMENT_NODE) {
                        //获取了element类型节点的节点名
                        System.out.print("第" + (k + 1) + "个节点的节点名："
                                + childNodes.item(k).getNodeName());
                        //获取了element类型节点的节点值
                        System.out.println("--节点值是：" + childNodes.item(k).getFirstChild().getNodeValue());
                        System.out.println("--节点值是：" + childNodes.item(k).getTextContent());
                    }
                }
                System.out.println("======================结束遍历第" + (i) + "次=================");
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获得图片名称
     *
     * @param filePath
     * @return
     */
    public static String getFileName(String filePath) {
        int index = filePath.lastIndexOf("/");
        return filePath.substring(index + 1);
    }
    public static void main(String[] args) throws Exception {
        ParserHtml.parseHtml(null);
    }
}
