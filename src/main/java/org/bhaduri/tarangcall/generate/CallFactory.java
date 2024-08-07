/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.bhaduri.tarangcall.generate;

import org.bhaduri.tarangcall.TARANGPARAMS;
import org.bhaduri.tarangcall.results.Results;
import org.bhaduri.tarangdto.CallResults;
import org.bhaduri.tarangdto.CallResultsIntermediate;

/**
 *
 * @author bhaduri
 */
public class CallFactory {
    CallResultsIntermediate callResultsIntermediate;

    public CallFactory(CallResultsIntermediate callResultsIntermediate) {
        this.callResultsIntermediate = callResultsIntermediate;
    }
    
    public Results generateCall() {
        //CallResultsIntermediate callResultsIntermediate = getScripLastTransactionPriceList(scripid);
        for (int i = 1; i < TARANGPARAMS.CALL_GENERATION_LAYERS + 1; i++) {
            callResultsIntermediate = new SmoothData(callResultsIntermediate, i).removeDupsAndKeepReversals().analyseTrendLayers();
            //TarangUtils.printLTP(callResultsIntermediate.getIntermediateLTPList());
        }        
        CallResults callResults = callResultsIntermediate;
        System.out.println(callResults.getScripId()+" : "+callResults.getCallVersionOne()+" "+callResults.getCallVersionTwo()+" "+callResults.getCallGenerationTimeStamp());
        return new Results(callResults);        
    }
}
