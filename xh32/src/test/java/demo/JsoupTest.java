package demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hotent.core.util.FileUtil;


public class JsoupTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String html=FileUtil.readFile("e:\\subtable.html");
		Document doc= Jsoup.parseBodyFragment(html);
		Elements els= doc.select("[tablename=detail]");
		if(els.size()>0){
			Element el=els.first();
			el.append("<input type='hidden' value='a' />");
//			Element addEl=doc.createElement("input").attr("type", "hidden").attr("name", "name");
//			el.appendChild(addEl);
//			Element addTxt=doc.createElement("textarea").attr("name", "fname");
			//el.appendChild(addEl);
//			el.appendChild(addTxt);
		}
		//doc.children().last().append("<input");
		System.out.println(doc.body().html());

	}

}
