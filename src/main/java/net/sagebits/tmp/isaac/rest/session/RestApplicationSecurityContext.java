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

package net.sagebits.tmp.isaac.rest.session;

import java.security.Principal;
import javax.ws.rs.core.SecurityContext;
import net.sagebits.uts.auth.rest.api1.data.RestUser;
import net.sagebits.uts.auth.rest.api1.enumerations.RestUserRoleType;

/**
 * 
 * {@link RestApplicationSecurityContext}
 *
 * @author <a href="mailto:joel.kniaz.list@gmail.com">Joel Kniaz</a>
 *
 */
public class RestApplicationSecurityContext implements SecurityContext
{
	private RestUser user;
	private String scheme;

	public RestApplicationSecurityContext(RestUser user, String scheme)
	{
		this.user = user;
		this.scheme = scheme;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.core.SecurityContext#getUserPrincipal()
	 */
	@Override
	public Principal getUserPrincipal()
	{
		return user == null ? null : new Principal()
		{
			@Override
			public String getName()
			{
				return user.userName;
			}

			@Override
			public String toString()
			{
				return getName();
			}
		};
	}

	@Override
	public boolean isUserInRole(String s)
	{
		if (user == null)
		{
			return false;
		}
		for (RestUserRoleType role : user.effectiveRoles)
		{
			if (role.friendlyName.equalsIgnoreCase(s))
			{
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isSecure()
	{
		return "https".equals(this.scheme);
	}

	@Override
	public String getAuthenticationScheme()
	{
		return "custom"; // SecurityContext.BASIC_AUTH;
	}
}
