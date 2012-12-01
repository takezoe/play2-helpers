package jp.sf.amateras.play2.helpers

import play.api.templates.Html
import play.api.data.Form
import org.apache.commons.lang3.StringEscapeUtils

/**
 * Provides helpers which generate simple form input elements.
 */
object form {

  /**
   * Generates &lt;input type="text" ... /&gt;
   */
  def inputText(form: Form[_], name: String, attrs: (Symbol, String)*): Html =
    Html("<input type=\"text\" name=\"%s\" value=\"%s\" %s/>".format(
        StringEscapeUtils.escapeHtml4(name), 
        StringEscapeUtils.escapeHtml4(form(name).value.getOrElse("")), 
        makeAdditionalAttributes(attrs: _*)))
  
  /**
   * Generates &lt;input type="password" ... /&gt;
   */
  def inputPassword(form: Form[_], name: String, attrs: (Symbol, String)*): Html =
    Html("<input type=\"password\" name=\"%s\" value=\"%s\" %s/>".format(
        StringEscapeUtils.escapeHtml4(name), 
        StringEscapeUtils.escapeHtml4(form(name).value.getOrElse("")), 
        makeAdditionalAttributes(attrs: _*)))
  
  /**
   * Generates &lt;textarea ... &gt;
   */
  def textarea(form: Form[_], name: String, attrs: (Symbol, String)*): Html =
    Html("<textarea name=\"%s\" %s>%s</textarea>".format(
        StringEscapeUtils.escapeHtml4(name),
        makeAdditionalAttributes(attrs: _*),
        StringEscapeUtils.escapeHtml4(form(name).value.getOrElse(""))))
        
  /**
   * Generates &lt;input type="hidden" ... /&gt;
   */
  def inputHidden(form: Form[_], name: String, attrs: (Symbol, String)*): Html =
    Html("<input type=\"hidden\" name=\"%s\" value=\"%s\" %s/>".format(
        StringEscapeUtils.escapeHtml4(name), 
        StringEscapeUtils.escapeHtml4(form(name).value.getOrElse("")), 
        makeAdditionalAttributes(attrs: _*)))

  private def makeAdditionalAttributes(attrs: (Symbol, String)*): String =
    attrs.map { case (key, value) =>
      "%s=\"%s\"".format(key.name, StringEscapeUtils.escapeHtml4(value))
    }.mkString(" ")
}
