/*******************************************************************************
 * Copyright 2013, the Optique Consortium
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * This first version of the R2RML API was developed jointly at the University of Oslo, 
 * the University of Bolzano, La Sapienza University of Rome, and fluid Operations AG, 
 * as part of the Optique project, www.optique-project.eu
 ******************************************************************************/
package eu.optique.api.mapping;

/**
 * R2RML Term Map
 * 
 * @author Marius Strandhaug
 */
public interface TermMap extends ManageResource, SerializeR2RML {

	/**
	 * The term map must be set to one of these values when created.
	 */
	public enum TermMapType {
		CONSTANT_VALUED, TEMPLATE_VALUED, COLUMN_VALUED
	}

	/**
	 * Get the TermMapType of this TermMap. A TermMap's TermMapType will never
	 * change.
	 * 
	 * @return The TermMapType of this TermMap.
	 */
	public TermMapType getTermMapType();

	/**
	 * Set the template-value of this TermMap if it's a template-valued TermMap.
	 * 
	 * @param template
	 *            The template value that will be set.
	 * @throws IllegalStateException
	 *             If the TermMap is not template-valued.
	 */
	public void setTemplate(Template template);

	/**
	 * Set the constant-value of this TermMap if it's a constant-valued TermMap.
	 * 
	 * @param constVal
	 *            The constant value that will be set.
	 * @throws IllegalStateException
	 *             If the TermMap is not constant-valued.
	 */
	public void setConstant(String constVal);

	/**
	 * Set the column-value of this TermMap if it's a constant-valued TermMap.
	 * The columnName must be a column name in the TriplesMap's LogicalTable.
	 * 
	 * @param columnName
	 *            The column value that will be set.
	 * @throws IllegalStateException
	 *             If the TermMap is not column-valued.
	 */
	public void setColumn(String columnName);

	/**
	 * Sets the inverse expression of this TermMap. An inverse expression can
	 * only be set if the TermMap is either column-valued or template-valued.
	 * The inverse expression template must satisfy the conditions given at
	 * http://www.w3.org/TR/r2rml/#dfn-inverse-expression. A TermMap can have
	 * zero or one inverse expressions.
	 * 
	 * @param invExp
	 *            The inverse expression.
	 * @throws IllegalStateException
	 *             If the TermMap is not template-valued or column-valued.
	 */
	public void setInverseExpression(InverseExpression invExp);

	/**
	 * Set the term type of this TermMap to the default term type. The default
	 * term type is given at http://www.w3.org/TR/r2rml/#dfn-term-type.
	 */
	public void setDefaultTermType();

	/**
	 * Returns the term type of this TermMap. The term type can be rr:IRI,
	 * rr:BlankNode or rr:Literal.
	 * 
	 * @param resourceClass
	 *            Must be equal to (or a superclass of) the library's resource
	 *            class.
	 * @return The term type of this TermMap.
	 */
	public <R> R getTermType(Class<R> resourceClass);

	/**
	 * Get the template value of this TermMap. It will return null if this is
	 * not a template-valued TermMap.
	 * 
	 * @return The template value of this TermMap.
	 */
	public Template getTemplate();

	/**
	 * Get the template value of this TermMap as a String.
	 * 
	 * @return The string value of the template of this TermMap.
	 */
	public String getTemplateString();

	/**
	 * Get the constant value of this TermMap. It will return null if this is
	 * not a constant-valued TermMap.
	 * 
	 * @return The constant value of this TermMap.
	 */
	public String getConstant();

	/**
	 * Get the column value of this TermMap. It will return null if this is not
	 * a column-valued TermMap.
	 * 
	 * @return The column value of this TermMap.
	 */
	public String getColumn();

	/**
	 * Get the inverse expression of this TermMap if there is one.
	 * 
	 * @return The inverse expression of this TermMap.
	 */
	public InverseExpression getInverseExpression();

	/**
	 * Get the inverse expression of this TermMap as a String.
	 * 
	 * @return The inverse expression String of this TermMap.
	 */
	public String getInverseExpressionString();

	/**
	 * Remove this TermMap's inverse expression if there is one.
	 */
	public void removeInverseExpression();

}
