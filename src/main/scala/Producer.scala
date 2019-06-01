import java.io.{BufferedReader, InputStreamReader}
import java.net.URL
import java.time.LocalDateTime
import java.util.Properties

import org.apache.kafka.clients.producer._

object Producer extends App {
  def ipAddress(): String = {
    val whatismyip = new URL("http://checkip.amazonaws.com")
    val in: BufferedReader = new BufferedReader(new InputStreamReader(
      whatismyip.openStream()))
    in.readLine()
  }

  def confKafka(): Properties = {

    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    props
  }

  val props = confKafka()

  val producer = new KafkaProducer[String, String](props)

  while (true) {

    val currentIP = ipAddress()
    val value = "%s->%s".format(currentIP, LocalDateTime.now)
    val record = new ProducerRecord[String, String]("test1", "key", value)

    producer.send(record)
  }

  Thread.sleep(1000)

  producer.close()
}