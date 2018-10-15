package model;

public class KeyValueModel {
	private String ID;
	private String Value = "";

	public KeyValueModel() {
		this.ID = "";
		this.Value = "";
	}

	public KeyValueModel(String _ID, String _Value) {
		this.ID = _ID;
		this.Value = _Value;
	}

	@Override
	public String toString() {
		// ΪʲôҪ��дtoString()�أ���Ϊ����������ʾ���ݵ�ʱ����������������Ķ������ַ���������£�ֱ�Ӿ�ʹ�ö���.toString()
		return Value;
	}

	public String GetID() {
		return this.ID;
	}

	public String GetValue() {
		return this.Value;
	}
}
