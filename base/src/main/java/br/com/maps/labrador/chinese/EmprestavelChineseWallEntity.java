package br.com.maps.labrador.chinese;

/**
 * Identifica uma entidade associada com a entidade emprestavel onde se aplica o filtro de chinese wall.
 * 
 * @author diego.ferreira
 * @created Sep 20, 2013
 */
public interface EmprestavelChineseWallEntity {

    /**
     * Nome do filtro de chinese wall de entidades relacionadas com Emprestável.
     */
    String FILTER_NAME = "emprestavelChineseWallFilter";

    /**
     * Nome das colunas no DB que identificam o ID de um usuário.
     */
    String COLUMN_NAME = "COD_USUARIO";

    /**
     * Nome do parâmetro.
     */
    String PARAM_NAME = "credentialId";

    /**
     * Condição do filtro.
     */
    String CONDITION = COLUMN_NAME + " in ( SELECT cwei.COD_ENTITY FROM $SCHEMA_NAME$.CREDENTIAL_ENTITY_ID cwei "
            + "JOIN $SCHEMA_NAME$.CREDENTIAL cred ON cwei.COD_CREDENTIAL = cred.COD_CREDENTIAL " + "where cred.TYPE_CREDENTIAL = 2 AND "
            + "cred.ENTITY_NAME = 'FUNDO' AND " + "cred.COD_CREDENTIAL IN ( :" + PARAM_NAME + "))";

}
