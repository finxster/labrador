package br.com.maps.labrador.services;

import jmine.tec.persist.impl.persister.listener.PersisterListener;
import jmine.tec.security.impl.domain.User;
import jmine.tec.services.api.annotations.Execution;
import jmine.tec.services.api.annotations.Output;
import jmine.tec.services.api.annotations.Parameter;
import jmine.tec.services.api.annotations.ServiceImplementor;
import jmine.tec.services.api.ActionsEnum;
import br.com.maps.labrador.services.model.Corrida;

/**
 * 
 * Este é um serviço exemplo. Ele é utilizado na planilha de testes exemplo, encontrada em src/test/resources/cenarios/corrida-test.xls
 * Analisar a planilha para entender todo o contexto.
 * 
 * @author renan.oliveira
 */
@ServiceImplementor(serviceName="Corre", action=ActionsEnum.PROCESSAR)
public class CorreService {

	/**
     * Este é o método de execução do serviço, retornando TEMPO_KM para a planilha
     */
    @Execution @Output(propertyName="TEMPO_KM")
    public Corrida executeService(@Parameter("Percurso") String percurso, @Parameter("Distancia") Double distancia, @Parameter("Tempo") Double tempo){
    	System.out.println("Correndo em " + percurso + ", percurso de " + distancia + "km em " + tempo + " minutos...");
    	return new Corrida(percurso,distancia,tempo);
    }
	
}
