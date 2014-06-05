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
package org.estatio.fixture.guarantee;

import org.estatio.dom.guarantee.Guarantee;
import org.estatio.dom.guarantee.GuaranteeType;
import org.estatio.dom.lease.Lease;
import org.estatio.fixture.EstatioOperationalTeardownFixture;
import org.estatio.fixture.lease.LeaseForOxfTopModel001;

import static org.estatio.integtests.VT.bd;
import static org.estatio.integtests.VT.ld;

public class GuaranteeForOxfTopModel001 extends GuaranteeAbstract {

    @Override
    protected void execute(ExecutionContext executionContext) {

        if(isExecutePrereqs()) {
            execute(new EstatioOperationalTeardownFixture(), executionContext);
            execute(new LeaseForOxfTopModel001(), executionContext);
        }

        createGuaranteeForOxfTopModel001(executionContext);
    }

    private void createGuaranteeForOxfTopModel001(ExecutionContext executionContext) {

        final Lease lease = leases.findLeaseByReference(LeaseForOxfTopModel001.LEASE_REFERENCE);
        String reference = lease.getReference() + "-D";

        final Guarantee guarantee = newGuarantee(
                lease, reference, reference, GuaranteeType.BANK_GUARANTEE,
                ld(2014, 1, 1), ld(2015, 1, 1), "Description", bd(50000),
                executionContext);
    }
}
