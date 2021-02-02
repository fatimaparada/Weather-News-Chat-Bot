import org.jibble.pircbot.*;

public class MyBot extends PircBot {
	// weather api object
	UseAPI weatherAPI = new UseAPI();

	// news api object
	UseAPI newsAPI = new UseAPI();

	// set bot name
	public MyBot() {
		this.setName("dumbBot");
	}

	public void onMessage(String channel, String sender, String login, String hostname, String message) {

		// greet user, describe functionalities
		if (message.contains("hello") || message.contains("hey") || message.contains("hi")) {
			sendMessage(channel,
					"Hello, " + sender
							+ "! Ask me for the weather followed by the name of the city/zip code (eg. Weather Dallas)"
							+ "OR ask me for a top BBC news headline! (eg. 'give me a headline') ");
		}

		// user wants weather report
		if (message.contains("weather")) {
			int index = message.indexOf("eather ");
			String city = message.substring(index + 7);

			// get temperature
			String currtemp = weatherAPI.getWeather(city);
			double d = Double.parseDouble(currtemp);
			double temperature = (d - 273.15) * (9 / 5) + 32;

			// get max temp
			String maxTemp = weatherAPI.getMax(city);
			double d1 = Double.parseDouble(maxTemp);
			double max = (d1 - 273.15) * (9 / 5) + 32;

			// get min temp
			String minTemp = weatherAPI.getMin(city);
			double d2 = Double.parseDouble(minTemp);
			double min = (d2 - 273.15) * (9 / 5) + 32;

			// respond to user
			sendMessage(channel,
					sender + ": The current temperature in " + city + " is " + String.format("%.2f", temperature)
							+ " degrees with a high of " + String.format("%.2f", max) + "F and a low of "
							+ String.format("%.2f", min) + "F!");

		}

		// user wants headline
		if (message.contains("headline")) {
			// random number for json article array
			int random = (int) (Math.random() * 10);

			// returns news article title
			String news = newsAPI.getHeadline(random);

			// returns news article description
			String des = newsAPI.getDescription(random);

			sendMessage(channel, "Title: " + news);
			sendMessage(channel, "Description: " + des);
		}

		// if user says thank you, be polite :)
		if (message.contains("thank")) {
			sendMessage(channel, "You're welcome, " + sender + "!!!!!");
		}
	}
}