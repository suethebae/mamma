package shop.mammastore.common;

public class Parser {
	public String changeHtmlToText(String data) {
		data = data.replaceAll("&", "&amp;");
		data = data.replaceAll("<", "&lt;");
		data = data.replaceAll(">", "&gt;");
		data = data.replaceAll("\"", "&quot;");
		return data;
	}

	public String changeTextToHtml(String data) {
		data = data.replaceAll("&amp;", "&");
		data = data.replaceAll("&lt;", "<");
		data = data.replaceAll("&gt;", ">");
		data = data.replaceAll("&quot;", "\"");
		return data;
	}
}
