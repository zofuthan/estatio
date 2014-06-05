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
package org.estatio.fixture.lease;

import org.estatio.dom.lease.Lease;

public class LeaseItemAndLeaseTermForTurnoverRentForOxfPoison003 extends LeaseItemAndTermsAbstract {

    @Override
    protected void execute(ExecutionContext fixtureResults) {
        createLeaseTermsForOxfPoison003(fixtureResults);
    }

    private void createLeaseTermsForOxfPoison003(ExecutionContext executionContext) {

        // prereqs
        if(isExecutePrereqs()) {
            execute(new LeaseForOxfPoison003(), executionContext);
        }

        // exec
        Lease lease = leases.findLeaseByReference(LeaseForOxfPoison003.LEASE_REFERENCE);

        createLeaseItemIfRequiredAndLeaseTermForTurnoverRent(
                lease,
                lease.getStartDate(), null,
                "7",
                executionContext);
    }

}
