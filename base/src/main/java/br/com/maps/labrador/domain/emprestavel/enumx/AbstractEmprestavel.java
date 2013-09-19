package br.com.maps.labrador.domain.emprestavel.enumx;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import jmine.tec.component.Documentation;
import jmine.tec.persist.impl.annotation.Alias;
import jmine.tec.persist.impl.annotation.Comment;
import jmine.tec.persist.impl.bussobj.PersistableBusinessObject;
import br.com.maps.labrador.domain.emprestavel.Emprestavel;

@Alias("EMPRES")
@Table(name = "EMPRESTAVEL")
@Documentation("TABELA QUE CONTEM AS TAREFAS AGENDADAS")
@SequenceGenerator(name = "SEQ_EMPRES", sequenceName = "SEQ_EMPRES")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TP", discriminatorType = DiscriminatorType.INTEGER)
@Comment(table = "EMPRESTAVEL", column = "TP", value = "INDICA O TIPO DE TAREFA AGENDADA.")
public abstract class AbstractEmprestavel extends PersistableBusinessObject implements Emprestavel {

}
