package com.example.xmldomparsing;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class Activity_Home extends Activity {
	TextView txt_textview1;
	ProgressDialog mProgress;
	Context context;
	NodeList nodelist;
	String url = "http://www.androidbegin.com/tutorial/XMLParseTutorial.xml";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
		new Downloadxml().execute(url);
	}

	public void init() {
		txt_textview1 = (TextView) findViewById(R.id.text);
		context = Activity_Home.this;
		mProgress = new ProgressDialog(context);
	}

	class Downloadxml extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			mProgress.setTitle("Stream Loader");
			mProgress.setMessage("Please wait, Loading XML Data...");
			mProgress.setCancelable(true);
			mProgress.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(String... URL) {
			try {
				URL url = new URL(URL[0]);
				DocumentBuilderFactory dbf = DocumentBuilderFactory
						.newInstance();

				DocumentBuilder db = dbf.newDocumentBuilder();

				org.w3c.dom.Document doc = db.parse(new InputSource(url
						.openStream()));
				doc.getDocumentElement().normalize();
				nodelist = doc.getElementsByTagName("item");
			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (mProgress.isShowing()) {
				mProgress.dismiss();
			}
			for (int temp = 0; temp < nodelist.getLength(); temp++) {
				Node nNode = nodelist.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					// Set the texts into TextViews from item nodes
					// Get the title
					txt_textview1.setText(txt_textview1.getText() + "Title : "
							+ getNode("title", eElement) + "\n" + "\n");
					// Get the description
					txt_textview1.setText(txt_textview1.getText()
							+ "Description : "
							+ getNode("description", eElement) + "\n" + "\n");
					// Get the link
					txt_textview1.setText(txt_textview1.getText() + "Link : "
							+ getNode("link", eElement) + "\n" + "\n");
					// Get the date
					txt_textview1.setText(txt_textview1.getText() + "Date : "
							+ getNode("date", eElement) + "\n" + "\n" + "\n"
							+ "\n");
				}
			}
			super.onPostExecute(result);
		}

	}

	private static String getNode(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();
		Node nValue = (Node) nlList.item(0);
		return nValue.getNodeValue();
	}

}
