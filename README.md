play2-helpers
=============

Substitute for play2 standard helpers.

```html
@(form: Form[User])
@import jp.sf.amateras.play2.helpers.form._
@helper.form(action = routes.Application.index) {
  <dl>
    <dt>User Name</dt>
    <dd>@inputText(form, "userName")</dd>
    <dt>Password</dt>
    <dd>@inputPassword(form, "password")</dd>
  </dl>
  <input type="submit" value="Login"/>
}
```