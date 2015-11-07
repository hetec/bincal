/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hetec.bincal;

import java.util.HashMap;
import java.util.Map;
import org.hetc.binaryNumber.BinaryNumber;

/**
 *
 * @author patrick
 */
public enum BasicOperation {
    ADD("+"){
        @Override
        BinaryNumber apply(BinaryNumber bin1, BinaryNumber bin2){
            return bin1.add(bin2);
        }
    },
    SUBTRACT("-"){
        @Override
        BinaryNumber apply(BinaryNumber bin1, BinaryNumber bin2){
            return bin1.subtract(bin2);
        }
    },
    MULTIPLY("*"){
        @Override
        BinaryNumber apply(BinaryNumber bin1, BinaryNumber bin2){
            return bin1.multiply(bin2);
        }
    },
    DIVIDE("/"){
        @Override
        BinaryNumber apply(BinaryNumber bin1, BinaryNumber bin2){
            return bin1.divide(bin2);
        }
    };
    
    private final String sign;
    
    private static final Map<String, BasicOperation> signsToOps = new HashMap<>();
    
    BasicOperation(String sign){
        this.sign = sign;
    }
    
    static {
        for(BasicOperation op : BasicOperation.values()){
            signsToOps.put(op.sign, op);
        }
    }
    
    abstract BinaryNumber apply(BinaryNumber bin1, BinaryNumber bin2);
    
    static BasicOperation fromString(String sign){
        return BasicOperation.signsToOps.get(sign);
    }

    static boolean isValidOperation(String operation){
        if(BasicOperation.fromString(operation) != null){
            return true;
        }else{
            return false;
        }
    }
}
