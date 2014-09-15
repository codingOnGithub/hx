package demo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

public class WordTest {

	public static void main(String[] args) {
		Map map=new HashMap();
		map.put("name", "ray");
		HWPFDocument document=replaceDoc("e:\\name.doc", map);

		 ByteArrayOutputStream ostream = new ByteArrayOutputStream();
		          try {
		              document.write(ostream);
		              //输出word文件
		              OutputStream outs=new FileOutputStream("e:\\name1.doc");
		              outs.write(ostream.toByteArray());
		              outs.close();
		          } catch (IOException e) {
		              e.printStackTrace();
		          }

	}
	
	
	 public static HWPFDocument replaceDoc(String srcPath, Map<String, String> map) {
		          try {
		              // 读取word模板
		              FileInputStream fis = new FileInputStream(new File(srcPath));
		              HWPFDocument doc = new HWPFDocument(fis);
		             // 读取word文本内容
		             Range bodyRange = doc.getRange();
		             // 替换文本内容
		             for (Map.Entry<String, String> entry : map.entrySet()) {
		                 bodyRange.replaceText("${" + entry.getKey() + "}", entry
		                         .getValue());
		             }
		             return doc;
		         } catch (Exception e) {
		             e.printStackTrace();
		             return null;
		         }
		     }

}
