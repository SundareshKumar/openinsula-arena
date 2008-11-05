package org.openinsula.arena.gwt.client.ui.form.validator;


import com.google.gwt.user.client.ui.TextBoxBase;

public class RegexpFormItemValidator extends SyncFormItemValidator<TextBoxBase> {

	private String pattern;

	private String errorMessage;

	public final static String EMAIL = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*" +
			"@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";

	public final static String SOMENTE_NUMEROS = "\\d*";

	public final static String DATA = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)" +
			"(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|" +
			"[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|" +
			"1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";

	public final static String MOEDA = "^(?!\\u00a2)\\p{Sc}?(?!0,?\\d)(?:\\d{1,3}(?:([, .])\\d{3})" +
			"?(?:\\1\\d{3})*|(?:\\d+))((?!\\1)[,.]\\d{2})?$";

	public final static String DECIMAL = "^(\\d*|\\d+(\\.|\\,)\\d+)$";

	public RegexpFormItemValidator(String pattern, String errorMessage) {
		super();
		this.pattern = pattern;
		this.errorMessage = errorMessage;
	}

	public String getInvalidValueMessage() {
		return errorMessage;
	}

	protected void evaluate(TextBoxBase widget, EvaluateCallback callback) {
		String text = widget.getText();
		if (text.trim().length() == 0 || text.matches(pattern)) {
			callback.success();
		} else {
			callback.fail();
		}
	}

}
