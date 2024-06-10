package br.com.microservices.orchestrated.orchestratorservice;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * enum para mapear os topicos usados de forma a facilitar a inclusão de novos tópicos e ou manutenção
 */
@Getter
@AllArgsConstructor
public enum ETopics {

    INICIA_SAGA("inicia-saga"),
    BASE_ORCHESTRATOR("orchestrator"),
    FIM_SUCESSO("fim-sucesso"),
    FIM_FALHA("fim-falha"),
    PRODUTO_VALIDACAO_SUCESSO("produto-validacao-sucesso"),
    PRODUTO_VALIDACAO_FALHA("produto-validacao-falha"),
    PAGAMENTO_SUCESSO("pagamento-sucesso"),
    PAGAMENTO_FALHA("pagamento-falha"),
    INVENTARIO_SUCESSO("inventario-sucesso"),
    INVENTARIO_FALHA("inventario-falha"),
    FINALIZA_SAGA("finaliza-saga");

    private String topic;
}
