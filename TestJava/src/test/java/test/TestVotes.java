package test;



import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

import querys.Votos;


public class TestVotes {
	@Test
    public void getVotes() throws ParseException, IOException, JSONException {
		List<String> votos = Votos.getVotos(8080);
		Assert.assertTrue(votos.size()>10);
		for(String voto: votos){
			Assert.assertNotNull(voto);
			Assert.assertTrue(voto!="");
		}
    }
}