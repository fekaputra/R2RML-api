/*******************************************************************************
 * Copyright 2013, the Optique Consortium
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
package owlapiTest;

import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.junit.Assert;

import org.junit.Ignore;
import org.semanticweb.owlapi.io.RDFResource;
import org.semanticweb.owlapi.io.RDFTriple;
import org.junit.Test;
import org.xml.sax.InputSource;

import eu.optique.api.mapping.LogicalTable;
import eu.optique.api.mapping.ObjectMap;
import eu.optique.api.mapping.PredicateMap;
import eu.optique.api.mapping.PredicateObjectMap;
import eu.optique.api.mapping.R2RMLMappingManager;
import eu.optique.api.mapping.impl.owlapi.OWLAPIR2RMLMappingManagerFactory;
import eu.optique.api.mapping.SubjectMap;
import eu.optique.api.mapping.Template;
import eu.optique.api.mapping.TriplesMap;
import eu.optique.api.mapping.impl.owlapi.OWLAPIUtil;
import eu.optique.api.mapping.impl.SQLTableImpl;

/**
 * JUnit Test Cases
 * 
 * @author Riccardo Mancini
 */
public class RDFSyntax_Test
{
    @Ignore("RDF/XML is not officially supported by W3C R2RML mapping specification")
	@Test
	public void test1() throws Exception {
		InputStream fis = getClass().getResourceAsStream("../mappingFiles/test21.ttl");
		
		R2RMLMappingManager mm = new OWLAPIR2RMLMappingManagerFactory().getR2RMLMappingManager();
		
		InputSource is = new InputSource(fis);
		is.setSystemId("");
		Set<RDFTriple> triples = OWLAPIUtil.readRDFXML(is);
		Collection<TriplesMap> coll = mm.importMappings(triples);
		
		Assert.assertTrue(coll.size()==1);
		
		Iterator<TriplesMap> it=coll.iterator();
		while(it.hasNext()){
			TriplesMap current=it.next();

			SubjectMap s=current.getSubjectMap();
			Template t=s.getTemplate();
			Assert.assertTrue(t.getColumnName(0).contains("EMPNO"));
		
			int cont=0;
			Iterator<RDFResource> classesit=s.getClasses(RDFResource.class).iterator();
			while(classesit.hasNext()){
				RDFResource u= classesit.next();
				Assert.assertTrue(u.toString().contains("Employee"));
				cont++;
			}
			Assert.assertTrue(cont==1);
			
			LogicalTable table=current.getLogicalTable();
			
			SQLTableImpl ta= (SQLTableImpl) table;
			Assert.assertTrue(ta.getSQLTableName().contains("EMP"));
			
		}			
	}
	
	
	
	@Test
	public void test2() throws Exception {
		InputStream fis = getClass().getResourceAsStream("../mappingFiles/artist.ttl");
		
		R2RMLMappingManager mm = new OWLAPIR2RMLMappingManagerFactory().getR2RMLMappingManager();

		Set<RDFTriple> s = OWLAPIUtil.readTurtle(fis);
		Collection<TriplesMap> coll = mm.importMappings(s);
		
		Assert.assertTrue(coll.size()==1);
		
		Iterator<TriplesMap> it=coll.iterator();
		while(it.hasNext()){
			TriplesMap current=it.next();

			int cont=0;
			Iterator<PredicateObjectMap> pomit=current.getPredicateObjectMaps().iterator();
			while(pomit.hasNext()){
				PredicateObjectMap pom=pomit.next();
				
				Iterator<PredicateMap> pmit=pom.getPredicateMaps().iterator();
				PredicateMap p=pmit.next();
				Assert.assertTrue(p.getConstant().contains("name"));
				Assert.assertFalse(pmit.hasNext());
				
				
				Iterator<ObjectMap> omit=pom.getObjectMaps().iterator();
				ObjectMap o=omit.next();
				Assert.assertTrue(o.getColumn().contains("ENAME"));
				Assert.assertFalse(omit.hasNext());
					
				cont++;
			}
			Assert.assertTrue(cont==1);
		}
	}

	
}
