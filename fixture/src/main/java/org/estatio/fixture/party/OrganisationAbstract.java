/*
 *
 *  Copyright 2012-2014 Eurocommercial Properties NV
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.estatio.fixture.party;

import javax.inject.Inject;
import org.estatio.dom.communicationchannel.CommunicationChannelContributions;
import org.estatio.dom.communicationchannel.CommunicationChannelType;
import org.estatio.dom.geography.Countries;
import org.estatio.dom.geography.Country;
import org.estatio.dom.geography.State;
import org.estatio.dom.geography.States;
import org.estatio.dom.party.Organisations;
import org.estatio.dom.party.Party;
import org.estatio.dom.party.Persons;
import org.estatio.fixture.EstatioFixtureScript;
import org.apache.isis.core.commons.ensure.Ensure;

import static org.hamcrest.CoreMatchers.*;

/**
 * Sets up the {@link org.estatio.dom.party.Organisation} and also a number of {@link org.estatio.dom.communicationchannel.CommunicationChannel}s.
 */
public abstract class OrganisationAbstract extends EstatioFixtureScript {

    @Override
    protected abstract void execute(ExecutionContext executionContext);

    protected Party createOrganisation(String input, ExecutionContext executionContext) {

        String[] values = input.split(";");
        Party party = organisations.newOrganisation(values[0], values[1]);

        Ensure.ensureThatArg(party, is(not(nullValue())), "could not find party '" + values[0] + "', '" + values[1] + "'");
        getContainer().flush();

        if(defined(values, 2)) {
            final Country country = countries.findCountry(values[7]);
            final State state = states.findState(values[6]);
            if(country != null && state != null) {
                communicationChannelContributedActions.newPostal(
                        party, 
                        CommunicationChannelType.POSTAL_ADDRESS, 
                        country, 
                        state, 
                        values[2], 
                        values[3], 
                        null, 
                        values[4], values[5]);
            }
            getContainer().flush();
        }
        if(defined(values, 8)) {
            communicationChannelContributedActions.newPhoneOrFax(
                    party, 
                    CommunicationChannelType.PHONE_NUMBER, 
                    values[8]);
            getContainer().flush();
        }
        if(defined(values, 9)) {
            communicationChannelContributedActions.newPhoneOrFax(
                    party, 
                    CommunicationChannelType.FAX_NUMBER, 
                    values[9]);
            getContainer().flush();
        }
        if(defined(values, 10)) {
            communicationChannelContributedActions.newEmail(
                    party, 
                    CommunicationChannelType.EMAIL_ADDRESS, 
                    values[10]);
            getContainer().flush();
        }

        return executionContext.add(this, party.getReference(), party);
    }

    protected boolean defined(String[] values, int i) {
        return values.length>i && !values[i].isEmpty();
    }

    // //////////////////////////////////////

    @Inject
    protected Countries countries;

    @Inject
    protected States states;

    @Inject
    protected Organisations organisations;

    @Inject
    protected Persons persons;

    @Inject
    protected CommunicationChannelContributions communicationChannelContributedActions;

}
