package com.example.himaGatya.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;



abstract class GetEventAPI<T> {
	Events event;


	public void ReadAPI(String request,String filename) {
		//リクエストのURLをもとにjsonデータをテキストファイルに保存

		try {
			//URL url = new URL("http://api.atnd.org/events/?event_id=97895,97897,97899&format=json");
			URL url = new URL(request);

			File destFile = new File(filename);
			FileUtils.copyURLToFile(url, destFile);
		} catch (MalformedURLException e1) {
			// TODO 自動生成された catch ブロック
			return;
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			return;
		}
		return;


	}

	public String WriteAPI(String filename) {
		//テキストファイルからjsonデータをString型に読み込む
		String result = null;
		File file = new File(filename);
		// ファイルのパスを指定する
	    try (FileReader fileReader = new FileReader(file);){
	        // ファイルが存在しない場合に例外が発生するので確認する
	        if (!file.exists()) {
	            return "ファイルが存在しません";
	        }

	        // BufferedReaderクラスのreadLineメソッドを使って1行ずつ読み込む
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        StringBuilder builder = new StringBuilder();
	        ArrayList<String> list = new ArrayList<>();
	        String data;
	        while ((data = bufferedReader.readLine()) != null) {
	            list.add(data);
	        }
	        for(String str : list) {
	            builder.append(str);
	        }
	        result = builder.substring(0, builder.length() - 1);
	        //return "size = " + list.size();

	    } catch (IOException e) {
	    	e.printStackTrace();
	        return "ファイルが作れませんでした";
	    }

		return result;
	}

	//リクエストクエリの作成
	abstract String CreateRequestURL();

	//必要なデータへの整形
	abstract String ShapingString(String data);

	//Beanに書き込んだデータのBeanへの書き込み
	abstract T[] StrageData(String str);
}
