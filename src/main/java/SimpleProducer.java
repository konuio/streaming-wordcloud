import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import twitter4j.Status;

import java.util.List;
import java.util.Properties;


public class SimpleProducer {

    public static final String TOPIC = "test";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer(props);
        SearchTweets searchTweets = new SearchTweets();
        List<String> results = searchTweets.search("Trump");
        for (String result: results) {
            producer.send(new ProducerRecord<String, String>(TOPIC, null, result));
        }
        producer.close();
    }

}
