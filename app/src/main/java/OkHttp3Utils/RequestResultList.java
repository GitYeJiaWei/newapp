package OkHttp3Utils;

import java.util.List;

/**
 * Created by kimhillzhang on 2017-12-07.
 */

public class RequestResultList<T> {
    public int ErrorCode;
    public String Message;
    public double TotalValue1;
    public double TotalValue2;
    public double TotalValue3;
    /*
    * 总页面数
    * */
    public int PageCount;
    public List<T> DataList;
}
