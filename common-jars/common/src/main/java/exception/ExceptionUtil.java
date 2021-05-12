package exception;

public final class ExceptionUtil {

	public static String getErrorMsg(Throwable t) {
		StringBuilder sb = new StringBuilder();
		sb.append(t.getMessage());
		while ((t = t.getCause()) != null) {
			sb.append("\n").append(t.getMessage());

		}

		return sb.toString();
	}

}
