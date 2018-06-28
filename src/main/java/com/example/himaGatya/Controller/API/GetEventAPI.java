package com.example.himaGatya.Controller.API;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.io.FileUtils;







abstract class GetEventAPI<T> {
	Events event;


	public void ReadAPI(String request,String dirname) {
		//リクエストのURLをもとにjsonデータをテキストファイルに保存

		try {
			//URL url = new URL("http://api.atnd.org/events/?event_id=97895,97897,97899&format=json");
			URL url = new URL(request);
			Date date = new Date();
			String ymd = new SimpleDateFormat("yyyyMMdd").format(date);
			File destFile = new File(dirname + "\\" + ymd +".txt");
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
		Date date = new Date();
		String ymd = new SimpleDateFormat("yyyyMMdd").format(date);
		File file = new File(filename + "\\" + ymd + ".txt");
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

	String convertUnicode(String data) {

		//最初に出てきたUnicode文字を中心に前後の3つに分ける　例　aaaa \ubbbb ccccc
		String front,unicode,back;
		if(data==null) return data;
		if(data.indexOf("\\u") >= 0) {
			front = data.substring(0,data.indexOf("\\u"));
			unicode = data.substring(data.indexOf("\\u"),data.indexOf("\\u") + 6);
			back = data.substring(data.indexOf("\\u")+6);

			//切り出したUnicode文字を変換
			String encodedUnicode;
			encodedUnicode = convertToOriginal(unicode);

			//後ろの文字列で再帰
			String resultBack;
			resultBack = convertUnicode(back);
			//再帰の結果に前の文字列、変換した文字をつけて返す
			StringBuilder result = new StringBuilder();
			result.append(front).append(encodedUnicode).append(resultBack);
			return result.toString();
		}
		//unicode文字がなければそのまま返す
		return data;
	}

	protected static String convertToOriginal(String unicode)
	{
	    String[] codeStrs = unicode.split("\\\\u");
	    int[] codePoints = new int[codeStrs.length - 1]; // 最初が空文字なのでそれを抜かす
	    for (int i = 0; i < codePoints.length; i++) {
	        codePoints[i] = Integer.parseInt(codeStrs[i + 1], 16);
	    }
	    String encodedText = new String(codePoints, 0, codePoints.length);
	    return encodedText;
	}

	//リクエストクエリの作成
	abstract String CreateRequestURL();

	//必要なデータへの整形
	abstract String ShapingString(String data);

	//Beanに書き込んだデータのBeanへの書き込み
	abstract T[] StrageData(String str);
}
