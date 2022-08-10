package jksoft.com.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibatis.sqlmap.client.SqlMapClient;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

public class XAbstractDAO extends EgovAbstractDAO {
	protected Logger log;
    private String sqlClientBeanName;
    
    public XAbstractDAO() {
        log = LoggerFactory.getLogger(getClass());
        sqlClientBeanName = "sqlMapClient";
    }

    /*
     * DAO별 sql변경시 사용한다.
     * Ref.)
     * 		@Resource(name = "sqlMapClientName")
	  		public void setSqlClient(SqlMapClient sqlMapClient) {
	        	super.setSqlClient(sqlMapClientName, sqlMapClient);
	   		}
     */
    protected void setSqlClient (String sqlClientBeanName, SqlMapClient sqlMapClient) {
        this.sqlClientBeanName = sqlClientBeanName;
        super.setSuperSqlMapClient(sqlMapClient);
    }

    protected String getSqlClientBeanName() {
        return sqlClientBeanName;
    }
}
