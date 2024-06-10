package br.com.microservices.orchestrated.orchestratorservice.config.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

import static br.com.microservices.orchestrated.orchestratorservice.core.enums.ETopics.*;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.ETopics.BASE_ORCHESTRATOR;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.ETopics.INICIA_SAGA;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    private static final Integer PARTITION_COUNT = 1;
    private static final Integer REPLICA_COUNT = 1;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;

    /**
     * Configura o Consumer do Kafka
     */
    @Bean
    public ConsumerFactory<String, String> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerProps());
    }

    /**
     * Propriedades do consumidor
     */
    private Map<String, Object> consumerProps(){
        var props = new HashMap<String, Object>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        //Para consumir vamos desserializar os dados da chave
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        //Para consumir o valor vamos desserializar os dados do payload
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        return props;
    }
    
    @Bean
    public ProducerFactory<String, String> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerProps());
    }

    private Map<String, Object> producerProps() {
        var props = new HashMap<String, Object>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        //Para publicar o evento vamos serializar os dados da chave
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        //Para publicar o evento vamos serializar os dados do payload
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    /**
     * Configura o KafkaTemplate com base nas configurações do producer
     * @param producerFactory já é injetado quando usamos o @Bean então é válido
     * @return
     */
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    private NewTopic buildTopic(String topicName) {
        return TopicBuilder
                .name(topicName)
                .partitions(PARTITION_COUNT)
                .replicas(REPLICA_COUNT)
                .build();
    }

    @Bean
    public NewTopic iniciaSagaTopic(){
        return buildTopic(INICIA_SAGA.getTopic());
    }

    @Bean
    public NewTopic orchestratorTopic(){
        return buildTopic(BASE_ORCHESTRATOR.getTopic());
    }

    @Bean
    public NewTopic fimSucessoTopic(){
        return buildTopic(FIM_SUCESSO.getTopic());
    }

    @Bean
    public NewTopic fimFalhaTopic(){
        return buildTopic(FIM_FALHA.getTopic());
    }

    @Bean
    public NewTopic produtoValidacaoSucessoTopic(){
        return buildTopic(PRODUTO_VALIDACAO_SUCESSO.getTopic());
    }

    @Bean
    public NewTopic produtoValidacaoFalhaTopic(){
        return buildTopic(PRODUTO_VALIDACAO_FALHA.getTopic());
    }

    @Bean
    public NewTopic pagamentoSucessoTopic(){
        return buildTopic(PAGAMENTO_SUCESSO.getTopic());
    }

    @Bean
    public NewTopic pagamentoFalhaTopic(){
        return buildTopic(PAGAMENTO_FALHA.getTopic());
    }

    @Bean
    public NewTopic inventarioSucessoTopic(){
        return buildTopic(INVENTARIO_SUCESSO.getTopic());
    }

    @Bean
    public NewTopic inventarioFalhaTopic(){
        return buildTopic(INVENTARIO_FALHA.getTopic());
    }

    @Bean
    public NewTopic finalizaSagaTopic(){
        return buildTopic(FINALIZA_SAGA.getTopic());
    }
}
