package querys;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
 
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
 
public class Votos {
 
	public static List<String> getVotos(int votation_id) throws ParseException,IOException, JSONException {
		List<String> votos = new ArrayList<String>();
		String url = "http://php-egc.rhcloud.com/get_votes.php?votation_id=" + votation_id;
		JSONObject jsonObject = readJsonFromUrl(url);
		JSONArray votes = (JSONArray) jsonObject.get("votes");
		for (int i = 0; i < votes.size(); i++) {
			votos.add((String) votes.get(i));
		}
		return votos;
	}
 
	private static JSONObject readJsonFromUrl(String url) throws IOException,
			JSONException, ParseException {
		JSONParser jsonParser = new JSONParser();
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is,
					Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = (JSONObject) jsonParser.parse(jsonText);
			return json;
		} finally {
			is.close();
		}
	}
 
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
}