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
 * Describe: 解析数据的管理
 * https://www.cnblogs.com/felix-hua/archive/2012/01/10/2317404.html 解析学习
 */
public class ParseDataManager {
    private static final String TAG = "ParseDataManager";
    private String assetPath = "person.xml";
    private static final int NODE_NAME = 1;
    private static final int NODE_AGE = 2;
    private static final int NODE_SEX = 3;
    private Person mPerson;
    private  List<Person> mDefaultEntries = new ArrayList<>();
    private int mCurrentState = 0;
    private ParseDataManager(){}

    public static ParseDataManager getInstance(){
        return ParseDataManagerHolder.MINATANCE;
    }

    private static class ParseDataManagerHolder{
        private static final ParseDataManager MINATANCE = new ParseDataManager();
    }

    public  void parseData(Context context){
        new EntryLoad().load(context,assetPath);
        if (mDefaultEntries != null){
            for (Person person:mDefaultEntries){
                Log.e(TAG, person.toString());
            }
        }
    }

    // sax解析必须实现的类
    private class EntryLoad extends DefaultHandler{

        public void load(Context context,String assetPath){
            InputStream is = null;
            try {
                is = context.getAssets().open(assetPath);
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
         * xml解析开始 ------ 只会调用一次
         * @throws SAXException
         */
        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            Log.e(TAG, "startDocument:开始解析");
        }

        /**
         * xml解析结束 ------- 只会调用一次
         * @throws SAXException
         */
        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
            Log.e(TAG, "endDocument:结束解析 ");
        }

        /**
         * 检测节点开始 <....>
         */
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            Log.e(TAG, "startElement: 处理节点 = "+localName);
            if ("data".equals(localName)){
                mCurrentState = 0;
                return;
            }

            if ("person".equals(localName)){
                mPerson = new Person();
                return;
            }

            if ("name".equals(localName)){
                mCurrentState = NODE_NAME;
                return;
            }

            if ("age".equals(localName)){
                mCurrentState = NODE_AGE;
                return;
            }

            if ("sex".equals(localName)){
                mCurrentState = NODE_SEX;
                return;
            }

            mCurrentState = 0;
        }

        /**
         * 检测节点结束  </....>
         */
        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            Log.e(TAG, "endElement: ----------");
            if ("person".equals(localName)){
                mDefaultEntries.add(mPerson);
            }
        }

        /**
         * 节点中的文本信息
         */
        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            String value = new String(ch, start, length);
            Log.e(TAG, "characters 文本 = "+value);
            switch (mCurrentState){
                case NODE_NAME:
                    mPerson.setName(value);
                    break;
                case NODE_AGE:
                    mPerson.setAge(Integer.parseInt(value));
                    break;
                case NODE_SEX:
                    mPerson.setSex(value);
                    break;
                    default:
                        break;
            }
            mCurrentState = 0;
        }
    }
}
