package CityList.CityList.Model;

/**
 * Created by kimhillzhang on 2018-03-09.
 */

public class CitySortModel {
    private int id;
    private String name;//显示的数据
    private String pinyin;
    private String sortLetters;//显示数据拼音的首字母

    public int getId()
    {
        return  id;
    }
    public void setId(int id)
    {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public String getPinyin()
    {
        return  pinyin;
    }
    public  void setPinyin(String pinyin)
    {
        this.pinyin = pinyin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }
}
