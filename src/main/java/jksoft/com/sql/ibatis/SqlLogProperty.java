package jksoft.com.sql.ibatis;


public class SqlLogProperty
{
	private boolean queryId = false;
    private boolean logKey = false;
    private boolean startURI = false;
    private boolean startJsp = false;
    private boolean targetURI = false;

    public void setQueryId(boolean queryId)
    {
        this.queryId = queryId;
    }

    public void setLogKey(boolean logKey)
    {
        this.logKey = logKey;
    }

    public void setStartURI(boolean startURI)
    {
        this.startURI = startURI;
    }

    public void setStartJsp(boolean startJsp)
    {
        this.startJsp = startJsp;
    }

    public void setTargetURI(boolean targetURI)
    {
        this.targetURI = targetURI;
    }

    public boolean getQueryId()
    {
        return queryId;
    }

    public boolean getLogKey()
    {
        return logKey;
    }

    public boolean getStartURI()
    {
        return startURI;
    }

    public boolean getStartJsp()
    {
        return startJsp;
    }

    public boolean getTargetURI()
    {
        return targetURI;
    }
}
