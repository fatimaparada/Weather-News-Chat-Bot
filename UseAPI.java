import java.io.*;
import java.net.*;
import com.google.gson.*;

public class UseAPI {
	public void main(String[] args) {
	}

	// method that returns the current temperature of city
	public String getWeather(String city) {
		String response;

		try {
			// where everything is appended to
			StringBuilder result = new StringBuilder();

			// create url with city
			URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city
					+ "&APPID=26aa1d90a24c98fad4beaac70ddbf274");

			// open connection to url
			URLConnection con = url.openConnection();

			// read in the information and append it all together into result
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			in.close();

			// System.out.println(result);
			JsonElement jelement = new JsonParser().parse(result.toString());

			JsonObject jobject = jelement.getAsJsonObject();
			jobject = jobject.getAsJsonObject("main");
			response = jobject.get("temp").getAsString();

		} catch (IOException e) {
			// throw exception
			response = e.getMessage();
		}

		// returns correct output
		return response;
	}

	// gets maximum temperature
	public String getMax(String city) {
		String response;
		try {
			StringBuilder result = new StringBuilder();

			//create new weather url
			URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city
					+ "&APPID=26aa1d90a24c98fad4beaac70ddbf274");
			URLConnection con = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			in.close();

			//parse json
			JsonElement jelement = new JsonParser().parse(result.toString());

			JsonObject jobject = jelement.getAsJsonObject();
			jobject = jobject.getAsJsonObject("main");
			response = jobject.get("temp_max").getAsString();

		} catch (IOException e) {
			response = e.getMessage();
		}
		return response;
	}

	// gets minimum temperature
	public String getMin(String city) {
		String response;
		try {
			StringBuilder result = new StringBuilder();

			//create url connection and read in
			URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city
					+ "&APPID=26aa1d90a24c98fad4beaac70ddbf274");
			URLConnection con = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			in.close();

			//parse json
			JsonElement jelement = new JsonParser().parse(result.toString());

			JsonObject jobject = jelement.getAsJsonObject();
			jobject = jobject.getAsJsonObject("main");

			response = jobject.get("temp_min").getAsString();

		} catch (IOException e) {
			response = e.getMessage();
		}
		return response;
	}

	/*
	 * API FOR NEWS HEADLINE!
	 */

	public String getHeadline(int rand) {
		String headline;

		try {
			// where everything is appended to
			StringBuilder result = new StringBuilder();

			// create URL using my api key :p
			URL url = new URL(
					"https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=5a2adaf65ac84ad38ce0026fddb3cb89");

			// open connection to url
			URLConnection con = url.openConnection();

			// read in the information and append it all together into result
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			in.close();

			// parse result into string
			JsonElement jelement = new JsonParser().parse(result.toString());

			JsonObject jobject = jelement.getAsJsonObject();
			jobject = jobject.getAsJsonObject();

			JsonArray jarray = jobject.getAsJsonArray("articles");
			jobject = jarray.get(rand).getAsJsonObject();
			headline = jobject.get("title").getAsString();

		} catch (IOException e) {
			// throw exception
			headline = e.getMessage();
		}

		// returns correct output
		return headline;
	}

	public String getDescription(int rand) {
		String headline;

		try {
			// where everything is appended to
			StringBuilder result = new StringBuilder();

			// create URL using my api key :p
			URL url = new URL(
					"https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=5a2adaf65ac84ad38ce0026fddb3cb89");

			// open connection to url
			URLConnection con = url.openConnection();

			// read in the information and append it all together into result
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			in.close();

			// parse result into string
			JsonElement jelement = new JsonParser().parse(result.toString());

			JsonObject jobject = jelement.getAsJsonObject();
			jobject = jobject.getAsJsonObject();

			JsonArray jarray = jobject.getAsJsonArray("articles");
			jobject = jarray.get(rand).getAsJsonObject();
			headline = jobject.get("description").getAsString();

		} catch (IOException e) {
			// throw exception
			headline = e.getMessage();
		}

		// returns correct output
		return headline;
	}
}
