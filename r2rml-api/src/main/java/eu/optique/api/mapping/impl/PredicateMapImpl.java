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
package eu.optique.api.mapping.impl;

import java.util.HashSet;
import java.util.Set;

import eu.optique.api.mapping.LibConfiguration;
import eu.optique.api.mapping.PredicateMap;
import eu.optique.api.mapping.Template;

/**
 * An implementation of a PredicateMap.
 * 
 * @author Marius Strandhaug
 */
public class PredicateMapImpl extends TermMapImpl implements PredicateMap {

	public PredicateMapImpl(LibConfiguration c, TermMapType termMapType,
			Template template) {
		super(c, termMapType, template);
	}

	public PredicateMapImpl(LibConfiguration c, TermMapType termMapType,
			String columnOrConst) {
		super(c, termMapType, columnOrConst);
	}

	@Override
	public <T> Set<T> serialize(Class<T> tripleClass) {
		Set<T> stmtSet = new HashSet<T>();

		stmtSet.addAll(super.serialize(tripleClass));

		stmtSet.add(tripleClass.cast(lc.createTriple(res, lc.getRDFType(),
				lc.createResource(R2RMLVocabulary.TYPE_PREDICATE_MAP))));

		return stmtSet;
	}

	@Override
	public String toString() {
		return "PredicateMapImpl [type=" + type + ", termtype=" + termtype
				+ ", template=" + template + ", constVal=" + constVal
				+ ", columnName=" + columnName + ", inverseExp=" + inverseExp
				+ ", res=" + res + "]";
	}

}
