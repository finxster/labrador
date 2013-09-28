package br.com.maps.labrador.service.alterar;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import jmine.tec.di.annotation.Injected;
import jmine.tec.services.api.ActionsEnum;
import jmine.tec.services.api.ServiceExecutionException;
import jmine.tec.services.api.annotations.Execution;
import jmine.tec.services.api.annotations.Input;
import jmine.tec.services.api.annotations.ServiceImplementor;
import jmine.tec.utils.date.StoppedClock;
import jmine.tec.utils.date.Timestamp;

/**
 * Serviço para alteração do relógio parado no Spring.
 *
 * @author joao.enomoto
 */
@ServiceImplementor(action = ActionsEnum.ALTERAR)
public class RelogioService {

    public static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public final static String TEMPO = "Tempo";

    private StoppedClock clock;

    private String timestamp;

    /**
     * Executa o serviço
     */
    @Execution
    public void execute() throws ServiceExecutionException {
        Timestamp timestamp = this.convertedTimestamp();
        this.clock.adjustClockTo(timestamp);
    }

    /**
     * @return
     */
    private Timestamp convertedTimestamp() throws ServiceExecutionException {
        try {
            return new Timestamp(FORMATTER.parse(this.timestamp).getTime());
        } catch (ParseException e) {
            throw new ServiceExecutionException(e);
        }
    }

    /**
     * @param clock the clock to set
     */
    @Injected
    public void setClock(StoppedClock clock) {
        this.clock = clock;
    }

    /**
     * @param timestamp the timestamp to set
     */
    @Input(fieldName = TEMPO)
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
