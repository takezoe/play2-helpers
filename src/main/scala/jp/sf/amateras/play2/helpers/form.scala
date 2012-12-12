package jp.sf.amateras.play2.helpers

import play.api.templates.Html
import play.api.data.Field
import org.apache.commons.lang3.StringEscapeUtils

/**
 * Provides helpers which generate simple form input elements.
 */
object form {

  /**
   * Generates &lt;input type="text" ... /&gt;
   */
  def inputText(field: Field, attrs: (Symbol, Any)*): Html =
    Html("<input type=\"text\" id=\"%s\" name=\"%s\" value=\"%s\" %s/>".format(
        StringEscapeUtils.escapeHtml4(field.id),
        StringEscapeUtils.escapeHtml4(field.name),
        StringEscapeUtils.escapeHtml4(field.value.getOrElse("")), 
        makeAdditionalAttributes(attrs: _*)))
  
  /**
   * Generates &lt;input type="password" ... /&gt;
   */
  def inputPassword(field: Field, attrs: (Symbol, Any)*): Html =
    Html("<input type=\"password\" id=\"%s\" name=\"%s\" value=\"%s\" %s/>".format(
        StringEscapeUtils.escapeHtml4(field.id),
        StringEscapeUtils.escapeHtml4(field.name),
        StringEscapeUtils.escapeHtml4(field.value.getOrElse("")), 
        makeAdditionalAttributes(attrs: _*)))
  
  /**
   * Generates &lt;textarea ... &gt;
   */
  def textarea(field: Field, attrs: (Symbol, Any)*): Html =
    Html("<textarea id=\"%s\" name=\"%s\" %s>%s</textarea>".format(
        StringEscapeUtils.escapeHtml4(field.id),
        StringEscapeUtils.escapeHtml4(field.name),
        makeAdditionalAttributes(attrs: _*),
        StringEscapeUtils.escapeHtml4(field.value.getOrElse(""))))
        
  /**
   * Generates &lt;input type="hidden" ... /&gt;
   */
  def inputHidden(field: Field, attrs: (Symbol, Any)*): Html =
    Html("<input type=\"hidden\" id=\"%s\" name=\"%s\" value=\"%s\" %s/>".format(
        StringEscapeUtils.escapeHtml4(field.id), 
        StringEscapeUtils.escapeHtml4(field.name), 
        StringEscapeUtils.escapeHtml4(field.value.getOrElse("")), 
        makeAdditionalAttributes(attrs: _*)))

  /**
   * Generates &lt;input type="checkbox" ... /&gt;
   */
  def checkbox(field: Field, attrs: (Symbol, Any)*): Html = {
    val value = attrs.collectFirst {
      case ('value, value) => value.toString
    } getOrElse("true")
    
    Html("<input type=\"checkbox\" id=\"%s\" name=\"%s\" value=\"%s\" %s/>".format(
        StringEscapeUtils.escapeHtml4(field.id), 
        StringEscapeUtils.escapeHtml4(field.name), 
        StringEscapeUtils.escapeHtml4(value),
        (if(attrs.map(_._1).contains('checked) || field.value == Some(value)) "checked " else "") +
        makeAdditionalAttributes(attrs.filter(_._1 match {
          case 'value|'label|'checked => false
          case _ => true
        }): _*)) + (attrs.collectFirst {
          case ('label, value) => "<label for=\"%s\">%s</label>".format(
            StringEscapeUtils.escapeHtml4(field.id), 
            StringEscapeUtils.escapeHtml4(value.toString) 
          )
        } getOrElse("")))
  }
  
  /**
   * Generates &lt;select ...&gt;
   */
  def select(field: Field, options: Seq[(String, Any)], attrs: (Symbol, Any)*): Html = {
    Html("<select id=\"%s\" name=\"%s\" %s>".format(
        StringEscapeUtils.escapeHtml4(field.id),
        StringEscapeUtils.escapeHtml4(field.name),
        makeAdditionalAttributes(attrs: _*)) + 
        options.map { case (label, value) =>
          "<option value=\"%s\"%s>%s</option>".format(
              StringEscapeUtils.escapeHtml4(value.toString),
              (if(field.value == Some(value.toString)) " selected" else ""),
              StringEscapeUtils.escapeHtml4(label))
        }.mkString + 
        "</select>")
  }
  
  private def makeAdditionalAttributes(attrs: (Symbol, Any)*): String =
    attrs.map { case (key, value) =>
      "%s=\"%s\"".format(key.name, StringEscapeUtils.escapeHtml4(value.toString))
    }.mkString(" ")
}
