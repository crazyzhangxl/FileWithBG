package com.jit.bgwithasset;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import com.jit.bgwithasset.model.Person;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author crazyZhangxl on 2018/12/24.
 * Describe:
 */
public class PareDateAttributeManager {
    private String assetAstName = "person2.xml";
    private List<Person> mEntriesList = new ArrayList<>();


    private PareDateAttributeManager(){

    }

    private static  class PareDateAttributeManagerHolder{
        private static final PareDateAttributeManager MINSTANCE = new PareDateAttributeManager();
    }

    public static PareDateAttributeManager getInstance(){
        return PareDateAttributeManagerHolder.MINSTANCE;
    }

    public void init(Context context){
        mEntriesList.clear();
        new EntryHandler().load(context,assetAstName);
        for (Person person:mEntriesList){
            Log.e("解析", person.toString());
        }
    }


    private class EntryHandler extends DefaultHandler{

        void load(Context context,String assetName){
            InputStream is = null;
            try {
                is = context.getAssets().open(assetName);
                Xml.parse(is,Xml.Encoding.UTF_8,this);
            } catch (IOException e) {
                e.printStackTrace();
            }catch (SAXException e) {
                e.printStackTrace();
            }finally {
                if (is != null){
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        /**
         * 根据parson 获取属性值加入缓存中
         * @param uri
         * @param localName
         * @param qName
         * @param attributes
         * @throws SAXException
         */
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if ("person".equals(localName)){
                String name = attributes.getValue(uri, "name");
                String age = attributes.getValue(uri, "age");
                String sex = attributes.getValue(uri, "sex");
                mEntriesList.add(new Person(name,sex,Integer.parseInt(age)));
            }
        }
    }
}
