import com.google.common.base.Throwables;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

import java.util.Map;

/**
 * Created by matt on 3/13/16.
 */
public class StreamingWordCount {
    public static void main(String[] args) {
        try {
            SparkConf conf = new SparkConf();
            conf.setAppName("StreamingWordCount");
            conf.setMaster("spark://localhost:7077");
            JavaStreamingContext sc = new JavaStreamingContext(conf, Durations.seconds(10));
            JavaReceiverInputDStream<String> words = sc.socketTextStream("localhost", 9999);
            JavaPairDStream<String, Long> counts = words.countByValue();
            counts.print();
            sc.start();
            sc.awaitTermination();
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }
}
