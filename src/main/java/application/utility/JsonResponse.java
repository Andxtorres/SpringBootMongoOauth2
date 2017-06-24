package application.utility;

public class JsonResponse<T> {
	private int code;
	private T response;
	public JsonResponse(int code, T response) {
		super();
		this.code = code;
		this.response = response;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public T getResponse() {
		return response;
	}
	public void setResponse(T response) {
		this.response = response;
	}
	
	
}
