package OkHttp3Utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
	/**
	 * 对象转换成json字符串
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		Gson gson = new GsonBuilder()
				.setPrettyPrinting()
				.disableHtmlEscaping()
				.create();
		return gson.toJson(obj);
	}

	/**
	 * json字符串转成对象
	 * @param str
	 * @param type
	 * @return
	 */
	public static <T> T fromJson(String str, Type type) {
		Gson gson = new GsonBuilder()
				.setPrettyPrinting()
				.disableHtmlEscaping()
				.create();
		return gson.fromJson(str, type);
	}

	/**
	 * json字符串转成对象
	 * @param str
	 * @param type
	 * @return
	 */
	public static <T> T fromJson(String str, Class<T> type) {
		Gson gson = new GsonBuilder()
				.setPrettyPrinting()
				.disableHtmlEscaping()
				.create();
		return gson.fromJson(str, type);
	}
	/*
     * 使用方法
     * //json字符串转换成javabean对象
     * 列表
    * List<Person> rtn = JsonUtil.fromJson(jsonStr, new TypeToken<List<Person>>(){}.getType());
    *对象
    Person newPerson = JsonUtil.fromJson(jsonStr, Person.class);
     *
     * */
	public static <T> List<T> jsonToList(String json, Type type)
	{
		List<T> newList = new ArrayList<>();
		Gson gson = new GsonBuilder()
				.setPrettyPrinting()
				.disableHtmlEscaping()
				.create();
		List<T> list = gson.fromJson(json, type);
		for(int i = 0; i<list.size(); i++)
		{
			newList.add(list.get(i));
		}
		return newList;
	}
}
