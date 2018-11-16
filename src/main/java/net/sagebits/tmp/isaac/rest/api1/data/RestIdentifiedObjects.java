/*
 * Copyright 2018 VetsEZ Inc, Sagebits LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Contributions from 2015-2017 where performed either by US government
 * employees, or under US Veterans Health Administration contracts.
 *
 * US Veterans Health Administration contributions by government employees
 * are work of the U.S. Government and are not subject to copyright
 * protection in the United States. Portions contributed by government
 * employees are USGovWork (17USC §105). Not subject to copyright.
 * 
 * Contribution by contractors to the US Veterans Health Administration
 * during this period are contractually contributed under the
 * Apache License, Version 2.0.
 *
 * See: https://www.usa.gov/government-works
 */

package net.sagebits.tmp.isaac.rest.api1.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.mahout.math.Arrays;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * {@link RestIdentifiedObjects}
 * 
 * A simple wrapper class to return an array of {@link RestIdentifiedObject} for cases where you want to return an array 
 * directly as the result of a call.
 *
 * @author <a href="mailto:daniel.armbrust.list@sagebits.net">Dan Armbrust</a>
 */
@XmlRootElement
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY)
public class RestIdentifiedObjects
{
	@XmlElement
	public RestIdentifiedObject[] identifiedObjects;

	public RestIdentifiedObjects(RestIdentifiedObject[] identifiedObjects)
	{
		this.identifiedObjects = identifiedObjects;
	}

	protected RestIdentifiedObjects()
	{
		// For jaxb
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return "RestIdentifiedObjects " + Arrays.toString(identifiedObjects);
	}
}
