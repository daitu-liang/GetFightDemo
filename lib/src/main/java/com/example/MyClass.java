package com.example;

public class MyClass {


    public static void main(String[] args) throws Exception {
        String dirName="apple";
        String saveUrl = "F:\\azone\\dirName";
        String fileName="Gal_304X240.jpg";
        String url="https://ion.r2net.com/Sets/SegJewel/41746/Img.Gal_304X240.jpg";

        int totals=5;
        for(int i=1;i<totals;i++){
            String resourceUrl = "https://www.jamesallen.com/JSite/Core/jx.ashx?" +
                    "PageUrl=wedding-rings/mens-alternative-metals/?PageNumber="+i+"%.IgnorePagePosition=1&CashedTemplates=,&Container=%23" +
                    "TempGalleryResults&Ondemand=True&RequestNum=0&_=1488593427213";
            String htmlString = Html.getHtml(resourceUrl);
            ParserHtml.parseHtml(htmlString);
        }

        Download.download(url,fileName,saveUrl);
    }
}
