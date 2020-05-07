package org.isda.cdm.functions.testing;

import static org.isda.cdm.functions.testing.FunctionUtils.guard;

import java.util.Collections;

import javax.inject.Inject;

import org.isda.cdm.BusinessEvent;
import org.isda.cdm.Contract;
import org.isda.cdm.functions.Create_ContractFormation;
import org.isda.cdm.functions.Create_Execution;

import com.regnosys.rosetta.common.testing.ExecutableFunction;

public class RunFormContract implements ExecutableFunction<Contract, BusinessEvent> {

    @Inject
    Create_Execution execute;

    @Inject
    Create_ContractFormation formContract;


    @Override
    public BusinessEvent execute(Contract contract) {
        BusinessEvent executeBusinessEvent = execute.evaluate(contract.getTradableProduct().getProduct(),
                guard(contract.getTradableProduct().getQuantityNotation()),
                guard(contract.getTradableProduct().getPriceNotation()),
                guard(contract.getParty()),
                guard(contract.getPartyRole()));

        return formContract.evaluate(executeBusinessEvent, null);
    }

    @Override
    public Class<Contract> getInputType() {
        return Contract.class;
    }

    @Override
    public Class<BusinessEvent> getOutputType() {
        return BusinessEvent.class;
    }
}
