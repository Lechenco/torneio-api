package io.github.lechenco.tier.services.jogadores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;

@Service
public class JogadoresServiceImpl implements JogadoresService {
    private static final Logger logger = LoggerFactory.getLogger(JogadoresServiceImpl.class);

    private final DynamoDBMapper dynamoDBMapper;

    public JogadoresServiceImpl(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public Jogador getjogador(String id) {
        if (!StringUtils.hasLength(id)) {
            // TODO JogadorException
        }
        Jogador jogador = dynamoDBMapper.load(Jogador.class, id);
        return jogador;
    }

    @Override
    public Jogador save(Jogador jogador) {
        logger.info("Salvando jogador na base de dados. nome: {} id: {}", jogador.getNome(), jogador.getId());
        if (ObjectUtils.isEmpty(jogador)) {
            // TODO JogadorException
        }
        dynamoDBMapper.save(jogador);
        logger.info("Jogador salvo na base de dados. {}", jogador);

        return jogador;
    }

    @Override
    public List<Jogador> getAllJogadores() {
        List<Jogador> jogadores = dynamoDBMapper
                .scan(Jogador.class, new DynamoDBScanExpression());

        return jogadores;
    }

    @Override
    public Jogador updateJogador(Jogador jogador) {
        if (ObjectUtils.isEmpty(jogador)) {
            // TODO
        }
        dynamoDBMapper.save(jogador, buildExpression(jogador));
        return jogador;
    }

    @Override
    public void deletaJogador(String idJogador) {
        Jogador jogador = new Jogador();
        jogador.setId(idJogador);

        dynamoDBMapper.delete(jogador);
    }

    private DynamoDBSaveExpression buildExpression(Jogador jogador) {
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expectedAttributeMap = new HashMap<>();
        expectedAttributeMap.put("id",
                new ExpectedAttributeValue(
                        new AttributeValue().withS(jogador.getId())));
        saveExpression.setExpected(expectedAttributeMap);
        return saveExpression;
    }
}
