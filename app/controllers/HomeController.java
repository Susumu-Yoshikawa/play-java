package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import javax.inject.Inject;
import play.data.FormFactory;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 * import views.html.index;
 */
public class HomeController extends Controller {

	//private Form<SampleForm> form;

    //Form用の内部クラス
	@Inject
	FormFactory formFactory;

	public static class SampleForm{
		public String message;
	}
	// ルートにアクセスした際のAction
	public Result index() {
		return ok(index.render("何か書いて。", formFactory.form(SampleForm.class)));
	}

	// sendにアクセスした際のAction
	public Result send() {
		Form<SampleForm> f = formFactory.form(SampleForm.class).bindFromRequest();
		if(!f.hasErrors()) {
			SampleForm data = f.get();
			String msg = "you typed : " + data.message;
			return ok(index.render(msg,f));
		} else {
			return badRequest(index.render("Error", formFactory.form(SampleForm.class)));
		}
	}

    /*public Result index() {
        return ok(index.render(
        		"これは最初の値です。",
        		"これは真ん中の引数です。",
        		"これは最後のテキストです。"
        		));
    }*/

}
