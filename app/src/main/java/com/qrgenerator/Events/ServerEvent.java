package com.qrgenerator.Events;

import com.qrgenerator.models.AddVisitorParams;
import com.qrgenerator.models.AddVisitorResponse;
import com.qrgenerator.models.Visitor;

/**
 * Created by inmkhan021 on 7/24/2017.
 */

public class ServerEvent<T> {
    private T  typeParameterClass;

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    private Visitor visitor;
    public ServerEvent(T typeParameterClass, Visitor visitor) {
        this.typeParameterClass = typeParameterClass;
        this.visitor=visitor;
    }
    public ServerEvent(T typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    public T getServerResponse() {
        return typeParameterClass;
    }



}
