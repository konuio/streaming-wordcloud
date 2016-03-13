import twitter4j.*;

import java.util.ArrayList;
import java.util.List;

public class SearchTweets {

    public List<String> search(String searchParams) {
        Twitter twitter = new TwitterFactory().getInstance();
        List<String> ret = new ArrayList<String>();
        try {
            Query query = new Query(searchParams);
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();

                for (Status tweet : tweets) {
                    String stringified = "@" + tweet.getUser().getScreenName() + " - " + tweet.getText();
                    System.out.println(stringified);
                    ret.add(stringified);
                }

            } while ((query = result.nextQuery()) != null);

        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
        return ret;
    }

    public static void main(String[] args) {
        SearchTweets s = new SearchTweets();
        s.search("Trump");
    }
}
