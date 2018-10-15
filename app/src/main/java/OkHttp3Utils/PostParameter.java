package OkHttp3Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kimhillzhang on 2017-12-06.
 */

public class PostParameter {
    public String Key;
    public String Value;
    public List<PostParameter> postParameters = new ArrayList<>();
    public void Add(String key, String value)
    {
        PostParameter parameter = new PostParameter();
        parameter.Key = key;
        parameter.Value = value;
        this.postParameters.add(parameter);
    }
}
