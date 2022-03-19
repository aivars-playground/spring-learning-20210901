package com.example.demo.integration.consumer;

import com.example.demo.model.Customer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.kafka.KafkaItemReader;
import org.springframework.batch.item.kafka.KafkaItemWriter;
import org.springframework.batch.item.kafka.builder.KafkaItemReaderBuilder;
import org.springframework.batch.item.kafka.builder.KafkaItemWriterBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@EnableBatchProcessing
@SpringBootApplication
@RequiredArgsConstructor
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    private static Logger LOGGER = LoggerFactory.getLogger(ConsumerApplication.class);

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final KafkaProperties kafkaProperties; //from autoconfiguration

    @Bean
    Job kafkaDataReaderJob() {
        return this.jobBuilderFactory
                .get("kafkaDataReaderJob")
                .start(kafkaDataReaderStep())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    Step kafkaDataReaderStep() {
        return this.stepBuilderFactory
                .get("step1")
                .<Customer, Customer>chunk(10)
                .reader(kafkaTopicReader())
                .writer(consoleWriter())
                .build();
    }

    //Reads data from kafka topic
    @Bean
    KafkaItemReader<Long, Customer> kafkaTopicReader() {
        var props = new Properties();
        props.putAll(this.kafkaProperties.buildConsumerProperties());
        return new KafkaItemReaderBuilder<Long, Customer>()
                .partitions(0)
                .consumerProperties(props)
                .name("customers-reader")
                .topic("customers")
                .build();
    }

    //Writes  data to console
    @Bean
    ItemWriter<Customer> consoleWriter() {
        return new ItemWriter<Customer>() {
            @Override
            public void write(List<? extends Customer> items) throws Exception {
                LOGGER.info("received items- count:" + items.size() + " values:"+ items.stream().map(Customer::id).map(Long::toUnsignedString).collect(Collectors.joining(".")));
            }
        };
    }
}
