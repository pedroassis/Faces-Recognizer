/*
 * Todos os direitos reservados ao autor pedro_ha@yahoo.com.br
 * Este programa deve ser distribuido junto à sua licença
 * 
 * Aviso: 
 * Este programa de computador é protegido por leis de direitos autorais.
 * Qualquer reprodução, distribuição ou modificação não autorizada deste
 * programa de computador, em sua totalidade ou parcial, resultará em
 * severas punições civis e criminais, e os infratores serão punidos sob a
 * máxima extensão possível dentro da lei. 
 * Este programa de computador é distribuído sem nenhuma garantia
 * implícita ou explicita, em nenhum caso o licenciante será responsável
 * por quaisquer danos diretos, indiretos, incidental, incluindo, mas não 
 * limitado, a perda de uso, dados, lucros ou interrupções de negócios.
 */

package test;

import entity.Foto;
import entity.Funcionario;
import entity.Registro;
import java.util.Date;
import java.util.List;
import model.Banco;
import model.EntityManager;

/**
 * @since 02/06/2013, 21:30:21
 * @author pedro_ha@yahoo.com.br
 */
public class TesteMain {

    public static void main(String[] args) {
        Banco b = new Banco();
        
        b.executeStatement(
                "insert into registro values(default, ?, ?, ?)"
                , 1, new Date(), 1);
        
    }

}